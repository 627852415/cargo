layui.define('layer', function(exports) {
	var $ = layui.$;
	var obj = {
		'currencydata' : function(time, coin) {
			var d = "";
			$.ajax({
				url : '/check/data/currency',
				type : 'POST',
				contentType : "application/json",
				data : JSON.stringify({
					checkTime : time,
					coinId : coin
				}),
				dataType : 'json',
				async : false,
				success : function(data) {
					//console.log(data);
					d = data;
				}
			});
			return d;
		}
	}
	exports('currencydata', obj);
});