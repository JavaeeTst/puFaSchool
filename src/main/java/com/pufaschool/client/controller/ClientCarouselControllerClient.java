package com.pufaschool.client.controller;

import com.pufaschool.client.dao.ClientCarouselDao;
import com.pufaschool.client.service.ClientCarouselService;
import com.pufaschool.conn.domain.PuFaCarousel;
import com.pufaschool.conn.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/system/puFaSchool/client/carousel")
@RestController
@Api(tags = "普法轮播图接口(前端)")
public class ClientCarouselControllerClient {

    @Autowired
    private ClientCarouselService carouselService;

    /**
     * 用户查询所有轮播图
     */
    @GetMapping("/getList")
    @ApiOperation("用户查询所有轮播图")
    public Result getList() {

        List<PuFaCarousel> carouselList = carouselService.getCarouselList();

        return Result.success(carouselList);
    }


}
