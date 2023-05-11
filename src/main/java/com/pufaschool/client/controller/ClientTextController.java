package com.pufaschool.client.controller;


import com.pufaschool.conn.domain.PuFaText;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaTextService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "普法文章接口(前端)")
@RequestMapping("/system/puFaSchool/client/text")
@RestController
public class ClientTextController {

    @Autowired
    private PuFaTextService textService;


    @GetMapping("/getTextAll")
    @ApiOperation("获取所有文章")
    public Result getTextAll() {

        List<PuFaText> textAll = textService.getTextAll();

        return Result.success(textAll);

    }

    /**
     * 单个文章查询(传文章id)
     */
    @GetMapping("/getTextByTextId/{textId}")
    @ApiOperation("单个文章信息详细查询（传文章id）")
    public Result getTextByTextId(@PathVariable("textId") Long textId) {

        PuFaText textByTextId = textService.getTextByTextId(textId);

        return Result.success(textByTextId);

    }
    /**
     * 模糊查询文章
     */
    @GetMapping("/getTextByAttribute/{key}")
    @ApiOperation("模糊查询文章")
    public Result getTextByAttribute(@PathVariable("key") String key){

        List<PuFaText> textByAttribute = textService.getTextByAttribute(key);

        return Result.success(textByAttribute);
    }
    /**
     * 文章浏览量+1(每点击一次发送一次请求)
     */
    @GetMapping("/textPageViews/{id}")
    @ApiOperation("文章浏览量+1")
    public void textPageViews(@PathVariable Long id){

        textService.textPageViews(id);

    }


}
