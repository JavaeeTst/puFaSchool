package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("普法视频评论实体类")
@TableName("t_video_comment")
@Data
public class PuFaVideoComment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户")
    @TableField(exist = false)
    private PuFaUser user;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("视频id")
    @TableField("video_id")
    private Long videoId;

    @ApiModelProperty("评论")
    @TableField("comment")
    private String comment;

}
