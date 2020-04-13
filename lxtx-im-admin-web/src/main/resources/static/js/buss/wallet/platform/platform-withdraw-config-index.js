//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'coinId', title: '编号',align:'center'}," +
    "                    {field: 'coinName', title: '币种名称',align:'center'}," +
    "                    {field: 'validTime',  title: '有效时长', align:'center', sort: true}," +
    "                    {field: 'minMoney', title: '最小限额',align:'center'}," +
    "                    {field: 'maxMoney', title: '最大限额',align:'center'}," +
    "                    {field: 'feeType',  title: '手续费类型', align:'center', sort: false, templet: function (data) {" +
    "                           if (data.feeType === 1) {" +
    "                               return '百分比';" +
    "                            } else {" +
    "                               return '固定值';" +
    "                            }" +
    "                        }" +
    "                    }," +
    "                    {field: 'feeValue', title: '提现手续费',align:'center'}," +
    "                    {field: 'idx', title: '排序加权',align:'center'}," +
    "                    {field: 'display', title: '是否显示', align:'center', sort: false, templet: function (data) {" +
    "                            return showDisplayText(data.display);" +
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
loadTable("dataTable", '/platform/withdraw/config/list', columns);

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use(['table','jquery','layer'], function (){
        //监听行工具事件
        table.on('tool(dataTable)', function(obj){
            if(obj.event === 'del'){
                var dataJson = JSON.stringify({id: obj.data.id});
                delData("/platform/withdraw/config/delete", dataJson);
            } else if(obj.event === 'edit'){
                dictSave(obj.data.id);
            }
        });

    });

}


//保存，编辑
function dictSave(id){
    var title = "新增";
    var url = "/platform/withdraw/config/modifyPage";
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

layui.use(['jquery','layer','form', 'upload'], function () {
    var form = layui.form;

    //监听提交
    form.on('submit(sub-btn)', function (data) {
        var formData = JSON.stringify(data.field)
        submit('/platform/withdraw/config/save', formData);
        return false;
    });


});
