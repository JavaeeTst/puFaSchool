package com.pufaschool.server.utilController;

import com.pufaschool.conn.exception.FileFormatException;
import com.pufaschool.conn.exception.FileOverFlowMaxException;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传controller
 */
@RestController
@Api(tags = "文件上传")
@RequestMapping("/system/puFaSchool/upload")
public class FileController {

    //磁盘路径
    @Value("${upload-path}")
    private String uploadPath;

    //映射路径
    @Value("${upload-mapper}")
    private String uploadMapper;

    //轮播图路径
    @Value("${upload-carousel}")
    private String carousel;

    //头像路径
    @Value("${upload-avatar}")
    private String avatar;

    //项目路径
    @Value("${url-pattern}")
    private String url;

    //视频路径
    @Value("${upload-video}")
    private String videoPath;

    //课件路径
    @Value("${upload-courseWare}")
    private String courseWarePath;

    //视频封面
    @Value("${upload-video-cover}")
    private String uploadVideoCover;

    //文章插画
    @Value("${upload-textIllustration}")
    private String uploadTextIllustration;

    //文章封面
    @Value("${upload-textCover}")
    private String uploadTextCover;


    /**
     * 文章封面上传
     */

    @PostMapping("/uploadTextCover")
    @ApiOperation("文章封面上传")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result uploadTextCover(@RequestParam("uploadTextCover") MultipartFile[] files) {

        return FileUtil.imageFile(files, uploadPath, uploadMapper, url, uploadTextCover);

    }

    /**
     * 文章插画上传
     */
    @PostMapping("/uploadTextIllustration")
    @ApiOperation("文章插画上传")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result uploadTextIllustration(@RequestParam("uploadTextIllustration") MultipartFile[] files) {


        return FileUtil.imageFile(files, uploadPath, uploadMapper, url, uploadTextIllustration);

    }


    /**
     * 视频封面上传(支持多文件)
     */
    @PostMapping("/videoCover")
    @ApiOperation("视频封面上传")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result videoCover(@RequestParam("videoCover") MultipartFile[] files) {


        return FileUtil.imageFile(files, uploadPath, uploadMapper, url, uploadVideoCover);

    }

    /**
     * 轮播图上传(多文件或者单文件,管理员使用)
     *
     * @param files
     * @return
     */
    @PostMapping("/carousel")
    @ApiOperation("轮播图上传(支持多文件)")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result carousel(@RequestParam("carousel") MultipartFile[] files) {


        return FileUtil.imageFile(files, uploadPath, uploadMapper, url, carousel);
    }

    /**
     * 用户头像上传
     */
    @ApiOperation("用户头像上传")
    @PostMapping("/avatar")
    public Result avatar(@RequestParam("avatar") MultipartFile[] files) {


        return FileUtil.imageFile(files, uploadPath, uploadMapper, url, avatar);

    }

    /**
     * 管理员上传视频
     */
    @PostMapping("/video")
    @ApiOperation("视频上传(支持多文件)")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result videoUpload(@RequestParam("video") MultipartFile[] files) {


        return FileUtil.videoFile(files, uploadPath, uploadMapper, url, videoPath);
    }

    /**
     * 管理员上传课件
     */
    @PostMapping("/courseWare")
    @ApiOperation("课件上传(支持多文件,后端)")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result courseWareUpload(@RequestParam("courseWare") MultipartFile[] files) {


        return FileUtil.courseWareFile(files,uploadPath,uploadMapper,url,courseWarePath);
    }
}
