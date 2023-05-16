package com.pufaschool.conn.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户vo类（密码修改）
 */
@Data
@ApiModel("用户密码修改(找回)vo")
public class SysUserUpdatePasswordVo implements Serializable {

    private static final long serialVersionUID = 1L;
    //搜索条件
    @ApiModelProperty("搜索条件")
    private String key;

    //旧密码
    @ApiModelProperty("旧密码")
    private String oldPassword;

    //新密码
    @ApiModelProperty("新密码")
    private String newPassword;

    //确认新密码
    @ApiModelProperty("确认新密码")
    private String confirmNewPassword;

    //用户名
    @ApiModelProperty("用户名")
    private String username;

    //用户id
    @ApiModelProperty("用户id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;
}
