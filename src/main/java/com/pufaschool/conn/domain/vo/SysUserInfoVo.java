package com.pufaschool.conn.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName("t_user")
@ApiModel("用户信息(用户返回用户)")
@AllArgsConstructor
@NoArgsConstructor
public class SysUserInfoVo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty("手机号")
    @TableField("phone_num")
    private String phoneNum;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("邮箱")
    @NotNull(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("总积分")
    @TableField("total_integration")
    private Double TotalIntegration;

    @ApiModelProperty("籍贯")
    @TableField("native")
    private String birthplace;

    @ApiModelProperty("家庭住址")
    @TableField("home_address")
    private String homeAddress;

    @ApiModelProperty("身份证号")
    @TableField("id_card")
    private String idCard;
}
