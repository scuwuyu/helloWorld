package com.gongsi.mycoin.controllers;

import com.gongsi.mycoin.services.UserService;
import com.gongsi.mycoin.utils.UserUtil;
import com.gongsi.mycoin.vo.BaseVO;
import com.gongsi.mycoin.vo.MineVO;
import com.gongsi.mycoin.vo.UserSessionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 吴宇 on 2018-05-26.
 */
@Controller
@RequestMapping("/mini/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 我的页面
     * @param vo
     * @return
     */
    @RequestMapping(value = "/mine",method = RequestMethod.POST)
    @ResponseBody
    public MineVO add(@RequestBody BaseVO vo){
        UserSessionVO user = UserUtil.getUser(vo.getKey());
        return userService.mine(user.getUserId());
    }
}
