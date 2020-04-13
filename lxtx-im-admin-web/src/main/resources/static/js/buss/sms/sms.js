//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'countryCode',  title: '国际简码', align:'center', sort: true}," +
    "                    {field: 'telephone', title: '手机号码',align:'center', sort: true}," +
    "                    {field: 'captcha',  title: '短信验证码', align:'center', sort: false}," +
    "                    {field: 'typeName', title: '类型',align:'center', sort: false}," +
    "                    {field: 'smsMsg', title: '短信内容',align:'center', sort: false}," +
    "                    {field: 'smsRespMsg', title: '返回结果',align:'center', sort: true}," +
    "                    {field: 'stateName', title: '发送状态',align:'center', sort: true}," +
    "                    {" +
    "                        field: 'createTime', title: '创建时间', align:'center', sort: true, templet: function (data) {" +
    "                            return formatDateL(data.createTime);" +
    "                        }" +
    "                    }" +
    "                ]" +
    "            ]");


var table;
/**
 * 列表加载
 * @param eid   表格ID
 * @param url  请求地址
 * @param columns 列表字段
 */
function loadMyTable(eid, url, columns, sortFild, sortOrder) {

    layui.use('table', function (){
        table = layui.table;
        table.render({
            elem: '#' + eid,
            url: url,
            method: 'post',
            toolbar: '#toolbarData',
            // defaultToolbar: ['filter', 'print', 'exports'],//筛选、打印、导出
            defaultToolbar: ['filter', 'exports'],
            request:{
                pageName:'current',
                limitName:'size'
            },
            parseData : function(res) {
                var msg,code = "1",records,total=0;
                if(!res.success || $.isEmptyObject(res.data)){
                    if(res.msg){
                        msg = '<b style="color: red">' + res.msg + '</b>';
                    }else{
                        msg = '<b style="color: red">未查询到相关数据</b>';
                    }
                }else{
                    records = res.data.records;
                    if(records !=undefined &&  records.length > 0){
                        code = '0';
                        total = res.data.total;

                        $.each( records, function( key, value ) {
                            switch (value.type) {
                                case "1": value.typeName = "登录";
                                    break;
                                case "2": value.typeName = "注册";
                                    break;
                                case  "3": value.typeName = "修改密码";
                                    break;
                                case  "4": value.typeName = "修改密码";
                                    break;
                                case "5": value.typeName = "忘记支付密码重置";
                                    break;
                            }
                            //设置状态名
                            switch (value.state) {
                                case "1": value.stateName = "成功";
                                    break;
                                case "0": value.stateName = "失败";
                                    break;
                            }


                        });

                    }else{
                        msg = '<b style="color: red">未查询到相关数据</b>';
                    }
                }
                return {
                    "code" : code,
                    "msg" : msg,
                    "count" : total,
                    "data" : records
                }
            },
            cols: columns,
            id: 'dataReload' ,
            page: true
        })
    });
}


//加载列表数据
loadMyTable("dataTable", '/sms/list', columns);


layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var createTime, endTime;

    laydate.render({
        elem: '#startTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("开始日期不能大于当前日期");
                $("#startTime").val("");
                createTime = '';
                return;
            } else if (endTime != "" && value > endTime) {
                layer.msg("开始日期不能大于结束日期");
                $("#startTime").val("");
                createTime = '';
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#endTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("结束日期不能大于当前日期");
                $("#endTime").val("");
                endTime = '';
                return;
            } else if (value < createTime) {
                layer.msg("结束日期不能小于开始日期");
                $("#endTime").val("");
                endTime = '';
                return;
            }
            endTime = value;
        }
    });

});



//时间插件格式化
// initializeDatetime("createTimeStart");
// initializeDatetime("createTimeEnd");
