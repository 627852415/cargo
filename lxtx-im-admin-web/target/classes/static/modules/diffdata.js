layui.define('layer', function(exports) {
	var $ = layui.$;
	var obj = {
		'diffdata' : function(time, coin) {
			var d = "";
			$.ajax({
				url : '/check/data/diff',
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
	exports('diffdata', obj);
});