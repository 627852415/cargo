var current = 1;
var size = 10;
var coinId;
var userId;
var type;
var respData;
layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var createTime, updateTime;
    // [[#{text.time.selection.general.usage}]]
    laydate.render({
        elem: '#createTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.transaction.date.cannot.be.greater.than.end.transaction.date}]]");
                $("#createTime").val("");
                return;
            } else if (value > updateTime) {
                layer.msg("[[#{text.start.transaction.date.cannot.be.greater.than.end.transaction.date}]]");
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
                layer.msg("[[#{text.end.of.trading.period.cannot.be.larger.than.the.current.period}]]");
                $("#updateTime").val("");
                return;
            } else if (value < createTime) {
                layer.msg("[[#{text.the.end.of.the.trading.period.cannot.be.less.than.the.beginning.of.the.trading.period}]]");
                $("#updateTime").val("");
                return;
            }
            updateTime = value;
        }
    });
});

loadTableWithoutColumns('/withdraw/apply/fail/list', 'dataTable', 'searchForm');

function getCoinName(data) {

    return data.coinName;
}

function getCoinAmount(data) {
    return data.amount;
}

function getTransferNum(data) {
    return data.transferNum !== undefined && data.transferNum !== "undefined" ? data.transferNum : '[[#{text.no}]]';
}

function getApplyId(data) {
    return data.applyId !== undefined && data.applyId !== "undefined" ? data.applyId : '[[#{text.no}]]';
}

function getFromOrToAddr(isFrom, fromAddr, toAddr) {
    var fromAddr = fromAddr === undefined ? "" : fromAddr, toAddr = toAddr === undefined ? "" : toAddr;
    return isFrom ? fromAddr : toAddr;
}

function getStatusStr(status) {
    var statusStr = "";
    if (status == 0) {
        statusStr = "[[#{text.pending}]]";
    } else if (status == 1) {
        statusStr = "<font color='#dc143c'>[[#{text.processing.faileds}]]</font>";
    } else {
        statusStr = "<font color='#ffd700'>[[#{text.processing.succeededsds}]]</font>";
    }
    return statusStr;
}

function getTypeStr(type) {
    var typeStr = "";
    if (type == 1) {
        typeStr = "[[#{text.withdraw.money.off.the.station}]]";
    } else {
        typeStr = "[[#{text.yuebao.transferred.to.withdrawal}]]";
    }
    return typeStr;
}

function renderBtn(data) {
    if (data.status == 0) {
        var html = '';
        html += '<a href="javascript:;" onclick="saveFail(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm layui-btn-danger">[[#{text.processing.failed}]]</a>';
        html += '<a href="javascript:;" onclick="saveSucess(this)" num="' + data.transferNum + '" aid="' + data.id + '" class="layui-btn layui-btn-sm">[[#{text.processing.succeeded}]]</a>';
        return html;
    } else {
        return "[[#{text.no}]]";
    }

}

function saveFail(id) {
    layer.confirm('[[#{text.are.you.sure.you.want.to.process.the.record.as.failed.and.unfreeze.the.refund}]]', {icon: 3}, function (index) {
        var pkId = jQuery(id).attr('aid');
        $.ajaxUkey({
            url: '/withdraw/apply/fail/dealFail',
            type: "POST",
            data: JSON.stringify({
                'recordId': pkId
            }),
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                if (result.success) {
                    if (!$.isEmptyObject(result.data)) {
                        layer.alert(result.data, {title: '[[#{text.information}]]', icon: 5});
                        return;
                    }
                    layer.alert('[[#{text.successful.operation}]]', {title: '[[#{text.information}]]', icon: 1}, function (index) {
                        layer.close(index);
                        $(".layui-laypage-btn")[0].click();
                    });
                } else {
                    if (!$.isEmptyObject(result.msg)) {
                        layer.alert(result.msg, {title: '[[#{text.information}]]', icon: 2});
                        return;
                    }
                    layer.alert('[[#{text.sys.op.fail}]]', {title: '[[#{text.information}]]', icon: 2});
                }
            }
        });
    });

}

function saveSucess(id) {
    layer.confirm('[[#{text.are.you.sure.handel.callback }]]<br>[[#{text.marks}]]<br>[[#{text.if.no.tran.pls.apply}]]<br>[[#{text.no.callback.pls.resend}]]', {icon: 3,area:['400px','240px']}, function (index) {
        var pkId = jQuery(id).attr('aid');
        var num = jQuery(id).attr('num');
        if (num == null || num == undefined || num == "undefined") {
            num = "";
            layer.prompt({title: '[[#{text.enter.business.platform.transaction.number}]]', formType: 0}, function (pass, index) {
                layer.close(index);
                save(pkId, pass);
            });
        } else {
            save(pkId, num);
        }
    });
}

function save(pkId, num) {
    $.ajaxUkey({
        url: '/withdraw/apply/fail/dealSuccess',
        type: "POST",
        data: JSON.stringify({"recordId": pkId, "transferNum": num}),
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.success) {
                if (!$.isEmptyObject(result.data)) {
                    layer.alert(result.data, {title: '[[#{text.information}]]', icon: 5});
                    return;
                }
                layer.alert('[[#{text.successful.operation}]]', {title: '[[#{text.information}]]', icon: 1}, function (index) {
                    layer.close(index);
                    $(".layui-laypage-btn")[0].click();
                });
            } else {
                if (!$.isEmptyObject(result.msg)) {
                    layer.alert(result.msg, {title: '[[#{text.information}]]', icon: 2});
                    return;
                }
                layer.alert('[[#{text.system.is.abnormal,.operation.failed}]]', {title: '[[#{text.information}]]', icon: 2});
            }
        }
    });
}


