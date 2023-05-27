package com.pufaschool.client.controller;

import com.pufaschool.client.service.ClientLearnRecordService;
import com.pufaschool.conn.domain.PuFaLearnRecord;
import com.pufaschool.conn.result.Result;
import com.pufaschool.conn.utils.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 学习记录控制层
 */
@RestController
@Api(tags = "学习记录查询(前端和后端都可)")
@RequestMapping("/system/puFaSchool/client/learnRecord")
public class ClientLearnRecordController {

    @Autowired
    private ClientLearnRecordService recordService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 按学习类型查询
     */
    @GetMapping("/getLearnRecordByLearnType/{learnTypeId}")
    @ApiOperation("按学习类型查询学习记录(传学习类型id)")
    public Result getLearnRecordByLearnType(@PathVariable Long learnTypeId) {

        Long userId = (Long) JWTUtils.checkToken(request.getHeader("token")).get("userId");

        List<PuFaLearnRecord> learnRecordByLearnType = recordService.findLearnRecordByLearnType(userId, learnTypeId);


        return Result.success(learnRecordByLearnType);

    }

    /**
     * 按学习类型和学习时间查询学习记录
     */
    @GetMapping("/getLearnRecordByLearnTypeAndLearnTime")
    @ApiOperation("按学习类型和学习时间查询(传学习类型id和时间)")
    public Result getLearnTypeByLearnTypeAndLearnTime(@RequestParam("learnTypeId") Long learnTypeId, @RequestParam("learnTime") Date learnTime) {

        Long userId = (Long) JWTUtils.checkToken(request.getHeader("token")).get("userId");

        List<PuFaLearnRecord> learnRecordByLearnTypeAndTime = recordService.findLearnRecordByLearnTypeAndTime(userId, learnTypeId, learnTime);


        return Result.success(learnRecordByLearnTypeAndTime);
    }

    /**
     * 添加学习记录
     */
    @PostMapping("/addLearnRecord")
    @ApiOperation("添加学习记录")
    public Result addLearnRecord(@RequestBody PuFaLearnRecord record) {

        Long userId = (Long) JWTUtils.checkToken(request.getHeader("token")).get("userId");

        boolean result = recordService.addLearnRecord(record);

        return Result.success(result ? "添加成功" : "添加失败");
    }

    /**
     * 删除学习记录
     */
    @DeleteMapping("/deleteLearnRecordByUrl")
    @ApiOperation("删除学习记录(传url)")
    public Result deleteLearnRecordByUrl(@RequestParam("url") String url) {

        boolean result = recordService.deleteLearnRecordByUrl(url);

        return Result.success(result ? "删除成功" : "删除失败");
    }
}
