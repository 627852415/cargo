layui.use(['form', 'laydate'], function() {
    var laydate = layui.laydate;
    var form = layui.form;
    var curDate = formatDate(new Date);
    var startTime,endTime;
    var updateTime,updateEndTime;
    laydate.render({
        elem: '#startTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("开始日期不能大于当前日期");
                $("#startTime").val("");
                return;
            } else if (value > endTime) {
                layer.msg("开始日期不能大于结束日期");
                $("#startTime").val("");
                return;
            }
            startTime = value;
        }
    });
    laydate.render({
        elem: '#endTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("结束日期不能大于当前日期");
                $("#endTime").val("");
                return;
            } else if (value < startTime) {
                layer.msg("结束日期不能小于开始日期");
                $("#endTime").val("");
                return;
            }
            endTime = value;
        }
    });

});
loadTableWithoutColumns('/user/coin/snapshot/list/page','dataTable', 'searchForm');

//如果资产大于0 显示黑色，小于0红色
function setStyle(obj) {
    if(obj > 0){
        return "<b style='color: black'>" + obj +"</b>";
    }
    if(obj < 0){
        return "<b style='color: red'>" + obj +"</b>";
    }
    return obj;
}

//延时加载
//setTimeout('rowTool()', 1000); //延迟1秒

//function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        var table = layui.table;
        table.on('tool(dataTable)', function(obj){
            if(obj.event === 'detail'){
                detail("/user/coin/snapshot/detail?recordsDate=" + obj.data.recordsDate);
            } else if(obj.event === 'download'){
                console.log(obj.data);

                var url="/user/coin/snapshot/list/down?id="+obj.data.id;
                $.fileDownload(url,{
                    httpMethod: 'get',
                    prepareCallback:function(url){
                        console.log("Excel准备中...");
                    },
                    successCallback:function(url){
                        console.log('导出Excel');
                    },
                    failCallback: function (html, url) {
                        console.log('导出Excel');
                    }
                });
            }
        });
    });
//}

//详情
function detail(url) {
    var e = $(this),
        i = url;
    t = "快照详情";
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}

//申请列表文件导出
// function download() {
//     var url = "/user/coin/asset/download/list?" + $("#searchForm").serialize();
//     $.ajax({
//         url:url,
//         type:"get",
//         async:"true",
//         contentType: "application/x-www-form-urlencoded; charset=utf-8",
//         success:function(responseText){
//             if(responseText.success == false){
//                 layer.alert(responseText.msg, { title: '提示信息', icon: 2 });
//             }else{
//                 window.location.href = url;
//             }
//
//         },
//         error:function(){
//             layer.alert("系统异常，导出失败", { title: '提示信息', icon: 2 });
//         }
//     });
// }