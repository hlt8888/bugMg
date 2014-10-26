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
<link rel="stylesheet" type="text/css"
	href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css" />
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
</head>
<body style="overflow: hidden;">
	<form action="">
		<table>
			<tr>
				<td>bugTitle</td>
				<td><input name="name" /></td>
			</tr>
			<tr>
				<td>content</td>
				<td><textarea>${bug.name }</textarea></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
	</script>
</body>
</html>