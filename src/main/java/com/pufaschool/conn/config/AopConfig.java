package com.pufaschool.conn.config;

import com.pufaschool.conn.domain.*;
import com.pufaschool.conn.utils.LogUtil;
import com.pufaschool.server.controller.CarouselController;
import com.pufaschool.server.service.PuFaLogService;
import com.pufaschool.server.service.PuFaRoleService;
import com.pufaschool.server.service.PuFaUserService;
import com.pufaschool.conn.utils.JWTUtils;
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
 * 日志类aop
 */
@Configuration
@Aspect
public class AopConfig {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PuFaRoleService roleService;

    @Autowired
    private PuFaLogService logService;

    @Autowired
    private PuFaUserService userService;

    /**
     * (管理员)登录日志切入点
     */
    @Pointcut("execution(* com.pufaschool.server.controller.IndexController..*(..))")
    public void indexController() {

    }

    /**
     * 用户控制层日志切入点(管理员)
     */
    @Pointcut("execution(* com.pufaschool.server.controller.UserController..*(..))")
    public void AdminUserController() {

    }

    /**
     * 用户日志记录
     */
    @Pointcut("execution(* com.pufaschool.client.controller.ClientUserController..*(..))")
    public void userController() {

    }

    /**
     * 角色控制层日志切入点
     */
    @Pointcut("execution(* com.pufaschool.server.controller.RoleController..*(..))")
    public void roleController() {

    }

    /**
     * 轮播图控制层日志切入点
     */
    @Pointcut("execution(* com.pufaschool.server.controller.CarouselController..*(..))")
    public void carouselController() {

    }

    /**
     * 视频控制层日志切入点
     */
    @Pointcut("execution(* com.pufaschool.server.controller.VideoController..*(..))")
    public void videoController() {

    }

    /**
     * 管理员登录日志
     */
    @After("indexController()")
    public void userAndAdminLoginLog(JoinPoint point) {


    }

    /**
     * 视频记录
     */
    @After("videoController()")
    public void videoControllerLog(JoinPoint point) {

        //先获取请求头的token
        String token = request.getHeader("token");

        if (token == null) {

            return;
        } else {

            //解析token，获取管理员id
            Integer userId = (Integer) JWTUtils.checkToken(token).get("userId");

            //取出管理员信息
            PuFaUser userById = userService.getUserById(Long.valueOf(userId));

            //取出角色
            List<PuFaRole> roleByUsernameOrUserId = roleService.getRoleByUsernameOrUserId(null, Long.valueOf(userId));

            //截取方法
            String methodName = point.getSignature().getName();

            //日志类型(默认为查询)
            Integer logType = LogUtil.QUERY_LOG;

            //截取当前日期
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            //遍历角色
            for (PuFaRole puFaRole : roleByUsernameOrUserId) {

                //如果是管理员
                if ("ADMIN".equals(puFaRole.getRoleCode())) {

                    switch (methodName) {

                        case "getPageList":
                            methodName = "分页查询所有视频";
                            break;

                        case "addPuFaVideo":

                            PuFaVideo arg = (PuFaVideo) point.getArgs()[0];

                            methodName = "上传了名为" + arg.getVideoName() + "的视频";

                            break;
                        case "updateVideoById":

                            PuFaVideo pointArg = (PuFaVideo) point.getArgs()[0];

                            methodName = "修改了id为" + pointArg.getId() + ",名字为" + pointArg.getVideoName() + "的视频";

                            break;
                        case "deleteVideoById":

                            Long id = (Long) point.getArgs()[0];

                            methodName = "删除了id为" + "的视频";

                            break;

                        case "getVideoById":

                            Long aLong = (Long) point.getArgs()[0];


                            methodName = "查询了id为" + aLong + "的视频信息";

                            break;
                    }

                    //生成日志对象
                    PuFaLog log = new PuFaLog(Long.valueOf(userId), userById.getUsername() + "管理员" + dateTime + methodName, logType);

                    //存入数据库
                    logService.addAdminLog(log);

                }


            }

        }
    }

