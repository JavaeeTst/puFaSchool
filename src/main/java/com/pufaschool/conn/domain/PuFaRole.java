package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName("t_role")
@ApiModel("普法角色实体类")
@Data
public class PuFaRole extends BaseEntity {

    @ApiModelProperty("角色编号")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty("角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("角色描述")
    @TableField("description")
    private String description;

}
