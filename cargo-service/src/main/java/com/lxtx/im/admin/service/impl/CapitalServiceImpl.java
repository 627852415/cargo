package com.lxtx.im.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.lxtx.framework.common.base.BaseResult;
import com.lxtx.framework.common.utils.DateUtils;
import com.lxtx.im.admin.feign.feign.CapitalFeign;
import com.lxtx.im.admin.feign.feign.UserFeign;
import com.lxtx.im.admin.feign.feign.WalletUserCoinFeign;
import com.lxtx.im.admin.feign.feign.WalletUserFeign;
import com.lxtx.im.admin.feign.request.*;
import com.lxtx.im.admin.service.CapitalService;
import com.lxtx.im.admin.service.enums.EnumCapitalOperationRefType;
import com.lxtx.im.admin.service.enums.EnumGameType;
import com.lxtx.im.admin.service.enums.EnumWithdrawApplyState;
import com.lxtx.im.admin.service.exception.LxtxBizException;
import com.lxtx.im.admin.service.request.CapitalDetailReq;
import com.lxtx.im.admin.service.request.CapitalExcelOutputReq;
import com.lxtx.im.admin.service.request.CapitalReq;
import com.lxtx.im.admin.service.response.CapitalResp;
import com.lxtx.im.admin.service.response.UserDetailResp;
import com.lxtx.im.admin.service.response.UserListResp;
import com.lxtx.im.admin.service.utils.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author CaiRH
 * @since 2018-09-27
 */
@Service
public class CapitalServiceImpl implements CapitalService {

    private static final String APPLY_LIST_FILENAME = "交易流水列表";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private CapitalFeign capitalFeign;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private WalletUserFeign walletUserFeign;
    @Autowired
    private WalletUserCoinFeign walletUserCoinFeign;

