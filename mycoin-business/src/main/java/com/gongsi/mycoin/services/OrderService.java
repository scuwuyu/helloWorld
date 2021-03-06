package com.gongsi.mycoin.services;

import com.gongsi.mycoin.core.Pagination;
import com.gongsi.mycoin.vo.OrderVO;
import com.gongsi.mycoin.vo.UserSessionVO;
import com.gongsi.mycoin.vo.page.OrderPageVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
public interface OrderService {
    /** 统计订单数量*/
    int countByUserId(String userId);
    /** 查询订单列表 */
    List<OrderVO> selectList(OrderVO vo, UserSessionVO user);

    /** 订单详情 */
    OrderVO detail(String orderNumber, UserSessionVO user);

    /** 下单，返回订单号 */
    String order(OrderVO vo, UserSessionVO sessionVO);


    /** ------------------以下为b端操作------------------------*/

    /** 活动订单列表*/
    Pagination<OrderVO> activityOrderList(OrderPageVO vo, UserSessionVO user);

}
