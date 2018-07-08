package com.gongsi.mycoin.enums;

/**
 * 所有交易平台枚举
 * Created by 吴宇 on 2018-07-07.
 */

public enum  PlatformEnum {
    HUOBI(1,"火币网");

    PlatformEnum(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    private Integer id;

    private String desc;

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
}
