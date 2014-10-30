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
<script type="text/javascript" src="/static/ckeditor/ckeditor.js"></script>
</head>
<body style="overflow: auto;">
	<div class="easyui-panel" style="display: inline;  float: left;">
		<div style="padding: 10px 60px 20px 60px">
			<input type="hidden" id="bug_id" name="bug_id" value="${bug.id }" />
			<input type="hidden" id="createdby" name="createdby" value="${bug.createdby }" />
			<input type="hidden" id="userid" value="${userid }" />
			<table cellpadding="5" style="width:95%;">
				<tr>
					<td style="width:50px;">Name:</td>
					<td style="width:330px;">
						<input class="easyui-textbox" disabled type="text" name="name" data-options="required:true" value="${bug.name }" />
					</td>
					<td style="width:100px;">Who Repair:</td>
                    <td>
                        <select class="easyui-combobox" name="action" id="action">
                        	<option value=""></option>
							<option value="1" <c:if test="${bug.action eq 1}">selected="selected"</c:if>>新建</option>
							<option value="2" <c:if test="${bug.action eq 2}">selected="selected"</c:if>>处理中</option>
							<option value="3" <c:if test="${bug.action eq 3}">selected="selected"</c:if>>已完成</option>
                        </select>
                    </td>
				</tr>
				<tr>
					<td style="width:50px;">Createdby:</td>
					<td style="width:330px;">
						<input class="easyui-textbox" disabled type="text" name="username" data-options="required:true" value="${bug.username }" />
					</td>
					<td style="width:50px;">Createddate:</td>
					<td style="width:330px;">
						<input class="easyui-textbox" disabled type="text" name="createddate" data-options="required:true" value="${bug.createddate }" />
					</td>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateBug()">Save</a> </td>
				</tr>
			</table>
		</div>
		<div style="padding: 10px 60px 20px 60px">
				${bug.content }
		</div>
		<div class="messages" id="messages">
			<div>sdfsdfsdfsdfsfsdfdf</div>
		</div>
	</div>
	<div style="display:none;">
		<textarea class="ckeditor" cols="80" id="content" name="content" rows="10"></textarea>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitMessage()">SubmitMessage</a> 
		</div>
	</div>
	<script>
		function submitMessage() {
			var $_c = CKEDITOR.instances.content.getData(); //获取textarea的值
			console.log($_c);
			CKEDITOR.instances.content.setData(null);
		}
		
		function updateBug(){
			var bug_id = $('#bug_id').val();
			var createdby = $('#createdby').val();
			var userid = $('#userid').val();
			var action = $('#action').val();
			if(userid != createdby){
				alert("只有创建者才有权限进行状态更新！");
			} else {
				$.ajax({
					type : "get",
					url : "/aj/bug/updatebug",
					data : "bug_id=" + bug_id+"&action="+action+"&createdby="+createdby,
					success : function(data) {
						alert(data);
					}
				});
			}
		}
		$(document).ready(function(){
			var bug_id = $('#bug_id').val();
			$.ajax({
				type : "get",
				url : "/aj/bug/getmessages",
				data : "bug_id=" + bug_id,
				success : function(data) {
					console.log(data);
				}
			});
		});
	</script>
</body>
</html>