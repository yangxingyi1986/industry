<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<script src="<%=request.getContextPath()%>/js/sockjs-0.3.4.js"></script>
<script src="<%=request.getContextPath()%>/js/echarts.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/display/yunfan-socket.js"></script>

<script>
    
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
		    			debugger;
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
	    	}
    	}
    };
    
    websocket.onerror = function (evnt) {
    };
    
    websocket.onclose = function (evnt) {
    }

    $(document).ready(function(){
    });
</script>
</head>
<body>
<div style="color: red; text-align:center;width:100%;height:500px;" id="myP" >流数据处理</div> 
</body>
</html>