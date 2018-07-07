package com.gongsi.mycoin.services.impl;

import com.alibaba.fastjson.JSON;
import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.core.utils.BeanMapper;
import com.gongsi.mycoin.dao.ActivityMapper;
import com.gongsi.mycoin.entities.Activity;
import com.gongsi.mycoin.services.ActivityService;
import com.gongsi.mycoin.services.UserService;
import com.gongsi.mycoin.vo.ActivityVO;
import com.gongsi.mycoin.vo.UserSessionVO;
import com.gongsi.mycoin.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by 吴宇 on 2018-05-23.
 */
@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserService userService;
    /** 新增活动*/
    public Integer add(ActivityVO activityVO, UserSessionVO sessionVO){
        Activity activity = BeanMapper.map(activityVO,Activity.class);
        activity.setUserId(sessionVO.getUserId());
        return activityMapper.insert(activity);
    }

    /** 编辑活动*/
    public void edit(ActivityVO activityVO, UserSessionVO sessionVO){
        Activity record = activityMapper.selectByPrimaryKey(activityVO.getId());
        Ensure.that(record).isNotNull("对应的活动不存在");
        Ensure.that(record.getUserId().equals(sessionVO.getUserId())).isTrue("对应的活动不存在");
        Activity activity = BeanMapper.map(activityVO,Activity.class);
        int result = activityMapper.updateByPrimaryKeySelective(activity);
        Ensure.that(result).isGt(0,"更新失败");
    }

    /** 查询活动次数 */
    public int countByUserId(String userId){
        return activityMapper.countByUserId(userId);
    }

    /** 状态查询活动列表 */
    public List<ActivityVO> selectList(Integer status, UserSessionVO user){
        return activityMapper.selectList(status,user.getUserId());
    }

    /** c端 b端用户查看活动*/
    public ActivityVO detail(Long id, UserSessionVO user){
        log.info("查看活动详情user={},id={}", JSON.toJSONString(user),id);
        Activity activity = activityMapper.selectByPrimaryKey(id);
        ActivityVO vo = BeanMapper.map(activity,ActivityVO.class);
        Map<String,UserVO> map = userService.selectByIds(Collections.singletonList(user.getUserId()));

        vo.setUserInfo(map.get(user.getUserId()));
        return vo;
    }

    public Activity selectById(Long id){
        return activityMapper.selectByPrimaryKey(id);
    }
}
