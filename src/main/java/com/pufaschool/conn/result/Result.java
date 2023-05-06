package com.pufaschool.conn.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一返回结果
 */
@Data
@ApiModel("统一结果集返回")
public class Result {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("返回消息")
    private String message;

    @ApiModelProperty("返回数据")
    private Object data;


    public Result() {

    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result success(Object data) {

        return new Result(Status.SUCCESS, "成功", data);
    }

    public static Result error(Integer code, String message) {

        return new Result(code, message);
    }

}
