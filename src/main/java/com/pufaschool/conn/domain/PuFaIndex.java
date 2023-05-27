package com.pufaschool.conn.domain;

import com.pufaschool.conn.domain.vo.SysUserInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@ApiModel("普法首页数据")
@Data
public class PuFaIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户量")
    private Long userNum;

    @ApiModelProperty("管理员数量")
    private Integer adminNum;

    @ApiModelProperty("视频个数")
    private Integer videoNum;

    @ApiModelProperty("学习类型(有几种)")
    private Integer learnTypeNum;

    @ApiModelProperty("课件数量")
    private Integer courseWareNum;

    @ApiModelProperty("用户总积分")
    private Double userTotalIntegration;

    @ApiModelProperty("积分高于500的用户")
    private List<SysUserInfoVo> username;

    public PuFaIndex(Long userNum, Integer adminNum, Integer videoNum, Integer learnTypeNum, Integer courseWareNum, Double userTotalIntegration, List<SysUserInfoVo> username) {
        this.userNum = userNum;
        this.adminNum = adminNum;
        this.videoNum = videoNum;
        this.learnTypeNum = learnTypeNum;
        this.courseWareNum = courseWareNum;
        this.userTotalIntegration = userTotalIntegration;
        this.username = username;
    }

    public PuFaIndex(Long userNum, Integer adminNum, Integer videoNum, Integer learnTypeNum, Integer courseWareNum, Double userTotalIntegration) {
        this.userNum = userNum;
        this.adminNum = adminNum;
        this.videoNum = videoNum;
        this.learnTypeNum = learnTypeNum;
        this.courseWareNum = courseWareNum;
        this.userTotalIntegration = userTotalIntegration;
    }

    public PuFaIndex() {
    }
}
