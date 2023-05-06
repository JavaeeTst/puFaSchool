package com.pufaschool.conn.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 学习类型表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_learn_type")
@ApiModel("学习类型表实体类")
public class PuFaLearnType extends BaseEntity {

    @ApiModelProperty("学习类型")
    private String learnType;

    @ApiModelProperty("创建人")
    private String founder;
}
