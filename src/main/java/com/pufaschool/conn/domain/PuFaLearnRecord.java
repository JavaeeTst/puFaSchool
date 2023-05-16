package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 学习记录实体类
 */
@ApiModel("学习记录实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_learn_record")
public class PuFaLearnRecord {

    @ApiModelProperty("用户id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @ApiModelProperty("学习类型id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField("t_learn_type")
    private Long learnTypeId;

    @ApiModelProperty("学习时间")
    private Date createTime;

    @ApiModelProperty("删除时间")
    private Date deleteTime;

    @ApiModelProperty("地址")
    private String url;


}
