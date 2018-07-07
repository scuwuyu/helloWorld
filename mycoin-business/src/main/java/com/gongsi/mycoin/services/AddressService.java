package com.gongsi.mycoin.services;

import com.gongsi.mycoin.entities.Address;
import com.gongsi.mycoin.vo.AddressVO;
import com.gongsi.mycoin.vo.UserSessionVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
public interface AddressService {
    /** 新增收货地址*/
    void add(AddressVO vo, UserSessionVO user);

    /**
     * 查询收货地址列表
     * @param user
     * @return
     */
    List<AddressVO> list(UserSessionVO user);

    /** 设置默认收货地址*/
    void setDef(Long id, UserSessionVO user);

    /** 检验并返回 收货地址*/
    Address selectById(Long id, String userId);
}
