package com.gongsi.mycoin.response.huobi;

import lombok.Data;

import java.util.List;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Data
public class KlineResponse {
    private String status;
    private String ch;
    private String ts;
    public String errCode;
    public String errMsg;
    private List<Kline> data;

}
