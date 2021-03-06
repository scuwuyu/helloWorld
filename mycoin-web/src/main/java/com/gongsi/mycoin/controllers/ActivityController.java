package com.gongsi.mycoin.controllers;

import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.services.ActivityService;
import com.gongsi.mycoin.utils.UserUtil;
import com.gongsi.mycoin.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-23.
 */
@Controller
@RequestMapping("/mini/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody ActivityVO vo){
        Ensure.that(vo.getId()).isNull("id只能为空");
        vo.check();
        Integer result = activityService.add(vo, UserUtil.getUser(vo.getKey()));
        return "ok";
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public String edit(@RequestBody ActivityVO vo){
        vo.check();
        activityService.edit(vo, UserUtil.getUser(vo.getKey()));
        return "ok";
    }

    /** c端 b端用户查看活动*/
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    @ResponseBody
    public ActivityVO detail(@RequestBody ActivityVO vo){
        Ensure.that(vo.getId()).isNotNull("活动id不能为空");
        return activityService.detail(vo.getId(), UserUtil.getUser(vo.getKey()));
    }



    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<ActivityVO> list(@RequestBody ActivityVO vo){
        Ensure.that(vo.getStatus()).isNotNull("活动状态不能为空");
        return activityService.selectList(vo.getStatus(), UserUtil.getUser(vo.getKey()));
    }
}
