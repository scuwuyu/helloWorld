package com.gongsi.mycoin.services;

import com.gongsi.mycoin.entities.Activity;
import com.gongsi.mycoin.vo.ActivityVO;
import com.gongsi.mycoin.vo.UserSessionVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-23.
 */
public interface ActivityService {
    /** 新增活动*/
    Integer add(ActivityVO activityVO, UserSessionVO sessionVO);

    /** 编辑活动*/
    void edit(ActivityVO activityVO, UserSessionVO sessionVO);
    /** 查询活动次数 */
    int countByUserId(String userId);
    /** 状态查询活动列表 */
    List<ActivityVO> selectList(Integer status, UserSessionVO user);

    ActivityVO detail(Long id, UserSessionVO user);

    Activity selectById(Long id);
}
