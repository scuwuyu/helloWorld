package com.gongsi.mycoin.response.bithumb;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Data
public class Kline {

    private BigDecimal opening_price;
    private BigDecimal closing_price;

    private BigDecimal min_price;
    private BigDecimal max_price;

    private BigDecimal buy_price;
    private BigDecimal sell_price;


}
