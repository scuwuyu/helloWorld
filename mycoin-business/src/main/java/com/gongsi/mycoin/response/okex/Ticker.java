package com.gongsi.mycoin.response.okex;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Data
public class Ticker {

    /** 买一价*/
    private BigDecimal buy;
    /** 卖一价*/
    private BigDecimal sell;
    /** 最高价 */
    private BigDecimal high;
    private BigDecimal low;
    /** 最新 */
    private BigDecimal last;

    /** 成交额*/
    private BigDecimal vol;
}
