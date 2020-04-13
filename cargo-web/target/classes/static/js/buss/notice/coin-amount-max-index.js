//列数据
var columns = eval("[" +
    "                [" +
    "                    {field: 'description',  title: '描述', align:'center', sort: true}," +
    "                    {field: 'ikey',  title: '币种名称', align:'center', sort: false}," +
    "                    {field: 'value', title: '每笔最大交易额', align:'center', sort: false}" +
    "                ]" +
    "            ]");
//加载列表数据
loadTable("dataTable", '/dict/coin/list', columns);

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
    var title = "新增币种交易监控";
    var url = "/dict/transaction/coin/add";
    if (id) {
        title = "编辑币种交易监控";
        url = "/dict/transaction/coin/update?gid=" + id;
    }
    openModal(title, url, "800px", "360px");
}

layui.use('form', function () {
    var form = layui.form;

    // 动态提示：1、勾选的情况弹出内容提示；2、其它情况提示勾选
    if ($("#noticeMessage").attr("checked") === "checked") {
        removeReadOnly("#telephone");
        alertTip("#telephone", "多个用英文逗号隔开");
    } else {
        alertTip("#telephone", "勾选短信才能输入");
    }
    if ($("#noticeEmail").attr("checked") === "checked") {
        removeReadOnly("#adminMail");
        alertTip("#adminMail", "多个用英文逗号隔开");
    } else {
        alertTip("#adminMail", "勾选邮箱才能输入");
    }
    if ($("#noticeEmail").attr("checked") === "checked") {
        removeReadOnly("#ccMail");
        alertTip("#ccMail", "多个用英文逗号隔开");
    } else {
        alertTip("#ccMail", "勾选邮箱才能输入");
    }
    if ($("#noticeTelegram").attr("checked") === "checked") {
        removeReadOnly("#botToken");
        alertTip("#botToken", "多个用英文逗号隔开");
    } else {
        alertTip("#botToken", "勾选电报才能输入");
    }
    if ($("#noticeTelegram").attr("checked") === "checked") {
        removeReadOnly("#chatId");
        alertTip("#chatId", "多个用英文逗号隔开");
    } else {
        alertTip("#chatId", "勾选电报才能输入");
    }

    form.on('checkbox(noticeCheckbox)', function (data) {
        var type = data.value;
        var checked = data.elem.checked;
        if (checked) {
            if (type == 1) {
                alertTip("#telephone", "多个用英文逗号隔开");
                $("#telephone").removeAttr("readOnly");
            } else if (type == 2) {
                alertTip("#botToken", "多个用英文逗号隔开");
                alertTip("#chatId", "多个用英文逗号隔开");
                $("#botToken").removeAttr("readOnly");
                $("#chatId").removeAttr("readOnly");
            } else if (type == 3) {
                alertTip("#adminMail", "多个用英文逗号隔开");
                alertTip("#ccMail", "多个用英文逗号隔开");
                $("#adminMail").removeAttr("readOnly");
                $("#ccMail").removeAttr("readOnly");
            }
        } else {
            if (type == 1) {
                alertTip("#telephone", "勾选短信才能输入");
                $("#telephone").attr("readOnly",'true');
            } else if (type == 2) {
                alertTip("#botToken", "勾选电报才能输入");
                alertTip("#chatId", "勾选电报才能输入");
                $("#botToken").attr("readOnly",'true');
                $("#chatId").attr("readOnly",'true');
            } else if (type == 3) {
                alertTip("#adminMail", "勾选邮箱才能输入");
                alertTip("#ccMail", "勾选邮箱才能输入");
                $("#adminMail").attr("readOnly",'true');
                $("#ccMail").attr("readOnly",'true');
            }
        }
    });


    //监听提交
    form.on('submit(transactionNotice)', function (data) {
        var arr = new Array();
        $("input:checkbox[name='noticeType']:checked").each(function(i){
            arr[i] = $(this).val();
        });
        data.field.noticeType  = arr.join(",");//将数组合并成字符串
        if ($.isEmptyObject(data.field.domain)) {
            data.field.domain = "per_amount_notice";
        }
        var formData = JSON.stringify(data.field)
        submit('/dict/notice/save', formData, null, true);
        return false;
    });
});
