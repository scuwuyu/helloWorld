package com.gongsi.mycoin.services.api;

import com.gongsi.mycoin.constants.BithumbConstants;
import com.gongsi.mycoin.core.exception.BusinessException;
import com.gongsi.mycoin.enums.RequestMethod;
import com.gongsi.mycoin.services.http.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Service
public class ApiBithumbService extends AbstractApiService {

    /**
     * 获取K线数据
     */
    @Override
    public <T> T kline(String symbol, String period, String size, Class<T> clazz) {
        return get(BithumbConstants.TICKER_URL.replace("{currency}",symbol),null,clazz);
    }


    /** 具体的请求方法*/
    @Override
    protected <T> T call(RequestMethod method, String uri, Object object, Map<String, String> params, Class<T> clazz) {
        switch (method){
            case GET:
                return BaseService.exeGetJson(BithumbConstants.API_URL+uri+"?"+toQueryString(params),clazz);
            case POST:
                return BaseService.exePostJson(BithumbConstants.API_URL+uri+"?"+toQueryString(params),object,clazz);
            default:
                throw new BusinessException("bithumb this cannot happen");
        }
    }
}
