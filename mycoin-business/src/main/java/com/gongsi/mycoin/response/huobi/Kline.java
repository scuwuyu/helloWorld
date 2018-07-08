package com.gongsi.mycoin.response.huobi;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 吴宇 on 2018-07-07.
 */
@Data
public class Kline {
    private int id;
    /** 成交量*/
    private BigDecimal amount;
    /** 成交笔数*/
    private int count;
    /** 成交额*/
    private BigDecimal vol;

    private BigDecimal open;
    private BigDecimal close;
    private BigDecimal low;
    private BigDecimal high;

}
