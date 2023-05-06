package com.pufaschool.conn;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.junit.platform.commons.util.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用实体类
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id通用
     */
    @TableField("id")
    @ApiModelProperty("通用id")
    @TableId(type = IdType.ASSIGN_ID)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    @ApiModelProperty("修改时间")
    private Date updateTime;
    /**
     * 是否删除
     */
    @TableField("is_delete")
    @ApiModelProperty("是否删除1已删除0没有删除")
    @TableLogic
    private Integer isDelete;
}
