<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map" %>
<%
	Map<String, Object> user = (Map<String, Object>)session.getAttribute("user");
	String msg = "请您先之后，再使用该系统";
	boolean islogin = false;
	if(user != null){
		String name = (String)user.get("name");
		msg = name+"欢迎您使用bugMg系统";
		islogin = true;
	}
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>BugManage</title>
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/static/css/main.css">
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/static/js/sockjs.min.js"></script>
<script type="text/javascript" src="/static/js/fix.js"></script><!-- import this file for fixing two request bug -->
<script type="text/javascript" src="/static/js/main.js"></script>
<script type="text/javascript">
	_islogin = <%=islogin%>;
	if(!_islogin){
		// a test comm
		window.location.href = "/views/login.jsp";
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 60px; background: #B3DFDA; padding: 10px">
		<%=msg %>
		<div style="display: inline; float: right; padding-right: 20px;">
			<a href="#" class="easyui-menubutton" data-options="menu:'#sys_menu',iconCls:'icon-help'">系统菜单</a>
	    	<div id="sys_menu" style="width:100px;">
	        	<div id="logout">退出</div>
		        <div>重新登录</div>
		        <div>About</div>
		    </div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'West'"
		style="width: 150px; padding: 10px;" id="west">
		<div id="nav" class="easyui-accordion" data-options="fit:true,border:false">
		<!--  导航内容自动加载 -->
		</div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width: 100px; padding: 10px;">
		Working!!!
	</div>
	<div data-options="region:'south',border:false" style="height: 50px; background: #A9FACD; padding: 10px;">
		Working!!!
	</div>
	<div id="mainPanle" data-options="region:'center'" style="">
		<div  id="tabs" class="easyui-tabs" data-options='fit:true,border:false'  style="width: 100%; height: 100%;overflow:auto;">
		</div>
	</div>
	
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</html>
