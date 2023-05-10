package com.pufaschool.server.logmanager;

import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.conn.utils.LogUtil;
import com.pufaschool.server.service.PuFaLogService;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录日志
 */
@Aspect
@Configuration
public class LoginLogManager {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PuFaUserService userService;

    @Autowired
    private PuFaRoleService roleService;

    @Autowired
    private PuFaLogService logService;

    /**
     * 登录控制层切入点
     */
    @Pointcut("execution(* com.pufaschool.server.controller.IndexController..*(..))")
    public void login(){}

    /**
     * 登录日志管理
     * @param point
     */
    @After("login()")
    public void loginLogManager(JoinPoint point){

        //截取方法名
        String methodName = point.getSignature().getName();

        //获取当前时间
        String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

        //获取传过来的user对象
        PuFaUser puFaUser = (PuFaUser) point.getArgs()[0];

        puFaUser=userService.getUserByUsername(puFaUser.getUsername());

        PuFaLog log=null;

        switch (methodName){


            //如果是管理员登录
            case "loginServer":



                methodName="管理员"+puFaUser.getUsername()+currentDate+"登录普法后台系统";

                //实例化日志对象
               log= new PuFaLog(puFaUser.getId(),methodName, LogUtil.LOGIN_LOG);

                //存入数据库
                logService.addAdminLog(log);

                break;
            case "loginClient":

                methodName="管理员"+puFaUser.getUsername()+currentDate+"登录普法后台系统";

                //实例化日志对象
                log = new PuFaLog(puFaUser.getId(),methodName, LogUtil.LOGIN_LOG);

                //存入数据库
                logService.addUserLog(log);

                break;

        }




    }
}
