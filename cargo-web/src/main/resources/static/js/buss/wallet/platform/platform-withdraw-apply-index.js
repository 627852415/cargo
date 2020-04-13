layui.use(['form', 'laydate'], function() {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var startTime,endTime;
    var updateTime,updateEndTime;
    // 手续费统计
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
loadTableWithoutColumns('/platform/withdraw/apply/list/page','dataTable', 'searchForm');

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(dataTable)', function(obj){
            if(obj.event === 'detail'){
                detail("/platform/withdraw/apply/detail?id=" + obj.data.id);
            }
        });
    });
}

//保存，编辑
function detail(url){
    openModal("查看详情", url, "600px", "430px");
}



//系统提款申请列表文件导出
function download() {
    var coinId = document.getElementById("coinId").value;
    var startTime = document.getElementById("startTime").value;
    var endTime = document.getElementById("endTime").value;
    var id = document.getElementById("id").value;
    var status = document.getElementById("status").value;
    var telephone = document.getElementById("telephone").value;
    var account = document.getElementById("account").value;
    var url = "/platform/withdraw/apply/list/download?coinId="+ coinId + "&startTime=" + startTime + "&endTime=" + endTime
        + "&id=" + id + "&status=" + status + "&telephone=" + telephone + "&account=" + account;
    $.ajaxUkey({
        url:url,
        type:"get",
        async:"true",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(responseText){
            if(responseText.success == false){
                layer.alert(responseText.msg, { title: '提示信息', icon: 2 });
            }else{
                window.location.href = url;
            }

        },
        error:function(){
            layer.alert("系统异常，导出失败", { title: '提示信息', icon: 2 });
        }
    });
}