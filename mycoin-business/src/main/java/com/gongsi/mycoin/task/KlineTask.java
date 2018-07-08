package com.gongsi.mycoin.task;

import com.alibaba.fastjson.JSON;
import com.gongsi.mycoin.response.KlineResponse;
import com.gongsi.mycoin.services.api.ApiHuoBiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 行情查询
 * Created by 吴宇 on 2018-07-08.
 */
@Slf4j
@Service
public class KlineTask {
    @Autowired
    private ApiHuoBiService apiHuoBiService;

    public void execute(){
        log.info("行情查询开始");
        List<KlineResponse> response = apiHuoBiService.kline("btcusdt","1min","1");

        log.info("response={}", JSON.toJSONString(response));
    }
}
