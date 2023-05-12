package com.pufaschool.conn.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("邮箱修改验证")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class EmailVo {

    @ApiModelProperty("新邮箱验证码")
    private String newCode;

    @ApiModelProperty("老邮箱验证码")
    private String oldCode;

    @ApiModelProperty("新邮箱")
    private String newEmail;

    @ApiModelProperty("老邮箱")
    private String oldEmail;

    @ApiModelProperty("用户id")
    private Long userId;
}
