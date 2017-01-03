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
	    };
	    
	    websocket.onerror = function (evnt) {
	    };
	    
	    websocket.onclose = function (evnt) {
	    }
	    
	    
		var app = angular.module("machinedetail",[]);
	    app.config(function($httpProvider) {
	        $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
	        $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
	        $httpProvider.defaults.transformRequest = [function(data) {
	            var param = function(obj) {
	                var query = '';
	                var name, value, fullSubName, subName, subValue, innerObj, i;
	                for (name in obj) {
	                    value = obj[name];

	                    if (value instanceof Array) {
	                        for (i = 0; i < value.length; ++i) {
	                            subValue = value[i];
	                            fullSubName = name + '[' + i + ']';
	                            innerObj = {};
	                            innerObj[fullSubName] = subValue;
	                            query += param(innerObj) + '&';
	                        }
	                    } else if (value instanceof Object) {
	                        for (subName in value) {
	                            subValue = value[subName];
	                            fullSubName = name + '[' + subName + ']';
	                            innerObj = {};
	                            innerObj[fullSubName] = subValue;
	                            query += param(innerObj) + '&';
	                        }
	                    } else if (value !== undefined && value !== null) {
	                        query += encodeURIComponent(name) + '='
	                                + encodeURIComponent(value) + '&';
	                    }
	                }
	                return query.length ? query.substr(0, query.length - 1) : query;
	            };
	            return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
	        }];
	    });
		app.controller("formCtrl", function($scope, $http){
			$scope.search = function(){
				var tmpcontent = $scope.searchcon;
				if(tmpcontent == null){
					tmpcontent = "null";
				}
				var postdata = {searchcontent: tmpcontent,test:'123'};
				var postCfg = {
		        	headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
		        };
				var url = "<%=request.getContextPath()%>/display/searchRepairInfo.do";
				//$http.get("<%=request.getContextPath()%>/display/searchRepairInfo.do?searchcontent="+tmpcontent).success(function(data){
				$http.post(
					url,
					postdata,
					postCfg
				).success(function(data){
					$scope.contents = data.contents;
				});
			}
		});
	</script>
</head>
<body ng-app="machinedetail" ng-controller="formCtrl">
	<div ng-app="myApp">
		<form novalidate style="text-align:center">
			检索内容:
			<input type="text" ng-model="searchcon">&nbsp;&nbsp;
			<button ng-click="search()">检索</button>
		</form>
	</div>
	<br>
	<div style="text-align:center;">维修指南列表</div>
	<div style="width:100%">
		<table ng-table style="width:100%;margin:0px auto;">
			<tr style="background-color: #D9E1F2;">
				<td style="width:10%">序号</td>
				<td style="width:10%">问题描述</td>
				<td style="width:70%">维修指导</td>
				<td style="width:10%">匹配度</td>
			</tr>
			<tr ng-repeat="x in contents">
				<td style="width:10%">{{x.num}}</td>
				<td style="width:10%">{{x.description}}</td>
				<td style="width:70%">{{x.guildline}}</td>
				<td style="width:10%">{{x.match}}</td>
			</tr>
		</table>
	</div>
	<br>
	<br>
	<br>
	<br>
</body>
</html>