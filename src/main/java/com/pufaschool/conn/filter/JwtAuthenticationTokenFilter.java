package com.pufaschool.conn.filter;

import com.pufaschool.conn.domain.LoginUser;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import com.pufaschool.conn.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
        Integer id = (Integer) claims.get("userId");

        //取出username
        String username = (String) claims.get("username");

        //查询用户
        PuFaUser puFaUser = puFaUserService.getById(id);

        //如果用户为null，代表数据库没有该用户
        if (puFaUser == null) {
            throw new UsernameNotFoundException("没有该用户");
        }
        //查询所以的角色
        List<PuFaRole> byUsername = puFaRoleService.getRoleByUsernameOrUserId(username, null);

        //定义一个装角色的容器
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (PuFaRole puFaRole : byUsername) {
            //每次查询一个角色就new一个对象
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + puFaRole.getRoleCode());

            //把角色放入容器里面
            authorities.add(authority);

        }

        LoginUser user = new LoginUser(puFaUser, authorities);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}

