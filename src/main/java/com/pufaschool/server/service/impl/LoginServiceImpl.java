package com.pufaschool.server.service.impl;

import com.pufaschool.conn.domain.LoginUser;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.exception.UserErrorException;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.LoginService;
import com.pufaschool.conn.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    //用户登录接口
    public Result login(PuFaUser puFaUser) {

        //验证用户名密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(puFaUser.getUsername(), puFaUser.getPassword());

        System.out.println("测试" + authenticationManager);

        //authenicationToken对象不为null
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        System.out.println("测试" + authenticate);

        if (Objects.isNull(authenticate)) {
            throw new UserErrorException("登录失败用户名不存在");
        }

        LoginUser userId = (LoginUser) authenticate.getPrincipal();

        String token = JWTUtils.generatorToken(userId.getPuFaUser());

        Map<String, Object> map = new HashMap<>();

        map.put("token", token);


        return Result.success(map);

    }
}
