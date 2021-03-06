package com.gongsi.mycoin.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gongsi.mycoin.core.result.ResultUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by wuyu on 2018/5/28.
 */
public class MiniMessageConverter extends AbstractHttpMessageConverter<Object> {

    public MiniMessageConverter() {
        setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json",  StandardCharsets.UTF_8),
                new MediaType("application", "*+json",  StandardCharsets.UTF_8)));
    }

    private SerializerFeature[] features;
    /** 标准字符集 */
    private Charset CHARSET = StandardCharsets.UTF_8;

    @Override
    protected boolean supports(Class clazz) {
        return true;
    }

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String body = IOUtils.toString(inputMessage.getBody(), "UTF-8");
        MiniContext miniContext = MiniContext.getContext();
        miniContext.setPostBody(body);

        return JSON.parseObject(body,clazz);
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Object result = ResultUtils.getSuccessResult(obj);
        String jsonStr = convertToJsonStr(result);

        MiniContext miniContext = MiniContext.getContext();
        miniContext.setReturnBody(jsonStr);

        OutputStream out = outputMessage.getBody();
        out.write(jsonStr.getBytes(CHARSET));
    }

    private String convertToJsonStr(Object obj) {
        if (Objects.isNull(obj)) {
            return StringUtils.EMPTY;
        } else if (obj instanceof String) {
            return  (String) obj;
        } else {
            return Objects.isNull(features)?JSON.toJSONString(obj):JSON.toJSONString(obj, features);
        }
    }

    public void setFeatures(SerializerFeature[] features) {
        this.features = features;
    }
}
