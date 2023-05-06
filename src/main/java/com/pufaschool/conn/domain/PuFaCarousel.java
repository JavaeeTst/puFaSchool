package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName("t_carousel")
@ApiModel("普法轮播图实体类")
@Data
public class PuFaCarousel extends BaseEntity {

    @ApiModelProperty("轮播图路径")
    @TableField("picture_path")
    private String picturePath;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("上传人")
    private String uploader;

    @ApiModelProperty("来源(那个省)")
    private String source;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("图片名称")
    @TableField("picture_name")
    private String pictureName;


}
