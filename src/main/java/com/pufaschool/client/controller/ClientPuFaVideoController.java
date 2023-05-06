package com.pufaschool.client.controller;

import com.pufaschool.client.service.ClientPuFaVideoService;
import com.pufaschool.conn.domain.PuFaVideo;
import com.pufaschool.conn.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户获取视频控制层
 */
@RestController
@RequestMapping("/system/puFaSchool/client/video")
@Api(tags = "普法视频接口(前端)")
public class ClientPuFaVideoController {


    @Autowired
    private ClientPuFaVideoService puFaVideoService;

    @GetMapping("/getVideoList")
    @ApiOperation("获取所有的视频")
    public Result getVideoList(){

        List<PuFaVideo> videoList = puFaVideoService.getVideoList();

        return Result.success(videoList);

    }

    /**
     * 用户模糊查询视频
     */
    @GetMapping("/getVideoByKey")
    @ApiOperation("模糊查询视频")
    public Result getVideoByKey(@RequestParam("key")String key){

        List<PuFaVideo> videoByKey = puFaVideoService.getVideoByKey(key);

        return Result.success(videoByKey);
    }
    /**
     * 用户点击视频按视频id查询视频
     */
    @GetMapping("/getVideoByVideoId/{videoId}")
    @ApiOperation("用户点击视频查询视频信息(传视频id)")
    public Result getVideoByVideoId(@PathVariable Long videoId){

        PuFaVideo videoByVideoId = puFaVideoService.getVideoByVideoId(videoId);

        return Result.success(videoByVideoId);
    }


}
