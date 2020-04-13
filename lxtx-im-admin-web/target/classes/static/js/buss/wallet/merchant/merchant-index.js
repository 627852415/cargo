layui.use(['form', 'laydate'], function() {
    var laydate = layui.laydate;
    var curDate = formatDate(new Date);
    var startTime,endTime;
    var updateTime,updateEndTime;

    // 手续费统计
    laydate.render({
        elem: '#startTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("开始日期不能大于当前日期");
                $("#startTime").val("");
                return;
            } else if (value > endTime) {
                layer.msg("开始日期不能大于结束日期");
                $("#startTime").val("");
                return;
            }
            startTime = value;
        }
    });
    laydate.render({
        elem: '#endTime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("结束日期不能大于当前日期");
                $("#endTime").val("");
                return;
            } else if (value < startTime) {
                layer.msg("结束日期不能小于开始日期");
                $("#endTime").val("");
                return;
            }
            endTime = value;
        }
    });

});
loadTableWithoutColumns('/merchant/list','dataTable', 'searchForm');

//延时加载
setTimeout('rowTool()', 1000); //延迟1秒

function rowTool(){
    layui.use('table', function (){
        //监听商家状态操作
        form.on('switch(statusFilter)', function(obj){
            var status = this.value == 0 ? 1 : 0;
            var id = obj.elem.attributes.aid.value;
            var formData = JSON.stringify({"status": status, "id": id});
            sendAjaxPost("/merchant/update/status", formData);
        });
        //监听行工具事件
        table.on('tool(dataTable)', function (obj) {
            if (obj.event === 'detail') {
                // detail("/merchant/detail?id="+obj.data.id);
                merchantDetail("/merchant/detail?id="+obj.data.id);
            }else if(obj.event === "editBtn"){
                if(obj.data.certificateStatus==3){
                    merchantEdit("/merchant/edit?id="+obj.data.id);
                }else{
                    editMerchantConfig(obj);
                }
            }
        });
        form.on('submit(edit)', function (data) {
            var sle = $("#state")
            var cycleId = sle.attr("cycleId")
            var userId = sle.attr("userId")
            var cycle = sle.val()
            console.log(cycle)
            var list = new Array()
            var userId = "";
            $('#flag').find('.layui-form-item').each(function () {
                var a = $(this).find('.layui-inline').find('input').each(function (i, item) {
                    var coinId = $(item).attr("coinId")
                    var id = $(item).attr("id")
                    var feeRate = $(item).val()
                    userId = $(item).attr("name")
                    if (id == null || id == "undefined") {
                        id = null;
                    }
                    list.push({id: id, feeRate: feeRate + "%", coinId: coinId})
                })
            })
            var editData = JSON.stringify({userId: userId, cycleId: cycleId, cycle: cycle, coinList: list})
            $.ajaxUkey({
                url: "/billcheck/edit",
                type: 'POST',
                data: editData,
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (data) {
                    if (data.success) {
                        layer.msg("操作成功！",
                            {
                                //offset: '15px',
                                time: 1000
                            },
                            function () {
                                $("#addBankcardDiv").attr("style", "display: none;");
                                $("#flag").empty()
                                layer.closeAll();
                                $(".layui-laypage-btn")[0].click();
                                intable.reload()
                            });
                    } else {
                        layer.msg(data.msg);
                    }

                },
                error: function (e) {
                    //最后数据加载完 让 loading层消失
                    layer.close(tishi);
                    layer.msg('系统升级维护中，请稍后重试');
                }
            });

        })
    });
}

//编辑商家配置
function editMerchantConfig(obj) {
    var data = JSON.stringify({userId: obj.data.id})
    $.ajaxUkey({
        url: "/billcheck/pre/edit",
        type: 'POST',
        data: data,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            if (data.success) {
                var arr = data.data.coinList
                //console.log(data)
                $("#state").attr("cycleId", data.data.cycleId).attr("userId", data.data.userId)
                $("#state option[value=" + data.data.cycle + "]").attr("selected", "selected")
                /*$("#state").find('option').each(function (i, item) {
                    var i = $(item)
                    var v = i.val()
                    if (v == data.data.cycle) {
                        i.attr("selected", true)
                        $("#state").val(v)
                    }
                })*/
                console.log(data.data.cycle)
                if (!$.isEmptyObject(arr)) {
                    $.each(arr, function (i, item) {
                        // console.log(item)
                        $("#flag").append("<div class=\"layui-form-item\">\n" +
                            "            <div class=\"layui-inline\">\n" +
                            "                <label class=\"layui-form-label\" style=\"width:80px;\">" + item.coinName + "</label>\n" +
                            "                <div class=\"layui-input-block\" style=\"width:350px;margin-left:110px;\" id=\"\">\n" +
                            "                    <input type=\"text\" name=\"" + data.data.userId + "\"\n" +
                            "                           placeholder=\"\" autocomplete=\"off\" lay-verify=\"numberVerify\" class=\"layui-input\"\n" +
                            "                           id=\"" + item.id + "\" value=\"" + mutipy(item.feeRate, 100) + "\" coinId=\"" + item.coinId + "\">\n" +
                            "                </div>\n" +
                            "            </div>\n" +
                            "        </div>")
                    })
                }
                openModify(obj);

            } else {
                layer.msg(data.msg);
            }
        },
        error: function (e) {
            //最后数据加载完 让 loading层消失
            layer.close(tishi);
            layer.msg('系统升级维护中，请稍后重试');
        }
    });
}

//打开修改商家配置弹窗
function openModify(obj) {
    layer.open({
        type: 1,
        closeBtn: 0,
        title: '<b>商家配置修改</b>',
        area: ['540px', '400px'], //宽高
        content: $("#modifyData")
    });
    layui.form.render(); //更新全部
    layui.form.render('select'); //刷新select选择框渲染
}

function cancelModify() {
    $("#flag").empty()
    this.layer.closeAll();
}


//商家详情
function merchantDetail(url){
    //debugger;
    // console.info(obj);
    var e = $(this),
        i = url;
    t = "商家详情";
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}

//商家编辑
function merchantEdit(url){
    //debugger;
    // console.info(obj);
    var e = $(this),
        i = url;
    t = "商家编辑";
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text())
}

//查看详情
function detail(url){
    openModal("商家详情", url, "860px", "730px");
}


//编辑
function toEdit(id){
    var title = "编辑表情包";
    var url = "/sticker/jump/edit?id=" + id;
    openTab(title, url);
}

function mutipy(t1, t2) {
    if (t1 == null || t1 == "undefined" || t1 == "" ){
        return "";
    }
    if (t2 == null || t2 == "undefined" || t2 == "" ){
        return "";
    }

    return t1 * t2;
}