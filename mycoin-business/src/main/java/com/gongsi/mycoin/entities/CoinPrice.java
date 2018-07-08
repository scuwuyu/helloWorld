package com.gongsi.mycoin.entities;

import java.math.BigDecimal;
import java.util.Date;

public class CoinPrice {
    private Long id;

    private BigDecimal huobi;

    private BigDecimal okex;

    private BigDecimal percent;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getHuobi() {
        return huobi;
    }

    public void setHuobi(BigDecimal huobi) {
        this.huobi = huobi;
    }

    public BigDecimal getOkex() {
        return okex;
    }

    public void setOkex(BigDecimal okex) {
        this.okex = okex;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}