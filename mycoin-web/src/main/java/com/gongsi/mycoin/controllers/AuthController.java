package com.gongsi.mycoin.controllers;

import com.alibaba.fastjson.JSON;
import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.core.utils.BeanMapper;
import com.gongsi.mycoin.core.utils.IdGenerator;
import com.gongsi.mycoin.entities.User;
import com.gongsi.mycoin.services.UserService;
import com.gongsi.mycoin.services.http.WechatAuthResult;
import com.gongsi.mycoin.services.http.WechatAuthService;
import com.gongsi.mycoin.shiro.MyToken;
import com.gongsi.mycoin.utils.UserUtil;
import com.gongsi.mycoin.vo.UserSessionVO;
import com.gongsi.mycoin.vo.UserVO;
import com.gongsi.mycoin.vo.auth.AuthVO;
import com.gongsi.mycoin.vo.response.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * Created by 吴宇 on 2018-06-04.
 */
@Slf4j
@Controller
@RequestMapping("/mini/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    /**
     * openId 注册用户信息
     * @param vo
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public AuthResponse login(@RequestBody AuthVO vo){
        WechatAuthResult wechatAuthResult = WechatAuthService.authByJSCode(vo);
        User user = userService.selectByOpenId(wechatAuthResult.getOpenid());

        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new MyToken(user.getUserId()));
        } catch (Exception e) {
            log.error("用户登录错误:",e.getMessage(),e);
        }
        AuthResponse response = new AuthResponse(IdGenerator.nextId(),StringUtils.isEmpty(user.getAvatarUrl()));
        log.info("response={},user={}",JSON.toJSONString(response), JSON.toJSONString(user));
        UserUtil.saveUser(response.getKey(), BeanMapper.map(user,UserSessionVO.class));
        return response;
    }

    /**
     * 保存用户信息
     * @param vo
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody UserVO vo){
        Ensure.that(Objects.nonNull(vo.getNickName())&&Objects.nonNull(vo.getAvatarUrl())).isTrue("昵称和头像不能为空");
        UserSessionVO userSessionVO = UserUtil.getUser(vo.getKey());
        vo.setUserId(userSessionVO.getUserId());

        userService.updateByUserId(vo);
        return "ok";
    }
}
