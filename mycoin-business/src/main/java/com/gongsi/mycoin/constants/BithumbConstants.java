package com.gongsi.mycoin.constants;

/**
 * Created by 吴宇 on 2018-07-08.
 */
public class BithumbConstants {

    public static final String  API_URL = "https://api.bithumb.com";

    /**
     * 现货行情URL
     * {currency} = BTC, ETH, DASH, LTC, ETC, XRP, BCH, XMR, ZEC, QTUM, BTG, EOS, ICX, VEN, TRX, ELF,
     * MITH, MCO, OMG, KNC, GNT, HSR, ZIL, ETHOS, PAY, WAX, POWR, LRC, GTO, STEEM, STRAT, ZRX, REP,
     * AE, XEM, SNT, ADA (基本值: BTC), ALL(全部)
     */
    public static final String TICKER_URL = "/public/ticker/{currency}";

}
