<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页布局</title>
	<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$(".first-menu").each(function(item){
				if($(this).attr("href") != null){
					if($(this).attr("href").indexOf(location.pathname) >= 0){
						$(this).addClass("active-menu");
					}
				}
			})
			
		});
	</script>
</head>
<body>
	
	<nav class="navbar-default navbar-side" role="navigation">
		<div id="sideNav" href=""><i class="fa fa-caret-right"></i></div>
            <div class="sidebar-collapse" style="overflow: auto;" >
                <ul class="nav" id="main-menu">

                    <li>
                        <a class="first-menu" href="<%=request.getContextPath()%>/display/entryMap.do"><i class="fa fa-map"></i>机床地图</a>
                    </li>
                    <li>
                        <a class="first-menu" href="<%=request.getContextPath()%>/display/entryFatalStatics.do"><i class="fa fa-calculator"></i>故障统计</a>
                    </li>
                    <li>
                        <a class="first-menu" href="<%=request.getContextPath()%>/display/entryRepairInfo.do"><i class="fa fa-wrench"></i>维修定位</a>
                    </li>
                </ul>
            </div>
        </nav>
</body>
</html>