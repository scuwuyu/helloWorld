package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.MemberTransaction;
import org.apache.ibatis.annotations.Param;

public interface MemberTransactionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberTransaction record);

    int insertSelective(MemberTransaction record);

    MemberTransaction selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberTransaction record);

    int updateByPrimaryKey(MemberTransaction record);

    MemberTransaction selectByUserId(@Param("userId") String userId, @Param("memberTypeId")Long memberTypeId, @Param("status") int status);
}