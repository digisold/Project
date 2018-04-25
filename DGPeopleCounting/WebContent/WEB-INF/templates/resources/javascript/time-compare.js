var module = "/kldata";
var type = $("input[name=type]").val();
var chartTitle = (type == 1 ? '日' : type == 2 ? '周' : type == 3 ? '月' : type == 4 ? '年' : type == 5 ? '自定义' : '') + '客流量对比数据报表';
var showDataZoom = type == 3;

var dateListVue = new Vue({
	el: '#listFrm',
	data: {
		dateList: []
	},
	methods: {
		removeDate: function(date){
			var removeIdx = -1;
			$.each(this.dateList, function(idx, item){
				if(date == item.date) {
					removeIdx = idx;
					return false;
				}
			});
			this.dateList.splice(removeIdx, 1);
		}
	}
});

function initChart() {
	var dateList = dateListVue.$data.dateList;
	if(dateList.length == 0) {
		layer.msg('请选择要进行对比的时间！');
		return;	
	}
	$("#klChart").css("display", "block");
	var klChart = document.getElementById("klChart");
	var param = $("#listFrm").serializeArray();
	dg.ajaxRequest(module + "/getTimeCompareKlData", param, function(res){
		var data = res.data;
		if(!isNotNull(data)) {
			return;	
		}
		var legend = [];
		$.each(dateList, function(idx, item){
			legend.push(item.date);
		});
		var series = [];
		var xAxis = [];
		$.each(data, function(idx, item){
			dateList[idx].totalKl = item.totalEnter;
			series.push({data: item.enterArray, chartType: 'bar'});
			if(xAxis.length == 0) {
				xAxis = item.xAxis;	
			}
		});
		settingChart(klChart, chartTitle, legend, xAxis, series, (type == 5 && xAxis.length > 15) ? true : showDataZoom);
	});
}