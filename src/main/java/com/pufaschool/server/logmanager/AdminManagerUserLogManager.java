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
 * 管理员管理用户日志记录
 */
@Aspect
@Configuration
public class AdminManagerUserLogManager {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PuFaUserService userService;

    @Autowired
    private PuFaRoleService roleService;

    @Autowired
    private PuFaLogService logService;


    /**
     * 用户控制层日志切入点(管理员)
     */
    @Pointcut("execution(* com.pufaschool.server.controller.UserController..*(..))")
    public void AdminUserController() {

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


                }

            }
        }
    }
}
