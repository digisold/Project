var module = "/kldata";
var type = $("input[name=type]").val();
var chartTitle = (type == 1 ? '日' : type == 2 ? '周' : type == 3 ? '月' : type == 4 ? '年' : type == 5 ? '自定义' : '') + '客流量数据报表';
var showDataZoom = type == 3;

layui.use(['form', 'element'], function(){
	var form = layui.form;
	var element = layui.element;
	form.on('select(store)', function(obj){
		initChart();
	});
	element.on("tab(psChartTabBrief)", function(data){
		tabIdx = data.index;
		var layId = this.getAttribute('lay-id');
		passageChart(layId);
	});
	
	initChart();
});

var passageVue = new Vue({
	el: '#passageChart',
	data: {
		ptypeList:[]
	},
	updated: function(){
		// 默认打开第一个选项卡
		$(".layui-tab-title").find("li").removeClass("layui-this");
		$(".layui-tab-content").find("div").removeClass("layui-show");
		$(".layui-tab-title").find("li").eq(0).addClass("layui-this");
		$(".layui-tab-content").find("div").eq(0).addClass("layui-show");
		$.each(this.ptypeList, function(idx, item){
			var ptypeId = item.id;			
			if(idx == 0) {
				passageChart(ptypeId);				
			}			
		});	
	}
});

function passageChart(ptypeId) {
	var passageChart = document.getElementById("_" + ptypeId);
	var legend = [];
	var xAxis = null;
	var series = [];
	var passageList;
	var param = $("#listFrm").serializeArray();
	param.push({"name": "passageTypeId", "value": ptypeId});
	
	dg.ajaxRequest(module + "/getPassageKlDataList", param, function(res){
		var data = res.data;
		if(!isNotNull(data)) {
			return;	
		}	
		$.each(data, function(idx, item){
			legend.push(item.passage.name);
			var passageKl = item.passageKl;
			if(xAxis == null) {
				xAxis = passageKl.xAxis;
			}
			var seriesItem = {data: passageKl.enterArray, chartType: 'bar'};
			series.push(seriesItem);
		});
		settingChart(passageChart, "", legend, xAxis, series, (type == 5 && xAxis.length > 15) ? true : showDataZoom);
	});
	
}

function initChart() {		
	if(type == 5) {
		var sdate = $("#sdate").val();
		var edate = $("#edate").val();
		var startDate = new Date(sdate);
		var endDate = new Date(edate);
		if(startDate.getTime() > endDate.getTime()) {
			layer.msg("开始日期不能大于结束日期！");
			return;	
		}	
	}
	var klChart = document.getElementById("klChart");
	var passageChart = document.getElementById("passageChart");
	var param = $("#listFrm").serializeArray();
	param.push({"name": "isMain", "value": 1});
	dg.ajaxRequest(module + "/getKlDataList", param, function(res){
		var data = res.data;
		if(!isNotNull(data)) {
			return;	
		}
		$("#totalKL").html(data.totalEnter);
		var legend = ["客流量"];
		if(type == 1) {
			legend = ["客流量", "出客流", "停留客流"];
		}
		var series = [];
		if(isNotNull(data.enterArray)) {
			series.push({data: data.enterArray, chartType: 'bar'});	
		}
		if(isNotNull(data.exitArray)) {
			series.push({data: data.exitArray, chartType: 'bar'});	
		}
		if(isNotNull(data.stayArray)) {
			series.push({data: data.stayArray, chartType: 'line'});	
		}
		settingChart(klChart, chartTitle, legend, data.xAxis, series, (type == 5 && data.xAxis.length > 15) ? true : showDataZoom);
	});
	
	dg.ajaxRequest(module + "/activityList", param, function(res){
		var act = isNotNull(res.data) ? res.data.name : '-';
		$("#activityLabel").html(act);
		$("#activityLabel").attr("title", act);
	});
	
	
	param = $("#listFrm").serializeArray();		
	dg.ajaxRequest(module + "/getPassageTypeList", param, function(res){
		var data = res.data;
		if(!isNotNull(data)) {
			return;	
		}	
		passageVue.$data.ptypeList = data;		
	});		
}