package com.pufaschool.conn.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色vo类
 */
@Data
@ApiModel("用户分配角色vo")
public class SysUserRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;
    //用户id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    //角色id
    private Long roleId;

    //是否取消
    private Integer isCancel;
}
