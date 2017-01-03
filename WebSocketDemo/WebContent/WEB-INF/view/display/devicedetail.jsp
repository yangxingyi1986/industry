<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>机床详情</title>
	<style>
		table, th , td {
		  border: 1px solid grey;
		  border-collapse: collapse;
		  padding: 5px;
		}
		table tr:nth-child(odd) {
		  background-color: #f1f1f1;
		}
		table tr:nth-child(even) {
		  background-color: #ffffff;
		}
	</style>
	<script src="<%=request.getContextPath()%>/js/sockjs-0.3.4.js"></script>
	<script src="<%=request.getContextPath()%>/js/echarts.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/display/yunfan-socket.js"></script>
	<script src="<%=request.getContextPath()%>/js/angular.js"></script>
	<script type="text/javascript">
		initWebSocket("detail");

	    websocket.onopen = function (evnt) {
	    	websocket.send("123");
	    };

	    websocket.onmessage = function (evnt) {
	    	var obj = JSON.parse(evnt.data);
	    	if(obj["flag"]){
	    		var id = obj['id'];
	    		if($("#"+id).length <= 0){
		    		eval(obj["value"]);
	    		}
	    	}else{
		    	try{
		    		for(var key in obj){
			    		var newdata = datacenter[key]
			    		if(key.indexOf("chartdivdate") >= 0){
			    		}
			    		if(newdata == null){
			    			newdata = {};
			    			newdata.axis = obj[key].axis;
					    	newdata.data = obj[key].data;
					    	datacenter[key] = newdata;
			    		}else{
			    			newdata.axis = obj[key].axis;
					    	newdata.data = obj[key].data;
			    		}
		    		}
		    	}catch(e){
		    		debugger;
		    	}
	    	}
	    };
	    
	    websocket.onerror = function (evnt) {
	    };
	    
	    websocket.onclose = function (evnt) {
	    }
	    
		var app = angular.module("machinedetail",[]);
		app.controller("customersCtrl",function($scope,$http){
			$scope.infos = [
				{first:'进口轴承X',second:'0',third:'0.57',forth:'1.79',fifth:'3.85',sixth:'0<157',seventh:'0<158',eighth:'-',ninth:'-',tenth:'0'},
				{first:'进口轴承Y',second:'0',third:'0.57',forth:'1.79',fifth:'3.85',sixth:'0<157',seventh:'0<158',eighth:'0.16',ninth:'0.16',tenth:'0'},
				{first:'震动测点4003',second:'0',third:'0.57',forth:'1.79',fifth:'3.85',sixth:'0<157',seventh:'0<158',eighth:'0.16',ninth:'-',tenth:'0'},
				{first:'震动测点4004',second:'0',third:'0.57',forth:'1.79',fifth:'3.85',sixth:'0<157',seventh:'0<158',eighth:'0.16',ninth:'-',tenth:'0'},
				{first:'震动测点4005',second:'0',third:'0.57',forth:'1.79',fifth:'3.85',sixth:'0<157',seventh:'0<158',eighth:'0.16',ninth:'-',tenth:'0'},
				{first:'震动测点4006',second:'0',third:'0.57',forth:'1.79',fifth:'3.85',sixth:'0<157',seventh:'0<158',eighth:'0.16',ninth:'-',tenth:'0'},
				{first:'出口轴承X',second:'0',third:'0.57',forth:'1.79',fifth:'3.85',sixth:'0<157',seventh:'0<158',eighth:'0.16',ninth:'-',tenth:'0'},
				{first:'出口轴承Y',second:'0',third:'0.57',forth:'1.79',fifth:'3.85',sixth:'0<157',seventh:'0<158',eighth:'0.16',ninth:'-',tenth:'0'}
			];
		});
		var count = 20;
		var base = +new Date(new Date() - count*2000);
		var oneDay = 2000;
		var date = [];
		var data = [];
		var basedata = [];
		var now = new Date(base);
		var index = 0 ;
		function addData(shift) {
		    basedata.push(((index ++)%2 > 0) ? 22:23);
		    tmpnow = [now.getHours(), now.getMinutes(), now.getSeconds()].join('-');
		    date.push(tmpnow);
		    data.push(Number.parseInt(Math.random() * 10 + 20));
	        if(shift){
			    date.shift();
		        data.shift();
		        basedata.shift();
	        }
		    now = new Date(now.getTime() + oneDay);
		}
		option = {
		    title: {
		        text: '机床运行电压检测曲线'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['标准值','实际电压值']
		    },
		    xAxis:  {
		        type: 'category',
		        boundaryGap: false,
		        data: date
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		            formatter: '{value} kw'
		        }
		    },
		    visualMap: {
		        show:false,
	            pieces: [{
	                gt: 0,
	                lte: 21,
	                color: 'green'
	            },{
	                gt: 21,
	                lte: 27,
	                color: '#ffde33'
	            },{
	                gt: 27,
	                lte: 50,
	                color: '#cc0033'
	            }],
	            outOfRange: {
	                color: '#cc0033'
	            }
		        },
		    series: [
		        {
		            name: '标准值',
		            type: 'line',
		            data: basedata,
		            markLine: {
		                data: [
		                    {type: 'average', name: '平均值'}
		                ]
		            }
		        },
		        {
		            name:'实际电压值',
		            type:'line',
		            data: data,
		            markPoint: {
		                data: [
		                    {type: 'max', name: '最大值'},
		                    {type: 'min', name: '最小值'}
		                ]
		            },
		            markLine: {
		                data: [
		                    {type: 'average', name: '平均值'}
		                ]
		            }
		        }
		    ]
		};
		
		var mycharts = null;
		app.directive("mychart",function(){
			return {
				restrict:'AE',
				scope:{'id':'@','height':'@','legend':'=','item':'=','data':'='},
				replace:false,
				template:'<div style="height:{[{height}]}px;", id="{[{id}]}"></div>',
				link:function($scope,element,attrs,controller){
					for (var i = 0; i < count; i++) {
					    addData(false);
					}
					mycharts = echarts.init(element[0]);
					mycharts.setOption(option);
					setInterval(function (){
					    addData(true);
					    mycharts.setOption({
					    	xAxis:{
					    		data:date
					    	},
					        series: [{ 
					        	data: basedata
					        	},{
					            data: data
					        	}]
					        });
					},2000);
				}
			};
		});
	</script>
</head>
<body ng-app="machinedetail">
	机床详情
	<div ng-controller="customersCtrl">
		<table style="margin:0px auto;">
			<tr style="background-color: #D9E1F2;">
				<td>监测点</td>
				<td>转速</td>
				<td>有效值</td>
				<td>峰值</td>
				<td>通频值</td>
				<td>1倍频</td>
				<td>2倍频</td>
				<td>可选频段1频谱幅值</td>
				<td>可选频段2频谱幅值</td>
				<td>GAP电压</td>
			</tr>
			<tr ng-repeat="x in infos">
				<td>{{x.first}}</td>
				<td>{{x.second}}</td>
				<td>{{x.third}}</td>
				<td>{{x.forth}}</td>
				<td>{{x.fifth}}</td>
				<td>{{x.sixth}}</td>
				<td>{{x.seventh}}</td>
				<td>{{x.eighth}}</td>
				<td>{{x.ninth}}</td>
				<td>{{x.tenth}}</td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div mychart style="width:100%;height:400px;">
	</div>
	<br>
	<br>
	<br>
	<br>
	<div style="color: red; text-align:center;width:100%;height:800px;" id="myP" >start...</div> 
</body>
</html>