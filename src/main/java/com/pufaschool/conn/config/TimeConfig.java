package com.pufaschool.conn.config;

import com.pufaschool.conn.domain.PuFaCarousel;
import com.pufaschool.conn.domain.PuFaCourseWare;
import com.pufaschool.conn.domain.PuFaUser;
import com.pufaschool.conn.domain.PuFaVideo;
import com.pufaschool.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 图片视频清理
 */
@Component
public class TimeConfig {

    //用户service层
    @Autowired
    private PuFaUserService userService;

    //视频service层
    @Autowired
    private PuFaVideoService videoService;

    //轮播图servcice层
    @Autowired
    private PuFsCarouselService carouselService;

    @Autowired
    private PuFaLogService puFaLogService;

    //课件service层
    @Autowired
    private PuFaCourseWareService courseWareService;

    /**
     * 磁盘路径
     */
    @Value("${upload-path}")
    private String path;

    /**
     * 用户头像路径
     */
    @Value("${upload-avatar}")
    private String avatarPath;

    /**
     * 轮播图路径
     */
    @Value("${upload-carousel}")
    private String carouselPath;

    /**
     * 视频路径
     */
    @Value("${upload-video}")
    private String videoPath;

    /**
     * 视频封面路径
     */
    @Value("${upload-video-cover}")
    private String videoCoverPath;

    /**
     * 映射路径
     */
    @Value("${upload-mapper}")
    private String mapperPath;

    /**
     * 项目路径
     */
    @Value("${url-pattern}")
    private String urlPath;
    /**
     * 课件路径
     */
    @Value("${upload-courseWare}")
    private String courseWarePath;


    /**
     * 每三天对用户的头像清理(垃圾图片)
     */
    @Scheduled(initialDelay = 1000 * 60 * 60 * 24 * 3,fixedDelay = 1000 * 60 * 60 * 24 * 3)
    public void clearAvatar() {

        System.out.println("路径"+path);

        //先获取到头像文件的路径
        File file = new File(path + avatarPath);

        System.out.println("linux文件"+file);

        //在拿到所有的图片
        File[] list = file.listFiles();

        System.out.println("file长度"+list.length);
        System.out.println("file"+list);

        if (file != null) {
            //遍历文件
            for (File avatar : list) {

                //截取映射路径
                String mapper = mapperPath.substring(mapperPath.indexOf("/"), mapperPath.lastIndexOf("/"));

                //拿映射路径进行拼接http://localhost:8811
                String img = urlPath + mapper + avatarPath + "/" + avatar.getName();

                //在拿映射路径查询
                PuFaUser userByAvatar = userService.getUserByAvatar(img);

                //如果查不着就删除图片
                if (userByAvatar == null) {

                    avatar.delete();
                }

            }
        }
    }
    /**
     * 每过14天清理一次课件(垃圾课件)
     */
    @Scheduled(initialDelay = 1000 * 60 * 60 * 24 * 14,fixedDelay = 1000 * 60 * 60 * 24 * 14)
    public void clearCourseWare() {

        //先拿到课件的路径
        File file = new File(path + courseWarePath);

        //在拿到磁盘所有的课件
        File[] files = file.listFiles();

        //截取映射路径
        String mapper = mapperPath.substring(mapperPath.indexOf("/"), mapperPath.lastIndexOf("/"));

        //遍历所有的课件(课件不为null的情况)
        if (files != null) {
            for (File carousel : files) {

                //拼接映射路径比如http://localhost:8811
                String courseWare = urlPath + mapper + carouselPath + "/" + carousel.getName();


                //拿到最后的映射路径进行查找
                PuFaCourseWare courseWareByCourseWarePath = courseWareService.findCourseWareByCourseWarePath(courseWarePath);




                //如果没有查到为null的话，代表数据库没有这个课件,就清理(删除)
                if (courseWareByCourseWarePath == null) {

                    carousel.delete();
                }

            }

        }
    }


    /**
     * 每过5天清理一次轮播图(垃圾图片)
     */
    @Scheduled(initialDelay = 1000 * 60 * 60 * 24 * 5,fixedDelay = 1000 * 60 * 60 * 24 * 5)
    public void clearCarousel() {

        //先拿到轮播图的路径
        File file = new File(path + carouselPath);

        //在拿到磁盘所有的轮播图
        File[] files = file.listFiles();

        //截取映射路径
        String mapper = mapperPath.substring(mapperPath.indexOf("/"), mapperPath.lastIndexOf("/"));

        //遍历所有的图片文件(图片不为null的情况)
        if (files != null) {
            for (File carousel : files) {

                //拼接映射路径比如http://localhost:8811
                String img = urlPath + mapper + carouselPath + "/" + carousel.getName();


                //拿到最后的映射路径进行查找
                PuFaCarousel carouselByPicturePath = carouselService.getCarouselByPicturePath(img);


                //如果没有查到为null的话，代表数据库没有这张图片,就清理(删除)
                if (carouselByPicturePath == null) {

                    carousel.delete();
                }

            }

        }
    }

    /**
     * 每过一周就清理视频(垃圾视频和视频封面)
     */
    @Scheduled(initialDelay = 1000 * 60 * 60 * 24 * 7,fixedDelay = 1000 * 60 * 60 * 24 * 7)
    public void clearVideo() {

        //获取视频文件路径
        File videoFile = new File(path + videoPath);



        //获取视频文件下的所有视频
        File[] files = videoFile.listFiles();

        //截取映射路径
        String mapper = mapperPath.substring(mapperPath.indexOf("/"), mapperPath.lastIndexOf("/"));

        //如果视频文件不为null
        if (files != null) {
            //遍历所有的视频文件
            for (File video : files) {


                //拼接映射路径
                String img = urlPath + mapper + videoPath + "/" + video.getName();

                //在用拼接好的路径进行查找
                PuFaVideo videoByVideoPath = videoService.getVideoByVideoPath(img);

                System.out.println("视频" + img);

                //如果查询为null就代表数据库没有这视频,就进行清理(删除)
                if (videoByVideoPath == null) {

                    video.delete();
                }

            }

        }
        //获取视频封面的路径
        File videoCover = new File(path + videoCoverPath);

        //获取所有的视频封面
        File[] videoCovers = videoCover.listFiles();

        //截取映射路径
        String videoCoverMapper = mapperPath.substring(mapperPath.indexOf("/"), mapperPath.lastIndexOf("/"));

        //如果有文件
        if(videoCovers!=null){

            for (File cover : videoCovers) {

                //拼接视频封面路径
                String joinVideoCover = urlPath + mapper + videoCoverPath + "/" + cover.getName();

                //按视频封面查询
                PuFaVideo videoByVideoCover = videoService.getVideoByVideoCover(joinVideoCover);

                System.out.println("查询到视频封面"+joinVideoCover);

                //没有查询到就删除
                if (videoByVideoCover == null) {

                    cover.delete();
                }
            }
        }


    }



    /**
     * 一个月清理一次日志
     */
//    @Scheduled(fixedDelay = 1000*60*60*24*30)
//    public void logClear(){
//
//    }

}
