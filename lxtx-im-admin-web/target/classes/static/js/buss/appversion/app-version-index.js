//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'version',  title: '版本号', align:'center', sort: true}," +
    "                    {field: 'versionName', title: '版本名称',align:'center'}," +
    "                    {field: 'description', title: '更新内容',align:'center'}," +
    "                    {field: 'phoneType', title: '手机类型',align:'center', templet: function (data) {" +
    "                           if (data.phoneType === 0) {" +
    "                               return '安卓';" +
    "                            } else {" +
    "                               return 'IOS';" +
    "                            }" +
    "                        }" +
    "                    }," +
    "                    {field: 'url',  title: '下载地址', align:'center', sort: false}," +
    "                    {field: 'force',  title: '是否强制更新', align:'center', sort: false, templet: function (data) {" +
    "                           if (data.force === 0) {" +
    "                               return '否';" +
    "                            }else if (data.force === 1) {" +
    "                               return '是';" +
    "                            }" +
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
loadTable("dataTable", '/appVersion/listPage', columns);

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(dataTable)', function(obj){
            if(obj.event === 'del'){
                var dataJson = JSON.stringify({id: obj.data.id});
                delData("/appVersion/delete", dataJson);
            } else if(obj.event === 'edit'){
                dictSave(obj.data.id);
            }
        });
    });
}

//保存，编辑
function dictSave(id){
    var title = "新增";
    var url = "/appVersion/modifyPage";
    if (id) {
        title = "编辑";
        url += "?id=" + id;
    }
    openModal(title, url, "680px", "500px");
}


