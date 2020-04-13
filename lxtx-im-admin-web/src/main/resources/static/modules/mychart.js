layui.define(function(exports) {
	var obj = {
		'mychart' : function(id, data) {
			var dom = document.getElementById(id);
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			option = {
				title : {
					text : '趋势图'
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : data.legend
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				toolbox : {
					feature : {
						saveAsImage : {}
					}
				},
				xAxis : {
					type : 'category',
					boundaryGap : false,
					data : data.xAxis
				},
				yAxis : {
					type : 'value'
				},
				series : data.series
			};

			if (option && typeof option === "object") {
				myChart.setOption(option, true);
			}
		}
	}
	exports('mychart', obj);
});