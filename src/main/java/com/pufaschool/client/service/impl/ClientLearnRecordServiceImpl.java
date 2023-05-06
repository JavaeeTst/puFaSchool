package com.pufaschool.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pufaschool.client.dao.ClientLearnRecordDao;
import com.pufaschool.client.service.ClientLearnRecordService;
import com.pufaschool.conn.domain.PuFaLearnRecord;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 用户查询学籍记录业务层
 */
@Service
public class ClientLearnRecordServiceImpl extends ServiceImpl<ClientLearnRecordDao, PuFaLearnRecord> implements ClientLearnRecordService {


    /**
     * 按学习类型查询学习记录
     * @param learnTypeId
     * @return
     */
    @Override
    public List<PuFaLearnRecord> findLearnRecordByLearnType(Long userId,Long learnTypeId) {

        List<PuFaLearnRecord> learnRecordByLearnType = baseMapper.findLearnRecordByLearnType(userId,learnTypeId);


        return learnRecordByLearnType;
    }


    /**
     * 按学习时间和学习记录查询
     * @param learnType
     * @param learnDate
     * @return
     */
    @Override
    public List<PuFaLearnRecord> findLearnRecordByLearnTypeAndTime(Long userId,Long learnType, Date learnDate) {

        List<PuFaLearnRecord> learnRecordByLearnTypeAndTime = this.findLearnRecordByLearnTypeAndTime(userId,learnType, learnDate);


        return learnRecordByLearnTypeAndTime;
    }

    /**
     * 添加学习记录
     * @param puFaLearnRecord
     * @return
     */
    @Override
    public boolean addLearnRecord(PuFaLearnRecord puFaLearnRecord) {

        boolean result = this.save(puFaLearnRecord);

        return result;
    }

    /**
     * 删除学习记录
     * @param url
     * @return
     */
    @Override
    public boolean deleteLearnRecordByUrl(String url) {

        LambdaQueryWrapper<PuFaLearnRecord> wrapper=new LambdaQueryWrapper<>();

        wrapper.eq(PuFaLearnRecord::getUrl,url);

        boolean result = this.remove(wrapper);


        return result;
    }
}
