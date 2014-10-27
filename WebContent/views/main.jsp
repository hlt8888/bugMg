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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BugManage</title>
<link rel="stylesheet" type="text/css"
	href="/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/default/easyui.css" />
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/static/js/fix.js"></script><!-- import this file for fixing two request bug -->
<script type="text/javascript" src="/static/js/main.js"></script>
<script type="text/javascript">
	_url = "http://127.0.0.1:8080";
	_islogin = <%=islogin%>;
	if(!_islogin){
		window.location.href = "/views/login.jsp";
	}
	var _menus = {
		"menus" : [
				{
					"menuid" : "1",
					"icon" : "icon-sys",
					"menuname" : "Bug管理",
					"menus" : [
							{
								"menuid" : "11",
								"menuname" : "未修复Bug",
								"icon" : "icon-log",
								"url" : _url+"/views/bug/buglist.jsp"
							},
							{
								"menuid" : "12",
								"menuname" : "修复中Bug",
								"icon" : "icon-log",
								"url" : "/"
							},
							{
								"menuid" : "13",
								"menuname" : "已修复Bug",
								"icon" : "icon-log",
								"url" : "/"
							} ]
				},
				{
					"menuid" : "2",
					"icon" : "icon-sys",
					"menuname" : "项目成员列表",
					"menus" : [
							{
								"menuid" : "21",
								"menuname" : "正式员工",
								"icon" : "icon-log",
								"url" : "/"
							},
							{
								"menuid" : "22",
								"menuname" : "实习生",
								"icon" : "icon-log",
								"url" : "/"
							}]
				}
		]
	};
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 60px; background: #B3DFDA; padding: 10px">
		<%=msg %>
	</div>
	<div data-options="region:'west',split:true,title:'West'"
		style="width: 150px; padding: 10px;" id="west">
		<div id="nav" class="easyui-accordion" data-options="fit:true,collapsed:true,border:false">
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
</body>
</html>