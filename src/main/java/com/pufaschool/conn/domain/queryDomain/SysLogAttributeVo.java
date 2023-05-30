package com.pufaschool.conn.domain.queryDomain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 日志查询vo
 */
@ApiModel("日志查询vo")
@Data
public class SysLogAttributeVo {

    @ApiModelProperty("用户id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @ApiModelProperty("日志时间")
    private String createTime;

    @ApiModelProperty("日志类型")
    private Integer logType;

}
