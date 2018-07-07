package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.MemberDate;

public interface MemberDateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberDate record);

    int insertSelective(MemberDate record);

    MemberDate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberDate record);

    int updateByPrimaryKey(MemberDate record);

    MemberDate selectByUserId(String userId);
}