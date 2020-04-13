layui.use(['form', 'laydate'], function() {
    var laydate = layui.laydate;
    var form = layui.form;


});
loadTableWithoutColumns('/user/coin/snapshot/detail/data','dataTable', 'searchForm');

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

// //延时加载
// setTimeout('rowTool()', 1000); //延迟1秒
//
// function rowTool(){
//     layui.use('table', function (){
//         //监听行工具事件
//         table.on('tool(dataTable)', function(obj){
//
//         });
//     });
// }


//申请列表文件导出
function download() {
    var url = "/user/coin/snapshot/detail/excel?" + $("#searchForm").serialize();
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