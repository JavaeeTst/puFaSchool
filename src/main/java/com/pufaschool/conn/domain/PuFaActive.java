package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_pufa_active")
@ApiModel("普法活动实体类")
public class PuFaActive extends BaseEntity {

    @ApiModelProperty("活动标题")
    private String activeTittle;

    @ApiModelProperty("活动内容")
    private String activeContent;

    @ApiModelProperty("活动名称")
    private String activityName;

    @ApiModelProperty("活动创建人")
    private String activeFounder;

    @ApiModelProperty("活动状态")
    private Integer activeStatus;

    @ApiModelProperty("删除时间")
    private Date deleteTime;

}
