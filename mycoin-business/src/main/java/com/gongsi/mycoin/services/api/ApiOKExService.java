package com.gongsi.mycoin.services.api;

import com.gongsi.mycoin.constants.OKExConstants;
import com.gongsi.mycoin.enums.RequestMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * OKEx 平台
 * Created by 吴宇 on 2018-07-08.
 */
@Slf4j
@Service
public class ApiOKExService extends AbstractApiService {

    /**
     * 获取K线数据
     */
    @Override
    public <T> T kline(String symbol, String period, String size,Class<T> clazz) {
        Map<String,String> params = new HashMap<>();
        params.put("symbol",symbol);

        return get(OKExConstants.TICKER_URL,params,clazz);
    }


    /** 具体的请求方法*/
    @Override
    protected <T> T call(RequestMethod method, String uri, Object object, Map<String, String> params, Class<T> clazz) {
        return null;
    }
}
