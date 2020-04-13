loadTableWithoutColumns('/sdk/game/order/list','tableData', 'searchForm');

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒
function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(tableData)', function(obj){
            var data = obj.data;
            if(obj.event === 'audit'){
                openAudit(data.id);
            }
        });
    });
}

//打开发送消息窗口
function openAudit(id) {
    layer.open({
        type: 1,
        closeBtn: 0,
        title: '<b>订单审核</b>',
        area: ['500px', '370px'], //宽高
        content: $("#auditData")});
    $("#id").val(id);
    $("#auditOpinion").val("");
    layui.form.render(); //更新全部
}
//取消发送
function cancelModify(){
    layer.closeAll();
}
//发送消息
function audit(aid){
    var tip = layer.load(1, {shade: [0.8, '#393D49']});
    $.ajaxUkey({
        url : '/sdk/game/order/audit',
        type : 'POST',
        data : $("#" + aid).serialize(),
        dataType : 'json',
        success : function(data) {
            layer.close(tip);
            if (data.success) {
                layer.msg('审核成功！',function() {
                    layer.closeAll();
                    $(".layui-laypage-btn")[0].click();
                });
            } else {
                layer.msg(data.msg);
            }
        },
        error : function(e) {
            //最后数据加载完 让 loading层消失
            layer.close(tip);
            layer.msg('系统升级维护中，请稍后重试');
        }
    });
}