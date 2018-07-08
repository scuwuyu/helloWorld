package com.gongsi.mycoin.task;

import com.gongsi.mycoin.constants.Constants;
import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.entities.CoinPrice;
import com.gongsi.mycoin.response.huobi.Kline;
import com.gongsi.mycoin.response.huobi.KlineResponse;
import com.gongsi.mycoin.services.CoinPriceService;
import com.gongsi.mycoin.services.api.ApiHuoBiService;
import com.gongsi.mycoin.services.api.ApiOKExService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private ApiOKExService apiOKExService;

    @Autowired
    private CoinPriceService coinPriceService;

    public void execute(){
        log.info("行情查询开始");
        List<BigDecimal> list = new ArrayList<>();

        KlineResponse response = apiHuoBiService.kline("btcusdt","1min","1", KlineResponse.class);
        Ensure.that(Constants.OK.equalsIgnoreCase(response.getStatus())).isTrue("火币网查询行情失败");
        BigDecimal huoBiPrice = response.getData().get(0).getClose();
        list.add(huoBiPrice);

        com.gongsi.mycoin.response.okex.KlineResponse okexResponse = apiOKExService.kline("btc_usd","","",com.gongsi.mycoin.response.okex.KlineResponse.class);
        BigDecimal okexPrice = okexResponse.getTicker().getLast();
        list.add(okexPrice);


        CoinPrice coinPrice = new CoinPrice();
        coinPrice.setHuobi(huoBiPrice);
        coinPriceService.save(coinPrice);


    }
}
