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
        elem: '#createTimeStart'
        ,type: 'datetime'
        , done: function (value, date) {
            if (value > curDate) {
                layer.msg("[[#{text.start.trading.period.cannot.be.larger.than.current.period}]]");
                $("#createTimeStart").val("");
                return;
            } else if (value > updateTime) {
                layer.msg("[[#{text.start.transaction.date.cannot.be.greater.than.end.transaction.date}]]");
                $("#createTimeStart").val("");
                return;
            }
            createTime = value;
        }
    });
    laydate.render({
        elem: '#createTimeEnd'
        ,type: 'datetime'
        , done: function (value, date) {
            if (value > curDate) {
//                layer.msg("[[#{text.end.of.trading.period.cannot.be.larger.than.the.current.period}]]");
//                $("#createTimeEnd").val("");
//                return;
            } else if (value < createTime) {
                layer.msg("[[#{text.the.end.of.the.trading.period.cannot.be.less.than.the.beginning.of.the.trading.period}]]");
                $("#createTimeEnd").val("");
                return;
            }
            updateTime = value;
        }
    });
    console.log("payUserCountryCode")
    //国际简码/区码
	initCountryCode($("#payUserCountryCode"));
	initCountryCode($("#merchantUserCountryCode"));

    //[[#{text.initial.currency.drop.down}]]
    $.ajax({
        url: "/coin/list",
        type: "post",
        contentType : "application/json; charset=utf-8",
        data: "{\"coinName\":null,\"current\":1,\"size\":1000}",
        success: function (res) {
            var msg = res.data;
            $.each(msg.dataResult.data.records, function (i, v) {
                var id = v.id;
                var text = v.coinName;
                $("#payCoinId").append("<option value='" + id + "'>" + text + "</option>");
//                $("#receiptCoinId").append("<option value='" + id + "'>" + text + "</option>");
            });
            form.render();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // alert(errorThrown);
        }
    });
//    $.ajax({
//        url: "/coin/list",
//        type: "post",
//        contentType : "application/json; charset=utf-8",
//        data: "{\"coinName\":null,\"current\":1,\"size\":1000}",
//        success: function (res) {
//            var msg = res.data;
//            $.each(msg.dataResult.data.records, function (i, v) {
//                var id = v.id;
//                var text = v.coinName;
//                $("#payUserCountryCode").append("<option value='" + id + "'>" + text + "</option>");
//                $("#merchantUserCountryCode").append("<option value='" + id + "'>" + text + "</option>");
//            });
//            form.render();
//        },
//        error: function (XMLHttpRequest, textStatus, errorThrown) {
//            // alert(errorThrown);
//        }
//    });
});

//[[#{text.list.data}]]
loadTableWithoutColumns('/transaction/transfer/scan/pay/listPage', 'dataTable', 'searchForm');

//[[#{text.application.list.file.export}]]
function download() {
	var url = "/transaction/transfer/scan/pay/download/list?"+ $("#searchForm").serialize();
	// [[#{text.acquire.lock}]]
	var urlLock = "/transaction/transfer/scan/pay/downloadLock?"+ $("#searchForm").serialize();
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
    var url = "/transaction/transfer/scan/pay/detail?id=" + id;
    var maxHeight = 750;
	var winHeight = window.parent.document.body.clientHeight;
    var height = winHeight>200? ( winHeight - 110) : winHeight;
    //[[#{text.limit.maximum.height}]]
    height = height>maxHeight?maxHeight:height;
	openModal("[[#{text.see.details}]]", url, "600px", height+"px");
}

function getStatusStr(status) {
	var statusStr = "";
	if (status == 0||status == 1) {
		statusStr = "<font color='orange'>[[#{text.processing}]]</font>";
	} else if (status == 2) {
		statusStr = "<font color='green'>[[#{text.success}]]</font>";
	} else if (status == 3) {
		statusStr = "<font color='red'>[[#{text.failure}]]</font>";
	}
	return statusStr;
}
