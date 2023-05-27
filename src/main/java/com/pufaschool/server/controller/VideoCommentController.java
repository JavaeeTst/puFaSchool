package com.pufaschool.server.controller;

import com.pufaschool.conn.domain.PuFaVideoComment;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.utils.JWTUtils;
import com.pufaschool.server.service.PuFaVideoCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "普法用户评论")
@RestController
@RequestMapping("/system/puFaSchool/server/comment")
public class VideoCommentController {

    @Autowired
    private PuFaVideoCommentService commentService;

    /**
     * 用户评论
     *
     * @param comment
     * @return
     */
    @PostMapping("/addComment")
    @ApiOperation("用户评论")
    public Result addComment(@RequestBody PuFaVideoComment comment,HttpServletRequest request) {

        Long userId= (Long) JWTUtils.checkToken(request.getHeader("token")).get("userId");

        comment.setUserId(userId);

        boolean result = commentService.addUserComment(comment);

        return Result.success(result ? "评论成功" : "评论失败");

    }
    /**
     * 用户删除评论
     */
    @DeleteMapping("/deleteVideoCommentByUserIdAndCommentId/{commentId}")
    @ApiOperation("用户删除评论(只传评论id)")
    public Result deleteVideoCommentByUserIdAndCommentId(@PathVariable Long commentId, HttpServletRequest request){

        Long userId= Long.valueOf((Integer)JWTUtils.checkToken(request.getHeader("token")).get("userId"));

        boolean result = commentService.deleteVideoCommentByUserIdAndCommentId(userId, commentId);

        return Result.success(result?"删除成功":"删除失败");

    }
}
