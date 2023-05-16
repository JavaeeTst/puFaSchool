package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 积分表
 */
@TableName("t_integrate")
@ApiModel("积分表实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuFaIntegration {


    @ApiModelProperty("用户id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @ApiModelProperty("视频或者课件id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long scoreId;

    @ApiModelProperty("得分类型(视频得分,下载课件得分)")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long scoreTypeId;

    @ApiModelProperty("获得积分")
    private Double get_integrate;

}
