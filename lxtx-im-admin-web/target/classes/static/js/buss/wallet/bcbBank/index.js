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

loadTableWithoutColumns('/bcb/bankcard/type/list', 'dataTable', 'searchForm');

function getTypeName(obj) {
    if (obj == 1) {
        return "银联";
    } else {
        return "visa";
    }
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

function renderImg(url) {
    return '<img class="img" src="' + url + '" height="30" width="30" lay-event="click" onclick="showBig(this)"/>';
}

function renderBtn(data) {
    var html = '';
    html += '<a href="javascript:;" onclick="add(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm">编辑</a>';
    html += '<a href="javascript:;" onclick="del(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm layui-btn-danger">删除</a>';
    return html;
}

//新增或编辑
function add(obj) {
    var aid = $(obj).attr("aid");
    var url;
    var title = "新增";
    if (aid != null && aid != undefined) {
        url = "/bcb/bankcard/type/edit?id=" + aid.toString();
        title = "编辑";
    } else {
        url = "/bcb/bankcard/type/add";
    }
    layer.open({
        type: 2,
        closeBtn: 1,
        offset: '50px',
        title: '<b>' + title + '</b>',
        area: ['700px', '700px'], //宽高
        content: url
    });
}

function del(obj) {
    var aid = $(obj).attr("aid");
    var dataJson = JSON.stringify({id: aid});
    delData("/bcb/bankcard/delete", dataJson);
}
