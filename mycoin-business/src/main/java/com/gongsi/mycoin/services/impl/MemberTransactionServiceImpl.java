package com.gongsi.mycoin.services.impl;

import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.core.utils.IdGenerator;
import com.gongsi.mycoin.dao.MemberTransactionMapper;
import com.gongsi.mycoin.entities.MemberTransaction;
import com.gongsi.mycoin.services.MemberTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by 吴宇 on 2018-06-03.
 */
@Service
public class MemberTransactionServiceImpl implements MemberTransactionService {
    @Autowired
    private MemberTransactionMapper memberTransactionMapper;
    /**
     * 获取充值流水
     * @return
     */
    public synchronized MemberTransaction insertOrGet(String userId, Long memberTypeId, BigDecimal amount){
        MemberTransaction transaction = memberTransactionMapper.selectByUserId(userId,memberTypeId,0);
        if (Objects.nonNull(transaction) && transaction.getAmount().compareTo(amount)==0){
            return transaction;
        }
        transaction = new MemberTransaction();
        transaction.setOrderNumber(IdGenerator.nextId());
        transaction.setUserId(userId);
        transaction.setMemberTypeId(memberTypeId);
        transaction.setAmount(amount);
        int result = memberTransactionMapper.insertSelective(transaction);
        Ensure.that(result).isEq(1,"保存充值流水失败");
        return transaction;
    }
}
