package com.pufaschool.conn.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户属性vo
 */
@ApiModel("用户属性用于模糊查询")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysUserAttributeVo {

    @ApiModelProperty("用户的创建时间")
    private Date createTime;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户状态")
    private Integer status;

    @ApiModelProperty("邮箱")
    private String email;
}
