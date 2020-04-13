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
        elem: '#createTime',
        type: 'datetime',
        done: function (value, date) {
            //实时修正当前时间（场景是用户出于日交替的时间前一天跨度到后一天时候，不[[#{text.update.time}]]会出现时间还是昨天）
            curDate = formatDateL(new Date);
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
        elem: '#updateTime',
        type: 'datetime',
        done: function (value, date) {
            //实时修正当前时间（场景是用户出于日交替的时间前一天跨度到后一天时候，不[[#{text.update.time}]]会出现时间还是昨天）
            curDate = formatDateL(new Date);
            if (value > curDate) {
//                layer.msg("[[#{text.end.of.trading.period.cannot.be.larger.than.the.current.period}]]");
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

});

//[[#{text.list.data}]]
loadTableWithoutColumns('/transaction/yeb/order/listPage', 'dataTable', 'searchForm');

//[[#{text.list.file.export}]]
function download() {
    var url = "/transaction/yeb/order/download?"+ $("#searchForm").serialize();
    // [[#{text.acquire.lock}]]
    var urlLock = "/transaction/yeb/order/downloadLock?"+ $("#searchForm").serialize();
    $.ajaxUkey({
        url : urlLock,
        type : "post",
        async : "true",
        contentType : "application/x-www-form-urlencoded; charset=utf-8",
        success : function(responseText) {
            if (responseText.success == false) {
                layer.alert(responseText.msg, {
                    title : '[[#{text.information}]]',
                    icon : 2
                });
            } else {
                //[[#{text.download.after.obtaining.the.lock}]]
                //window.location.href = url;
                //[[#{text.download.after.obtaining.the.lock}]]
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
        error : function() {
            layer.alert("[[#{text.system.exception.export.failed}]]", {
                title : '[[#{text.information}]]',
                icon : 2
            });
        }
    });
}


//[[#{text.see.details}]]
function renderBtn(data) {
    return '<a href="javascript:;" onclick="detail(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm">[[#{text.see.details}]]</a>';
}
function detail(obj) {
    var me = $(obj);
    var id = me.attr('aid');
    var url = "/transaction/yeb/order/detail?id=" + id;
    openModal("[[#{text.see.details}]]", url, "800px", "620px");
}

function getStatusStr(status) {
    var statusStr = "";
    if ("[[#{text.processing}]]" === status) {
        statusStr = "<font color='orange'>[[#{text.processing}]]</font>";
    } else if ("[[#{text.success}]]" === status) {
        statusStr = "<font color='green'>[[#{text.success}]]</font>";
    } else if ("[[#{text.failure}]]" === status) {
        statusStr = "<font color='red'>[[#{text.failure}]]</font>";
    }
    return statusStr;
}
