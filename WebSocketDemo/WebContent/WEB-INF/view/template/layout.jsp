<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页布局</title>
	<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" />
	<link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/css/custom-styles.css" rel="stylesheet"/>
	<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.metisMenu.js"></script>
    <script src="<%=request.getContextPath()%>/js/custom-scripts.js"></script>
    <script src="<%=request.getContextPath()%>/js/raphael-2.1.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/morris.js"></script>
	<script src="<%=request.getContextPath()%>/js/angular.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>
	<div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
			<tiles:insertAttribute name="header" />
        </nav>
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
				<tiles:insertAttribute name="menu" />
            </div>
        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	
</body>
</html>