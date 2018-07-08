package com.gongsi.mycoin.response.bithumb;

import lombok.Data;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Data
public class KlineResponse {
    private String status;

    private Kline data;

}
