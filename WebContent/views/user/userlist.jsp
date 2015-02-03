<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE>
<html>
<head>
<title>user列表</title>
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
<!-- <script type="text/javascript" src="/static/easyui/plugins/jquery.edatagrid.js"></script> -->
<script type="text/javascript" src="/static/js/fix.js"></script>
<!-- <script type="text/javascript" src="/static/js/common.js"></script> -->
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
			var Userlist = {
				lastIndex: undefined,
				rols: [],
				deps: [],
				sdroleid: undefined,
				formatOper: function( val,row,index ) {
					var _html = '';
					if( row.YXZT == 1 ) {
						_html = '<a href="javascript:void(0)" data-status="2" data-id="'+row.ID+'" class="lvstatus"><span style="color:gray;">启用</span>/<span style="color:red;">停用</span></a>';
					} else {
						_html = '<a href="javascript:void(0)" data-status="1" data-id="'+row.ID+'" class="lvstatus"><span style="color:red;">启用</span>/<span style="color:gray;">停用</span></a>  ';
					}
					return _html;  
				},
				
				msgShow: function( title, msgString, msgType ) {
					$.messager.alert(title, msgString, msgType);
				},
				getRoles: function() {
					var _this = Userlist;
					$.ajax({
			            type: 'POST',
			            url: '/user/roles',
			            data:{},
			            dataType: 'json',
			            async: false,
			            success: function(data, textStatus){
			            	_this.rols = data;
			            },
			            error: function(textStatus){
			              _this.msgShow("ERROR","请求！","error");
			            }
			         });
				},
				getDeps: function(roid) {
					var _this = Userlist;
					$.ajax({
			            type: 'POST',
			            url: '/user/deps',
			            data:{roid: roid},
			            dataType: 'json',
			            async: false,
			            success: function(data, textStatus){
			            	_this.deps = data;
			            },
			            error: function(textStatus){
			              _this.msgShow("ERROR","请求！","error");
			            }
			         });
				},
				getEditRow: function( lastIndex, field ){
					return $FD = jQuery('#list_table').datagrid('getEditor', {
				        index : lastIndex,
				        field : field
					});
				},
				initDatas: function() {
					var _this = Userlist;
					_this.getRoles();
					_this.getDeps(11);
				},
				
				initEvent: function() {
				},
				
				init: function() {
					var _this = Userlist;
					
					_this.initEvent();
					_this.initDatas();
				}
			};
			
			Userlist.init();
			
			var queryParams = {};
			$('#list_table').datagrid({
				pagination: true,
				rownumbers: true,
				fitColumns: true,
				singleSelect: true,
				fit: true,
				height:"100%",
				url:'/views/user/users',
				//saveUrl: '/views/user/saveuser',
                //updateUrl: '/views/user/updateuser',
				queryParams: queryParams, //额外的参数
				idField: 'id',
				columns:[[
					{field:'id', title:'ID',width:200},
					{field:'name',title:'UserName',width:200,editor:'text'},
					{field:'eamil',title:'Email',width:500,editor:'text'},
					{
						field:'role',
						title:'Role',
						width:500,
						formatter: function(val,row,index) {
							for( var i = 0; i < Userlist.rols.length; i++ ) {
								if( Userlist.rols[i].roid == val) {
									return Userlist.rols[i].roname;
								}
							}
							return val;
						},
						editor: {
							type: 'combobox',
							options: {
								valueField: 'roid',
								textField: 'roname',
								data: Userlist.rols,
								required: true,
								onSelect: function(rec) {
									if( !!rec ) {
										Userlist.getDeps( rec.roid );
										Userlist.sdroleid = rec.roid;
										var depart =  Userlist.getEditRow(Userlist.lastIndex, 'depart');
		                                var depart_t = depart.target;
		                                depart_t.combobox('clear');
		                                var url = '/user/depsbyid?roid=' + rec.roid + '&random=' + Math.random();
		                                depart_t.combobox('reload', url);
		                                
		                                var dpno =  Userlist.getEditRow(Userlist.lastIndex, 'dpno');
		                                var dpno_t = dpno.target;
		                                dpno_t.text('clear');
		                                dpno_t.text('reload', rec.roid);
									} else {
										Userlist.sdroleid = null;
									}
								}
							}
						}
					},
					{
						field:'depart',
						title:'Department',
						width:500,
						formatter: function(val,row,index) {
							for( var i = 0; i < Userlist.deps.length; i++ ) {
								if( Userlist.deps[i].depid == val) {
									return Userlist.deps[i].depname;
								}
							}
							return val;
						},
						editor: {
							type: 'combobox',
							options: {
								valueField: 'depid',
								textField: 'depname',
								data: Userlist.deps,
								required: true
							}
						}
					},
					{
						field:'dpno',
						title:'DPNO',
						width:500,
						editor: {
							type: 'text'
						}
					}
				]],
				onDblClickRow:function(rowIndex,rowData){
		        	Userlist.getDeps(10);
		        	debugger;
		        	if (Userlist.lastIndex != rowIndex){
						$('#list_table').datagrid('endEdit', Userlist.lastIndex);
						$('#list_table').datagrid('beginEdit', rowIndex);
					}
		        	
		        	Userlist.lastIndex = rowIndex;
		        },
				toolbar: [
					{
						text: "添加行",
						iconCls: "icon-add",
						handler: function(){
							$('#list_table').datagrid('endEdit', Userlist.lastIndex);
							$('#list_table').datagrid('appendRow',{
								id:'',
								name:'',
								email:'',
								role:'',
								depart:'',
								dpno:''
							});
								
							Userlist.lastIndex = $('#list_table').datagrid('getRows').length-1;
							$('#list_table').datagrid('selectRow', Userlist.lastIndex);
							$('#list_table').datagrid('beginEdit', Userlist.lastIndex);
						}
					},
					{
						text: "删除添加行",
						iconCls: "icon-undo",
						handler: function(){
							editRow = undefined;
							$("#list_table").datagrid('rejectChanges');
							$("#list_table").datagrid('unselectAll');
						}
					},
					{
						text: "保存",
						iconCls: "icon-save",
						handler: function(){
							$("#list_table").datagrid('endEdit', editRow);

							//使用JSON序列化datarow对象，发送到后台。
							var rows = $("#list_table").datagrid('getChanges');
							debugger;
							var rowstr = JSON.stringify(rows);
						}
					}
				]
			});
		});
	</script>
</body>
</html>