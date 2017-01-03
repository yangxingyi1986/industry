<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>地图测试</title>
<script src="<%=request.getContextPath()%>/js/echarts.min.js" ></script>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/map/china.js"></script>
<script src="<%=request.getContextPath()%>/js/yunfan.js"></script>
<script src="<%=request.getContextPath()%>/js/display/datamap.js"></script>
<link href="<%=request.getContextPath()%>/css/datamap.css" rel="stylesheet"/>
<script type="text/javascript">
	var data = [
            {name: '大连', value: 470},
            {name: '沈阳', value: 470},
            {name: '北京', value: 470},
            {name: '上海', value: 470},
            {name: '广州', value: 470},
            {name: '郑州', value: 470},
            {name: '天津', value: 470},
            {name: '南京', value: 470},
            {name: '哈尔滨', value: 600},
            {name: '乌鲁木齐', value: 470},
            {name: '石家庄', value: 470}
       ];
       var geoCoordMap = {
           '大连':[121.62,38.92],
           '沈阳':[123.38,41.8],
           '北京':[116.46,39.92],
           '上海':[121.48,31.22],
           '广州':[113.23,23.16],
           '郑州':[113.65,34.76],
           '天津':[117.2,39.13],
           '南京':[118.78,32.04],
           '哈尔滨':[126.63,45.75],
           '乌鲁木齐':[87.68,43.77],
           '石家庄':[114.48,38.03]
       };

       var convertData = function (data) {
           var res = [];
           for (var i = 0; i < data.length; i++) {
               var geoCoord = geoCoordMap[data[i].name];
               if (geoCoord) {
                   res.push({
                       name: data[i].name,
                       value: geoCoord.concat(data[i].value)
                   });
               }
           }
           return res;
       };

       option = {
           backgroundColor: '#ffffff',
           title: {
               text: '全国机床分布',
               left: 'center',
               textStyle: {
                   color: '#fff'
               }
           },
           tooltip : {
               trigger: 'item',
               formatter: function(a){return a.data.value[2];}
           },
           legend: {
               orient: 'vertical',
               y: 'bottom',
               x:'right',
               data:['pm2.5'],
               textStyle: {
                   color: '#fff'
               }
           },
           geo: {
               map: 'china',
               label: {
                   emphasis: {
                       show: false
                   }
               },
               roam: true,
               itemStyle: {
                   normal: {
                       areaColor: '#EEEEEE',
                       borderColor: '#969696'
                   },
                   emphasis: {
                       areaColor: '#48d1cc'
                   }
               }
           },
           series : [
               {
                   name: '机床',
                   type: 'scatter',
                   coordinateSystem: 'geo',
                   data: convertData(data),
                   symbolSize: function (val) {
                       return val[2] / 25;
                   },
                   label: {
                       normal: {
                           formatter: '{b}',
                           position: 'right',
                           show: false
                       },
                       emphasis: {
                           show: true
                       }
                   },
                   itemStyle: {
                       normal: {
                           color: '#ddb926'
                       }
                   }
               },
               {
                   name: 'Top 10',
                   type: 'effectScatter',
                   coordinateSystem: 'geo',
                   data: convertData(data.sort(function (a, b) {
                       return b.value - a.value;
                   }).slice(0, 10)),
                   symbolSize: function (val) {
                       return val[2] / 25;
                   },
                   showEffectOn: 'render',
                   rippleEffect: {
                       brushType: 'stroke'
                   },
                   hoverAnimation: true,
                   label: {
                       normal: {
                           formatter: '{b}',
                           position: 'right',
                           show: true
                       }
                   },
                   itemStyle: {
                       normal: {
                           color: 'blue',
                           shadowBlur: 10,
                           shadowColor: '#333'
                       }
                   },
                   zlevel: 1
               }
           ]
       };
       $(document).ready(function(){
    	   $("#mapchart").height($(window).height() * 0.9);
	       var mycharts = echarts.init($("#mapchart")[0]);
	       mycharts.setOption(option);
	       mycharts.on("click",function(params){
	    	   if("geo" == params.componentType){
	    	   }else if("series" == params.componentType && "effectScatter" == params.componentSubType){
	    		   $("#devicedetail").attr("href","<%=request.getContextPath()%>/display/entryDetailInfo.do?machineId="+params.name);
		    	   clickalert(event);
	    	   }
	       });
       });
</script>
</head>
<body>
	<div id="mapchart" style="height:100%;width:95%"></div>
	<div id="mapalert" class="show_div left">
		<div class="first_div" >
			<font color="#327DE1" class="title_font">
				生产信息
			</font>
			<font class="first_font">
				当前速度：<label>123</label>
			</font>
			<font class="second_font">
				当前速度：<label>123</label>
			</font>
			<font class="third_font">
				当前速度：<label>123</label>
			</font>
		</div>
		<div class="second_div">
			<font color="#327DE1" class="title_font">
				进给速度（rm/min）
			</font>
			<font class="first_font">
				当前速度：<label>123</label>
			</font>
			<font class="second_font">
				设定速度：<label>123</label>
			</font>
			<font class="third_font">
				进给速度：<label>123</label>
			</font>
		</div>
		<div class="third_div">
			<table>
				<tr>
					<td class="left">
						工作模式：<label>MDA</label>
					</td>
					<td class="right">
						轴数：<label>5</label>
					</td>
				</tr>
				<tr>
					<td class="left">
						主轴状态：<label>运行</label>
					</td>
					<td class="right">
						报警：<label>无</label>
					</td>
				</tr>
				<tr>
					<td class="left">
						程序状态：<label>进行</label>
					</td>
					<td class="right">
						当前状态:<label>运行良好</label>
					</td>
				</tr>
				<tr>
					<td class="left">开机时间：<label>6：00</label>
					</td>
					<td class="right">
						闲置时间：<label>00:00:00</label>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;width:100%;" colspan=2 >
						<a id="devicedetail" href="">设备详情</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>