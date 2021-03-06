package com.gongsi.mycoin.services;

import com.gongsi.mycoin.entities.User;
import com.gongsi.mycoin.vo.MineVO;
import com.gongsi.mycoin.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by 吴宇 on 2018-05-22.
 */
public interface UserService {
    User selectByUserId(String userId);
    /** 我的页面*/
    MineVO mine(String userId);
    /** 查询用户头像*/
    Map<String,UserVO> selectByIds(List<String> userIds);
    /** 如果不存在则创建*/
    User selectByOpenId(String openId);

    /** 更新用户头像信息*/
    void updateByUserId(UserVO vo);
}
