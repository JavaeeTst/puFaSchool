package com.pufaschool.conn.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pufaschool.conn.BaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("t_text_content")
@ApiModel("普法文章内容")
public class PuFaTextContent extends BaseEntity {


    @ApiModelProperty("文章id(父id)")
    private Long textId;

    @ApiModelProperty("文章内容(使用富文本)")
    private String textContent;

    @ApiModelProperty("内容排序数字")
    private Long textSort;

    @ApiModelProperty("内容名字(比如第几章)")
    private String contentName;

    @ApiModelProperty("文章积分(每一章的积分)")
    private Double integration;

}
