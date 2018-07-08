package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.CoinAccount;

public interface CoinAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoinAccount record);

    int insertSelective(CoinAccount record);

    CoinAccount selectByPrimaryKey(Long id);

    CoinAccount selectByPlatformId(Integer platformId);

    int updateByPrimaryKeySelective(CoinAccount record);

    int updateByPrimaryKey(CoinAccount record);
}