package com.gongsi.mycoin.constants;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by 吴宇 on 2018-07-07.
 */
public class HuoBiConstants {
    /**
     * public static final String  API_HOST = "api.huobi.pro";
     *
     * 一级域名huobi.pro和huobipro.com都可以，一个不好用可切换另一个
     */
    public static final String  API_HOST = "api.hadax.com";
    public static final String  API_URL = "https://"+API_HOST;

    /** 行情查询 */
    public static final String  MARKET_HISTORY_KLINE = "/market/history/kline";

    public static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    public static final ZoneId ZONE_GMT = ZoneId.of("Z");

}
