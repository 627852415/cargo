var _datetime = new Date();
var a = '[[#{text.operation.name}]]'
//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'coinName',  title: '币种', align:'center', sort: false}" +
    "                    ,{title: 'startTime',title: '状态',align:'center', templet: function (data) {" +
    "                           if (compareCalendar(_datetime, data.startTime)) {" +
    "                               return '未开始';" +
    "                            } else if (compareCalendar(_datetime, data.endTime)) {" +
    "                               return '进行中';" +
    "                            } else {" +
    "                               return '已结束';" +
    "                            }" +
    "                        }" +
    "                     }" +
    "                    ,{field: 'type',  title: '空投对象', align:'center', sort: false, templet: function (data) {" +
    "                           if (data.type === 1) {" +
    "                               return '新用户';" +
    "                            } else {" +
    "                               return '全站用户';" +
    "                            }" +
    "                        }" +
    "                    }" +
    "                    ,{field: 'endTime', title: '活动时间', width:320, align:'center', sort: false, templet: function (data) {" +
    "                            var startTime =  formatDateL(data.startTime);" +
    "                            var endTime =  formatDateL(data.endTime);" +
    "                            var time =  startTime + '~' + endTime;" +
    "                            return time;" +
    "                        }" +
    "                    }" +
    "                    ,{field: 'unlockTime', title: '解锁时间', align:'center', sort: false, templet: function (data) {" +
    "                            return formatDateL(data.unlockTime);" +
    "                        }" +
    "                    }" +
    "                    ,{field: 'amount', title: '空投总量', align:'center', sort: false}" +
    "                    ,{field: 'unitAmount', title: '单位空投数量', align:'center', sort: false}" +
    "                    ,{width:250, title: '[[#{text.operation.name}]]', align:'center', sort: false,toolbar: '#barData'}" +
    "                ]" +
    "            ]");
//加载列表数据
loadTable("dataTable", '/airdrop/list', columns);

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒
function rowTool(){
    layui.use('table', function (){
        //监听行工具事件
        table.on('tool(dataTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                var jsonData = JSON.stringify({id: obj.data.id});
                delData("/airdrop/delete" , jsonData);
            } else if(obj.event === 'edit'){
                airdropSave(obj.data.id);
            } else if(obj.event === 'detail'){
                openTab("空投详情", "/airdrop/detail?id=" + obj.data.id);
            }
        });
    });
}

//保存，编辑
function airdropSave(id){
    var title = "新增空投";
    var url = "/airdrop/save";
    if (id) {
        title = "编辑空投";
        url += "?id=" + id;
    }
    openTab(title, url);
}