    @Override
    public BaseResult listPage(CapitalReq capitalReq, HttpSession session) {
        List<String> userIds = null;
        List<String> accountIds = null;
        // 根据用户名称查询指定用户
        if (StringUtils.isNotBlank(capitalReq.getUserName())) {
            FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
            usernameReq.setName(capitalReq.getUserName());
            BaseResult queryByUsername = userFeign.queryByUsername(usernameReq);
            if (queryByUsername.isSuccess()) {
                if (queryByUsername.getData() == null) {
                    return BaseResult.success(new Page<>());
                }
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryByUsername.getData()));
                accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                if (CollectionUtils.isEmpty(accountIds)) {
                    return BaseResult.success(new Page<>());
                }
            } else {
                return BaseResult.success(new Page<>());
            }
        }

        // 查询对应钱包用户ID
        if (accountIds != null && !accountIds.isEmpty()) {
            FeignQueryWalletRegisterUserReq registerUserReq = new FeignQueryWalletRegisterUserReq();
            registerUserReq.setAccounts(accountIds);
            BaseResult registerWalletUser = walletUserFeign.queryRegisterWalletUser(registerUserReq);
            if (registerWalletUser.isSuccess()) {
                if (registerWalletUser.getData() == null) {
                    return BaseResult.success(new Page<>());
                }
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(registerWalletUser.getData()));
                userIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
                if (CollectionUtils.isEmpty(userIds)) {
                    return BaseResult.success(new Page<>());
                }
            } else {
                return BaseResult.success(new Page<>());
            }
        }

        //根据到账地址 查询用户钱包ID


        FeignCapitalReq req = new FeignCapitalReq();
        BeanUtils.copyProperties(capitalReq, req);
        req.setUserIds(userIds);
        if (req.getUpdateTime() != null) {
            req.setUpdateTime(DateUtils.formatDate(DateUtils.getEndDateFormat(req.getUpdateTime()),
                    DateUtils.DATE_FORMAT_DEFAULT));
        }
        if (req.getType() != null && req.getType() == 0) {
            req.setType(null);
        }

        BaseResult baseResult = capitalFeign.listPage(req);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(baseResult.getData()));
            List<CapitalResp> capitalRespList = JSONObject.parseArray(jsonObject.getString("records"),
                    CapitalResp.class);
            updateCapitalRespListData(capitalRespList);
            jsonObject.put("records", capitalRespList);
            baseResult.setData(jsonObject);
        }
        return baseResult;
    }

    @Override
    public void downloadList(HttpServletResponse response, CapitalExcelOutputReq capitalReq) {
        List<String> userIds = null;
        List<String> accountIds = null;
        // 根据用户名称查询指定用户
        if (StringUtils.isNotBlank(capitalReq.getUserName())) {
            FeignQueryUsernameReq usernameReq = new FeignQueryUsernameReq();
            usernameReq.setName(capitalReq.getUserName());
            BaseResult queryByUsername = userFeign.queryByUsername(usernameReq);
            if (queryByUsername.isSuccess() && queryByUsername.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(queryByUsername.getData()));
                accountIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
            }
        }

        // 查询对应钱包用户ID
        if (accountIds != null && !accountIds.isEmpty()) {
            FeignQueryWalletRegisterUserReq registerUserReq = new FeignQueryWalletRegisterUserReq();
            registerUserReq.setAccounts(accountIds);
            BaseResult registerWalletUser = walletUserFeign.queryRegisterWalletUser(registerUserReq);
            if (registerWalletUser.isSuccess() && registerWalletUser.getData() != null) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(registerWalletUser.getData()));
                userIds = JSONObject.parseArray(jsonObject.getString("list"), String.class);
            }
        }

        FeignCapitalReq req = new FeignCapitalReq();
        BeanUtils.copyProperties(capitalReq, req);
        req.setUserIds(userIds);
        if (req.getUpdateTime() != null) {
            req.setUpdateTime(DateUtils.formatDate(DateUtils.getEndDateFormat(req.getUpdateTime()),
                    DateUtils.DATE_FORMAT_DEFAULT));
        }
        if (req.getType() != null && req.getType() == 0) {
            req.setType(null);
        }
        BaseResult baseResult = capitalFeign.list(req);
        if (baseResult.isSuccess() && baseResult.getData() != null) {
            Map<String, Object> dataMap = (Map<String, Object>) baseResult.getData();
            Object list = dataMap.get("list");
            if (list != null) {
                List<CapitalResp> capitalRespList = JSONObject.parseArray(JSONArray.toJSONString(list),
                        CapitalResp.class);
                if (CollectionUtils.isEmpty(capitalRespList)) {
                    throw LxtxBizException.newException("未查询到可导出数据，请检查搜索条件！");
                }
                updateCapitalRespListData(capitalRespList);

                //导出的文件名
                String fileName = setPlatformWithdrawApplyDownloadFileName(req.getCreateTime(), req.getUpdateTime());
                ExcelUtil.exportExcel(response, capitalRespList, fileName, APPLY_LIST_FILENAME);
            }
        }

    }

    /**
     * 给导出文件命名
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private String setPlatformWithdrawApplyDownloadFileName(Date startTime, Date endTime) {
        String fileName = APPLY_LIST_FILENAME;
        if (startTime != null) {
            if (endTime != null) {
                fileName = fileName + dateFormat.format(startTime) + "至" + dateFormat.format(endTime);
            } else {
                fileName = fileName + dateFormat.format(startTime) + "至" + dateFormat.format(new Date());
            }
        } else {
            if (endTime != null) {
                fileName = fileName + "至" + dateFormat.format(endTime);
            } else {
                fileName = fileName + dateFormat.format(new Date());
            }
        }
        return fileName;
    }

    /**
     * 重装Resp返回交易流水信息
     *
     * @param capitalRespList
     */
    private void updateCapitalRespListData(List<CapitalResp> capitalRespList) {
        if (CollectionUtils.isEmpty(capitalRespList)) {
            return;
        }
        List<String> userIds = new ArrayList<>();
        for (CapitalResp resp : capitalRespList) {
            userIds.add(resp.getUserId());
            if (StringUtils.isNotBlank(resp.getPlatformUserId())) {
                userIds.add(resp.getPlatformUserId());
            }
        }

        // 查询符合条件的用户信息并转Map
        Map<String, String> userNameMap = new HashMap<>();
        FeignQueryUserListReq queryUserListReq = new FeignQueryUserListReq();
        queryUserListReq.setIds(userIds);
        BaseResult coreResult = userFeign.queryList(queryUserListReq);
        if (coreResult.isSuccess() && coreResult.getData() != null) {
            UserListResp userListResp = JSONObject.parseObject(JSONArray.toJSONString(coreResult.getData()),
                    UserListResp.class);
            List<UserDetailResp> userDetailResps = userListResp.getList();
            if (CollectionUtils.isEmpty(userDetailResps)) {
                return;
            }
            for (UserDetailResp record : userDetailResps) {
                userNameMap.put(record.getAccount(), record.getName());
            }
        }

        // 重装Resp返回用户名称、类型以及状态名称
        for (CapitalResp resp : capitalRespList) {
            resp.setUserName(userNameMap.get(resp.getUserId()));

            if (StringUtils.isNotBlank(resp.getPlatformUserId())) {
                resp.setPlatformUserId(userNameMap.get(resp.getPlatformUserId()));
            } else if (resp.getGroupRedPacket() != null && resp.getGroupRedPacket()) {
                resp.setPlatformUserId("【群红包】");
            }

            resp.setCoinName(getCoinName(resp));
            resp.setAmount(getCoinAmount(resp));
            resp.setTransferNum(getTransferNum(resp));
            resp.setStatusValue(getStatusValue(resp.getStatus()));
            resp.setTypeValue(getTypeValue(resp.getType(), resp.getReject()));
            resp.setGameTypeValue(getGameTypeValue(resp.getType(), resp.getGameType()));
            resp.setFromAddr(getFromOrToAddr(true, resp));
            resp.setToAddr(getFromOrToAddr(false, resp));
        }
    }

    private String getCoinName(CapitalResp capitalResp) {
        if (capitalResp.getType() != null) {
            return capitalResp.getType() == 15 ? capitalResp.getPayCoin() : capitalResp.getCoinName();
        }
        return "";
    }

    private String getCoinAmount(CapitalResp capitalResp) {
        if (EnumCapitalOperationRefType.FEX_WITHDRAW.getCode().equals(capitalResp.getType())) {
            return capitalResp.getRealPayAmount() != null && new BigDecimal(capitalResp.getPayAmount())
                    .compareTo(new BigDecimal(capitalResp.getRealPayAmount())) > 0
                    ? capitalResp.getPayAmount() + "(" + capitalResp.getRealPayAmount() + ")" : capitalResp.getPayAmount();
        } else {
            return capitalResp.getAmount();
        }
    }

    private String getTransferNum(CapitalResp capitalResp) {
        return EnumCapitalOperationRefType.FEX_WITHDRAW.getCode().equals(capitalResp.getType()) ? capitalResp
                .getOrderNo() : (StringUtils.isNotBlank(capitalResp.getTransferNum()) ? capitalResp.getTransferNum() : "无");
    }

    private String getGameTypeValue(Integer type, Integer gameType) {
        String typeStr = "";
        if (type == null) {
            return typeStr;
        }

        EnumCapitalOperationRefType refType = EnumCapitalOperationRefType.find(type);
        if (refType == null) {
            return typeStr;
        }
        switch (refType) {
            case GAME_BANKER:
                typeStr = "牛牛（庄）";
                break;
            case GAME_PLAYER:
                typeStr = "牛牛（玩）";
                break;
            case GAME_VERSION_NEW:
                if (EnumGameType.BLACKJACK.getCode().equals(gameType)) {
                    typeStr = "21点";
                } else if (EnumGameType.MINESWEEPER.getCode().equals(gameType)) {
                    typeStr = "扫雷";
                }
                break;
            default:
                break;
        }
        return typeStr;
    }

    private String getTypeValue(Integer type, Boolean reject) {
        String typeStr = "未知";
        if (type == null) {
            return typeStr;
        }
        if (type == 1) {
            typeStr = "转账";
        } else if (type == 2) {
            typeStr = "收款";
        } else if (type == 3) {
            typeStr = "好友转账";
        } else if (type == 5) {
            typeStr = "发送红包";
        } else if (type == 6) {
            if (reject) {
                typeStr = "退款红包";
            } else {
                typeStr = "接收红包";
            }
        } else if (type == 7 || type == 8 || type == 10) {
            typeStr = "游戏";
        } else if (type == 9) {
            typeStr = "第三方游戏好友转账";
        } else if (type == 11) {
            typeStr = "平台提款";
        } else if (type == 12) {
            typeStr = "OTC提款";
        } else if (type == 13) {
            typeStr = "OTC充值";
        } else if (type == 14) {
            typeStr = "游戏分佣";
        } else if (type == 15) {
            typeStr = "闪兑";
        } else if (type == 16) {
            typeStr = "群主返佣";
        } else if (type == 17) {
            typeStr = "下级返佣";
        } else if (type == 18) {
            typeStr = "游戏红包费用";
        } else if (type == 19) {
            typeStr = "扫码付款";
        } else if (type == 20) {
            typeStr = "收款码收款";
        } else if (type == 21) {
            typeStr = "余额宝转出";
        } else if (type == 22) {
            typeStr = "余额宝转入";
        } else if (type == 23) {
            typeStr = "商户消费";
        } else if (type == 24) {
            typeStr = "商户收款";
        } else if (type == 25) {
            typeStr = "余额宝转出手续费";
        } else if (type == 26) {
            typeStr = "银行转账";
        } else if (type == 27) {
            typeStr = "线下换汇-保证金缴纳";
        } else if (type == 28) {
            typeStr = "线下换汇-保证金返还";
        } else if (type == 29) {
            typeStr = "线下换汇-商品发布资金冻结";
        } else if (type == 30) {
            typeStr = "线下换汇-商品发布资金解冻";
        } else if (type == 32) {
            typeStr = "线下换汇-换汇资金到账";
        } else if (type == 33) {
            typeStr = "线下换汇-换汇交易返利";
        } else {
            typeStr = "未知";
        }
//        EnumCapitalOperationRefType refType = EnumCapitalOperationRefType.find(type);
//        if (refType == null) {
//            return typeStr;
//        }
//        switch (refType) {
//            case WITHDRAW:
//                typeStr = "转账";
//                break;
//            case RECHARGE:
//                typeStr = "收款";
//                break;
//            case TRANSFER:
//                typeStr = "好友转账";
//                break;
//            case SEND_RED_PACKET:
//                typeStr = "发送红包";
//                break;
//            case RECEIVE_RED_PACKET:
//                if (reject) {
//                    typeStr = "退款红包";
//                } else {
//                    typeStr = "接收红包";
//                }
//                break;
//            case GAME_BANKER:
//                typeStr = "游戏";
//                break;
//            case GAME_PLAYER:
//                typeStr = "游戏";
//                break;
//            case THIRD_GAME_TRANSFER:
//                typeStr = "第三方游戏好友转账";
//                break;
//            case GAME_VERSION_NEW:
//                typeStr = "游戏";
//                break;
//            case PLATFORM_WITHDRAW:
//                typeStr = "平台提款";
//                break;
//            case OTC_WITHDRAW:
//                typeStr = "OTC提款";
//                break;
//            case OTC_RECHARGE:
//                typeStr = "OTC充值";
//                break;
//            case FEX_WITHDRAW:
//                typeStr = "闪兑";
//                break;
//            default:
//                typeStr = "未知";
//                break;
//        }
        return typeStr;
    }

    private String getStatusValue(Integer status) {
        String statusStr = "成功";
        if (status == null) {
            return statusStr;
        }

        EnumWithdrawApplyState refState = EnumWithdrawApplyState.find(status);
        if (refState == null) {
            return statusStr;
        }
        switch (refState) {
            case PENDING:
                statusStr = "待处理";
                break;
            case HANDING:
                statusStr = "处理中";
                break;
            case COMMITTED:
                statusStr = "已提交";
                break;
            case SUCCESS:
                statusStr = "成功";
                break;
            case FAILURE:
                statusStr = "失败";
                break;
            case PARTIAL_SUCCESS:
                statusStr = "部分成功";
                break;
            default:
                //红包类型默认“操作成功”
                statusStr = "成功";
                break;
        }
        return statusStr;
    }

    private String getFromOrToAddr(boolean isFrom, CapitalResp capitalResp) {
        String fromAddr = StringUtils.isBlank(capitalResp.getFromAddr()) ? "" : capitalResp.getFromAddr();
        String toAddr = StringUtils.isBlank(capitalResp.getToAddr()) ? "" : capitalResp.getToAddr();

        if (EnumCapitalOperationRefType.SEND_RED_PACKET.getCode().equals(capitalResp.getType())) {
            fromAddr = capitalResp.getUserId();
            toAddr = capitalResp.getPlatformUserId();
        } else if (EnumCapitalOperationRefType.RECEIVE_RED_PACKET.getCode().equals(capitalResp.getType()) &&
                capitalResp.getReject()) {
            fromAddr = capitalResp.getUserId();
            toAddr = capitalResp.getUserId();
        } else if (EnumCapitalOperationRefType.RECEIVE_RED_PACKET.getCode().equals(capitalResp.getType()) &&
                !capitalResp.getReject()) {
            fromAddr = capitalResp.getPlatformUserId();
            toAddr = capitalResp.getUserId();
        } else if (EnumCapitalOperationRefType.FEX_WITHDRAW.getCode().equals(capitalResp.getType())) {
            if (StringUtils.isNotBlank(capitalResp.getRealPayAmount()) && BigDecimal.ZERO
                    .compareTo(new BigDecimal(capitalResp.getRealPayAmount())) != 0) {
                fromAddr = capitalResp.getRealPayAmount() + " " + capitalResp.getPayCoin();
            } else if (StringUtils.isNotBlank(capitalResp.getPayAmount()) && BigDecimal.ZERO
                    .compareTo(new BigDecimal(capitalResp.getPayAmount())) != 0) {
                fromAddr = capitalResp.getPayAmount() + " " + capitalResp.getPayCoin();
            } else {
                fromAddr = capitalResp.getPayCoin();
            }

            if (StringUtils.isNotBlank(capitalResp.getRealGotAmount()) && BigDecimal.ZERO
                    .compareTo(new BigDecimal(capitalResp.getRealGotAmount())) != 0) {
                toAddr = capitalResp.getRealGotAmount() + " " + capitalResp.getGotCoin();
            } else if (StringUtils.isNotBlank(capitalResp.getGotAmount()) && BigDecimal.ZERO
                    .compareTo(new BigDecimal(capitalResp.getGotAmount())) != 0) {
                toAddr = capitalResp.getGotAmount() + " " + capitalResp.getGotCoin();
            } else {
                toAddr = capitalResp.getGotCoin();
            }
        }
        return isFrom ? fromAddr : toAddr;
    }

    /**
     * 查看交易流水详情
     *
     * @param req
     * @return
     */
    @Override
    public String detail(CapitalDetailReq req, Model model) {
        FeignCapitalDetailReq feignReq = new FeignCapitalDetailReq();
        feignReq.setId(req.getId());
        BaseResult capitalResult = capitalFeign.detail(feignReq);
        if (!capitalResult.isSuccess() || null == capitalResult.getData()) {
            throw LxtxBizException.newException("数据有误");
        }
        Map<String, Object> dataMap = (Map<String, Object>) capitalResult.getData();
        List<CapitalResp> detailList = JSONObject.parseArray(JSON.toJSONString(dataMap.get("list")),
                CapitalResp.class);
        CapitalResp resp = null;
        if (CollectionUtils.isNotEmpty(detailList)) {
            updateCapitalRespListData(detailList);
            resp = detailList.get(0);
        }
        model.addAttribute("detail", resp);
        return "wallet/capital-detail";
    }
}
