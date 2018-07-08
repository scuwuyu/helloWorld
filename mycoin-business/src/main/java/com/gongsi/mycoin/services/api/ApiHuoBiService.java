package com.gongsi.mycoin.services.api;

import com.alibaba.fastjson.TypeReference;
import com.gongsi.mycoin.constants.HuoBiConstants;
import com.gongsi.mycoin.core.exception.BusinessException;
import com.gongsi.mycoin.enums.RequestMethod;
import com.gongsi.mycoin.response.BaseResponse;
import com.gongsi.mycoin.response.KlineResponse;
import com.gongsi.mycoin.services.http.base.BaseService;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Service
public class ApiHuoBiService extends AbstractApiService {
    /**
     * 获取K线数据
     */
    @Override
    public List<KlineResponse> kline(String symbol, String period, String size) {
        Map<String,String> map = new HashMap<>();
        map.put("symbol", symbol);
        map.put("period", period);
        map.put("size", size);
        BaseResponse<List<KlineResponse>> response = get(HuoBiConstants.MARKET_HISTORY_KLINE,map,new TypeReference<BaseResponse<List<KlineResponse>>>(){});

        return response.getData();
    }



    /** 具体的请求方法*/
    @Override
    protected <T extends BaseResponse> T call(RequestMethod method, String uri, Object object, Map<String, String> params, TypeReference<T> ref) {
        createSignature(HuoBiConstants.appKey,HuoBiConstants.appSecretKey,method,uri,params);
        switch (method){
            case GET:
                return BaseService.exeGetJson(HuoBiConstants.API_URL+uri+"?"+toQueryString(params),ref);
            case POST:
                return BaseService.exePostJson(HuoBiConstants.API_URL+uri+"?"+toQueryString(params),object,ref);
            default:
                throw new BusinessException("this cannot happen");
        }
    }


    /**
     * 创建一个有效的签名。该方法为客户端调用，将在传入的params中添加AccessKeyId、Timestamp、SignatureVersion、SignatureMethod、Signature参数。
     *
     * @param appKey       AppKeyId.
     * @param appSecretKey AppKeySecret.
     * @param method       请求方法，"GET"或"POST"
     * param host         请求域名，例如"be.huobi.com"
     * @param uri          请求路径，注意不含?以及后的参数，例如"/v1/api/info"
     * @param params       原始请求参数，以Key-Value存储，注意Value不要编码
     */
    private void createSignature(String appKey, String appSecretKey, RequestMethod method,
                                String uri, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(1024);
        sb.append(method.name()).append('\n')
                .append(HuoBiConstants.API_HOST).append('\n')
                .append(uri).append('\n'); // /path
        params.remove("Signature");
        params.put("AccessKeyId", appKey);
        params.put("SignatureVersion", "2");
        params.put("SignatureMethod", "HmacSHA256");
        params.put("Timestamp", gmtNow());
        // build signature:
        SortedMap<String, String> map = new TreeMap<>(params);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key).append('=').append(urlEncode(value)).append('&');
        }
        /** remove last '&':*/
        sb.deleteCharAt(sb.length() - 1);
        // sign:
        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secKey =
                    new SecretKeySpec(appSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key: " + e.getMessage());
        }
        String payload = sb.toString();
        byte[] hash = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        String actualSign = Base64.getEncoder().encodeToString(hash);
        params.put("Signature", actualSign);
    }


    private String gmtNow() {
        return Instant.ofEpochSecond(Instant.now().getEpochSecond()).atZone(HuoBiConstants.ZONE_GMT).format(HuoBiConstants.DT_FORMAT);
    }
}
