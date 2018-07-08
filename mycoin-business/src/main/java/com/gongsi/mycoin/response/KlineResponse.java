package com.gongsi.mycoin.response;

import lombok.Data;

/**
 * Created by 吴宇 on 2018-07-07.
 */
@Data
public class KlineResponse {
    private int id;
    private double amount;
    private int count;
    private double open;
    private int close;
    private int low;
    private int high;
    private double vol;

}
