package com.gongsi.mycoin.apis;

import com.gongsi.mycoin.dtos.*;

import javax.validation.constraints.NotNull;

/**
 * dubboService，调用localService服务，目前只公开需要开放出去的接口
 */
public interface UserDubboService {

    /**
     * 更新，以update开头
     *
     * @return
     */
    Boolean updateUser(@NotNull(message = "test_1001") Integer dto);

}
