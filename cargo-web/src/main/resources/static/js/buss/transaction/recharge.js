var current = 1;
var size = 10;
var iframeNode;

//[[#{text.control.initialization}]]
layui.use(['form', 'laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    var curDate = formatDateL(new Date);
    var createTime, updateTime;
    // [[#{text.time.selection.general.usage}]]
    laydate.render({
        elem: '#createTime'
        ,type: 'datetime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.trading.period.cannot.be.larger.than.current.period}]]");
                $("#createTime").val("");
                return;
            } else if (value > updateTime) {
                layer.msg("[[#{text.start.transaction.date.cannot.be.greater.than.end.transaction.date}]]");
                $("#createTime").val("");
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#updateTime'
        ,type: 'datetime'
        , done: function (value, date) {
            if (value > curDate) {
//                layer.msg("结束交易[[#{text.day}]]期不能大于当前[[#{text.day}]]期");
//                $("#updateTime").val("");
//                return;
            } else if (value < createTime) {
                layer.msg("[[#{text.the.end.of.the.trading.period.cannot.be.less.than.the.beginning.of.the.trading.period}]]");
                $("#updateTime").val("");
                return;
            }
            updateTime = value;
        }
    });
    //[[#{text.initial.currency.drop.down}]]
    $.ajax({
        url: "/coin/list",
        type: "post",
        contentType : "application/json; charset=utf-8",
        data: "{\"coinName\":null,\"current\":1,\"size\":1000}",
        success: function (res) {
            var msg = res.data;
            $.each(msg.dataResult.data.records, function (i, v) {
                if(!v.hideFlag){
                    var id = v.coinName;
                    var text = v.coinName;
                    $("#coinId").append("<option value='" + id + "'>" + text + "</option>");
                }
            });
            form.render();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // alert(errorThrown);
        }
    });
});

//[[#{text.list.data}]]
loadTableWithoutColumns('/transaction/recharge/listPage', 'dataTable', 'searchForm');

//[[#{text.list.file.export}]]
function download() {
    var url = "/transaction/recharge/download/list?" + $("#searchForm").serialize();
    // [[#{text.acquire.lock}]]
    var urlLock = "/transaction/recharge/downloadLock";
    $.ajaxUkey({
        url: urlLock,
        type: "post",
        async: "true",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (responseText) {
            if (responseText.success === false) {
                layer.alert(responseText.msg, {title: '[[#{text.information}]]', icon: 2},function (index) {
                    layer.close(layer.index);
                    return false;
                });
                return false;
            } else {
                //[[#{text.acquire.lock}]]后去下载
                //window.location.href = url;
                //[[#{text.acquire.lock}]]后去下载
                if(iframeNode!=undefined&&iframeNode!=null){
                    document.body.removeChild(iframeNode);
                }
                iframeNode = document.createElement("iframe");
                iframeNode.src = url;
                iframeNode.style.display = "none";
                document.body.appendChild(iframeNode);
                if (iframeNode.attachEvent){ // 兼容IE写法
                    iframeNode.attachEvent("onload", function(){
                        // iframeNode[[#{text.what.to.do.after.loading}]]
                        var msgerror = iframeNode.contentWindow.document.body.innerHTML;
                        if(!""==msgerror){
                            layer.alert(msgerror, {
                                title : '[[#{text.information}]]',
                                icon : 2
                            });
                        }
                    })
                } else {
                    iframeNode.onload = function(){
                        // iframeNode[[#{text.what.to.do.after.loading}]]
                        var msgerror = iframeNode.contentWindow.document.body.innerHTML;
                        if(!""==msgerror){
                            layer.alert(msgerror, {
                                title : '[[#{text.information}]]',
                                icon : 2
                            });
                        }
                    }
                }
            }

        },
        error: function () {
            layer.alert("[[#{text.system.abnormal.export.failure.prompt.message}]]", {title: '[[#{text.information}]]', icon: 2});
        }
    });
}

//[[#{text.see.details}]]
function renderBtn(data) {
    return '<a href="javascript:;" onclick="detail(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm">[[#{text.column.see.detail}]]</a>';
}
function detail(obj) {
    var me = $(obj);
    var id = me.attr('aid');
    var url = "/transaction/recharge/detail?id=" + id;
    var maxHeight = 750;
    var winHeight = window.parent.document.body.clientHeight;
    var height = winHeight>200? ( winHeight - 110) : winHeight;
    //[[#{text.limit.maximum.height}]]
    height = height>maxHeight?maxHeight:height;
    openModal("[[#{text.column.see.detail}]]", url, "600px", height+"px");
}
