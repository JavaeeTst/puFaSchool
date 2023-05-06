package com.pufaschool.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pufaschool.conn.domain.PuFaCarousel;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFsCarouselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "普法轮播图接口(后端)")
@RequestMapping("/system/puFaSchool/server/carousel")
public class CarouselController {

    @Autowired
    private PuFsCarouselService carouselService;


    @GetMapping("/getPageList/{firstPage}/{lastPage}")
    @ApiOperation("轮播图分页查询")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result getPageList(@PathVariable("firstPage") Integer firstPage,
                              @PathVariable("lastPage") Integer lastPage) {
        //把第一页和要显示的页数放入page对象里面
        Page<PuFaCarousel> page = new Page<>(firstPage, lastPage);

        IPage<PuFaCarousel> pageList = carouselService.getPageList(page);

        System.out.println(pageList);

        return Result.success(page);


    }

    @PostMapping("/addCarousel")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @ApiOperation("添加轮播图")
    public Result addCarousel(@RequestBody PuFaCarousel puFaCarousel) {

        boolean result = carouselService.addCarousel(puFaCarousel);

        System.out.println(puFaCarousel);

        return Result.success(result ? "添加成功" : "添加失败");

    }

    @DeleteMapping("/deleteById")
    @ApiOperation("删除轮播图(id)")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result deleteById(@RequestParam("id") Long id) {

        boolean result = carouselService.deleteById(id);

        return Result.success(result ? "删除成功" : "删除失败");
    }

    @ApiOperation("修改轮播图信息")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PostMapping("/updateCarouselById")
    public Result updateCarouselById(@RequestBody PuFaCarousel carousel) {

        boolean result = carouselService.updateCarouselById(carousel);

        return Result.success(result ? "修改成功" : "修改失败");
    }


    @ApiOperation("按id查找轮播图")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getCarouselById")
    public Result getCarouselById(@RequestParam("id") Long id) {

        PuFaCarousel carouselById = carouselService.getCarouselById(id);

        return Result.success(carouselById);
    }

    @ApiOperation("按创建时间查询")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @GetMapping("/getCarouselByCreateTime")
    public Result getCarouselCreateTime(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("date") Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String format = dateFormat.format(date);


        List<PuFaCarousel> carouselByCreateTime = carouselService.getCarouselByCreateTime(format);

        return Result.success(carouselByCreateTime);
    }

}
