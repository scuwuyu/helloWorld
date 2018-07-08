package com.gongsi.mycoin.services;

import com.gongsi.mycoin.entities.CoinAccount;
import com.gongsi.mycoin.enums.PlatformEnum;

/**
 * Created by 吴宇 on 2018-07-08.
 */
public interface CoinAccountservice {
    /** 查询账号*/
    CoinAccount selectByPlatform(PlatformEnum platform);
}
