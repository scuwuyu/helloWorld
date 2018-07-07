package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.Address;
import com.gongsi.mycoin.vo.AddressVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);
    /** 删除默认地址*/
    int deleteDefaultAddress(@Param("userId") String  userId);

    List<AddressVO> selectList(@Param("userId") String userId);

    int setDefById(Long id);
}