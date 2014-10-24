<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>bug列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css" />
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/plugins/jquery.parser.js"></script>
<script type="text/javascript" src="/easyui/plugins/jquery.tooltip.js"></script>
</head>
<body style="overflow: hidden">
	<table id="list_table"></table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Remove User</a>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#list_table').datagrid({
				pagination: true,
				rownumbers: true,
				fitColumns: true,
				singleSelect: true,
				toolbar:'#toolbar',
				url:'/bugs',
				columns:[[
					{field:'id', title:'ID',width:200},
					{field:'name',title:'BugName',width:200},
					{field:'content',title:'BugContent',width:500}
				]]
			});
		});
	</script>
</body>
</html>