<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
<html>
<head>
<title>搜索的图片源列表列表</title>
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
<script type="text/javascript" src="/static/easyui/plugins/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/static/js/fix.js"></script>
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
		$(document).ready(function(){
			var Listurllist = {
				msgShow: function( title, msgString, msgType ) {
					$.messager.alert(title, msgString, msgType);
				},
				
				initEvent: function() {
				},
				
				init: function() {
					var _this = Listurllist;
					
					_this.initEvent();
				}
			};
			
			Listurllist.init();
			
			var queryParams = {};
			$('#list_table').edatagrid({
				pagination: true,
				rownumbers: true,
				fitColumns: true,
				singleSelect: true,
				fit: true,
				height:"100%",
				url:'/views/imageabout/urls',
				saveUrl: '/views/imageabout/saveurl',
                updateUrl: '/views/imageabout/updateurl',
				queryParams: queryParams, //额外的参数
				idField: 'id',
				pageSize: 20,
				columns:[[
					{field:'id', title:'ID',width:10, hidden: true},
					{field:'artist_name',title:'ArtistName',width:10,editor:'text'},
					{field:'url',title:'Url',width:100,editor:'text'}
					
				]],
				toolbar: [
					{
						text: "添加行",
						iconCls: "icon-add",
						handler: function(){
							$('#list_table').edatagrid('addRow')
						}
					},
					{
						text: "删除添加行",
						iconCls: "icon-undo",
						handler: function(){
							$('#list_table').edatagrid('cancelRow')
						}
					},
					{
						text: "保存",
						iconCls: "icon-save",
						handler: function(){
							$('#list_table').edatagrid('saveRow')
						}
					}
				]
			});
		});
	</script>
</body>
</html>