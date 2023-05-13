package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaTextContent;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.result.Status;
import com.pufaschool.server.service.PuFaTextContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "文章内容")
@RestController
@RequestMapping("/system/puFaSchool/server/TextContent")
public class TextContentController {

    @Autowired
    private PuFaTextContentService contentService;


    /**
     * 文章内容添加
     * @param puFaTextContent
     * @return
     */
    @GetMapping("/addTextContent")
    @ApiOperation("添加文章内容")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Result addTextContent(@RequestBody PuFaTextContent puFaTextContent){

        boolean result = contentService.addTextContent(puFaTextContent);

        return result?Result.success("添加成功"):Result.error(Status.ERROR,"添加失败");
    }

    @DeleteMapping("/deleteTextContentByContentId/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @ApiOperation("按文章内容id删除文章内容")
    public Result deleteTextContentByContentId(@PathVariable Long id){

        boolean result = contentService.deleteTextContentById(id);

        return result?Result.success("删除成功"):Result.error(Status.ERROR,"删除失败");
    }
    @ApiOperation("修改文章内容")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @PutMapping("/updateTextContentByTextContentId")
    public Result updateTextContentByTextContentId(@RequestBody PuFaTextContent puFaTextContent){

        boolean result = contentService.updateTextContentById(puFaTextContent);

        return result?Result.success("修改成功"):Result.error(Status.ERROR,"修改失败");
    }

}
