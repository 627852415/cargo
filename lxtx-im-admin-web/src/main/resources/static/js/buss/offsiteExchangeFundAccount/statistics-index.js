var tabType = 0;
layui.use('element', function () {
    var $ = layui.jquery
        , element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    var amountIdStr = $("input[name='amountId']").val();
    var amountArray = JSON.parse(amountIdStr);
    if (amountArray != null && amountArray.length > 0) {
        for (var x in amountArray) {
            $("div[name=amountBody]").append(amountArray[x]).append("<br>");
        }
    }
    //触发事件
    var active = {
        tabChange: function () {
            //切换到指定Tab项
            //element.tabChange('demo', '22'); //切换到：用户管理
            console.log(1);
        }
    };

    // $('.site-demo-active').on('click', function () {
    //     var othis = $(this), type = othis.data('type');
    //     active[type] ? active[type].call(this, othis) : '';
    //     console.log(type);
    // });
    //
    //Hash[[#{text.address}]]的定位
    var layid = location.hash.replace(/^#docDemoTabBrief=/, '');
    element.tabChange('docDemoTabBrief', layid);
    var date1 = getNowFormatDate();
    searchOrder(date1, date1);
    element.on('tab(docDemoTabBrief)', function (elem) {
        location.hash = 'docDemoTabBrief=' + $(this).attr('lay-id');
        var layId = $(this).attr('lay-id');
        if (layId == '22') {
            var date1 = getNowFormatDate();
            searchOrder(date1, date1);
        } else if (layId == '23') {
            var date1 = getNowFormatDate();
            var date2 = getDay(-7);
            searchOrder(date2, date1);
        } else if (layId == '24') {
            var date1 = getNowFormatDate();
            var date2 = getPreMonth(date1);
            searchOrder(date2, date1);
        }
        if (layId == '25') {
            tabType = 1;
        }
        changeClass(layId);
    });
    $('button[name=searchOrderData]').on('click', searchOrderFun);
});
changeClass(22);
var isSearch = 0;

function changeClass(layId) {
    var div = $('div[name=yjjb]').find('div').eq(1);
    //console.log(tabType);
    if (layId == '22' || layId == '23' || layId == '24') {
        if (tabType == 1) {
            div.height(div.height() - 55);
            isSearch = 0;
            tabType = 0;
        }
    } else {
        if (search == 1) {
            return;
        }
        if (tabType == 1 && isSearch == 0) {
            div.height(div.height() + 55);
            isSearch = 1;
            tabType = 1;
        }
    }
}

function searchOrderFun() {
    //console.log($('input[name=startDate]').val());
    var startDate = $('input[name=startDate]').val();
    var endDate = $('input[name=endDate]').val();
    if (startDate == null) {
        layer.msg("[[#{text.start.date.cannot.be.empty}]]");
        return;
    }
    if (endDate == null) {
        layer.msg("[[#{text.end.date.cannot.be.empty}]]");
        return;
    }
    searchOrder(startDate, endDate);
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

function getPreMonth(date) {
    var arr = date.split('-');
    var year = arr[0]; //获取当前[[#{text.day}]]期的年份
    var month = arr[1]; //获取当前日期的[[#{text.month}]]份
    var day = arr[2]; //获取当前[[#{text.day}]]期的[[#{text.day}]]
    var days = new Date(year, month, 0);
    days = days.getDate(); //获取当前日期中[[#{text.month}]]的天数
    var year2 = year;
    var month2 = parseInt(month) - 1;
    if (month2 == 0) {
        year2 = parseInt(year2) - 1;
        month2 = 12;
    }
    var day2 = day;
    var days2 = new Date(year2, month2, 0);
    days2 = days2.getDate();
    if (day2 > days2) {
        day2 = days2;
    }
    if (month2 < 10) {
        month2 = '0' + month2;
    }
    var t2 = year2 + '-' + month2 + '-' + day2;
    return t2;
}

function getDay(day) {
    var today = new Date();
    var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
    today.setTime(targetday_milliseconds); //注意，这行是关键代码
    var tYear = today.getFullYear();
    var tMonth = today.getMonth();
    var tDate = today.getDate();
    tMonth = doHandleMonth(tMonth + 1);
    tDate = doHandleMonth(tDate);
    return tYear + "-" + tMonth + "-" + tDate;
}

function doHandleMonth(month) {
    var m = month;
    if (month.toString().length == 1) {
        m = "0" + month;
    }
    return m;
}

function searchOrder(startDate, endDate) {
    $.ajax({
        url: '/offsite/exchange/fundAccount/statistics/get/order',
        type: "POST",
        data: JSON.stringify({"startDate": startDate, "endDate": endDate}),
        async: "false",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        success: function (responseText) {
            if (responseText.success == false) {
                layer.alert(responseText.msg, {title: '[[#{text.information}]]', icon: 2});
            } else {
                $('cite[name=totalIncome]').text(responseText.data.exchangeIncome);
                $('cite[name=totalPayment]').text(responseText.data.exchangePayment);
                $('cite[name=totalProfit]').text(responseText.data.totalProfits);
                $('cite[name=totalMerchantAmount]').text(responseText.data.merchantRebateAmount);
                $('cite[name=totalOrders]').text(responseText.data.totalOrders);
                $('cite[name=totalSuccessOrders]').text(responseText.data.successOrders);
            }
        },
        error: function () {
            layer.alert("系统异常，导出失败", {title: '[[#{text.information}]]', icon: 2});
        }
    });
}

layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var createTime, endTime;
    var updateStartTime, updateEndTime;

    laydate.render({
        elem: '#startDate'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.the.current.date}]]");
                $("#startDate").val("");
                return;
            } else if (endTime != "" && value > endTime) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.end.date}]]");
                $("#startDate").val("");
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#endDate'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.end.date.cannot.be.greater.than.the.current.date}]]");
                $("#endDate").val("");
                return;
            } else if (value < createTime) {
                layer.msg("[[#{text.end.date.cannot.be.less.than.start.date}]]");
                $("#endDate").val("");
                return;
            }
            endTime = value;
        }
    });

    laydate.render({
        elem: '#startTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.the.current.date}]]");
                $("#startTime").val("");
                return;
            } else if (updateEndTime != "" && value > updateEndTime) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.end.date}]]");
                $("#startTime").val("");
                return;
            }
            updateStartTime = value;
        }
    });

    laydate.render({
        elem: '#endTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.end.date.cannot.be.greater.than.the.current.date}]]");
                $("#endTime").val("");
                return;
            } else if (value < updateStartTime) {
                layer.msg("[[#{text.end.date.cannot.be.less.than.start.date}]]");
                $("#endTime").val("");
                return;
            }
            updateEndTime = value;
        }
    });

});

