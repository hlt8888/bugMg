<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
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
<script type="text/javascript" src="/static/js/plugin.js"></script>
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
					<td style="width:100px;">Status:</td>
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
				</tr>
				<tr>
					<td style="width:50px;">RepairBy:</td>
					<td style="width:330px;">
						<input class="easyui-textbox" disabled type="text" name="username" data-options="required:true" value="${bug.repair }" />
					</td>
                    <td><a href="javascript:void(0)" class="easyui-linkbutton" id="saveBug" >Save</a> </td>
				</tr>
			</table>
		</div>
		<div style="padding: 10px 60px 20px 60px">
				${bug.content }
		</div>
		<div class="messages" id="messages">
		</div>
		<div>
			 <a href="#" id="showMessageEdit" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Add Message</a>
		</div>
	</div>
	<div id="ck_div" style="display:none;">
		<textarea class="ckeditor" cols="80" id="msg_content" name="msg_content" rows="10"></textarea>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="addMessage" >AddMessage</a> 
		</div>
	</div>
	<script>
		$(document).ready(function(){
			function initMessage() {
				var bug_id = $('#bug_id').val();
				$.ajax({
					type : "get",
					url : "/aj/bug/getmessages",
					data : "bug_id=" + bug_id,
					success : function(data) {
						var _bugs = data;
						if ( _bugs && _bugs.length > 0 ) {
							var _html = "";
							$(_bugs).each(function() {
								var msg = "<div id=\"p_" + this.id + "\">" + this.message + "</div>";
								_html += msg;
							});
							$('#messages').html(_html);
							
							$(_bugs).each(function() {
								var _id = "p_" + this.id;
								$('#' + _id).panel({
								    width:500,
								    height:150,
								    title:this.username + " " +this.createddate
								}); 
							});
						}
					}
				});
			}
			
			initMessage();
			
			$('#addMessage').on('click', function() {
				var bug_id = $('#bug_id').val();
				var _msg_content = CKEDITOR.instances.msg_content.getData(); //获取textarea的值
				$.ajax({
					type : "POST",
					url : "/aj/bug/addMessage",
					data : "bug_id=" + bug_id + "&msg_content=" + _msg_content,
					success : function(data) {
						var _data = data;
						
				    	if(_data.flag==="OK"){
				    		window.parent.mainPage.msgShow( _data.title, _data.msg, _data.msgType );
				    		$('#ck_div').toggle();
							CKEDITOR.instances.msg_content.setData(null);
							initMessage();
				    	} else {
				    		window.parent.mainPage.msgShow( _data.title, _data.msg, _data.msgType );
				    		return false;
				    	}
					}
				});
			});
			
			$('#saveBug').on('click', function() {
				var bug_id = $('#bug_id').val();
				var createdby = $('#createdby').val();
				var userid = $('#userid').val();
				var action = $('#action').combobox("getValue");
				if(userid != createdby){
					window.parent.mainPage.msgShow("错误","只有创建者才有权限进行状态更新！","error");
				} else {
					$.ajax({
						type : "get",
						url : "/aj/bug/updatebug",
						data : "bug_id=" + bug_id+"&action="+action+"&createdby="+createdby,
						success : function(data) {
							if(data === "OK"){
								window.parent.mainPage.msgShow("Save","保存成功！","info");
							} else {
								window.parent.mainPage.msgShow("错误","保存失败！","error");
							}
						}
					});
				}
			});
			
			$('#showMessageEdit').on('click', function() {
				$('#ck_div').toggle();
			});
		});
	</script>
</body>
</html>