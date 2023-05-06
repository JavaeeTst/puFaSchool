package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志类
 */
@ApiModel("日志管理实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuFaLog extends BaseEntity {


    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("日志信息")
    @TableField("log_info")
    private String logInfo;

    @ApiModelProperty("日志类型")
    @TableField("log_type")
    public Integer logType;
}
