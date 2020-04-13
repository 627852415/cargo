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
                layer.msg("[[#{text.start.date.cannot.be.greater.than.the.current.date}]]");
                $("#startTime").val("");
                return;
            } else if (value > endTime) {
                layer.msg("[[#{text.start.date.cannot.be.greater.than.end.date}]]");
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
                layer.msg("[[#{text.end.date.cannot.be.greater.than.the.current.date}]]");
                $("#endTime").val("");
                return;
            } else if (value < startTime) {
                layer.msg("[[#{text.end.date.cannot.be.less.than.start.date}]]");
                $("#endTime").val("");
                return;
            }
            endTime = value;
        }
    });

});
loadTableWithoutColumns('/user/authentication/page','dataTable', 'searchForm');

//[[#{text.lazy.loading}]]
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use('table', function (){
        //监听商家状态[[#{text.operating}]]
        form.on('switch(statusFilter)', function(obj){
            var status = this.value == 0 ? 1 : 0;
            var id = obj.elem.attributes.aid.value;
            var formData = JSON.stringify({"status": status, "id": id});
            sendAjaxPost("/user/authentication/update/status", formData);
        });
        //[[#{text.listen.for.line.tool.events}]]
        table.on('tool(dataTable)', function (obj) {
            if (obj.event === 'detail') {
                userAuthenticationDetail("/user/authentication/detail?id="+obj.data.id+"&edit=false");
            }else if(obj.event === "editBtn"){
                userAuthenticationEdit("/user/authentication/detail?id="+obj.data.id+"&edit=true");
            }else if(obj.event === 'delBtn'){
                layer.confirm("[[#{text.operation.confirm.delete}]]", {icon: 3, title: '提示', offset: 100}, function (index) {
                    //[[#{text.close.popup.layer}]]
                    layer.close(index);
                    var tishi = layer.load(1, {shade: [0.8, '#393D49']});
                    var tmpId = obj.data.id;
                    $.ajaxUkey({
                        data: JSON.stringify({"id": tmpId}),
                        type: 'POST',
                        dataType: "JSON",
                        contentType: 'application/json;charset=UTF-8',
                        url: '/user/authentication/del',
                        success: function (data) {
                            if (data.success) {
                                layer.msg("[[#{text.successful.operation}]]");
                                $(".layui-laypage-btn")[0].click();
                            } else {
                                layer.msg(data.msg);
                            }
                            //最后数据加载完 让 loading层消失
                            layer.close(tishi);
                        },
                        error: function (e) {
                            layer.msg('[[#{text.system.upgrade.and.maintenance,.please.try.again.later}]]');
                            //最后数据加载完 让 loading层消失
                            layer.close(tishi);
                        }
                    });
                });
            }
        });
    });
}

//用户认证[[#{text.details}]]
function userAuthenticationDetail(url){
    var e = $(this),
        i = url;
    t = "[[#{text.user.authentication.details}]]";
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}

//用户认证[[#{text.edit}]]
function userAuthenticationEdit(url){
    var e = $(this),
        i = url;
    t = "[[#{text.user.authentication.editor}]]";
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}
