package com.gongsi.mycoin.services;

import com.gongsi.mycoin.entities.OrderItem;
import com.gongsi.mycoin.vo.OrderItemVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
public interface OrderItemService {
    /** 查询订单商品列表 */
    List<OrderItemVO> selectByOrderNumber(String orderNumber);

    int batchInsert(List<OrderItem> orderItems);
}
