package com.pufaschool.conn.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel("用户默认头像")
public class SysUserDefaultAvatar {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("头像地址")
    private String avatarUrl;

}
