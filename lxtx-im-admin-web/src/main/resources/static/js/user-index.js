layui.use('layer', function() {
    var layer = layui.layer;
});
layui.use('form', function() {

    var form = layui.form;
    //监听提交
    form.on('submit(sub-btn)', function (data) {

        var formData = data.field;



        var account = $.trim(formData.account.replace(/[\r\n]/g,""));

        formData.account = account;
        console.info(formData);
        console.info(account);


        if ( formData.userType=='part' && ( account=='' || account== null)) {
            layer.alert('请填写用户account, 多个用,分割', {title: '提示信息', icon: 1});
            return false;
        }


        layer.msg("同步命令已提交。");
        $.ajaxUkey({
            url: '/user/synchronize/yunxin',
            type: "POST",
            data: formData,
            dataType: "json",
            success: function (result) {
                if (result.success) {
                    if (!$.isEmptyObject(result.data)) {
                        layer.alert(result.data, {title: '提示信息', icon: 5});
                        return;
                    }
                    layer.alert('同步网易云信用户操作成功！', {title: '提示信息', icon: 1},function (index) {
                        layer.close(index);
                        // 刷新当前页
                        $(".layui-laypage-btn").click();;
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

        // var formData = data.field;
        // var id = formData.id;
        // var url = "/coinCharge/save";
        // if(id){
        //     url = "/coinCharge/update";
        // }
        // $.ajaxUkey({
        //     data: JSON.stringify(formData),
        //     type: "POST",
        //     dataType: "JSON",
        //     contentType:'application/json;charset=UTF-8',
        //     url: url,
        //     success: function (result) {
        //         if (result.success) {
        //             layer.alert('操作成功！', {title: '提示信息', icon: 6}, function (index) {
        //                 parent.layer.closeAll();
        //                 parent.$(".layui-btn")[0].click();
        //             });
        //         } else if (result.msg != null) {
        //             layer.msg(result.msg);
        //         } else {
        //             // 提示失败
        //             layer.alert('系统异常，操作失败!', {title: '提示信息', icon: 5});
        //         }
        //     }
        // });
        return false;
    });

});
var current = 1;
var size = 10;
$(function () {
    search(current, size);
})

var respData;

function search(current, size){
    if(!size){
        size = 10;
    }
    if(!current){
        current = 1;
    }

    var data = $.param({"current":current, "size":size}) + "&" + $("#searchForm").serialize();
    $.ajax({//异步请求返回给后台
        url: '/user/listPage',
        type: 'POST',
        data: data,
        dataType: 'json',
        success: function (data) {
            if (data.success) {
                respData = data.data.records;
                if(respData.length < 1){
                    $("#content").html('<tr><td colspan="11" style="color: red;text-align: center"><b>未查询到相关数据</b></td></tr>');
                    $("#page").html("");
                    return;
                }
                var html = '',trStart = '<tr><th>',  trEnd = '</th></tr>',thStart = '<th>', th = '</th><th>', thEnd = '</th>' ;
                $.each(respData, function (i, item) {
                    canEdit = false;
                    var handle = '<div class="layui-btn-group">' +
                        '<button class="layui-btn" onclick="group(\'' + item.account + '\')">所在群组</button>' +
                        '<button class="layui-btn" account="'+ item.account + '" userName="' + item.name + '" telephone="' + item.telephone + '" countryCode="' + item.countryCode + '"  onclick="openModify(this)">修改</button>' +
                        '<button class="layui-btn " onclick="resetPsd(\'' + item.account + '\')">密码重置</button>';
                    var state = "";
                    if(item.state == "0"){
                        state = "正常";
                        handle += '<button class="layui-btn" onclick="operate(\'' + item.account + '\', \'1\', \'禁用\')">禁用</button>';
                    }
                    if(item.state == "1"){
                        state = "禁用";
                        handle += '<button class="layui-btn" onclick="operate(\'' + item.account + '\', \'0\', \'启用\')">启用</button>';
                    }

                    handle += '</div>';
                    var  userAvatarUrl = item.userAvatarUrl == undefined ? "" : '<img width="40px" height="40px" src="' + item.userAvatarUrl + '">';
                    html += trStart
                        + userAvatarUrl + th
                        + item.account + th
                        + (item.name == undefined ? "" : item.name) + th
                        + (item.countryCode == undefined ? "" : item.countryCode)+ th
                        + (item.telephone == undefined ? "" : item.telephone) + th
                        + (item.createTime == undefined ? "" : formatDateL(item.createTime)) + th
                        + (item.createIp == undefined ? "" : item.createIp) + th
                        + (item.loginTime == undefined ? "" : formatDateL(item.loginTime)) + th
                        + (item.loginIp == undefined ? "" : item.loginIp) + th
                        + state + th
                        + handle +trEnd
                    ;
                });
                $("#content").html(html);
                <!------- 分页 -------->
                layui.use(['laypage', 'layer'], function () {
                    var laypage = layui.laypage
                        , layer = layui.layer;
                    laypage.render({
                        elem: 'page'
                        , limit: size
                        , curr: current
                        , count: data.data.total
                        , layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                        , jump: function (obj, first) {
                            this.current = obj.curr;
                            this.size = obj.limit;
                            if(!first){
                                search(obj.curr, obj.limit);
                            }
                        }
                    });
                });
            }else{
                $("#content").html('<tr><td colspan="11" style="color: red;text-align: center"><b>' + data.msg + '</b></td></tr>');
            }
        }
    });
}

//所在群组
function group(account){
    var e = $(this),
        i = "/user/group?account=" + account;
    t = "群列表";
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}

function operate(account, state, stateValue){
    layer.confirm('请确定是否' + stateValue + "该账号？", {icon: 3, title:'提示'}, function(index){
        //关闭弹出层
        layer.close(index);

        var tishi = layer.load(1, {shade: [0.8, '#393D49']})
        $.ajaxUkey({
            url : '/user/operateState',
            type : 'POST',
            data : {
                account : account,
                state: state
            },
            dataType : 'json',
            success : function(data) {
                if (data.success) {
                    layer.msg(data.data);
                    $(".layui-laypage-btn")[0].click();
                } else {
                    layer.msg(data.msg);
                }
                //最后数据加载完 让 loading层消失
                layer.close(tishi);
            },
            error : function(e) {
                layer.msg('系统升级维护中，请稍后重试');
                //最后数据加载完 让 loading层消失
                layer.close(tishi);
            }
        });
    });
}


function resetPsd(account){
    layer.confirm('确定将密码重置为m123456吗', {icon: 3, title:'提示'}, function(index){
        //关闭弹出层
        layer.close(index);

        var tishi = layer.load(1, {shade: [0.8, '#393D49']})
        $.ajaxUkey({
            url : '/user/resetPsd',
            type : 'POST',
            data : {
                account : account
            },
            dataType : 'json',
            success : function(data) {
                if (data.success) {
                    layer.msg(data.data);
                } else {
                    layer.msg(data.msg);
                }
                //最后数据加载完 让 loading层消失
                layer.close(tishi);
            },
            error : function(e) {
                layer.msg('系统升级维护中，请稍后重试');
                //最后数据加载完 让 loading层消失
                layer.close(tishi);
            }
        });
    });
}

function openModify(obj) {
    var countryCode = $(obj).attr("countryCode");
    countryCode = countryCode == "undefined" ? "": countryCode;
    //后台动态获取所有国际区号，并设置选中状态
    $.ajax({
        url : '/user/global/code',
        type : 'POST',
        dataType : 'json',
        success : function(data) {
            if (data.success) {
                // console.info(data);
                var respData = data.data.list;
                var html = '<select name="countryCode" lay-verify="" lay-search="">';
                $.each(respData, function (i, item) {
                    //设置为选中状态
                    if(countryCode==item.countryCode){
                        html += ' <option selected value="' + item.countryCode + '">'  + item.cnName + '</option>';
                    }else{
                        html += ' <option value="' + item.countryCode + '">'  + item.cnName + '</option>';
                    }
                });
                html += "</select>";
                layer.open({
                    type: 1,
                    closeBtn: 0,
                    title: '<b>用户信息修改</b>',
                    area: ['540px', '490px'], //宽高
                    content: $("#modifyData")});

                var account = $(obj).attr("account");
                var userName = $(obj).attr("userName");
                userName = userName == "undefined" ? "": userName;
                var gender = $(obj).attr("gender");
                gender = gender == "undefined" ? "": gender;
                var telephone = $(obj).attr("telephone");
                telephone = telephone == "undefined" ? "": telephone;
                $("#accountDis").val(account);
                $("#account").val(account);
                $("#name").val(userName);
                // $("#gender").val(gender);
                $("#telephone").val(telephone);
                $("#countryCode").html(html);
                layui.form.render(); //更新全部
                layui.form.render('select'); //刷新select选择框渲染
            } else {
                layer.msg(data.msg);
            }
        },
        error : function(e) {
            layer.msg('获取国际区号列表失败！');
        }
    });
}

function modify(fid){
    var email = $("#email").val();
    if(email){
        var patrn = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!patrn.test(email)) {
            layer.msg("邮箱格式格式错误");
            return false;
        }
    }
    var tishi = layer.load(1, {shade: [0.8, '#393D49']});
    var data = $("#" + fid).serialize();
    $.ajaxUkey({
        url : '/user/modify',
        type : 'POST',
        data : data,
        dataType : 'json',
        success : function(data) {
            layer.close(tishi);
            if (data.success) {
                layer.msg(data.data,
                    {
                        offset : '15px',
                        time : 1000
                    },
                    function() {
                        $("#modifyData").attr("style","display: none;");
                        layer.closeAll();
                        $(".layui-laypage-btn")[0].click();
                    });
            } else {
                layer.msg(data.msg);
            }
        },
        error : function(e) {
            //最后数据加载完 让 loading层消失
            layer.close(tishi);
            layer.msg('系统升级维护中，请稍后重试');
        }
    });
}
function cancelModify(){
    $("#modifyData").attr("style","display: none;");
    layer.closeAll();
}



function openlayui() {

    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
        var form = layui.form;

        layer.open({
            type: 1,
            closeBtn: 1,
            moveType: 0,
            move: false,
            fixed: false,
            resize: false,
            shade: 0,
            area: ['720px', '580px'],
            content: $("#syncData")
        });

        return false;
    });
}

//取消修改
function cancelSync() {
    $("#syncData").attr("style", "display: none;");
    layer.closeAll();
}


