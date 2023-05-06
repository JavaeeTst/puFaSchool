package com.pufaschool.client.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pufaschool.conn.domain.PuFaLearnRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 学习记录dao层
 */
public interface ClientLearnRecordDao extends BaseMapper<PuFaLearnRecord> {

    //学习记录查询(按学习类型)
    List<PuFaLearnRecord> findLearnRecordByLearnType(@Param("userId") Long userId,@Param("learnTypeId")Long learnTypeId);

    //学习记录查询(按时间和学习类型)
    List<PuFaLearnRecord> findLearnRecordByLearnTypeAndTime(@Param("userId") Long userId,@Param("learnType")Long learnType, @Param("learnDate")Date learnDate);
}
