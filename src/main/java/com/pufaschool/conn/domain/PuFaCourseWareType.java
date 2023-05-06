package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 课件类型实体类
 */
@Data
@TableName("t_courseware_type")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("课件类型实体类")
public class PuFaCourseWareType extends BaseEntity {


    @ApiModelProperty("课件类型")
    @TableField("courseware_type")
    private String courseWareType;

    private Date deleteTime;


}
