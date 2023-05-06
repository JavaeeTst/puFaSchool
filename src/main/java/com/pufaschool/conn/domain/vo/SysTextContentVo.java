package com.pufaschool.conn.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文章内容名字(比如第几章)")
public class SysTextContentVo {

    @ApiModelProperty("文章内容id")
    private Long id;

    @ApiModelProperty("文章内容名(第几章)")
    private String contentName;
}
