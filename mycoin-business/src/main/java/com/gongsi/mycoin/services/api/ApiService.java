package com.gongsi.mycoin.services.api;

import com.gongsi.mycoin.response.KlineResponse;

import java.util.List;

/**
 * Created by 吴宇 on 2018-07-07.
 */
public interface ApiService {
    /**
     * 获取K线数据
     * @param symbol
     * @param period
     * @param size
     * @return
     */
    List<KlineResponse> kline(String symbol, String period, String size);
}
