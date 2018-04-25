var module = "/kldata";
var type = $("input[name=type]").val();
var chartTitle = (type == 1 ? '日' : type == 2 ? '周' : type == 3 ? '月' : type == 4 ? '年' : type == 5 ? '自定义' : '') + '客流量对比数据报表';
var showDataZoom = type == 3;

var storeListVue = new Vue({
	el: '#listFrm',
	data: {
		storeList: []
	},
	methods: {
		removeStore: function(id){
			var removeIdx = -1;
			$.each(this.storeList, function(idx, item){
				if(id == item.id) {
					removeIdx = idx;
					return false;
				}
			});
			this.storeList.splice(removeIdx, 1);
		}
	}
});
layui.use(['form'], function(){
	var form = layui.form;
	form.on('select(store)', function(obj){
		var id = obj.value;
		if(id == '') {
			return;	
		}
		var name = $(obj.elem).find("option:selected").text().replace("∟", "");
		name = $.trim(name);
		var storeList = storeListVue.$data.storeList;
		if(storeList.length == 3) {
			layer.msg('最多只能选择三个案场进行对比！');
			return;
		}
		var isExit = false;
		$.each(storeList, function(idx, item){
			if(id == item.id) {
				layer.msg('案场已经在对比列表中，请重新选择！');
				isExit = true;
				return false;
			}
		});
		if(!isExit) {
			storeList.push({"id": id, "name": name, "totalKl": 0});
		}
	});
});

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
	var storeList = storeListVue.$data.storeList;
	if(storeList.length == 0) {
		layer.msg('请选择要进行对比的案场！');
		return;	
	}
	$("#klChart").css("display", "block");
	var klChart = document.getElementById("klChart");
	var param = $("#listFrm").serializeArray();
	dg.ajaxRequest(module + "/getLocationCompareKlData", param, function(res){
		var data = res.data;
		if(!isNotNull(data)) {
			return;	
		}
		var legend = [];
		$.each(storeList, function(idx, item){
			legend.push(item.name);
		});
		var series = [];
		var xAxis = [];
		$.each(data, function(idx, item){
			storeList[idx].totalKl = item.totalEnter;
			series.push({data: item.enterArray, chartType: 'bar'});
			if(xAxis.length == 0) {
				xAxis = item.xAxis;	
			}
		});
		settingChart(klChart, chartTitle, legend, xAxis, series, (type == 5 && xAxis.length > 15) ? true : showDataZoom);
	});
}