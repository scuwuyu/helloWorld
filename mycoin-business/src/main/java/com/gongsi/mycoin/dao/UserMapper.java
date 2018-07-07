package com.gongsi.mycoin.dao;

import com.gongsi.mycoin.entities.User;
import com.gongsi.mycoin.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByUserIdSelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserId(String userId);

    List<UserVO> selectByIds(@Param("userIds") List<String> userIds);

    User selectByOpenId(String openId);
}