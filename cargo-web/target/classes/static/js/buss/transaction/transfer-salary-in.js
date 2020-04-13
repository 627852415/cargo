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
    // 时间选择常规用法
    laydate.render({
        elem: '#createTimeStart'
        ,type: 'datetime'
        , done: function (value, date) {
        	//实时修正当前时间（场景是用户出于日交替的时间前一天跨度到后一天时候，不更新时间会出现时间还是昨天）
        	curDate = formatDateL(new Date);
            if (value > curDate) {
                layer.msg("开始交易日期不能大于当前日期");
                $("#createTimeStart").val("");
                return;
            } else if (value > updateTime) {
                layer.msg("开始交易日期不能大于结束交易日期");
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
        	//实时修正当前时间（场景是用户出于日交替的时间前一天跨度到后一天时候，不更新时间会出现时间还是昨天）
        	curDate = formatDateL(new Date);
            if (value > curDate) {
                layer.msg("结束交易日期不能大于当前日期");
                $("#createTimeEnd").val("");
                return;
            } else if (value < createTime) {
                layer.msg("结束交易日期不能小于开始交易日期");
                $("#createTimeEnd").val("");
                return;
            }
            updateTime = value;
        }
    });
    
    //初始化币种下拉
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

loadTableWithoutColumns('/transaction/transfer/payroll/in/pageList', 'dataTable', 'searchForm');

function getStatusStr(status) {
	var statusStr = "";
	if (status == 1 || status == 2 || status == 3) {
        statusStr = "<font color='orange'>处理中</font>";
	} else if (status == 4) {
		statusStr = "<font color='green'>成功</font>";
	} else if (status == 5) {
		statusStr = "<font color='red'>失败</font>";
	}
	return statusStr;
}


//申请列表文件导出
function download() {
	var url = "/transaction/transfer/payroll/in/download/list?"+ $("#searchForm").serialize();
	// 获取锁
	var urlLock = "/transaction/transfer/payroll/in/downloadLock?"+ $("#searchForm").serialize();
	$.ajaxUkey({
		url : urlLock,
		type : "post",
		async : "true",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(responseText) {
			if (responseText.success == false) {
				layer.alert(responseText.msg, {
					title : '提示信息',
					icon : 2
				});
			} else {
				//获取锁后去下载
				//window.location.href = url;
				//获取锁后去下载
				if(iframeNode!=undefined&&iframeNode!=null){
					document.body.removeChild(iframeNode);
				}
				iframeNode = document.createElement("iframe");
				iframeNode.src = url;
				iframeNode.style.display = "none";
				document.body.appendChild(iframeNode);
				if (iframeNode.attachEvent){ // 兼容IE写法
					iframeNode.attachEvent("onload", function(){
						// iframeNode加载完成后要进行的操作
						var msgerror = iframeNode.contentWindow.document.body.innerHTML;
						if(!""==msgerror){
							layer.alert(msgerror, {
								title : '提示信息',
								icon : 2
							});
						}
					})
				} else {
					iframeNode.onload = function(){
						// iframeNode加载完成后要进行的操作
						var msgerror = iframeNode.contentWindow.document.body.innerHTML;
						if(!""==msgerror){
							layer.alert(msgerror, {
								title : '提示信息',
								icon : 2
							});
						}
					}
				}
			}

		},
		error : function() {
			layer.alert("系统异常，导出失败", {
				title : '提示信息',
				icon : 2
			});
		}
	});
}

function renderBtn(data) {
    return '<a href="javascript:;" onclick="detail(this)" aid="' + data.id + '" class="layui-btn layui-btn-sm">查看详情</a>';
}

//查看详情
function detail(obj) {
    var me = $(obj);
    var id = me.attr('aid');
    var url = "/transaction/transfer/wallet/detail?id=" + id;
    var maxHeight = 750;
	var winHeight = window.parent.document.body.clientHeight;
    var height = winHeight>200? ( winHeight - 110) : winHeight;
    //限制最高高度
    height = height>maxHeight?maxHeight:height;
	openModal("查看详情", url, "600px", height+"px");
}