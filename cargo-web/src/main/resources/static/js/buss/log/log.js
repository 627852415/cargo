//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'username',  title: '用户名', align:'center', sort: true}," +
    "                    {field: 'operation', title: '操作',align:'center'}," +
    "                    {field: 'method',  title: '请求方法', align:'center', sort: false}," +
    "                    {field: 'params', title: '请求参数', align:'center', sort: false}," +
    "                    {field: 'ip', title: 'IP地址', align:'center', sort: false}," +
    "                    {" +
    "                        field: 'createTime', title: '创建时间', align:'center', sort: true, templet: function (data) {" +
    "                            return formatDateL(data.createTime);" +
    "                        }" +
    "                    }" +
    "                ]" +
    "            ]");
//加载列表数据
loadTable("dataTable", '/sys/log/listPage', columns);

//时间插件格式化
initializeDatetime("createTimeStart");
initializeDatetime("createTimeEnd");
