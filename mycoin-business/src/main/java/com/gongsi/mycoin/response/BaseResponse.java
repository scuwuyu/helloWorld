package com.gongsi.mycoin.response;

import lombok.Data;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Data
public class BaseResponse<T> {
    private String status;
    private String ch;
    private String ts;
    public String errCode;
    public String errMsg;
    private T data;

}
