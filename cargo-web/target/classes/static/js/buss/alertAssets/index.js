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

loadTableWithoutColumns('/alertAssets/list', 'dataTable', 'searchForm');

function getSubTypeName(subType) {
    if (subType != null && subType != 'undefined') {
        var subTypeStr = jQuery('input[name=subTypeMapId]').val();
        var subTypeMap = JSON.parse(subTypeStr);
        return subTypeMap[subType];
    } else {
        return "无";
    }
}

function getTypeName(type) {
    if (type == 1) {
        return "资产频繁操作配置";
    } else {
        return "平台资产报警配置";
    }
}

function getStatusName(enable) {
    var statusStr = "";
    if (enable == 0) {
        statusStr = "<font color='#dc143c'>禁用</font>";
    } else {
        statusStr = "<font color='#ffd700'>启用</font>";
    }
    return statusStr;
}

function renderBtn(data) {
    if (data.enable == 0) {
        var html = '';
        if (hasAddPermission) {
            html += '<a href="javascript:;" onclick="add(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm">编辑</a>';
        }
        if (hasUpdatePermission) {
            html += '<a href="javascript:;" onclick="saveState(this)" aid="' + data.id + '" atype="1" class="layui-btn layui-btn-sm layui-btn-warm">启用</a>';
        }
        return html;
    } else if (data.enable == 1) {
        var html = '';
        if (hasAddPermission) {
            html += '<a href="javascript:;"  onclick="add(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm">编辑</a>';
        }
        if (hasUpdatePermission) {
            html += '<a href="javascript:;" onclick="saveState(this)" aid="' + data.id + '" atype="0" class="layui-btn layui-btn-sm layui-btn-danger">禁用</a>';
        }
        return html;
    } else {
        return "无";
    }
}

function saveState(id) {
    var tip = "";
    var type = jQuery(id).attr('atype');
    var pkId = jQuery(id).attr('aid');
    var enable = false;
    if (type == 0) {
        tip = '您确定要禁用吗？';
        enable = 'false';
    } else {
        tip = '您确定要启用吗？';
        enable = 'true';
    }
    layer.confirm('您确定要启用吗？', {icon: 3}, function (index) {
        $.ajaxUkey({
            url: '/alertAssets/update',
            type: "POST",
            data: JSON.stringify({
                "id": pkId, "enable": enable
            }),
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            success: function (result) {
                if (result.success) {
                    if (!$.isEmptyObject(result.data)) {
                        layer.alert(result.data, {title: '提示信息', icon: 5});
                        return;
                    }
                    layer.alert('操作成功！', {title: '提示信息', icon: 1}, function (index) {
                        layer.close(index);
                        $(".layui-laypage-btn")[0].click();
                    });
                } else {
                    if (!$.isEmptyObject(result.msg)) {
                        layer.alert(result.msg, {title: '提示信息', icon: 2});
                        return;
                    }
                    layer.alert('系统异常，操作失败！', {title: '提示信息', icon: 2});
                }
            }
        });
    });
}


//新增或编辑
function add(obj) {
    var aid = $(obj).attr("aid");
    var url;
    var title = "新增";
    if (aid != null && aid != undefined) {
        url = "/alertAssets/edit?id=" + aid.toString();
        title = "编辑";
    } else {
        url = "/alertAssets/add";
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
