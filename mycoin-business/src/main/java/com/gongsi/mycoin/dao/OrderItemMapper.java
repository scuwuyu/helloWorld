package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.OrderItem;
import com.gongsi.mycoin.vo.OrderItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItemVO> selectByOrderNumber(String orderNumber);

    int batchInsert(@Param("orderItems") List<OrderItem> orderItems);
}