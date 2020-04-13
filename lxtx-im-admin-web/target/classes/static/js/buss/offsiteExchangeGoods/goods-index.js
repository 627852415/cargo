layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var createTime, endTime;

    laydate.render({
        elem: '#startTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.the.current.date}]]");
                $("#startTime").val("");
                createTime = '';
                return;
            } else if (endTime != "" && value > endTime) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.end.date}]]");
                $("#startTime").val("");
                createTime = '';
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#endTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.end.date.cannot.be.greater.than.the.current.date}]]");
                $("#endTime").val("");
                endTime = '';
                return;
            } else if (value < createTime) {
                layer.msg("[[#{text.end.date.cannot.be.less.than.start.date}]]");
                $("#endTime").val("");
                endTime = '';
                return;
            }
            endTime = value;
        }
    });

});
loadTableWithoutColumns('/offsite/exchange/goods/list', 'dataTable', 'searchForm');
//[[#{text.lazy.loading}]]
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool() {
    layui.use('table', function () {
        //[[#{text.listen.for.line.tool.events}]]
        table.on('tool(dataTable)', function (obj) {
            var goods = obj.data;
            var userId = goods.userId;
            var merchantId = goods.merchantId;
            var goodsId = goods.goodsId;
            var detail = JSON.stringify(goods);
            if (obj.event === 'detail') {
                openModal("线下汇换商品[[#{text.details}]]", "/offsite/exchange/goods/detail?detail=" + encodeURIComponent(detail, 'utf-8'), 700, 500);
            }
            if (obj.event === 'goods-up') {
                goodsUp(userId, merchantId, goodsId, this);
            }
            if (obj.event === 'goods-down') {
                goodsDown(userId, merchantId, goodsId, this);
            }
        });
    });
}

//[[#{text.export.fee.list.file}]]
function download(obj) {
    console.info();
    var goodsId = document.getElementById("goodsId").value;
    var merchantId = document.getElementById("merchantId").value;
    var endTime = document.getElementById("endTime").value;
    var startTime = document.getElementById("startTime").value;
    var url = "/offsite/exchange/goods/download?goodsId=" + goodsId + "&startTime=" + startTime + "&endTime=" + endTime + "&merchantId=" + merchantId;
    $.ajaxUkey({
        url: url,
        type: "get",
        async: "true",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (responseText) {
            if (responseText.success == false) {
                layer.alert(responseText.msg, {title: '[[#{text.information}]]', icon: 2});
            } else {
                window.location.href = url;
            }

        },
        error: function () {
            layer.alert("[[#{text.system.abnormal.export.failure.prompt.message}]]", {title: '提示信息', icon: 2});
        }
    });
}

function goodsUp(userId, merchantId, goodsId, item) {
    $.ajaxUkey({
        url: '/offsite/exchange/goods/up',
        type: "POST",
        dataType: "json",
        data: JSON.stringify({
            'userId': userId,
            'merchantId': merchantId,
            'goodsId': goodsId
        }),
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.success) {
                layer.alert('[[#{text.successful.operation}]]', {title: '提示信息', icon: 1}, function (index) {
                    layer.close(index);
                    //location.reload();
                });
                search('searchForm', 'dataReload')
            } else {
                if (!$.isEmptyObject(result.msg)) {
                    layer.alert(result.msg, {title: '[[#{text.information}]]', icon: 2});
                    return;
                }
                layer.alert('系统异常操作失败', {title: '[[#{text.information}]]', icon: 2});
            }
        }
    });
}

function goodsDown(userId, merchantId, goodsId, item) {
    $.ajaxUkey({
        url: '/offsite/exchange/goods/down',
        type: "POST",
        dataType: "json",
        data: JSON.stringify({
            'userId': userId,
            'merchantId': merchantId,
            'goodsId': goodsId
        }),
        contentType: 'application/json;charset=UTF-8',
        success: function (result) {
            if (result.success) {
                layer.alert('[[#{text.successful.operation}]]', {title: '提示信息', icon: 1}, function (index) {
                    layer.close(index);
                    //location.reload();
                });
                search('searchForm', 'dataReload')
            } else {
                if (!$.isEmptyObject(result.msg)) {
                    layer.alert(result.msg, {title: '[[#{text.information}]]', icon: 2});
                    return;
                }
                layer.alert('系统异常，操作失败', {title: '[[#{text.information}]]', icon: 2});
            }
        }
    });
}

function telephoneHandle(data) {
    return data.replace(' ', '+');
}

function limitRangeHandle(data) {
    return data.replace('-', ' ~ ');
}
