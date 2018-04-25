var dg={};
var date_theme = '#009688';
function getBasePath(){
	 //获取当前网址，如： http://localhost:8083/proj/meun.jsp  
   var curWwwPath = window.document.location.href;  
   //获取主机地址之后的目录，如： proj/meun.jsp  
   var pathName = window.document.location.pathname;  
   var pos = curWwwPath.indexOf(pathName);  
   //获取主机地址，如： http://localhost:8083  
   var localhostPath = curWwwPath.substring(0, pos);  
   //获取带"/"的项目名，如：/proj  
   var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);  
   return localhostPath + projectName;
};

function isNotNull(obj) {
	return (obj != null && obj != undefined);	
}

// type: image, excel
function checkFileFormat(obj, type) {
	var flag = true;
	var filename = $(obj).val();
	if(filename != "") {
		var filesuffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
		if(type == "image" && filesuffix != ".jpg" && filesuffix != ".jpeg" && filesuffix != ".png" && filesuffix != ".gif"){
			 return false;
		}	
		if(type == "excel" && filesuffix != ".xls" && filesuffix != ".xlsx"){
			 return false;
		}	
	}
	return flag;
}

var loading;
dg.ajaxRequest=function(requestPath, param, fn){
	layui.use(['layer'], function(){
		var layer = layui.layer;
		if(!isNotNull(loading)) {
			loading = layer.load(0);
		}	
		$.ajax({
			url:getBasePath() + requestPath,
			type:"post",
			dataType:"json",
			data:param,
			success: function(e){
				if(isNotNull(loading)) {
					layer.close(loading);
					loading = null;	
				}
				fn(e);
				if(isNotNull(e.logout)) {	
					if(self.frameElement != null && self.frameElement.tagName.toLowerCase() == "iframe") {
						window.parent.location.href = e.logout;	
					} else {
						window.location.href = e.logout;
					}
				} else if(isNotNull(e.message)) {
					layer.msg(e.message);
				}
			},
			error:function(){
				if(isNotNull(loading)) {
					layer.close(loading);
					loading = null;	
				}
				layer.msg("服务器异常！");
			}
		});
	});
};

dg.ajaxSynchRequest=function(requestPath, param, fn){
	layui.use(['layer'], function(){
		var layer = layui.layer;
		if(!isNotNull(loading)) {
			loading = layer.load(0);
		}	
		$.ajax({
			url:getBasePath() + requestPath,
			type:"post",
			dataType:"json",
			data:param,
			async: false,
			success: function(e){
				if(isNotNull(loading)) {
					layer.close(loading);
					loading = null;	
				}
				fn(e);
				if(isNotNull(e.logout)) {	
					if(self.frameElement != null && self.frameElement.tagName.toLowerCase() == "iframe") {
						window.parent.location.href = e.logout;	
					} else {
						window.location.href = e.logout;
					}
				} else if(isNotNull(e.message)) {
					layer.msg(e.message);
				}
			},
			error:function(){
				if(isNotNull(loading)) {
					layer.close(loading);
					loading = null;	
				}
				layer.msg("服务器异常！");
			}
		});
	});
	
};

dg.formAjaxRequest=function(frmObj, requestPath, fn){
	layui.use(['layer'], function(){
		var layer = layui.layer;
		if(!isNotNull(loading)) {
			loading = layer.load(0);
		}	
		$(frmObj).ajaxSubmit({
			url: getBasePath() + requestPath,
			type: 'post',
			dataType: "json",
			success: function(e){
				if(isNotNull(loading)) {
					layer.close(loading);
					loading = null;	
				}	
				fn(e);
				if(e.logout != null && e.logout != undefined) {	
					if(self.frameElement != null && self.frameElement.tagName.toLowerCase() == "iframe") {
						window.parent.location.href = e.logout;	
					} else {
						window.location.href = e.logout;
					}
				} else if(e.message != null && e.message != undefined) {
					layer.msg(e.message);
				}
			},
			error:function(data){
				if(isNotNull(loading)) {
					layer.close(loading);
					loading = null;	
				}
				layer.msg("服务器异常！");
			}
		});
	});
};

dg.listPage=function(elem, count, limt, fn){
	layui.use('laypage', function(){
		var laypage = layui.laypage;
		laypage.render({
			elem: elem,
			count: count,
			limit: limt,
			first: '首页',
			last: '尾页',
			groups: 10,
			layout: ['count', 'prev', 'page', 'next'],
			theme: '#FF5722',
			jump: function(obj, first) {
				if(!first) {
					fn(obj.curr);
				}
			}
		});
	});	
}
  
// 加载案场下拉框
if($(".storeItems").length > 0) {
	if(isNotNull(window.parent.global_storeList)) {
		var url = window.location.href;
		var filter = ['kldata/date', 'kldata/week', 'kldata/month', 'kldata/year', 'kldata/custom'];
		var html = '<option value="">~ 请选择 ~</option>';
		for(var i = 0; i < filter.length; i++) {
			if(url.indexOf(filter[i]) > 0) {
				html = '';
				break;	
			}
		}
		var storeList = window.parent.global_storeList;
		$.each(storeList, function(idx, store){
			html += '<option value="' + store.id + '">' + (store.parentId != '' ? '&nbsp;&nbsp;∟' : '') + store.name + '</option>';
		});
		$(".storeItems").html(html);
	}
}

