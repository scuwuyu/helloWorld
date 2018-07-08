package com.gongsi.mycoin.task;

import com.alibaba.fastjson.JSON;
import com.gongsi.mycoin.entities.CoinPrice;
import com.gongsi.mycoin.response.KlineResponse;
import com.gongsi.mycoin.services.CoinPriceService;
import com.gongsi.mycoin.services.api.ApiHuoBiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 行情查询
 * Created by 吴宇 on 2018-07-08.
 */
@Slf4j
@Service
public class KlineTask {
    @Autowired
    private ApiHuoBiService apiHuoBiService;

    @Autowired
    private CoinPriceService coinPriceService;

    public void execute(){
        log.info("行情查询开始");
        List<KlineResponse> list = apiHuoBiService.kline("btcusdt","1min","1");

        BigDecimal currentPrice = list.get(0).getClose();

        if (Objects.nonNull(currentPrice)){
            CoinPrice coinPrice = new CoinPrice();
            coinPrice.setHuobi(currentPrice);
            coinPriceService.save(coinPrice);
        }


    }
}
