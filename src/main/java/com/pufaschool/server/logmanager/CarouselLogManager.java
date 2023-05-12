package com.pufaschool.server.logmanager;

import com.pufaschool.conn.domain.PuFaCarousel;
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
 * 轮播图日志管理
 */
@Aspect
@Configuration
public class CarouselLogManager {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PuFaUserService userService;

    @Autowired
    private PuFaRoleService roleService;

    @Autowired
    private PuFaLogService logService;

    /**
     * 轮播图控制层日志切入点
     */
    @Pointcut("execution(* com.pufaschool.server.controller.CarouselController..*(..))")
    public void carouselController() {

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
            Long userId =Long.valueOf((Long) claims.get("userId"));

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
}
