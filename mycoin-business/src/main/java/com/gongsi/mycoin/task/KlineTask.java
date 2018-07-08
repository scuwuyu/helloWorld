package com.gongsi.mycoin.task;

import com.gongsi.mycoin.constants.Constants;
import com.gongsi.mycoin.core.ensure.Ensure;
import com.gongsi.mycoin.entities.CoinPrice;
import com.gongsi.mycoin.response.huobi.Kline;
import com.gongsi.mycoin.response.huobi.KlineResponse;
import com.gongsi.mycoin.services.CoinPriceService;
import com.gongsi.mycoin.services.api.ApiBithumbService;
import com.gongsi.mycoin.services.api.ApiHuoBiService;
import com.gongsi.mycoin.services.api.ApiOKExService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private ApiBithumbService apiBithumbService;

    @Autowired
    private CoinPriceService coinPriceService;

    public void execute(){
        log.info("行情查询开始");
        List<BigDecimal> list = new ArrayList<>();
        CoinPrice coinPrice = new CoinPrice();

        queryHuoBi(list,coinPrice);
        queryOkex(list,coinPrice);
        queryBithumb(list,coinPrice);

        list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());

        /** 是否存在多个平台价格*/
        if (list.size()>=2){
            /** 计算最大价格 percent */
            Iterator<BigDecimal> iterator = list.iterator();
            BigDecimal min = iterator.next();
            BigDecimal max = min;

            while (iterator.hasNext()){
                BigDecimal next = iterator.next();
                if (next.compareTo(min)<0){
                    min = next;
                    continue;
                }
                if (next.compareTo(max)>0){
                    max = next;
                }
            }

            BigDecimal percent = max.subtract(min).multiply(new BigDecimal(100)).divide(max,2,BigDecimal.ROUND_HALF_UP);
            coinPrice.setPercent(percent);
        }

        coinPriceService.save(coinPrice);


    }

    /** 查询火币的行情 */
    private void queryHuoBi(List<BigDecimal> list,CoinPrice coinPrice){
        KlineResponse response = apiHuoBiService.kline("btcusdt","1min","1", KlineResponse.class);
        Ensure.that(Constants.OK.equalsIgnoreCase(response.getStatus())).isTrue("火币网查询行情失败");
        BigDecimal huoBiPrice = response.getData().get(0).getClose();
        list.add(huoBiPrice);
        coinPrice.setHuobi(huoBiPrice);
    }

    /** 查询okex的行情 */
    private void queryOkex(List<BigDecimal> list,CoinPrice coinPrice){
        com.gongsi.mycoin.response.okex.KlineResponse okexResponse = apiOKExService.kline("btc_usd","","",com.gongsi.mycoin.response.okex.KlineResponse.class);
        BigDecimal okexPrice = okexResponse.getTicker().getLast();
        list.add(okexPrice);
        coinPrice.setOkex(okexPrice);
    }

    /** 查询bithumb行情 */
    private void queryBithumb(List<BigDecimal> list,CoinPrice coinPrice){
        com.gongsi.mycoin.response.bithumb.KlineResponse okexResponse = apiBithumbService.kline("BTC","","",com.gongsi.mycoin.response.bithumb.KlineResponse.class);
        // TODO: 2018-07-08 7495000  韩元赚美元
        BigDecimal price = okexResponse.getData().getClosing_price();
        list.add(price);
        coinPrice.setBithumb(price);
    }
}
