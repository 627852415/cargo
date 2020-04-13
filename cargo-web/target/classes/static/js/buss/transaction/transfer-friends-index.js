var current = 1;
var size = 10;
var coinId;
var userId;
var transferNum;
var type;
var gameType;
var respData;
var iframeNode;
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
        	//实时修正当前时间（场景是用户出于日交替的时间前一天跨度到后一天时候，不[[#{text.update.time}]]会出现时间还是昨天）
        	curDate = formatDateL(new Date);
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
        	//实时修正当前时间（场景是用户出于日交替的时间前一天跨度到后一天时候，不[[#{text.update.time}]]会出现时间还是昨天）
        	curDate = formatDateL(new Date);
            if (value > curDate) {
//                layer.msg("结束交易[[#{text.day}]]期不能大于当前[[#{text.day}]]期");
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
            		var id = v.id;
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
loadTableWithoutColumns('/transaction/transfer/friends/listPage','dataTable','searchForm');
function getStatusStr(status) {
	var statusStr = "";
	if (status == 1||status == 2||status == 3) {
		statusStr = "<font color='orange'>[[#{text.processing}]]</font>";
	} else if (status == 4) {
		statusStr = "<font color='green'>[[#{text.success}]]</font>";
	} else if (status == 5) {
		statusStr = "<font color='red'>[[#{text.failure}]]</font>";
	}
	return statusStr;
}

// [[#{text.application.list.file.export}]]
function download() {
	var url = "/transaction/transfer/friends/download/list?"+ $("#searchForm").serialize();
	// [[#{text.acquire.lock}]]
	var urlLock = "/transaction/transfer/friends/downloadLock?"+ $("#searchForm").serialize();
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
		error : function() {
			layer.alert("[[#{text.system.exception.export.failed}]]", {
				title : '[[#{text.information}]]',
				icon : 2
			});
		}
	});
}

function renderBtn(data) {
	return '<a href="javascript:;" onclick="detail(this)" aid="' + data.id
			+ '" class="layui-btn layui-btn-sm">[[#{text.column.see.detail}]]</a>';
}

// [[#{text.see.details}]]
function detail(obj) {
	var me = $(obj);
	var id = me.attr('aid');
	var url = "/transaction/transfer/friends/detail?id=" + id;
	var maxHeight = 750;
	var winHeight = window.parent.document.body.clientHeight;
    var height = winHeight>200? ( winHeight - 110) : winHeight;
    //[[#{text.limit.maximum.height}]]
    height = height>maxHeight?maxHeight:height;
	openModal("[[#{text.column.see.detail}]]", url, "600px", height+"px");
}
