package com.gongsi.mycoin.services.impl;

import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.core.utils.BeanMapper;
import com.gongsi.mycoin.dao.AddressMapper;
import com.gongsi.mycoin.entities.Address;
import com.gongsi.mycoin.enums.YesOrNO;
import com.gongsi.mycoin.services.AddressService;
import com.gongsi.mycoin.vo.AddressVO;
import com.gongsi.mycoin.vo.UserSessionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    /** 新增收货地址*/
    @Transactional
    public void add(AddressVO vo, UserSessionVO user){
        /** 新增默认收货地址，需要清除原来的收货地址*/
        if (YesOrNO.YES.getCode().equals(vo.getDefaultAddress())){
            addressMapper.deleteDefaultAddress(user.getUserId());
        }
        Address address = BeanMapper.map(vo, Address.class);
        address.setUserId(user.getUserId());

        addressMapper.insertSelective(address);
    }

    /**
     * 查询收货地址列表
     * @param user
     * @return
     */
    public List<AddressVO> list(UserSessionVO user){
        return addressMapper.selectList(user.getUserId());
    }

    /** 设置默认收货地址*/
    @Transactional
    public void setDef(Long id, UserSessionVO user){
        Address address = addressMapper.selectByPrimaryKey(id);
        Ensure.that(Objects.nonNull(address)&&address.getUserId().equals(user.getUserId()))
                .isTrue("收货地址不存在");
        if (YesOrNO.YES.getCode().equals(address.getDefaultAddress())){
            /** 已经是默认地址*/
            return;
        }
        addressMapper.deleteDefaultAddress(user.getUserId());

        addressMapper.setDefById(id);
    }

    /** 检验并返回 收货地址*/
    public Address selectById(Long id, String userId){
        Address address = addressMapper.selectByPrimaryKey(id);
        Ensure.that(address.getUserId().equals(userId)).isTrue("收货地址不存在");
        return address;
    }
}
