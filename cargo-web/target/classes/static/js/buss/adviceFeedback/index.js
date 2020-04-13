var current = 1;
var size = 10;
var coinId;
var userId;
var type;
var respData;
layui.use(['form', 'laydate'], function () {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var createTime, updateTime;
    // 时间选择常规用法
    laydate.render({
        elem: '#createTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("开始日期不能大于当前日期");
                $("#createTime").val("");
                createTime = '';
                return;
            } else if (updateTime != "" && value > updateTime) {
                layer.msg("开始日期不能大于结束日期");
                $("#createTime").val("");
                createTime = '';
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#updateTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("结束日期不能大于当前日期");
                $("#updateTime").val("");
                updateTime = '';
                return;
            } else if (value < createTime) {
                layer.msg("结束日期不能小于开始日期");
                $("#updateTime").val("");
                updateTime = '';
                return;
            }
            updateTime = value;
        }
    });
});

loadTableWithoutColumns('/adviceFeedback/list', 'dataTable', 'searchForm');

function renderBtn(data) {
    var html = '';
    html += '<a href="javascript:;" onclick="detail(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm">详情</a>';
    return html;
}

//详情
function detail(obj) {
    var aid = $(obj).attr("aid");
    var e = $(this),
        i = "/adviceFeedback/detail?id=" + aid;
    t = "反馈详情";
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}

function download() {
    var url = "/adviceFeedback/download?" + $("#searchForm").serialize();
    $.ajaxUkey({
        url:url,
        type:"get",
        async:"true",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(responseText){
            if(responseText.success == false){
                layer.alert(responseText.msg, { title: '提示信息', icon: 2 });
            }else{
                window.location.href = url;
            }
        },
        error:function(){
            layer.alert("系统异常，导出失败", { title: '提示信息', icon: 2 });
        }
    });
}

