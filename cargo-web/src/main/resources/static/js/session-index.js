loadTableWithoutColumns('/session/list','tableData', 'searchForm');

//[[#{text.lazy.loading}]]
setTimeout('rowTool()', 1000); //延迟1秒
function rowTool(){
    layui.use('table', function (){
        //[[#{text.listen.for.line.tool.events}]]
        table.on('tool(tableData)', function(obj){
            var data = obj.data;
            if(obj.event === 'send'){
                openSend(data.account);
            }
        });
    });
}

//[[#{text.open.send.message.window}]]
function openSend(account) {
    layer.open({
        type: 1,
        closeBtn: 0,
        title: '<b>[[#{text.send.a.message}]]</b>',
        area: ['540px', '390px'], //[[#{text.width.height}]]
        content: $("#sendData")});
    $("#sendAccount").val(account);
    $("#sendContent").val("");
    layui.form.render(); //[[#{text.update.all}]]
}
//[[#{text.cancel.sending}]]
function cancelModify(){
    $("#sendData").attr("style","display: none;");
    layer.closeAll();
}
//[[#{text.send.a.message}]]
function send(){
    var tip = layer.load(1, {shade: [0.8, '#393D49']});
    var account = $("#sendAccount").val();
    var content = $("#sendContent").val();
    $.ajaxUkey({
        url : '/message/send',
        type : 'POST',
        data : {
            receiver: account,
            content: content
        },
        dataType : 'json',
        success : function(data) {
            layer.close(tip);
            if (data.success) {
                layer.msg('[[#{text.sent.successfully}]]！',function() {
                    $("#sendData").attr("style","display: none;");
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
            layer.msg('[[#{text.system.upgrade.and.maintenance,.please.try.again.later}]]');
        }
    });
}
