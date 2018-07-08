package com.gongsi.mycoin.services;

import com.gongsi.mycoin.entities.CoinPrice;

/**
 * Created by 吴宇 on 2018-07-08.
 */
public interface CoinPriceService {
    /** 保存*/
    int save(CoinPrice coinPrice);
}
