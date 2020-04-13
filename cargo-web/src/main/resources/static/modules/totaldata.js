layui.define(['layer','mychart'], function(exports) {
	var $ = layui.$;
	var mychart = layui.mychart;
	var obj = {
		'totaldata' : function(time, coin) {
			var d = "";
			$.ajaxUkey({
				url : '/check/data/total',
				type : 'POST',
				contentType : "application/json",
				data : JSON.stringify({
					checkTime : time,
					coinId : coin
				}),
				dataType : 'json',
				async : false,
				success : function(data) {
					d = data;
				}
			});
			return d;
		}
	}
	exports('totaldata', obj);
});