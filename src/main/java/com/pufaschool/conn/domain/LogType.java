package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apiguardian.api.API;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_log_type")
@ApiModel("日志类型表实体类")
public class LogType {


    @ApiModelProperty("日志类型id")
    private Integer id;

    @ApiModelProperty("日志类型")
    private String logType;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
