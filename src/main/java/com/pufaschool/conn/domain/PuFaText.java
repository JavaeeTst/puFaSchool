package com.pufaschool.conn.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import com.pufaschool.conn.domain.vo.SysTextContentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_text")
@Api("普法文章实体类")
public class PuFaText extends BaseEntity {


    @ApiModelProperty("文章描述")
    private String description;

    @ApiModelProperty("上传人")
    private String upload;

    @ApiModelProperty("学习类型")
    private Long learnType;

    @ApiModelProperty("总积分")
    private Double integration;

    @ApiModelProperty("文章封面")
    private String textCover;

    @ApiModelProperty("文章内容")
    @TableField(exist = false)
    private List<SysTextContentVo> textContent;




}
