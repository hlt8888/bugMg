<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Add a Bug</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="">
<meta http-equiv="description" content="Add a new bug for engineer">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/static/easyui/themes/default/easyui.css" />
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/static/ckeditor/ckeditor.js"></script>
</head>
<body style="overflow: hidden;">
	<div class="easyui-panel" title="New Bug Info" style="display: inline;  float: left;">
		<div style="padding: 10px 60px 20px 60px">
			<form id="new_bug" method="post" action="/views/bug/add">
				<table cellpadding="5" style="width:95%;">
					<tr>
						<td style="width:50px;">Name:</td>
						<td>
							<input class="easyui-textbox" type="text" name="name" data-options="required:true" value="${bug.name }" />
						</td>
						<td style="width:100px;">Who Repair:</td>
	                    <td>
	                        <select class="easyui-combobox" name="language">
	                        	<option value="ar">Arabic</option>
	                        	<option value="bg">Bulgarian</option>
	                        	<option value="ca">Catalan</option>
	                        	<option value="zh-cht">Chinese Traditional</option>
	                        	<option value="cs">Czech</option>
	                        	<option value="da">Danish</option>
	                        	<option value="nl">Dutch</option>
	                        	<option value="en" selected="selected">English</option>
	                        	<option value="et">Estonian</option>
	                        	<option value="fi">Finnish</option>
	                        	<option value="fr">French</option>
	                        	<option value="de">German</option>
	                        	<option value="el">Greek</option>
	                        	<option value="ht">Haitian Creole</option>
	                        	<option value="he">Hebrew</option>
	                        	<option value="hi">Hindi</option>
	                        	<option value="mww">Hmong Daw</option>
	                        	<option value="hu">Hungarian</option>
	                        	<option value="id">Indonesian</option>
	                        </select>
	                    </td>
					</tr>
					<tr>
						<td colspan="4">
							<textarea class="ckeditor" cols="80" id="content" name="content" rows="10"></textarea>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>
	<script>
		function submitForm() {
			$('#new_bug').form('submit');
		}
		function clearForm() {
			$('#new_bug').form('clear');
		}
	</script>
</body>
</html>