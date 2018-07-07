package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.MemberType;

import java.util.List;

public interface MemberTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberType record);

    int insertSelective(MemberType record);

    MemberType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberType record);

    int updateByPrimaryKey(MemberType record);

    List<MemberType> selectList();
}