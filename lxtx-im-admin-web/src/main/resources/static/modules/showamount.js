layui.define([ 'layer', 'util' ], function(exports) {
	var $ = layui.$;
	var util = layui.util;
	var obj = {
		'showamount' : function(time,coin) {
			$.ajax({
				url : '/check/getPlatformlCoinBatchDetail',
				type : 'POST',
				contentType : "application/json",
				data : JSON.stringify({
					checkTime : time,
					coinId : coin
				}),
				dataType : 'json',
				success : function(data) {
					var billTime = data.data.billTime;
					var totalAmount = data.data.totalAmount;
					var currencyAmount = data.data.currencyAmount;
					var diffBillNum = data.data.diffBillNum;
					var diffTotalAmount = data.data.diffTotalAmount;
					var coinId = data.data.coinId;
					var coinName = data.data.coinName;
					var t = util.toDateString(billTime, 'yyyy-MM-dd');
					$('#titl1').text(t + '日币种总资产:');
					$('#titl2').text(t + '日币种交易额：');
					$('#titl3').text(t + '日币种差异记录：');
					$('#coinname1').text(coinName);
					$('#coinname2').text(coinName);

					$('#amount1').text(totalAmount);
					$('#amount2').text(currencyAmount);
					$('#amount3').text(diffTotalAmount);
					$('#amount4').text(diffBillNum);
				}
			});
		}
	}
	exports('showamount', obj);
});