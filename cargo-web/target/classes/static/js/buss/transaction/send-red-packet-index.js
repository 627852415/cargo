var current = 1;
var size = 10;
var iframeNode;

layui.use(['form', 'laydate'], function () {
    var form = layui.form;
    var laydate = layui.laydate;
    var curDate = formatDateL(new Date);
    var createTime, updateTime;
    // [[#{text.time.selection.general.usage}]]
    laydate.render({
        elem: '#startCreateTime'
        , type: 'datetime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.trading.period.cannot.be.larger.than.current.period}]]");
                $("#startCreateTime").val("");
                return;
            } else if (value > updateTime) {
                layer.msg("[[#{text.start.transaction.date.cannot.be.greater.than.end.transaction.date}]]");
                $("#startCreateTime").val("");
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#endCreateTime'
        , type: 'datetime'
        , done: function (value, date) {
            if (value > curDate) {
//                layer.msg("结束交易[[#{text.day}]]期不能大于当前[[#{text.day}]]期");
//                $("#endCreateTime").val("");
//                return;
            } else if (value < createTime) {
                layer.msg("[[#{text.the.end.of.the.trading.period.cannot.be.less.than.the.beginning.of.the.trading.period}]]");
                $("#endCreateTime").val("");
                return;
            }
            updateTime = value;
        }
    });
    //[[#{text.initial.currency.drop.down}]]
    $.ajax({
        url: "/coin/list",
        type: "post",
        contentType: "application/json; charset=utf-8",
        data: "{\"coinName\":null,\"current\":1,\"size\":1000}",
        success: function (res) {
            var msg = res.data;
            $.each(msg.dataResult.data.records, function (i, v) {
                var id = v.id;
                var text = v.coinName;
                $("#coinId").append("<option value='" + id + "'>" + text + "</option>");
            });
            form.render();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // alert(errorThrown);
        }
    });
});

loadTableWithoutColumns('/red/packet/admin/send/list', 'dataTable', 'searchForm');

function getStatusStr(status) {
    var statusStr = "";
    if (status === 0) {
        statusStr = "[[#{text.uncollected}]]";
    } else if (status === 1) {
        statusStr = "[[#{text.collected}]]";
    } else if (status === 2) {
        statusStr = "[[#{text.not.claimed.and.expired}]]";
    }
    return statusStr;
}

/**
 * [[#{text.type.mapping}]]
 * @param type [[#{text.type.value}]]
 * @returns {string} [[#{text.type.string}]]
 */
function getTypeStr(type) {
    var typeStr = "";
    if (type === 0) {
        typeStr = "[[#{text.personal.red.envelope}]]";
    } else if (type === 1) {
        typeStr = "[[#{text.group.of.lucky.red.envelopes}]]";
    } else if (type === 2) {
        typeStr = "[[#{text.per.capita.red.envelope}]]";
    }
    return typeStr;
}

// [[#{text.application.list.file.export}]]
function download() {
    var url = "/red/packet/send/download/list?" + $("#searchForm").serialize();
    // [[#{text.acquire.lock}]]
    var urlLock = "/red/packet/send/downloadLock?" + $("#searchForm").serialize();
    $.ajaxUkey({
        url: urlLock,
        type: "post",
        async: "true",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (responseText) {
            if (responseText.success == false) {
                layer.alert(responseText.msg, {
                    title: '[[#{text.information}]]',
                    icon: 2
                });
            } else {
                //[[#{text.acquire.lock}]]后去下载
                //window.location.href = url;
                //[[#{text.acquire.lock}]]后去下载
                if (iframeNode != undefined && iframeNode != null) {
                    document.body.removeChild(iframeNode);
                }
                iframeNode = document.createElement("iframe");
                iframeNode.src = url;
                iframeNode.style.display = "none";
                document.body.appendChild(iframeNode);
                if (iframeNode.attachEvent) { // 兼容IE写法
                    iframeNode.attachEvent("onload", function () {
                        // iframeNode[[#{text.what.to.do.after.loading}]]
                        var msgerror = iframeNode.contentWindow.document.body.innerHTML;
                        if (!"" == msgerror) {
                            layer.alert(msgerror, {
                                title: '[[#{text.information}]]',
                                icon: 2
                            });
                        }
                    })
                } else {
                    iframeNode.onload = function () {
                        // iframeNode[[#{text.what.to.do.after.loading}]]
                        var msgerror = iframeNode.contentWindow.document.body.innerHTML;
                        if (!"" == msgerror) {
                            layer.alert(msgerror, {
                                title: '[[#{text.information}]]',
                                icon: 2
                            });
                        }
                    }
                }
            }

        },
        error: function () {
            layer.alert("[[#{text.system.exception.export.failed}]]", {
                title: '[[#{text.information}]]',
                icon: 2
            });
        }
    });
}

function renderBtn(data) {
    return '<a href="javascript:;" onclick="detail(this)" aid="' + data.id
        + '" class="layui-btn layui-btn-sm">[[#{text.see.details}]]</a>';
}

// [[#{text.see.details}]]
function detail(obj) {
    var me = $(obj);
    var id = me.attr('aid');
    var url = "/red/packet/send/detail?id=" + id;
    var maxHeight = 750;
    var winHeight = window.parent.document.body.clientHeight;
    var height = winHeight > 200 ? (winHeight - 110) : winHeight;
    //[[#{text.limit.maximum.height}]]
    height = height > maxHeight ? maxHeight : height;
    openModal("[[#{text.see.details}]]", url, "600px", height + "px");
}
