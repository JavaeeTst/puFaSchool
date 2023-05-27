package com.pufaschool.server.logmanager;

import com.pufaschool.server.service.PuFaLogService;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * 普法活动日志
 */
@Aspect
@Configuration
public class ActiveLogManager {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PuFaUserService userService;

    @Autowired
    private PuFaRoleService roleService;

    @Autowired
    private PuFaLogService logService;

    /**
     * 普法活动控制层
     */
    @Pointcut("execution(* com.pufaschool.server.controller.ActivityController..*(..))")
    public void activity(){

    }


}