var echart_theme={color:["#2ec7c9","#b6a2de","#5ab1ef","#ffb980","#d87a80","#8d98b3","#e5cf0d","#97b552","#95706d","#dc69aa","#07a2a4","#9a7fd1","#588dd5","#f5994e","#c05050","#59678c","#c9ab00","#7eb00a","#6f5553","#c14089"],title:{textStyle:{fontWeight:"normal",color:"#008acd"}},dataRange:{itemWidth:15,color:["#5ab1ef","#e0ffff"]},toolbox:{color:["#1e90ff","#1e90ff","#1e90ff","#1e90ff"],effectiveColor:"#ff4500"},tooltip:{backgroundColor:"rgba(50,50,50,0.5)",axisPointer:{type:"line",lineStyle:{color:"#008acd"},crossStyle:{color:"#008acd"},shadowStyle:{color:"rgba(200,200,200,0.2)"}}},dataZoom:{dataBackgroundColor:"#efefff",fillerColor:"rgba(182,162,222,0.2)",handleColor:"#008acd"},grid:{borderColor:"#eee"},categoryAxis:{axisLine:{lineStyle:{color:"#008acd"}},splitLine:{lineStyle:{color:["#eee"]}}},valueAxis:{axisLine:{lineStyle:{color:"#008acd"}},splitArea:{show:true,areaStyle:{color:["rgba(250,250,250,0.1)","rgba(200,200,200,0.1)"]}},splitLine:{lineStyle:{color:["#eee"]}}},polar:{axisLine:{lineStyle:{color:"#ddd"}},splitArea:{show:true,areaStyle:{color:["rgba(250,250,250,0.2)","rgba(200,200,200,0.2)"]}},splitLine:{lineStyle:{color:"#ddd"}}},timeline:{lineStyle:{color:"#008acd"},controlStyle:{normal:{color:"#008acd"},emphasis:{color:"#008acd"}},symbol:"emptyCircle",symbolSize:3},bar:{itemStyle:{normal:{barBorderRadius:5},emphasis:{barBorderRadius:5}}},line:{smooth:true,symbol:"emptyCircle",symbolSize:3},k:{itemStyle:{normal:{color:"#d87a80",color0:"#2ec7c9",lineStyle:{color:"#d87a80",color0:"#2ec7c9"}}}},scatter:{symbol:"circle",symbolSize:4},radar:{symbol:"emptyCircle",symbolSize:3},map:{itemStyle:{normal:{areaStyle:{color:"#ddd"},label:{textStyle:{color:"#d87a80"}}},emphasis:{areaStyle:{color:"#fe994e"}}}},force:{itemStyle:{normal:{linkStyle:{color:"#1e90ff"}}}},chord:{itemStyle:{normal:{borderWidth:1,borderColor:"rgba(128, 128, 128, 0.5)",chordStyle:{lineStyle:{color:"rgba(128, 128, 128, 0.5)"}}},emphasis:{borderWidth:1,borderColor:"rgba(128, 128, 128, 0.5)",chordStyle:{lineStyle:{color:"rgba(128, 128, 128, 0.5)"}}}}},gauge:{axisLine:{lineStyle:{color:[[0.2,"#2ec7c9"],[0.8,"#5ab1ef"],[1,"#d87a80"]],width:10}},axisTick:{splitNumber:10,length:15,lineStyle:{color:"auto"}},splitLine:{length:22,lineStyle:{color:"auto"}},pointer:{width:5}},textStyle:{fontFamily:"微软雅黑, Arial, Verdana, sans-serif"}};

function settingChart(chartDom, title, legendArray, xAxisArray, seriesArray, isShowDataZoom) {
	var chart_series = [];
	$.each(seriesArray, function(idx, series){
		var item = {
			name: legendArray[idx],
			type: series.chartType,
			data: series.data,
			markPoint : {
				data : [
					{type : 'max', name: '最大值'},
					{type : 'min', name: '最小值'}
				]
			},
			markLine : {
				data : [
					{type : 'average', name: '平均值'}
				]
			}
		};
		chart_series.push(item);
	});
	var option = {
		title : {
			text: title
		},
		tooltip : {
			trigger: 'axis'
		},
		legend: {
			data:legendArray
		},
		toolbox: {
			show : true,
			feature : {
				dataView : {show: true, readOnly: false},
				magicType : {show: true, type: ['bar', 'line']},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		dataZoom : {
			show : isShowDataZoom,
			realtime : true,
			start : 0,
			end : isShowDataZoom ? 70 : 100
		},
		xAxis : [
			{
				type : 'category',
				data : xAxisArray,
				axisLabel: {
					interval: 0
				}	
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : chart_series
	};
	echarts.init(chartDom, echart_theme).setOption(option);
}