package com.pufaschool.server.logmanager;

import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.conn.utils.LogUtil;
import com.pufaschool.server.service.PuFaLogService;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户日志管理
 */
@Configuration
@Aspect
public class UserLogManager {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PuFaUserService userService;

    @Autowired
    private PuFaRoleService roleService;

    @Autowired
    private PuFaLogService logService;

    /**
     * 用户日志切入点(用户日志记录)
     */
    @Pointcut("execution(* com.pufaschool.client.controller.ClientUserController..*(..))")
    public void userController() {

    }

    /**
     * 用户日志管理
     */
    @After("userController()")
    public void userLogManager(JoinPoint point) {

        System.out.println("用户日志");

        //获取token
        String token = request.getHeader("token");

        //如果token为null 代表没有
        if (token == null) {

            return;
        } else {

            //解析token
            Claims claims = JWTUtils.checkToken(token);

            //获取token里面的userId
            Long userId = Long.valueOf((Long) claims.get("userId"));

            //在按id查询用户
            PuFaUser userById = userService.getUserById(Long.valueOf(userId));

            //获取时间戳
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            //截取方法名
            String methodName = point.getSignature().getName();

            //定义日志类型(默认查询)
            Integer logType = LogUtil.QUERY_LOG;

            //定义日志
            PuFaLog log=null;

            //如果用户注册,则token会是null,则不需要看角色信息
            if (token == null) {


                //如果是注册的话
                if ("addUser".equals(methodName)) {

                    //获取用户注册的用户信息
                    PuFaUser puFaUser = (PuFaUser) point.getArgs()[0];

                    //在按邮箱查询用户(主要因为mybatis-plus的bug,获取不到生成的主键值,被迫查询)
                    PuFaUser userByEmail = (PuFaUser) userService.getUserByEmail(puFaUser.getEmail());

                    System.out.println(userByEmail);

                    //截取当前注册时间
                    String dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                    //拼接日志信息
                    methodName = dateFormat + "用户注册用户名" + puFaUser.getUsername();


                    logType = LogUtil.ADD_LOG;

                    //生成日志对象
                     log = new PuFaLog(userByEmail.getId(), methodName, logType);

                    //存入数据库
                    logService.addUserLog(log);
                }
            } else {


                switch (methodName) {

                    //用户修改信息日志记录
                    case "updateById":
                        methodName = "修改了自己的个人信息";

                        logType = LogUtil.UPDATE_TYPE;

                        //日志信息存入对象
                         log = new PuFaLog(Long.valueOf(userId), userById.getUsername() + "用户" + dateTime + methodName, logType);

                        //最后存入数据库
                        logService.addUserLog(log);

                        break;

                    //用户修改了自己的密码
                    case "updatePassword":

                        methodName = "修改了密码";

                        logType = LogUtil.UPDATE_TYPE;

                        //日志信息存入对象
                        log = new PuFaLog(Long.valueOf(userId), userById.getUsername() + "用户" + dateTime + methodName, logType);

                        //最后存入数据库
                        logService.addUserLog(log);
                        break;

                    //用户进行邮箱验证
                    case "sendEmail":

                        methodName = "进行邮箱验证";

                        logType = LogUtil.QUERY_LOG;

                        //日志信息存入对象
                        log = new PuFaLog(Long.valueOf(userId), userById.getUsername() + "用户" + dateTime + methodName, logType);

                        //最后存入数据库
                        logService.addUserLog(log);
                        break;

                    //用户找回密码
                    case "passwordRetrieval":

                        methodName = "执行找回密码";

                        logType = LogUtil.UPDATE_TYPE;

                        //日志信息存入对象
                        log = new PuFaLog(Long.valueOf(userId), userById.getUsername() + "用户" + dateTime + methodName, logType);

                        //最后存入数据库
                        logService.addUserLog(log);

                        break;



                }


            }
        }
    }
}
