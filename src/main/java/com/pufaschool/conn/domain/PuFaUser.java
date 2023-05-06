package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@TableName("t_user")
@ApiModel("普法用户实体类")
public class PuFaUser extends BaseEntity {


    @ApiModelProperty("用户名")
    @Length(min = 6, max = 11, message = "用户名长度必须6到11位")
    private String username;

    @ApiModelProperty("真实姓名")
    @TableField("real_name")
    @NotNull(message = "姓名不能为空")
    private String realName;

    @ApiModelProperty("密码")
    @Length(min = 6, max = 18, message = "密码长度必须6到18位")
    private String password;

    @ApiModelProperty("手机号")
    @TableField("phone_num")
    @Length(max = 11, min = 11, message = "请输入正确的手机号格式")
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
