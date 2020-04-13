//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'description',  title: '描述', align:'center', sort: true}," +
    "                    {field: 'domain', title: '模块',align:'center'}," +
    "                    {field: 'ikey',  title: 'key', align:'center', sort: false}," +
    "                    {field: 'value', title: 'value（如果是时间，单位为秒）', align:'center', sort: false}," +
    "                    {width:250, title: '操作', align:'center', sort: false,toolbar: '#barData'}" +
    "                ]" +
    "            ]");
//加载列表数据
loadTable("dataTable", '/dict/list', columns);

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(dataTable)', function(obj){
            var dataJson = JSON.stringify({gid: obj.data.gid});
            if(obj.event === 'del'){
                delData("/dict/delete", dataJson);
            } else if(obj.event === 'edit'){
                dictSave(obj.data.gid);
            }
        });
    });
}

//保存，编辑
function dictSave(id){
    var title = "新增字典";
    var url = "/dict/add";
    if (id) {
        title = "编辑字典";
        url = "/dict/info?gid=" + id;
    }
    openModal(title, url, "800px", "360px");
}


