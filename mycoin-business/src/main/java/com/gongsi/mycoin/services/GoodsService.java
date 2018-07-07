package com.gongsi.mycoin.services;

import com.gongsi.mycoin.core.Pagination;
import com.gongsi.mycoin.vo.GoodsVO;
import com.gongsi.mycoin.vo.UserSessionVO;
import com.gongsi.mycoin.vo.page.GoodsPageVO;

import java.util.List;

/**
 * Created by 吴宇 on 2018-05-26.
 */
public interface GoodsService {
    /** 新增商品 */
    void add(GoodsVO vo, UserSessionVO user);

    /** 商品分页查询*/
    Pagination<GoodsVO> selectList(GoodsPageVO vo, UserSessionVO user);

    void delete(List<Long> goodsIds, UserSessionVO user);

    List<GoodsVO> selectByIds(List<Long> goodsIds);
}
