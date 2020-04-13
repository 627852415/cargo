//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'id', title: '编号',align:'center'}," +
    "                    {field: 'coinName', title: '币种名称',align:'center'}," +
    "                    {field: 'minMoney', title: '最小限额',align:'center'}," +
    "                    {field: 'maxMoney', title: '最大限额',align:'center'}," +
    "                    {field: 'quickArrivalFee', title: '快速到账手续费',align:'center', templet: function (data) {" +
    "                            return data.quickArrivalFee * 100 + '%';" +
    "                        }" +
    "                    }," +
    "                    {field: 'ordinaryArrivalFee', title: '普通到账手续费',align:'center', templet: function (data) {" +
    "                            return data.ordinaryArrivalFee * 100 + '%';" +
    "                        }" +
    "                    }," +
    "                    {field: 'valid', title: '状态',align:'center', templet: function (data) {" +
    "                            return data.valid ? '已启用':'未启用';" +
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
loadTable("dataTable", '/otc/withdraw/config/listPage', columns);

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool() {
    layui.use(['table', 'jquery', 'layer'], function () {
        //监听行工具事件
        table.on('tool(dataTable)', function (obj) {
            if (obj.event === 'del') {
                var dataJson = JSON.stringify({id: obj.data.id});
                delData("/otc/withdraw/config/delete", dataJson);
            } else if (obj.event === 'edit') {
                dictSave(obj.data.id);
            } else if (obj.event === 'enable') {
                dictEnable("/otc/withdraw/config/enable", JSON.stringify({id: obj.data.id}));
            }
        });

    });

}


//保存，编辑
function dictSave(id) {
    var title = "新增";
    var url = "/otc/withdraw/config/modifyPage";
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

layui.use(['jquery', 'layer', 'form', 'upload'], function () {
    var form = layui.form;

    //监听提交
    form.on('submit(sub-btn)', function (data) {
        var formData = JSON.stringify(data.field)
        submit('/otc/withdraw/config/saveOrUpdate', formData);
        return false;
    });


});

function dictEnable(url, jsonData) {
    $.ajaxUkey({
        url: url,
        type: 'POST',
        data: jsonData,
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (data) {
            if (data.success) {
                var message = data.data;
                if ($.isEmptyObject(message)) {
                    message = "操作成功";
                }
                layer.msg(message,
                    {
                        offset: '15px',
                        time: 1000
                    },
                    function () {
                        window.location.reload();
                    });
            } else {
                layer.msg(data.msg);
            }
        },
        error: function (e) {
            //最后数据加载完 让 loading层消失
            layer.msg('系统升级维护中，请稍后重试');
        }
    });

}