    /**
     * 轮播图记录(管理员)
     *
     * @param point
     */
    @After("carouselController()")
    public void carouselControllerLog(JoinPoint point) {

        //先获取管理员的token
        String token = request.getHeader("token");

        if (token == null) {

            return;
        } else {

            //解析token
            Claims claims = JWTUtils.checkToken(token);

            //去除token里面的id
            Integer userId = (Integer) claims.get("userId");

            //取出管理员的信息
            PuFaUser userById = userService.getUserById(Long.valueOf(userId));

            //取出角色信息
            List<PuFaRole> roleByUsernameOrUserId = roleService.getRoleByUsernameOrUserId(null, Long.valueOf(userId));

            //获取当前时间
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            //定义日志类型(默认查询)
            Integer logType = LogUtil.QUERY_LOG;

            //获取方法名
            String methodName = point.getSignature().getName();

            //遍历角色信息
            for (PuFaRole puFaRole : roleByUsernameOrUserId) {

                //如果是管理员记录日志
                if ("ADMIN".equals(puFaRole.getRoleCode())) {

                    switch (methodName) {

                        //管理员获查看所有轮播图信息
                        case "getPageList":

                            methodName = "查看所有轮播图信息";

                            break;
                        //上传轮播图
                        case "addCarousel":
                            PuFaCarousel arg = (PuFaCarousel) point.getArgs()[0];

                            methodName = "上传了轮播图,轮播图id为" + arg.getId();

                            logType = LogUtil.ADD_LOG;
                            break;

                        //删除轮播图
                        case "deleteById":

                            Long pointArg = (Long) point.getArgs()[0];

                            methodName = "删除了id为" + pointArg + "的轮播图";

                            logType = LogUtil.DELETE_LOG;

                            break;

                        //修改轮播图信息

                        case "updateCarouselById":

                            PuFaCarousel puFaCarousel = (PuFaCarousel) point.getArgs()[0];

                            methodName = "修改了id为" + puFaCarousel.getId() + "的轮播图信息";

                            logType = LogUtil.UPDATE_TYPE;

                            break;
                    }
                    //生成日志对象
                    PuFaLog log = new PuFaLog(Long.valueOf(userId), userById.getUsername() + "管理员" + dateTime + methodName, logType);


                    //最终把日志存入数据库里面
                    logService.addAdminLog(log);

                }

            }

        }
    }

    /**
     * 用户日志记录
     */

