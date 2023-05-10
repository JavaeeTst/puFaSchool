package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课件实体类
 */
@Data
@ApiModel("课件实体类")
@TableName("t_courseware")
public class PuFaCourseWare extends BaseEntity {

    @ApiModelProperty("课件id")
    private Long id;

    @ApiModelProperty("课件url")
    private String coursewareUrl;

    @ApiModelProperty("课件名称")
    @TableField("courseware_name")
    private String coursewareName;

    @ApiModelProperty("课件上传人")
    private String upload;

    @ApiModelProperty("课件描述")
    private String description;

    @ApiModelProperty("课件类型")
    @TableField("courseware_type")
    private String courserwareType;

    @ApiModelProperty("课件积分")
    @TableField("integration")
    private Integer integration;

    @ApiModelProperty("学习类型")
    private Integer learnType;


}
