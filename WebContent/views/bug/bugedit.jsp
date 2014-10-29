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
	<input type="hidden" id="bug_id" value="${bug.id }" />
	<div class="easyui-panel" title="Bug Info" style="display: inline;  float: left;">
		<div style="padding: 10px 60px 20px 60px">
				${bug.content }
		</div>
	</div>
	<div class="easyui-panel" title="Messages" style="display: inline; float: left;">
		<div>sdfsdfsdfsdfsfsdfdf</div>
	</div>
	<div>
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
		
		$(document).ready(function(){
			var bug_id = $('#bug_id').val();
			$.ajax({
				type : "get",
				url : "/aj/bug/getmessages",
				data : "bug_id=" + bug_id,
				success : function(data) {
				}
			});
		});
	</script>
</body>
</html>