<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>bug编辑页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/default/easyui.css" />
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
table ._right {
	text-align: right;
}
</style>
</head>
<body style="overflow: hidden;">
	<div class="easyui-panel" title="Bug Info" style="display: inline;  float: left;">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td class="_right">Name:</td>
						<td><input class="easyui-textbox" type="text" name="name"
							data-options="required:true" value="${bug.name }"></input></td>
					</tr>
					<tr>
						<td class="_right">Content:</td>
						<td><input class="easyui-textbox" name="message"
							data-options="multiline:true" style="height: 60px" value="${bug.content }"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">Submit</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>
	<div class="easyui-panel" title="Bug Messges"  style="display: inline; float: left;">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td class="_right">Name:</td>
						<td><input class="easyui-textbox" type="text" name="name"
							data-options="required:true" value="${bug.name }"></input></td>
					</tr>
					<tr>
						<td class="_right">Content:</td>
						<td><input class="easyui-textbox" name="message"
							data-options="multiline:true" style="height: 60px" value="${bug.content }"></input></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">Submit</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>
	<script>
		function submitForm() {
			$('#ff').form('submit');
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>
</html>