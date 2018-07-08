package com.gongsi.mycoin.response.okex;

import lombok.Data;

import java.util.Date;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Data
public class KlineResponse {
    private Date date;

    private Ticker ticker;
}
