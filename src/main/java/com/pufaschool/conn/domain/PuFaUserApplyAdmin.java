package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户申请管理员实体
 */
@Data
@TableName("t_user_apply_admin")
@ApiModel("用户申请管理员实体")
public class PuFaUserApplyAdmin extends BaseEntity {

    @ApiModelProperty("用户id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @ApiModelProperty("申请理由")
    @TableField("reasons_for_application")
    private String reasonsForApplication;

    @ApiModelProperty("审核人id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long reviewerId;

    @ApiModelProperty("审核状态（1待审核0同意2拒绝）")
    @TableField("reviewer_status")
    private Integer reviewerStatus;

    @ApiModelProperty("申请人身份证号")
    @TableField("applicant_idcard")
    private String applicantIdcard;

    @ApiModelProperty("申请人真实姓名")
    @TableField("applicant_real_name")
    private String applicantRealName;

    @ApiModelProperty("是否有前科")
    @TableField("is_previous_crime")
    private String isPreviousCrime;

    @ApiModelProperty("无前科证明上传")
    @TableField("no_previous_crime")
    private String noPreviousCrime;

    @ApiModelProperty("审核人")
    @TableField(exist = false)
    private PuFaUser reviewer;




}
