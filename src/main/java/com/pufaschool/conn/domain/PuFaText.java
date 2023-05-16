package com.pufaschool.conn.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pufaschool.conn.BaseEntity;
import com.pufaschool.conn.domain.vo.SysTextContentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_text")
@ApiModel("普法文章实体类")
public class PuFaText extends BaseEntity {


    @ApiModelProperty("文章描述")
    private String description;

    @ApiModelProperty("上传人")
    private String upload;

    @ApiModelProperty("学习类型")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long learnType;

    @ApiModelProperty("总积分")
    private Double TotalIntegration;

    @ApiModelProperty("文章封面")
    private String textCover;

    @ApiModelProperty("文章标题")
    private String textTittle;

    @ApiModelProperty("文章浏览量")
    private  Long pageViews;

    @ApiModelProperty("文章内容")
    @TableField(exist = false)
    private List<SysTextContentVo> textContent;




}
