package com.gongsi.mycoin.core;

import com.gongsi.mycoin.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 吴宇 on 2018-05-29.
 */
@Slf4j
public class MiniInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        MiniContext context = MiniContext.getContext();
        if (!context.isInited()){
            context.init(httpServletRequest);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        MiniContext miniContext = MiniContext.getContext();

        if (miniContext.isInited()){
            log.info(Constants.LOG_STRING,miniContext.getRequestUrl(),miniContext.getPostBody(),miniContext.getReturnBody());
        }

        MiniContext.remove();
    }
}
