package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.CoinPrice;

public interface CoinPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CoinPrice record);

    int insertSelective(CoinPrice record);

    CoinPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoinPrice record);

    int updateByPrimaryKey(CoinPrice record);
}