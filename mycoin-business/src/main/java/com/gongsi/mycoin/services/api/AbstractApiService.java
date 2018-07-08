package com.gongsi.mycoin.services.api;

import com.gongsi.mycoin.enums.RequestMethod;
import com.gongsi.mycoin.services.CoinAccountservice;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by 吴宇 on 2018-07-07.
 */
public abstract class AbstractApiService implements ApiService{

    @Autowired
    protected CoinAccountservice coinAccountservice;

    /** get请求 */
    protected <T> T get(String uri, Map<String, String> params, Class<T> clazz){
        if (Objects.isNull(params)){
            params = new HashMap<>();
        }
        return call(RequestMethod.GET,uri,null,params,clazz);
    }

    /** post请求 */
    protected <T> T post(String uri, Object object, Class<T> clazz) {
        return call(RequestMethod.POST, uri, object, new HashMap<>(), clazz);
    }

    /** 具体的请求方法，子类实现*/
    protected abstract <T> T call(RequestMethod method, String uri, Object object, Map<String, String> params, Class<T> clazz);

    /**  Encode as "a=1&b=%20&c=&d=AAA" */
    protected String toQueryString(Map<String, String> params) {
        return String.join("&", params.entrySet().stream().map((entry) ->
                entry.getKey() + "=" + urlEncode(entry.getValue())).collect(Collectors.toList()));
    }

    /**
     * 使用标准URL Encode编码。注意和JDK默认的不同，空格被编码为%20而不是+。
     *
     * @param s String字符串
     * @return URL编码后的字符串
     */
    protected String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding not supported!");
        }
    }
}
