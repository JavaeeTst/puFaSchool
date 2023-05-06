package com.pufaschool.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pufaschool.conn.domain.PuFaLearnRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 用户查询学习记录service
 */
public interface ClientLearnRecordService extends IService<PuFaLearnRecord> {

    //学习记录查询(按学习类型)
    List<PuFaLearnRecord> findLearnRecordByLearnType(Long userId, Long learnTypeId);

    //学习记录查询(按时间和学习类型)
    List<PuFaLearnRecord> findLearnRecordByLearnTypeAndTime(Long userId, Long learnType, Date learnDate);

    //添加学习记录
    boolean addLearnRecord(PuFaLearnRecord puFaLearnRecord);

    //删除学习记录(url)
    boolean deleteLearnRecordByUrl(String url);
}
