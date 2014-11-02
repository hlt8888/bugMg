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
<script type="text/javascript" src="/static/js/common.js"></script>
<style scoped="scoped">
.textbox {
	height: 20px;
	margin: 0;
	padding: 0 2px;
	box-sizing: content-box;
}
</style>
</head>
<body style="overflow: hidden;">

	<table id="list_table"></table>
	<div id="search" class="easyui-window" title="Bug Search" data-options="iconCls:'icon-save',closed:true"
		style="width: 400px; height: 200px; padding: 5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding: 10px;">
				<table cellpadding="5">
					<tr>
						<td>Bug Name:</td>
						<td><input class="easyui-validatebox textbox" /></td>
					</tr>
					<tr>
						<td>CreatedDate:</td>
						<td><input class="easyui-datebox textbox"></td>
					</tr>
					<tr>
						<td>CreatedBy:</td>
						<td>
							<input class="easyui-combobox" style="" data-options="
				                loader: createdbyloader,
				                mode: 'remote',
				                valueField: 'id',
				                textField: 'name'
				            ">
	            		</td>
					</tr>
				</table>
			</div>
			<div data-options="region:'south',border:false"
				style="text-align: right; padding: 5px 0 0;">
				<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
					href="javascript:void(0)" onclick="javascript:alert('ok')"
					style="width: 80px">Ok</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var createdbyloader = function(param,success,error){
	        var q = param.q || '';
	        if (q.length <= 1){return false;}
	        $.ajax({
	            url: '/user/searchJSON',
	            dataType: 'jsonp',
	            data: {
	                featureClass: "P",
	                style: "full",
	                maxRows: 20,
	                name_startsWith: q
	            },
	            success: function(data){
	                var items = $.map(data, function(item){
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
	    
		$(document).ready(function(){
			var action = Util.getUrlParam('action');
			$('#list_table').datagrid({
				pagination: true,
				rownumbers: true,
				fitColumns: true,
				singleSelect: true,
				height:"100%",
				// toolbar:'#toolbar',
				url:'/views/bug/bugs',
				queryParams: {action: action}, //额外的参数
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
							window.parent.addTab("Add new Bug",window.parent._url+"/views/bug/tonew_bug");
						}
					},
					{
						id: "unfole",
						text: "展开",
						iconCls: "layout-button-right",
						handler: function(){
							if(Util.idsIsChecked()) {
								var ids = Util.getSelections();
								window.parent.addTab(ids[0]+"bug_show",window.parent._url+"/views/bug/bugedit?id="+ids[0]);
							}
						}
					},
					{
		                id: 'chaxun',
		                text: '查询',
		                iconCls: 'icon-search',
		                handler: function() {
		                	$('#search').window('open');
		                    //$('#name').focus();
		                }
		            }
				]
			});
		});
	</script>
</body>
</html>