<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>请登录</title>
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
</head>
<body style="overflow: hidden;">
	<div>
		<div style="width: 40%; margin:0 auto; padding:200px 0;">
		    <div class="easyui-panel" title="Login" style="width:400px">
		        <div style="padding:10px 60px 20px 60px">
		        <form id="login_fm" method="post" action="">
		            <table cellpadding="5">
		                <tr>
		                    <td>Email:</td>
		                    <td><input class="easyui-textbox" type="text" id="email" data-options="required:true,validType:'email'"></input></td>
		                </tr>
		                <tr>
		                    <td>Password:</td>
		                    <td><input class="easyui-textbox" type="password" id="pwd" data-options="required:true"></input></td>
		                </tr>
		            </table>
		        </form>
		        <div style="text-align:center;padding:5px">
		            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="aj_login()">Submit</a>
		            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
		        </div>
		        </div>
		    </div>
	    </div>
    </div>
	<script>
		function aj_login() {
			var email = $('#email').val();
			var pwd = $('#pwd').val();
			$.ajax({
				type : "POST",
				url : "/views/login",
				data : "email=" + email + "&pwd=" + pwd,
				success : function(data) {
					var flag = data.flag;
					if(flag=="OK"){
						window.location.href='/views/main.jsp';
					} else {
						alert("用户名或密码错误,请重新输入");
						clearForm();
					}
				}
			});
		}
		function clearForm() {
			$('#login_fm').form('clear');
		}
	</script>
</body>
</html>