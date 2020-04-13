var current = 1;
var size = 10;
var coinName = $("#key").val();
var respData;
$(function () {
    //初始化加载数据
    sendReq(current, size, null);
    //搜索
    $('#search').on('click', function () {
        coinName = $("#key").val();
        sendReq(current, size, coinName);
    });

});
var sendReq = function (current, size, coinName) {
    $.ajax({//异步请求返回给后台
        url: '/coin/list',
        type: 'POST',
        data: JSON.stringify({
            coinName: coinName,
            current: current,
            size: size
        }),
        dataType: 'json',
        contentType:'application/json;charset=UTF-8',
        success: function (data) {
            if (data.success) {
                var icoUrlHost = data["data"]["icoUrlHost"];
                var dataResult = data["data"]["dataResult"];
                respData = dataResult["data"]["records"];
                var html = '';
                $.each(respData, function (i, item) {
                    var usdx = item["usdxExchange"] == undefined ? '' : item["usdxExchange"];
                    var fee = item["withdrawFee"] == undefined ? '' : item["withdrawFee"];
                    html += '<tr>';
                    html += '<th>' + item["id"] + '</th>';
                    html += '<th>' + item["coinName"] + '</th>';
                    html += '<th>' + usdx + '</th>';
                    html += '<th>' + fee + '</th>';
                    if (item["icoUrl"]) {
                        html += '<th><img src="' + icoUrlHost + item["icoUrl"] + '" height="30" width="30" alt="logo"/></th>';
                    } else {
                        html += '<th>未上传</th>';
                    }
                    html += '<th>' + item["idx"] + '</th>';
                    html += '<th>' + (item["toCny"] == undefined ? '' : item["toCny"]) + '</th>';
                    if (item["customizedRate"]) {
                        html += '<th>是</th>';
                    } else {
                        html += '<th>否</th>';
                    }
                    if (!item["hideFlag"]) {
                        html += '<th>是</th>';
                    } else {
                        html += '<th>否</th>';
                    }
                    html += '<th>';
                    html += '<a href="javascript:;" onclick="save(this)" aid="' + item["id"] + '" class="layui-btn layui-btn-mini">' + '[[#{text.operation.edit}]]' +'</a>';
                    // html += '<a href="javascript:;" onclick="del(this)" aid="' + item["id"] + '" data-id="1"  class="layui-btn layui-btn-danger layui-btn-mini">删除</a></th>';
                    html += '</tr>'
                });
                $("#con").html(html);

                // 图标预览
                $('th img').on("click", function(){
                    var imgSrc=$(this).attr('src')
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

                <!------- 分页 -------->
                layui.use(['laypage', 'layer'], function () {
                    var laypage = layui.laypage
                        , layer = layui.layer;
                    laypage.render({
                        elem: 'page'
                        , limit: size
                        , curr: current
                        , count: dataResult.data.total
                        , layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                        , jump: function (obj, first) {
                            this.current = obj.curr;
                            this.size = obj.limit;
                            if (!first) {
                                sendReq(obj.curr, obj.limit, coinName);
                            }
                        }
                    });
                });
            }
        }
    });
}

// 编辑、保存
function save(obj) {
    var aid = $(obj).attr("aid");
    var url;
    var title = "新增币种";
    if (aid != undefined) {
        url = "/coin/edit?coinId=" + aid.toString();
        title = "编辑币种";
    } else {
        url = "/coin/add";
    }

    var e = $(this),
        i = url;
    var t = title;
    layui.router();
    var l = parent === self ? layui : top.layui;
    l.index.openTabsPage(i, t || e.text());
}


function del(obj) {
    var coinId = $(obj).attr("aid");
    layer.confirm('您确定要删除吗？', {icon: 3}, function (index) {
        $.ajaxUkey({
            url: '/coin/delete',
            type: "POST",
            data: JSON.stringify({coinId: coinId}),
            dataType: "json",
            contentType:'application/json;charset=UTF-8',
            success: function (result) {
                if (result.success) {
                    if (!$.isEmptyObject(result.data)) {
                        layer.alert(result.data, {title: '提示信息', icon: 5});
                        return;
                    }
                    layer.alert('删除成功！', {title: '提示信息', icon: 1},function (index) {
                        layer.close(index);
                        $(".layui-laypage-btn")[0].click();
                    });
                } else {
                    if (!$.isEmptyObject(result.msg)) {
                        layer.alert(result.msg, {title: '提示信息', icon: 2});
                        return;
                    }
                    layer.alert('系统异常，删除失败！', {title: '提示信息', icon: 2});
                }
            }
        });

    });
}