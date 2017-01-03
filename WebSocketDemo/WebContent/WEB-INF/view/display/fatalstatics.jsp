<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>机床故障统计分析</title>
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
	    };
	    
	    websocket.onerror = function (evnt) {
	    };
	    
	    websocket.onclose = function (evnt) {
	    }
	    
	    var date = [];
	    var month = new Date().getMonth() + 1;
	    var year = new Date().getFullYear();
	    for(var index = 0 ; index < 6 ; index ++){
	    	var tmpmonth = month - (5-index);
	    	if(tmpmonth <= 0){
	    		tmpmonth += 12;
	    	}
	    	date.push([year,tmpmonth].join("-"));
	    }
	    var app = angular.module("fatalstatics",[]);
	    repairtimechartoption = {
		    title: {
		        text: '机床近六个月故障维修情况'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        /* data:['故障预警','当月未维修','已维修','无故障','总机床'] */
		        data:['故障预警','当月未维修','已维修'],
		        top:'10%'
		    },
		    xAxis:  {
		        type: 'category',
		        boundaryGap: true,
		        data: date
		    },
		    yAxis: {
		        type: 'value',
		        axisLabel: {
		            formatter: '{value}'
		        }
		    },
		    series: [
		        {
		            name: '故障预警',
		            type: 'bar',
		            data: [3,4,1,14,2,10]
		        },
		        {
		            name:'当月未维修',
		            type:'bar',
		            data: [0,0,0,2,0,0],
		            stack:'fatal'
		        },
		        {
		            name:'已维修',
		            type:'bar',
		            data: [4,4,1,16,2,10],
		            stack:'fatal'
		        }
		    ]
		};
	    
		var repairtimechart = null;
		app.directive("repairtimechart",function(){
			return {
				restrict:'AE',
				scope:{'id':'@','height':'@','legend':'=','item':'=','data':'='},
				replace:false,
				template:'<div style="height:{[{height}]}px;", id="{[{id}]}"></div>',
				link:function($scope,element,attrs,controller){
					repairtimechart = echarts.init(element[0]);
					repairtimechart.setOption(repairtimechartoption);
				}
			};
		});
		
	    fatallevelchartoption = {
			    title: {
			        text: '机床近六个月故障等级情况'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        /* data:['故障预警','当月未维修','已维修','无故障','总机床'] */
			        data:['一级','二级','三级','四级'],
			        top:'10%'
			    },
			    xAxis:  {
			        type: 'category',
			        boundaryGap: true,
			        data: date
			    },
			    yAxis: {
			        type: 'value',
			        axisLabel: {
			            formatter: '{value}'
			        }
			    },
			    series: [
			        {
			            name: '一级',
			            type: 'bar',
			            data: [0,0,0,0,1,2]
			        },
			        {
			            name: '二级',
			            type: 'bar',
			            data: [0,1,1,1,0,2]
			        },
			        {
			            name:'三级',
			            type:'bar',
			            data: [2,2,0,7,1,3]
			        },
			        {
			            name:'四级',
			            type:'bar',
			            data: [2,1,0,10,0,3]
			        }
			    ]
			};
		var fatallevelchart = null;
		app.directive("fatallevelchart",function(){
			return {
				restrict:'AE',
				scope:{'id':'@','height':'@','legend':'=','item':'=','data':'='},
				replace:false,
				template:'<div style="height:{[{height}]}px;", id="{[{id}]}"></div>',
				link:function($scope,element,attrs,controller){
					fatallevelchart = echarts.init(element[0]);
					fatallevelchart.setOption(fatallevelchartoption);
				}
			};
		});
		fatalrepairtimechartoption = {
			    title: {
			        text: '机床近六个月维修时间统计情况'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        /* data:['故障预警','当月未维修','已维修','无故障','总机床'] */
			        data:['故障总数','10min<= x','10min<= x <60min','60min<= x <300min','300min<= x <600min','600min<= x'],
			        top:'10%'
			    },
			    xAxis:  {
			        type: 'category',
			        boundaryGap: true,
			        data: date
			    },
			    yAxis: {
			        type: 'value',
			        axisLabel: {
			            formatter: '{value}'
			        }
			    },
			    grid:{
			    	top:'25%'
			    },
			    series: [
			        {
			        	name: '故障总数',
			        	type: 'line',
			        	data: [4,4,1,18,2,10]
			        },
			        {
			            name: '10min<= x',
			            type: 'bar',
			            data: [3,3,0,3,1,2]
			        },
			        {
			            name: '10min<= x <60min',
			            type: 'bar',
			            data: [1,0,1,4,0,6]
			        },
			        {
			            name:'60min<= x <300min',
			            type:'bar',
			            data: [0,1,0,10,0,2]
			        },
			        {
			            name:'300min<= x <600min',
			            type:'bar',
			            data: [0,0,0,0,1,0]
			        },
			        {
			            name:'600min<= x',
			            type:'bar',
			            data: [0,0,0,1,0,0]
			        }
			    ]
			};
		var fatalrepairtimechart = null;
		app.directive("fatalrepairtimechart",function(){
			return {
				restrict:'AE',
				scope:{'id':'@','height':'@','legend':'=','item':'=','data':'='},
				replace:false,
				template:'<div style="height:{[{height}]}px;", id="{[{id}]}"></div>',
				link:function($scope,element,attrs,controller){
					fatalrepairtimechart = echarts.init(element[0]);
					fatalrepairtimechart.setOption(fatalrepairtimechartoption);
				}
			};
		});

		var districtnumchartoption = {
		    baseOption: {
		        timeline: {
		            axisType: 'category',
		            autoPlay: true,
		            playInterval: 1000,
		            data: date,
		            label: {
		                formatter : function(s) {
		                    /* return (new Date(s)).getFullYear(); */
		                    return ((new Date(s)).getMonth() + 1) + '月';
		                }
		            }
		        },
		        title: {
		            subtext: '近六个月地域机床数据统计'
		        },
		        tooltip: {},
		        legend: {
		            x: 'right',
		            data: ['正常机床', '故障机床','总机床'],
		            selected: {
		                '正常机床': true, '故障机床': true
		            }
		        },
		        calculable : true,
		        grid: {
		            top: '30%',
		            bottom: '20%'
		        },
		        xAxis: [
		            {
		                'type':'category',
		                'axisLabel':{'interval':0},
		                'data':[
		                    '北京','\n天津','石家庄','\n大连','上海','\n沈阳','乌鲁木齐','\n哈尔滨',
		                    '广州','\n郑州','南京'
		                ],
		                splitLine: {show: false}
		            }
		        ],
		        yAxis: [
		            {
		                type: 'value',
		                name: '台'
		            }
		        ],
		        series: [
		            {name: '总机床', type: 'bar', itemStyle:{normal:{color:'#d48265'}}},
		            {
		                name: '故障占比',
		                type: 'pie',
		                center: ['45%', '18%'],
		                radius: '25%'
		            }
		        ]
		    },
		    options: [
		        {
		            title: {text: '全国机床分布'},
		            series: [
		                {data: [430,443,430,427,430,420,446,490,460,467,440]},
		                {data: [
		                    {name: '正常机床', value: [430,443,430,427,430,420,446,488,459,466,440]},
		                    {name: '故障机床', value: [0,0,0,0,0,0,0,2,1,1,0]}
		                ]}
		            ]
		        },
		        {
		            title: {text: '全国机床分布'},
		            series: [
		                {data: [430,450,430,429,440,420,446,510,460,467,460]},
		                {data: [
		                    {name: '正常机床', value: [430,450,430,428,440,419,446,508,460,467,460]},
		                    {name: '故障机床', value: [0,0,0,1,0,1,0,2,0,0,0]}
		                ]}
		            ]
		        },
		        {
		            title: {text: '全国机床分布'},
		            series: [
		                {data: [430,450,430,429,440,470,446,520,460,467,470]},
		                {data: [
		                    {name: '正常机床', value: [430,450,430,428,440,470,446,520,460,467,470]},
		                    {name: '故障机床', value: [0,0,0,1,0,0,0,0,0,0,0]}
		                ]}
		            ]
		        },
		        {
		            title: {text: '全国机床分布'},
		            series: [
		                {data: [460,456,470,429,470,470,446,520,460,467,470]},
		                {data: [
		                    {name: '正常机床', value: [457,450,469,428,470,469,444,519,459,466,469]},
		                    {name: '故障机床', value: [3,6,1,1,0,1,2,1,1,1,1]}
		                ]}
		            ]
		        },
		        {
		            title: {text: '全国机床分布'},
		            series: [
		                {data: [460,470,470,470,470,470,470,550,460,470,470]},
		                {data: [
		                    {name: '正常机床', value: [459,470,470,470,470,470,470,550,460,469,470]},
		                    {name: '故障机床', value: [1,0,0,0,0,0,0,0,0,1,0]}
		                ]}
		            ]
		        },
		        {
		            title: {text: '全国机床分布'},
		            series: [
		                {data: [470,470,470,470,470,470,470,600,470,470,470]},
		                {data: [
		                    {name: '正常机床', value: [469,468,469,470,469,469,470,597,469,470,470]},
		                    {name: '故障机床', value: [1,2,1,0,1,1,0,3,1,0,0]}
		                ]}
		            ]
		        }
		    ]
		};
		var districtnumchart = null;
		app.directive("districtnumchart",function(){
			return {
				restrict:'AE',
				scope:{'id':'@','height':'@','legend':'=','item':'=','data':'='},
				replace:false,
				template:'<div style="height:{[{height}]}px;", id="{[{id}]}"></div>',
				link:function($scope,element,attrs,controller){
					districtnumchart = echarts.init(element[0]);
					districtnumchart.setOption(districtnumchartoption);
				}
			};
		});
	</script>
</head>
<body ng-app="fatalstatics" style="height:800px;">
	<div style="height:700px;">
	<div repairtimechart style="width:50%;height:300px;float:left;">
	</div>
	
	<div fatallevelchart style="width:50%;height:300px;float:left;">
	</div>
	
	<div fatalrepairtimechart style="width:50%;height:400px;float:left;">
	</div>
	
	<div districtnumchart style="width:50%;height:400px;float:left;">
	</div>
	</div>
</body>
</html>