package com.pufaschool.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pufaschool.conn.domain.LoginUser;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.exception.NonAdminException;
import com.pufaschool.conn.exception.UsernameFreezeException;
import com.pufaschool.conn.exception.UsernameNotFoundExceptions;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetail")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PuFaUserService puFaUserService;

    @Autowired
    private PuFaRoleService puFaRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //mybatis-plus wrapper条件查询
        LambdaQueryWrapper<PuFaUser> wrapper = new LambdaQueryWrapper<>();
        //按用户名称查询
        wrapper.eq(PuFaUser::getUsername, username);

        PuFaUser puFaUser = puFaUserService.getOne(wrapper);
        //没有查询到，用户名代表不存在
        if (puFaUser == null) {
            throw new UsernameNotFoundExceptions("用户名不存在");
        }
        //查到了,用户的状态为1代表用户名已经被冻结
        if (puFaUser.getStatus() == 1) {

            throw new UsernameFreezeException("用户已被冻结");
        }
        //查询到所有的角色
        List<PuFaRole> byUsername = puFaRoleService.getRoleByUsernameOrUserId(username, null);


        System.out.println(byUsername);

        //在定义一个存储角色的容器
        List<GrantedAuthority> authorities = new ArrayList<>();

        //如果不是管理员拦截
        if (byUsername.size() == 0) {

            throw new NonAdminException("抱歉,您还不是管理员");
        }

        for (PuFaRole puFaRole : byUsername) {


            //每次遍历一个角色的new一个对象
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + puFaRole.getRoleCode());

            //把对象放入到容器里面
            authorities.add(authority);
        }

//        if( !authorities.contains("ADMIN")&& !authorities.contains("SUPER_ADMIN")){
//
//            throw new NonAdminException("抱歉,您不是管理员");
//        }


        //程序走到这里,代表没有问题，直接返回loginUser对象
        return new LoginUser(puFaUser, authorities);
    }
}
