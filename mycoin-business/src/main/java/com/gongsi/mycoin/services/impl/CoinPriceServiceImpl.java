package com.gongsi.mycoin.services.impl;

import com.gongsi.mycoin.dao.CoinPriceMapper;
import com.gongsi.mycoin.entities.CoinPrice;
import com.gongsi.mycoin.services.CoinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Service
public class CoinPriceServiceImpl implements CoinPriceService {
    @Autowired
    private CoinPriceMapper coinPriceMapper;

    /** 保存*/
    public int save(CoinPrice coinPrice){
        return coinPriceMapper.insertSelective(coinPrice);
    }
}
