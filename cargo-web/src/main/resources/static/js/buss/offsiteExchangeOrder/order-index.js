layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate, tmpDate;

    laydate.render({
        elem: '#startDate'
        , done: function (value, date) {
            curDate = formatDate(new Date);
            tmpDate = $('#endDate').val();
            if (value > curDate) {
                layer.msg("开始交易日期不能大于当前日期");
                $("#startDate").val("");
                return;
            } else if (tmpDate != '' && value > tmpDate) {
                layer.msg("开始交易日期不能大于结束交易日期");
                $("#startDate").val("");
                return;
            }
        }
    });
    laydate.render({
        elem: '#endDate'
        , done: function (value, date) {
            curDate = formatDate(new Date);
            tmpDate = $('#startDate').val();
            if (value > curDate) {
                layer.msg("结束日期不能大于当前日期");
                $("#endDate").val("");
                return;
            } else if (tmpDate != '' && value < tmpDate) {
                layer.msg("结束日期不能小于开始日期");
                $("#endDate").val("");
                return;
            }
            endTime = value;
        }
    });

});

loadTableWithoutColumns('/offsite/exchange/order/list', 'dataTable', 'searchForm');

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

//订单详情
function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(dataTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                openModal("线下汇换订单详情", "/offsite/exchange/order/detail?orderId=" + obj.data.orderId, 700, 400);
            }
        });
    });
}

//订单列表文件导出
function download(obj) {
    var url = "/offsite/exchange/order/download?" + $('searchForm').serialize();
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