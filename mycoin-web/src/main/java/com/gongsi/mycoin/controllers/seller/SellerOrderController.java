package com.gongsi.mycoin.controllers.seller;

import com.gongsi.mycoin.core.Pagination;
import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.services.OrderService;
import com.gongsi.mycoin.utils.UserUtil;
import com.gongsi.mycoin.vo.OrderVO;
import com.gongsi.mycoin.vo.page.OrderPageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * b端订单管理
 * Created by 吴宇 on 2018-05-26.
 */
@Slf4j
@Controller
@RequestMapping("/mini/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /** 活动订单 列表*/
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Pagination<OrderVO> list(@RequestBody OrderPageVO vo){
        Ensure.that(vo.getActivityId()).isNotNull("活动id不能为空");
        return orderService.activityOrderList(vo, UserUtil.getUser(vo.getKey()));
    }

}
