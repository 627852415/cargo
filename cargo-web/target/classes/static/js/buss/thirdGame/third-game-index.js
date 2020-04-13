loadTableWithoutColumns('/sdk/game/list','tableData', 'searchForm');

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒
function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(tableData)', function(obj){
            var data = obj.data;
            var dataJson = JSON.stringify({id: obj.data.id});
            if(obj.event === 'del'){
                delData("/sdk/game/delete", dataJson);
            }else if(obj.event === 'edit'){
                save(data.id);
            }else if(obj.event === 'isEnable') {
                isEnable(data, "100px");
            }
        });
    });
}

//保存，编辑
function save(id){
    var title = "新增游戏";
    var url = "/sdk/game/add";
    if (id) {
        title = "编辑游戏";
        url = "/sdk/game/info?id=" + id;
    }
    openModal(title, url, "600px", "500px");
}

//发送消息
function isEnable(data, offset){
    var status = data.status;
    status = status == 0 ? 1 :0;
    var operate = status == 0 ? "禁用" : "启用";
    layer.confirm('您确定要' + operate + '该游戏吗？', {icon: 3, offset: offset}, function (index) {
        $.ajaxUkey({
            url : '/sdk/game/update/status',
            type : 'POST',
            data : JSON.stringify({
                id: data.id,
                status: status
            }),
            dataType : 'json',
            contentType : 'application/json;charset=UTF-8',
            success : function(data) {
                if (data.success) {
                    var message = data.data;
                    if($.isEmptyObject(message)){
                        message = "操作成功";
                    }
                    layer.msg(message,
                        {
                            offset : '15px',
                            time : 1000
                        },
                        function() {
                            layer.close(index);
                            $(".layui-laypage-btn")[0].click();
                        });
                } else {
                    layer.msg(data.msg);
                }
            },
            error : function(e) {
                //最后数据加载完 让 loading层消失
                layer.msg('系统升级维护中，请稍后重试');
            }
        });
    });
}