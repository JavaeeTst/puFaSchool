package com.pufaschool.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaLearnType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 学习类型dao层
 */
public interface PuFaLearnTypeDao extends BaseMapper<PuFaLearnType> {

    //查询有几种学习类型
    @Select("select count(*) from t_learn_type where is_delete=#{isDelete}")
    Integer findLearnTypeNum(@Param("isDelete")Integer isDelete);


}
