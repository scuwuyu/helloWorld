package com.gongsi.mycoin.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Data
public class GoodsVO extends BaseVO {
    private Long id;
    private String name;
    private String picture;

    private String desc;
    private BigDecimal price;
    /** 需要删除的商品id*/
    private List<Long> goodsIds;
}
