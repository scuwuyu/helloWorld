package com.gongsi.mycoin.services.api;

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
    <T> T kline(String symbol, String period, String size,Class<T> clazz);
}