loadTableWithoutColumns('/user/coin/asset/list/page', 'dataTable', 'searchForm');

//如果资产大于0 显示黑色，小于0红色
function setStyle(obj) {
    if (obj > 0) {
        return "<b style='color: black'>" + obj + "</b>";
    }
    if (obj < 0) {
        return "<b style='color: red'>" + obj + "</b>";
    }
    return obj;
}

//[[#{text.lazy.loading}]]
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool() {
    layui.use('table', function () {
        //[[#{text.listen.for.line.tool.events}]]
        table.on('tool(dataTable)', function (obj) {
            if (obj.event === 'detail') {
                detail("/user/coin/asset/detail?id=" + obj.data.id);
            }
        });
    });
}

//保存，[[#{text.edit}]]
function detail(url) {
    openModalOwn("[[#{text.see.details}]]", url, "1000px", "420px");
}


/**
 * 打开弹出框
 * @param title 弹出框标题
 * @param url 跳转[[#{text.address}]]
 * @param width 弹出框宽度
 * @param height 弹出框高度
 */
function  openModalOwn(title, url, width, height) {
    layer.open({
        type: 2,
        closeBtn: 1,
        title: '<b>' + title +'</b>',
        area: [width, height], //[[#{text.width.height}]]
        //content: $("#modifyData")});//TODO 修改为打开新页面
        content: url,
        offset: '50px',
    });
}

//申请列表文件导出
function download() {
    var url = "/user/coin/asset/download/list?" + $("#searchForm").serialize();
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
            layer.alert("[[#{text.system.abnormal.export.failure.prompt.message}]]", {title: '[[#{text.information}]]', icon: 2});
        }
    });
}
