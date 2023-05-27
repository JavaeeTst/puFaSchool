package com.pufaschool.server.logmanager;

import com.pufaschool.conn.domain.PuFaCourseWare;
import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.conn.utils.LogUtil;
import com.pufaschool.server.service.PuFaCourseWareService;
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
import java.util.List;

/**
 * 课件日志管理
 */
@Aspect
@Configuration
public class CourseWareLogManager {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PuFaUserService userService;

    @Autowired
    private PuFaRoleService roleService;

    @Autowired
    private PuFaLogService logService;

    @Autowired
    private PuFaCourseWareService wareService;

    @Pointcut("execution(* com.pufaschool.server.controller.CourseWareController..*(..))")
    public void courseWareController() {
    }

    @After("courseWareController()")
    public void courseWareLogMapper(JoinPoint point) {

        //先获取用户id
        Long userId = Long.valueOf((Long) JWTUtils.checkToken(request.getHeader("token")).get("userId"));

        //在使用id查询角色
        List<PuFaRole> roleByUsernameOrUserId = roleService.getRoleByUsernameOrUserId(null, userId);

        //截取方法名
        String methodName = point.getSignature().getName();

        //获取当前时间
        String currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

        //使用id查询用户
        PuFaUser userById = userService.getUserById(userId);

        //日志类型（默认查询）
        Integer logType = LogUtil.QUERY_LOG;

        //日志对象
        PuFaLog log = null;

        //课件对象
        PuFaCourseWare courseWare = null;


        //遍历角色
        for (PuFaRole puFaRole : roleByUsernameOrUserId) {

            //如果是管理员或者超级管理员
            if ("ADMIN".equals(puFaRole.getRoleCode()) || "SUPER_ADMIN".equals(puFaRole.getRoleCode())) {

                switch (methodName) {

                    //如果是上传课件
                    case "addCourseWare":

                        //先获取课件对象
                        courseWare = (PuFaCourseWare) point.getArgs()[0];

                        methodName = "管理员" + userById.getUsername() + "上传了课件名为" + courseWare.getCoursewareName() + "的课件";

                        logType = LogUtil.ADD_LOG;

                        //实例化日志对象
                        log = new PuFaLog(userId, methodName, logType);

                        //日志存入数据库
                        logService.addAdminLog(log);

                        break;
                    //删除课件
                    case "deleteById":

                        //把传过来的课件id查询一下课件对象
                        courseWare = wareService.getCourseWareByDeleteId((Long) point.getArgs()[0]);

                        methodName = "管理员" + userById.getUsername() + "删除了课件名为" + courseWare.getCoursewareName() + "的课件";

                        logType = LogUtil.DELETE_LOG;

                        //实例化日志对象
                        log = new PuFaLog(userId, methodName, logType);

                        //日志存入数据库
                        logService.addAdminLog(log);

                        break;
                }


            }else {

                //之后是用户
                switch (methodName){

                    //用户下载课件
                    case "getCourseWareById":

                        courseWare=wareService.getCourseWareById((Long) point.getArgs()[0]);

                        methodName="用户"+userById.getUsername()+"下载了名为"+courseWare.getCoursewareName()+"的课件";


                        logType=LogUtil.QUERY_LOG;

                        //实例化对象
                        log=new PuFaLog(userId,methodName,logType);

                        //存入数据库
                        logService.addUserLog(log);
                        break;
                    case "getCourseWareByIds":

                        //获取第一个参数数组
                        Long[] ids = (Long[]) point.getArgs()[0];

                        //遍历课件id
                        for (Long id : ids) {

                            courseWare=wareService.getCourseWareById(id);

                            methodName="用户"+userById.getUsername()+"下载了名为"+courseWare+"的课件";

                            //实例化对象
                            log=new PuFaLog(userId,methodName,LogUtil.QUERY_LOG);

                            //存入数据库
                            logService.addUserLog(log);
                        }

                        break;

                }


            }

        }
    }
}
