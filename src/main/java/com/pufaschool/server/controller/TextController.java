package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaText;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaTextService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "普法文章接口(后端)")
@RequestMapping("/system/puFaSchool/server/text")
@RestController
public class TextController {

    @Autowired
    private PuFaTextService textService;

    /**
     * 按id删除文章
     * @param textId
     * @return
     */
    @DeleteMapping("/deleteByTextId/{textId}")
    @ApiOperation("按文章id删除文章")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result deleteByTextId(@PathVariable("textId") Long textId) {


        boolean result = textService.deleteByTextId(textId);

        return result?Result.success("删除成功"):Result.error(Status.ERROR,"删除失败");

    }

    /**
     * 文章添加
     * @param puFaText
     * @return
     */
    @PostMapping("/addText")
    @ApiOperation("文章添加")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Result addText(@RequestBody PuFaText puFaText){

        boolean result = textService.addText(puFaText);

        return result?Result.success("添加成功"):Result.error(Status.ADD_ERR,"添加失败");

    }
    /**
     * 文章修改
     * @param puFaText
     * @return
     */
    @PostMapping("/updateByTextId")
    @ApiOperation("文章修改")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    public Result updateByTextId(@RequestBody PuFaText puFaText){

        boolean result = textService.updateTextByTextId(puFaText);

        return result?Result.success("修改"):Result.error(Status.ERROR,"修改失败");

    }

}
