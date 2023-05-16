package com.pufaschool.conn.filter;

import com.pufaschool.conn.domain.LoginUser;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.exception.UserErrorException;
import com.pufaschool.conn.exception.UsernameNotFoundExceptions;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import com.pufaschool.conn.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户每个请求都会先进入这个过滤器
 */
@Configuration
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private PuFaUserService puFaUserService;

    @Autowired
    private PuFaRoleService puFaRoleService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("token");


        //判断请求头是否携带token
        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }

//        if("/system/puFaSchool/server/login".equals(request.getRequestURI())){
//
//            filterChain.doFilter(request,response);
//
//            return;
//        }


        //解析token
        Claims claims = JWTUtils.checkToken(header);

        //如果token为null,代表没有登录
        if (claims == null) {
            throw new JwtException("请先登录");
        }

        //取出userId
        Long id = Long.valueOf((Long) claims.get("userId"));

        //取出username
        String username = (String) claims.get("username");

        //查询用户
        PuFaUser puFaUser = puFaUserService.getById(id);

        //如果用户为null，代表数据库没有该用户
        if (puFaUser == null) {
            throw new UsernameNotFoundExceptions("没有该用户");
        }

        //取出缓存的角色集合
        List<PuFaRole> byUsername=(List<PuFaRole>) redisTemplate.opsForValue().get("roleCode");


        //如果缓存没有
        if(byUsername==null){

            //查询所以的角色
             byUsername = puFaRoleService.getRoleByUsernameOrUserId(username, null);

            //把角色放入缓存
            redisTemplate.opsForValue().set("roleCode",byUsername);
        }



        //从缓存李取出一个装角色的容器
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) redisTemplate.opsForValue().get("authority");

        //如果容器为null
        if(authorities==null){

            //定义一个存放角色的容器
            authorities=new ArrayList<>();

            for (PuFaRole puFaRole : byUsername) {
                //每次查询一个角色就new一个对象
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + puFaRole.getRoleCode());

                //把角色放入容器里面
                authorities.add(authority);

            }
        }

        LoginUser user = new LoginUser(puFaUser, authorities);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}

