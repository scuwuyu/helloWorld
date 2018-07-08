package com.gongsi.mycoin.controllers;

import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.vo.ActivityVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 吴宇 on 2018-05-23.
 */
@Controller
@RequestMapping("/mycoin/activity")
public class ActivityController {


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody ActivityVO vo){
        Ensure.that(vo.getId()).isNull("id只能为空");
        vo.check();
        return "ok";
    }
}
