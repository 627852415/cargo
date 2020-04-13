layui.use(['form', 'laydate'], function() {
});
loadTableWithoutColumns('/platform/withdraw/config/otc/limit','dataTable', 'searchForm');

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(dataTable)', function (obj) {
            if (obj.event === 'edit') {
                dictSave(obj.data.gid);
            }
        });
    });
}

//保存，编辑
function dictSave(id){
    var title = "编辑币种交易监控";
    var url = "/platform/withdraw/config/otc/limit/edit?gid=" + id;
    openModal(title, url, "800px", "250px");
}

