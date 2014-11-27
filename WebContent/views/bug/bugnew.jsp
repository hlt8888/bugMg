<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
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
			<form id="new_bug" method="post" >
				<input type="hidden" name="action" value="1" />
				<input type="hidden" name="id" value="0" />
				<table cellpadding="5" style="width:95%;">
					<tr>
						<td style="width:50px;">Name:</td>
						<td>
							<input class="easyui-textbox" type="text" id="name" name="name" data-options="required:true" value="" />
						</td>
						<td style="width:100px;">Who Repair:</td>
	                    <td>
	                        <input class="easyui-combobox" id="repair" name="repair" style="" data-options="
				                loader: repair,
				                mode: 'remote',
				                valueField: 'id',
				                textField: 'name'
				            ">
	                    </td>
					</tr>
					<tr>
						<td colspan="4">
							<textarea class="ckeditor" cols="80" id="content" name="content" rows="10"></textarea>
						</td>
					</tr>
					<tr>
	                    <td colspan="4"><input type="submit" value="Submit"></input></td>
	                </tr>
				</table>
			</form>
		</div>
	</div>
	<script>
		var repair = function(param,success,error){
	        var q = param.q || '';
	        if (q.length <= 1){return false;}
	        $.ajax({
	            url: '/user/searchJSON',
	            dataType: 'json',
	            data: {
	                featureClass: "P",
	                style: "full",
	                maxRows: 20,
	                name_startsWith: q
	            },
	            success: function(data){
	                var items = $.map(data.value, function(item){
	                    return {
	                        id: item.id,
	                        name: item.name
	                    };
	                });
	                success(items);
	            },
	            error: function(){
	                error.apply(this, arguments);
	            }
	        });
	    };
		function submitForm() {
			$('#new_bug').form('submit');
		}
		function clearForm() {
			$('#new_bug').form('clear');
		}
		
		$(function(){
			$('#new_bug').form({
			    url:'/views/bug/add',
			    onSubmit: function(){
			    	var content = CKEDITOR.instances.content.getData(); //获取textarea的值
			    	var name = $('#name').val(), repair = $("[name='repair']").val();
			    	if( !content || !name || !repair ) {
			    		window.parent.mainPage.msgShow("错误","请把信息填写完整后再提交！","error");
			    		return false;
			    	}
			    },
			    success:function(data){
			    	var _data = JSON.parse(data);
			    	if(_data.flag==="OK"){
			    		window.parent.mainPage.msgShow( _data.title, _data.msg, _data.msgType );
			    	} else {
			    		window.parent.mainPage.msgShow( _data.title, _data.msg, _data.msgType );
			    		return false;
			    	}
			    	
			    	window.parent.mainPage.addTab("未修复Bug","/views/bug/buglist.jsp?action=1");
			        window.parent.mainPage.closeTab("Add new Bug");
			    }
			});
		});
	</script>
</body>
</html>