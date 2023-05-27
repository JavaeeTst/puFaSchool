package com.pufaschool.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pufaschool.conn.domain.PuFaVideo;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "普法视频接口(后端)")
@RestController
@RequestMapping("/system/puFaSchool/server/video")
public class   VideoController {

    @Autowired
    private PuFaVideoService videoService;


    /**
     * 清空被删除的视频
     */
    @ApiOperation("清空被删除的视频")
    @DeleteMapping("/deleteVideoByIdList")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result deleteVideoByIdList(@RequestParam("ids")Long[] ids){

        boolean result = videoService.removeVideoByIdList(ids);

        return result?Result.success("删除成功"):Result.error(Status.DELETE_ERR,"删除失败");
    }
    /**
     * 查询所有被删除的视频
     */
    @ApiOperation("查询所有被删除的视频")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getVideoByDeleteId/{deleteId}")
    public Result getVideoDeleteId(@PathVariable Long deleteId){

        PuFaVideo videoByDeleteId = videoService.getVideoByDeleteId(deleteId);

        return Result.success(videoByDeleteId);
    }
    /**
     * 按id查询被删除的视频
     */
    @ApiOperation("按id查询被删除的视频")
    @GetMapping("/getDeleteVideoList")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getDeleteVideoList(){

        List<PuFaVideo> deleteVideoList = videoService.getDeleteVideoList();

        return Result.success(deleteVideoList);
    }

    /**
     * 模糊查询视频
     */
    @ApiOperation("模糊查询视频(视频名称等其他)")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getVideoByVideoAttribute/{key}")
    public Result getVideoByVideoAttribute(@PathVariable String key){

        List<PuFaVideo> videoByVideoAttribute = videoService.getVideoByVideoAttribute(key);

        return Result.success(videoByVideoAttribute);
    }
    /**
     * 视频查询
     *
     * @param firstPage
     * @param lastPage
     * @return
     */
    @ApiOperation("查询视频的分页")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getPageList/{firstPage}/{lastPage}")
    public Result getPageList(@PathVariable Integer firstPage,
                              @PathVariable Integer lastPage) {

        Page<PuFaVideo> page = new Page<>(firstPage, lastPage);

        IPage<PuFaVideo> pageList = videoService.getPageList(page);

        return Result.success(pageList);
    }

    /**
     * 普法视频添加(管理员)
     *
     * @param puFaVideo
     * @return
     */
    @ApiOperation("普法视频添加")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PostMapping("/addPuFaVideo")
    public Result addPuFaVideo(@RequestBody PuFaVideo puFaVideo) {


        System.out.println(puFaVideo);

        boolean result = videoService.addPuFaVideo(puFaVideo);

        return Result.success(result ? "添加成功" : "添加失败");
    }

    /**
     * 普法视频删除(管理员)
     */
    @ApiOperation("普法视频删除(管理员)")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/deleteVideoById")
    public Result deleteVideoById(@RequestParam("id") Long id) {

        System.out.println(id);

        boolean result = videoService.deleteVideoById(id);

        return Result.success(result ? "删除成功" : "删除失败");
    }

    /**
     * 普法视频修改(管理员)
     */
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @ApiOperation("普法视频修改(管理员)")
    @PostMapping("/updateVideoById")
    public Result updateVideoById(@RequestBody PuFaVideo video) {

        boolean result = videoService.updateVideoById(video);

        return Result.success(result ? "修改成功" : "修改失败");
    }

    /**
     * 普法视频查询按id
     */
    @ApiOperation("普法视频查询(按id)")
    @GetMapping("/getVideoById")
    public Result getVideoById(@RequestParam("id") Long id) {

        PuFaVideo videoById = videoService.getVideoById(id);

        return Result.success(videoById);
    }


}
