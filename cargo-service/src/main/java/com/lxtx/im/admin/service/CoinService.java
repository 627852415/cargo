package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.service.request.*;
import com.lxtx.im.admin.service.response.CoinResp;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 币种模块业务接口
 *
 * @author CaiRH
 * @since 2018-08-29
 */
public interface CoinService {

    /**
     * 获取币种列表数据
     *
     * @param coinReq
     * @param session
     * @return
     */
    BaseResult listPage(CoinReq coinReq, HttpSession session);
    BaseResult listPageLegal(CoinReq coinReq, HttpSession session);

    /**
     * 获取币种详情
     *
     * @param coinDetailReq
     * @return
     */
    BaseResult selectOne(CoinDetailReq coinDetailReq);

    /**
     * 保存币种信息
     *
     * @param coinSaveReq
     * @param session
     * @return
     */
    BaseResult save(CoinSaveReq coinSaveReq, HttpSession session);

    /**
     * 删除币种
     *
     * @param coinDeleteReq
     * @return
     */
    BaseResult delete(CoinDeleteReq coinDeleteReq);

    /**
     * 上传币种图标
     *
     * @param file
     * @return
     */
    BaseResult upload(MultipartFile file) throws IOException;

    /**
     * 获取币种手续费
     *
     * @param coinFeeReq
     * @return
     */
    BaseResult obtainCoinFee(CoinFeeReq coinFeeReq);

    /**
     * 获取所有显示的币种
     *
     * @param coinReq
     * @param session
     * @return
     */
    BaseResult listAll(CoinReq coinReq, HttpSession session);

    /**
     * 功能描述: 获取所有显示的币种
     *
     * @return:
     * @author: Czh
     * @date: 2019-06-25 14:46
     */
    List<CoinResp> getAllCoinList();

    List<CoinResp> getAllLegalCoinList();
}
