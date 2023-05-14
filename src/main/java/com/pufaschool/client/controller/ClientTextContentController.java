package com.pufaschool.client.controller;

import com.pufaschool.conn.domain.PuFaTextContent;
import com.pufaschool.conn.domain.vo.SysTextContentVo;
import com.pufaschool.conn.result.Result;
import com.pufaschool.server.service.PuFaTextContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/puFaSchool/client/textContent")
@Api(tags = "普法文章内容(前端)")
public class ClientTextContentController {

    @Autowired
    private PuFaTextContentService contentService;

    /**
     * 查询文章内容(只查询id和内容名)
     */
    @GetMapping("/getTextContentIdAndTextNameByTextId/{textId}")
    @ApiOperation("查询文章内容(只返回内容id和内容名)")
    public Result getTextContentIdAndTextNameByTextId(@PathVariable Long textId){

        List<SysTextContentVo> textContextByTextId = contentService.getTextContextByTextId(textId);

        return Result.success(textContextByTextId);

    }
    /**
     * 按id查询文章内容
     */
    @GetMapping("/getTextContentByTextContentId/{textContentId}")
    @ApiOperation("按文章内容id查询文章内容")
    public Result getTextContentByTextContentId(@PathVariable Long textContentId){

        PuFaTextContent textContentByTextContentId = contentService.getTextContentByTextContentId(textContentId);

        return Result.success(textContentByTextContentId);
    }
}
