var current = 1;
var size = 10;
var coinId;
var userId;
var transferNum;
var type;
var gameType;
var respData;
layui.use(['form', 'laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var createTime, updateTime;
    // 时间选择常规用法
    laydate.render({
        elem: '#createTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("开始交易日期不能大于当前日期");
                $("#createTime").val("");
                return;
            } else if (value > updateTime) {
                layer.msg("开始交易日期不能大于结束交易日期");
                $("#createTime").val("");
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#updateTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("结束交易日期不能大于当前日期");
                $("#updateTime").val("");
                return;
            } else if (value < createTime) {
                layer.msg("结束交易日期不能小于开始交易日期");
                $("#updateTime").val("");
                return;
            }
            updateTime = value;
        }
    });

    //类型级联搜索条件
    // form.on('select(capitalType)', function (data) {
    //     var type = data.value;
    //     switch (type) {
    //         case "5":{
    //             $("#gameTypeDiv").css("display", "block");
    //             $("#gameType").empty();
    //             $("#gameType").append('<option value="0">请选择游戏类型</option>');
    //             $("#gameType").append('<option value="1">牛牛</option>');
    //             $("#gameType").append('<option value="2">21点</option>');
    //             $("#gameType").append('<option value="3">扫雷</option>');
    //             form.render('select');
    //             break;
    //         }
    //         default:{
    //             $("#gameType").empty();
    //             form.render('select');
    //             $("#gameTypeDiv").css("display", "none");
    //             break;
    //         }
    //
    //     }
    // });
});

loadTableWithoutColumns('/capital/listPage', 'dataTable', 'searchForm');

function getCoinName(data) {
    return data.type === 15 ? data.payCoin : data.coinName;
}

function getCoinAmount(data) {
    return data.type === 15 ? (
            data.realPayAmount != null && data.realPayAmount != 0 && data.payAmount > data.realPayAmount
                ? data.payAmount + "(" + data.realPayAmount + ")" : data.payAmount
        )
        : data.amount;
}

function getTransferNum(data) {
    return data.type === 15 ? data.orderNo : (data.transferNum !== undefined ? data.transferNum : '无');
}

function getFromOrToAddr(isFrom, type, reject, fromAddr, toAddr, userId, platformUserId, data) {
    var fromAddr = fromAddr === undefined ? "" : fromAddr, toAddr = toAddr === undefined ? "" : toAddr;
    if (type === 5) {
        fromAddr = userId;
        toAddr = platformUserId;
    } else if (type === 6 && reject === true) {
        fromAddr = userId;
        toAddr = userId;
    } else if (type === 6 && reject === false) {
        fromAddr = platformUserId;
        toAddr = userId;
    } else if (type === 15) {
        if (data.realPayAmount != null && data.realPayAmount != 0) {
            fromAddr = data.realPayAmount + " " + data.payCoin;
        } else if (data.payAmount != null && data.payAmount != 0) {
            fromAddr = data.payAmount + " " + data.payCoin;
        } else {
            fromAddr = data.payCoin;
        }

        if (data.realGotAmount != null && data.realGotAmount != 0) {
            toAddr = data.realGotAmount + " " + data.gotCoin;
        } else if (data.gotAmount != null && data.gotAmount != 0) {
            toAddr = data.gotAmount + " " + data.gotCoin;
        } else {
            toAddr = data.gotCoin;
        }
    }
    return isFrom ? fromAddr : toAddr;
}

function getStatusStr(status) {
    var statusStr = "";
    if (status == 1) {
        statusStr = "待处理";
    } else if (status == 2) {
        statusStr = "处理中";
    } else if (status == 3) {
        statusStr = "<font color='#adff2f'>已提交</font>";
    } else if (status == 4) {
        statusStr = "<font color='#ffd700'>成功</font>";
    } else if (status == 5) {
        statusStr = "<font color='#dc143c'>失败</font>";
    } else if (status == 6) {
        statusStr = "<font color='#dc143c'>部分成功</font>";
    } else {
        //红包类型默认“操作成功”
        statusStr = "<font color='#ffd700'>成功</font>";
    }
    return statusStr;
}

function getTypeStr(type, reject) {
    //console.log("type is :"+ type);
    var typeStr = "";
    if (type == 1) {
        typeStr = "转账";
    } else if (type == 2) {
        typeStr = "收款";
    } else if (type == 3) {
        typeStr = "好友转账";
    } else if (type == 5) {
        typeStr = "发送红包";
    } else if (type == 6) {
        if (reject === true) {
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
        typeStr = "理财宝转出";
    } else if (type == 22) {
        typeStr = "理财宝转入";
    } else if (type == 23) {
        typeStr = "商户消费";
    } else if (type == 24) {
        typeStr = "商户收款";
    } else if (type == 25) {
        typeStr = "理财宝转出手续费";
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
    return typeStr;
}

function getGameTypeStr(type, gameType) {
    var typeStr = "";
    if (type == 7) {
        typeStr = "牛牛（庄）";
    } else if (type == 8) {
        typeStr = "牛牛（玩）";
    } else if (type == 10) {
        if (gameType == 2) {
            typeStr = "21点";
        } else if (gameType == 3) {
            typeStr = "扫雷";
        }
    } else {
        typeStr = "";
    }
    return typeStr;
}

//申请列表文件导出
function download() {
    var url = "/capital/download/list?" + $("#searchForm").serialize();
    $.ajaxUkey({
        url: url,
        type: "get",
        async: "true",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (responseText) {
            if (responseText.success == false) {
                layer.alert(responseText.msg, {title: '提示信息', icon: 2});
            } else {
                window.location.href = url;
            }

        },
        error: function () {
            layer.alert("系统异常，导出失败", {title: '提示信息', icon: 2});
        }
    });
}

function renderBtn(data) {
    return '<a href="javascript:;" onclick="detail(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm">查看详情</a>';
}

//查看详情
function detail(obj) {
    var me = $(obj);
    var id = me.attr('aid');
    var url = "/capital/detail?id=" + id;
    openModal("查看详情", url, "1000px", "420px");
}

function getUserName(obj) {
    if (obj.userName == undefined && (obj.type == 23 || obj.type == 24)) {
        return "SDK 商户"
    }
    return obj.userName;
}