//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'type', title: '游戏类型',align:'center', templet: function (data) {" +
    "                           return data.typeName;" +
    "                        }" +
    "                    }," +
    "                    {field: 'name',  title: '游戏名称', align:'center', sort: true}," +
    "                    {field: 'remarks', title: '游戏备注',align:'center'}," +
    "                    {field: 'icon',  title: '游戏图片', align:'center', sort: false, templet: function (data) {" +
    "                           if (data.icon) {" +
    "                               return '<img class=\"img\" src=\"' + data.icon + '\" height=\"30\" width=\"30\" lay-event=\"click\" onclick=\"showBig(this)\"/>'" +
    "                            }" +
    "                        }" +
    "                    }," +
    "                    {field: 'image',  title: '游戏类型图标', align:'center', sort: false, templet: function (data) {" +
    "                           if (data.image) {" +
    "                               return '<img class=\"img\" src=\"' + data.image + '\" height=\"30\" width=\"30\" onclick=\"showBig(this)\"/>'" +
    "                            } else {" +
    "                               return '';" +
    "                           }" +
    "                        }" +
    "                    }," +
    "                    {field: 'display', title: '是否显示', align:'center', sort: false, templet: function (data) {" +
    "                            return showDisplayText(data.display);" +
    "                        }" +
    "                    }," +
    "                    {field: 'createTime', title: '创建时间', align:'center', sort: false, templet: function (data) {" +
    "                            return formatDateL(data.createTime);" +
    "                        }" +
    "                    }," +
    "                    {field: 'updateTime', title: '更新时间', align:'center', sort: false, templet: function (data) {" +
    "                            return formatDateL(data.updateTime);" +
    "                        }" +
    "                    }," +
    "                    {width:250, title: '操作', align:'center', sort: false,toolbar: '#barData'}" +
    "                ]" +
    "            ]");
//加载列表数据
loadTable("dataTable", '/game/listPage', columns);

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use(['table','jquery','layer'], function (){
        //监听行工具事件
        table.on('tool(dataTable)', function(obj){
            if(obj.event === 'del'){
                var dataJson = JSON.stringify({id: obj.data.id});
                delData("/game/delete", dataJson);
            } else if(obj.event === 'edit'){
                dictSave(obj.data.id);
            }
        });

    });

}

/**
 * 预览大图
 * @param obj
 */
function showBig(obj) {
    layui.use(['jquery','layer'], function (){
        var imgSrc=$(obj).attr('src');
        layer.open({
            type:1
            ,title:false
            ,closeBtn:0
            ,skin:'layui-layer-nobg'
            ,shadeClose:true
            ,content:'<img src="'+imgSrc+'" style="max-height:600px;max-width:100%;">'
            ,scrollbar:false
        });
    });
}

//保存，编辑
function dictSave(id){
    var title = "新增";
    var url = "/game/modifyPage";
    if (id) {
        title = "编辑";
        url += "?id=" + id;
    }
    openModalWithinScroll(title, url, "680px", "580px");
}

/**
 * 根据状态值获取显示文本
 * @param display
 * @returns {string}
 */
function showDisplayText(display) {
    var text = "未定义";
    if (display === false) {
        text = "<font color='#dc143c'>否</font>";
    } else if (display === true) {
        text = "是";
    }
    return text;
}