package com.gongsi.mycoin.services.http.base;

import com.alibaba.fastjson.JSON;
import com.gongsi.mycoin.core.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Created by wuyu on 2017/4/13.
 */
public abstract class BaseService {

    protected static final Logger logger = LoggerFactory.getLogger(BaseService.class);

    /**
     * Method = post
     * Content-Type = application/json
     * @param  requestUrl  请求url
     * @param  entity      请求参数
     * @param  clazz       返回结果Entity class
     * @return T
     * */
    public static <T>  T exePostJson(String requestUrl, Object entity, final Class<T> clazz) {
        logger.info("[request]:" + requestUrl + ":" +  StringUtils.substring(JSON.toJSONString(entity),0,2000));
        return HttpClientUtils.getInstance().postJson(requestUrl, JSON.toJSONString(entity),
                (response, charset) -> {
            HttpEntity entity1 = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            HttpEntity httpEntity = response.getEntity();
            if (statusCode != 200) {
                EntityUtils.consume(httpEntity);
                throw new BusinessException("相应状态码异常");
            }
            String toString = IOUtils.toString(entity1.getContent(), charset);
            logger.info("[response]:" + StringUtils.substring(toString,0,2000));
            return JSON.parseObject(toString, clazz);
        });
    }


    /**
     * method get
     * @param  requestUrl  请求url
     * @param  clazz       返回结果Entity class
     * @param <T>
     * @return
     * @throws URISyntaxException
     * @throws MalformedURLException
     */
    public static <T> T exeGetJson(String requestUrl,final Class<T> clazz){
        logger.info("[request]:" + requestUrl);
        return HttpClientUtils.getInstance().get(requestUrl, (response, charset) -> {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            int statusCode = statusLine.getStatusCode();
            HttpEntity httpEntity = response.getEntity();
            if (statusCode != 200) {
                EntityUtils.consume(httpEntity);
                throw new BusinessException("状态码不正确");
            }
            String toString = IOUtils.toString(entity.getContent(), charset);
            logger.info("[response]:" + toString);
            return JSON.parseObject(toString, clazz);
        });
    }


}