    @After("userController()")
    public void userLog(JoinPoint point) {

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
            Integer userId = (Integer) claims.get("userId");

            //在按id查询用户
            PuFaUser userById = userService.getUserById(Long.valueOf(userId));

            //获取时间戳
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            //截取方法名
            String methodName = point.getSignature().getName();

            //定义日志类型(默认查询)
            Integer logType = LogUtil.QUERY_LOG;

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
                    PuFaLog log = new PuFaLog(userByEmail.getId(), methodName, logType);

                    //存入数据库
                    logService.addUserLog(log);
                }
            } else {


                switch (methodName) {

                    //用户修改信息日志记录
                    case "updateById":
                        methodName = "修改了自己的个人信息";

                        logType = LogUtil.UPDATE_TYPE;

                        break;

                    //用户修改了自己的密码
                    case "updatePassword":

                        methodName = "修改了密码";

                        logType = LogUtil.UPDATE_TYPE;
                        break;

                    //用户进行邮箱验证
                    case "sendEmail":

                        methodName = "进行邮箱验证";

                        logType = LogUtil.QUERY_LOG;
                        break;

                    //用户找回密码
                    case "passwordRetrieval":

                        methodName = "执行找回密码";

                        logType = LogUtil.UPDATE_TYPE;
                        break;

                    case "login":

                        methodName = "登录";

                        logType = LogUtil.QUERY_LOG;

                }

                //日志信息存入对象
                PuFaLog log = new PuFaLog(Long.valueOf(userId), userById.getUsername() + "用户" + dateTime + methodName, logType);

                //最后存入数据库
                logService.addUserLog(log);
            }
        }
    }

    /**
     * 用户控制层日志记录(管理员)
     */
    @After("AdminUserController()")
    public void userAndAdminLog(JoinPoint point) {
        //先获取token
        String token = request.getHeader("token");

        if(token==null){

            return;
        }else {

            //解析token
            Claims claims = JWTUtils.checkToken(token);

            //获取token里面的userId
            Integer userId = (Integer) claims.get("userId");

            //再次查询用户的角色
            List<PuFaRole> roleByUsernameOrUserId = roleService.getRoleByUsernameOrUserId(null, Long.valueOf(userId));

            //在按id查询用户
            PuFaUser userById = userService.getUserById(Long.valueOf(userId));

            //截取时间戳
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            //定义日志类型(默认查询)
            Integer logType = LogUtil.QUERY_LOG;

            //遍历角色
            for (PuFaRole puFaRole : roleByUsernameOrUserId) {

                //如果是管理员的话日志记录在管理员表里面
                if ("ADMIN".equals(puFaRole.getRoleCode())) {

                    //截取管理员请求的方法名
                    String methodName = point.getSignature().getName();

                    switch (methodName) {

                        //分页查询用户记录日志
                        case "getPageList":

                            methodName = "分页查询所有用户";


                            break;

                        //删除用户记录日志
                        case "deleteById":

                            //先获取参数
                            Object[] args = point.getArgs();

                            //在按id查询用户(主要用户日志里面看看删除哪个用户)
                            PuFaUser userById1 = userService.getUserById((Long) args[0]);

                            //最终赋值
                            methodName = "删除用户" + userById1.getUsername();

                            logType = LogUtil.DELETE_LOG;
                            break;
                        //按状态查询用户
                        case "getUserByStatus":
                            methodName = "按状态查询用户";
                            break;

                        //按用户名查询用户
                        case "getUserByUsername":
                            methodName = "按用户名查询";
                            break;

                        //模糊查询用户
                        case "getByUserFiled":
                            methodName = "模糊查询";
                            break;

                        //按用户注册时间查询用户
                        case "getUserByCreateTime":
                            methodName = "按注册时间查询用户";
                            break;

                        case "updateUserStatus":

                            Integer status = (Integer) point.getArgs()[0];

                            Integer id = (Integer) point.getArgs()[1];

                            String username = userService.getUserById(Long.valueOf(id)).getUsername();

                            methodName = status == 1 ? "冻结了" + username + "的用户" : "启用了" + username + "用户";
                    }

                    PuFaLog log = new PuFaLog();

                    //用户id存入对象
                    log.setUserId(Long.valueOf(userId));

                    //把日志信息存入对象
                    log.setLogInfo(userById.getUsername() + "管理员" + dateTime + "执行了" + methodName);

                    //把日志类型存入对象
                    log.setLogType(logType);

                    //程序执行完之后把日志记录到数据库
                    logService.addAdminLog(log);

                    break;
                    //如果是用户
//                } else if ("USER".equals(puFaRole.getRoleCode())) {
//
//                    //获取用户执行的请求
//                    String methodName = point.getSignature().getName();
//
//                    switch (methodName) {
//
//                        //用户修改信息日志记录
//                        case "updateById":
//                            methodName = "修改了自己的个人信息";
//                            break;
//
//                        //用户修改了自己的密码
//                        case "updatePassword":
//                            methodName = "修改了密码";
//                            break;
//
//                        //用户进行邮箱验证
//                        case "sendEmail":
//                            methodName = "进行邮箱验证";
//                            break;
//
//                        //用户找回密码
//                        case "passwordRetrieval":
//                            methodName = "执行找回密码";
//                            break;
//
//                    }
//
//                    //日志信息存入对象
//                    PuFaLog log = new PuFaLog(Long.valueOf(userId), userById.getUsername() + "用户" + dateTime + methodName);
//
//                    //最后存入数据库
//                    logService.addUserLog(log);
//
//                    break;
//

                }

            }
        }
    }


}
