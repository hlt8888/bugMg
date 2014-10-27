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
	href="/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/default/easyui.css" />
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/easyui/jquery.easyui.min.js"></script>
</head>
<body style="overflow: hidden;">

	<table id="list_table"></table>

	<script type="text/javascript">
		$(document).ready(function(){
			$('#list_table').datagrid({
				pagination: true,
				rownumbers: true,
				fitColumns: true,
				singleSelect: true,
				height:"100%",
				// toolbar:'#toolbar',
				url:'/views/bug/bugs',
				columns:[[
					{field:'id', title:'ID',width:200},
					{field:'name',title:'BugName',width:200},
					{field:'content',title:'BugContent',width:500}
				]],
				toolbar: [
					{
						id: "unfole",
						text: "新建",
						iconCls: "icon-add",
						handler: function(){
							window.parent.addTab("Add new Bug",window.parent._url+"/views/bug/bugnew.jsp");
						}
					},
					{
						id: "unfole",
						text: "展开",
						iconCls: "layout-button-right",
						handler: function(){
							if(idsIsChecked()) {
								var ids = getSelections();
								window.parent.addTab(ids[0]+"bug_show",window.parent._url+"/views/bug/bugedit?id="+ids[0]);
							}
						}
					}
				]
			});

			var getSelections = function() {
				var ids = [];
				var rows = $('#list_table').datagrid('getSelections');
				for(var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}

				return ids;
			}

			var idsIsChecked = function() {
				if(getSelections().length ==0) {
					alert("请选择一条数据！");
					return false;
				}
				return true;
			}
		});
	</script>
</body>
</html>