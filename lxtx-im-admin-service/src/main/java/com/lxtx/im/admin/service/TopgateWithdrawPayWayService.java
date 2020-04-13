package com.lxtx.im.admin.service;

import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.request.DictDeleteReq;
import com.lxtx.im.admin.service.request.DictInfoReq;
import com.lxtx.im.admin.service.request.DictListPageReq;
import com.lxtx.im.admin.service.request.DictSaveReq;
import com.lxtx.im.admin.service.response.DictInfoResp;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public interface TopgateWithdrawPayWayService {

    /**
     * 取得topgate提现每日限额字典
     * @return
     */
    List<DictInfoResp> selectListDict();

    /**
     * topgate页删除字典数据
     *
     * @param req
     */
    void deleteDict(DictDeleteReq req);

    /**
     * 根据ID查询数据放到MODEL中
     *
     * @param req
     * @param model
     */
    void setDictModel(DictInfoReq req, Model model);

    /**
     * topgate新增或更改字典
     *
     * @param req
     * @return
     */
    BaseResult saveOrUpdateDict(DictSaveReq req);

    /**
     * topgate字典列表
     *
     * @param req
     * @return
     */
    BaseResult listPageDict(DictListPageReq req);

    /**
     * topgate开关
     *
     * @param req
     */
    void updateEnableWithdrawPayWay(TopGateWithdrawPaywayOnOrOffReq req);

    /**
     * topgate 新增页Model
     *
     * @param model
     * @param locale
     */
    void add(Model model, Locale locale);

    /**
     * topgate 提币支付分页
     *
     * @param req
     * @return
     */
    BaseResult pageWithdrawPayWay(TopGateWithdrawPaywayPageReq req, Locale locale);

    /**
     * 添加topgate提币支付
     *
     * @param req
     * @return
     */
    BaseResult saveWithdrawPayWay(TopGateWithdrawPaywaySaveReq req);

    /**
     * 删除topgate提币支付
     *
     * @param req
     * @return
     */
    BaseResult removeWithdrawPayWay(TopGateWithdrawPaywayRemoveReq req);

    /**
     * 查找topgate提币支付
     *
     * @param req
     * @return
     */
    void selectOneWithdrawPayWay(TopGateWithdrawPaywayFindOneReq req, Model model, Locale locale);

    /**
     * 上传支付方式logo
     *
     * @param file
     * @return
     */
    BaseResult upload(MultipartFile file) throws IOException;

    /**
     * 获取全部支付方式
     *
     * @return
     */
    BaseResult listAll(Locale locale);
}
