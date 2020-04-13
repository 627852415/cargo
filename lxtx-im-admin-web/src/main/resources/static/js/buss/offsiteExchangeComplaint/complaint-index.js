layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var createTime, endTime;

    laydate.render({
        elem: '#createTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("开始交易日期不能大于当前日期");
                $("#createTime").val("");
                return;
            } else if (value > endTime) {
                layer.msg("开始交易日期不能大于结束交易日期");
                $("#createTime").val("");
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#endTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("结束日期不能大于当前日期");
                $("#endTime").val("");
                return;
            } else if (value < createTime) {
                layer.msg("结束日期不能小于开始日期");
                $("#endTime").val("");
                return;
            }
            endTime = value;
        }
    });

});
loadTableWithoutColumns('/offsite/exchange/complaint/list', 'dataTable', 'searchForm');
//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

//订单详情
function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(dataTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                openTab("投诉详情", "/offsite/exchange/complaint/detail?id=" + obj.data.id);
            } else if (obj.event === 'edit') {
                openTab("处理投诉", "/offsite/exchange/complaint/processing?id=" + obj.data.id);
            }
        });
    });
}
