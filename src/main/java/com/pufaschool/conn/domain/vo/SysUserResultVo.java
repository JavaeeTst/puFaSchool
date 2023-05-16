package com.pufaschool.conn.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserResultVo implements Serializable {

    private static final long serialVersionUID = 1L;


    //用户id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    //用户名
    private String username;

}
