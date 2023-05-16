package com.pufaschool.conn.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pufaschool.conn.domain.PuFaUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户申请管理员vo
 */
@ApiModel("用户申请管理员vo")
public class SysUserApplyAdminVo {

    @ApiModelProperty("用户id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @ApiModelProperty("申请理由")
    @TableField("reasons_for_application")
    private String reasonsForApplication;

    @ApiModelProperty("审核人id(前端不用上传)")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long reviewerId;

    @ApiModelProperty("审核状态")
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

}
