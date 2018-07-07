package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.Transaction;

public interface TransactionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);
}