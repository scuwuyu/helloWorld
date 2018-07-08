package com.gongsi.mycoin.services.impl;

import com.gongsi.mycoin.dao.CoinAccountMapper;
import com.gongsi.mycoin.entities.CoinAccount;
import com.gongsi.mycoin.enums.PlatformEnum;
import com.gongsi.mycoin.services.CoinAccountservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 吴宇 on 2018-07-08.
 */
@Service
public class CoinAccountserviceImpl implements CoinAccountservice {

    @Autowired
    private CoinAccountMapper coinAccountMapper;

    /** 查询账号*/
    public CoinAccount selectByPlatform(PlatformEnum platform){

        return coinAccountMapper.selectByPlatformId(platform.getId());
    }
}
