package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel("普法视频实体类 ")
@Data
@TableName("t_video")
public class PuFaVideo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("视频标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("视频路径")
    @TableField("video_path")
    private String videoPath;

    @ApiModelProperty("视频名称")
    @TableField("video_name")
    private String videoName;

    @ApiModelProperty("状态(0正常,1下架)")
    @TableField("video_status")
    private Integer videoStatus;

    @ApiModelProperty("讲解")
    @TableField("video_explain")
    private String videoExplain;

    @TableField("video_description")
    @ApiModelProperty("描述")
    private String videoDescription;

    @TableField("video_uploader")
    @ApiModelProperty("上传人")
    private String videoUploader;

    @TableField("integration")
    @ApiModelProperty("积分")
    private Double integration;

    @TableField("video_cover")
    @ApiModelProperty("视频封面")
    private String videCover;

    @ApiModelProperty("视频浏览量")
    private Long pageViews;

    @TableField("lecturer")
    @ApiModelProperty("讲课人")
    private String lecturer;

    @ApiModelProperty("学习类型")
    private Long learnType;



    @ApiModelProperty("评论")
    @TableField(exist = false)
    private List<PuFaVideoComment> comments;

}
