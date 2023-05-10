package com.pufaschool.server.logmanager;

import com.pufaschool.conn.domain.PuFaLog;
import com.pufaschool.conn.domain.PuFaRole;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.PuFaVideo;
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
import java.util.List;

/**
 * 视频日志管理
 */
@Aspect
@Configuration
public class VideoLogManager {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PuFaRoleService roleService;

    @Autowired
    private PuFaLogService logService;

    @Autowired
    private PuFaUserService userService;

    /**
     * 视频控制层日志切入点
     */
    @Pointcut("execution(* com.pufaschool.server.controller.VideoController..*(..))")
    public void videoController() {

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
}
