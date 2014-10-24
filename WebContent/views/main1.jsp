<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Full Layout - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/easyui/demo/demo.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #B3DFDA; padding: 10px">north
		region</div>
	<div data-options="region:'west',split:true,title:'West'"
		style="width: 150px; padding: 10px;">
		<ul id="tt"></ul>
	</div>
	<div
		data-options="region:'east',split:true,collapsed:true,title:'East'"
		style="width: 100px; padding: 10px;">east region</div>
	<div data-options="region:'south',border:false"
		style="height: 50px; background: #A9FACD; padding: 10px;">south
		region</div>
	<div data-options="region:'center',title:'Center'">
		<div class="easyui-tabs" style="width: 700px; height: 250px">
			<div title="About" style="padding: 10px">
				<p style="font-size: 14px">jQuery EasyUI framework helps you
					build your web pages easily.</p>
				<ul>
					<li>easyui is a collection of user-interface plugin based on
						jQuery.</li>
					<li>easyui provides essential functionality for building
						modem, interactive, javascript applications.</li>
					<li>using easyui you don't need to write many javascript code,
						you usually defines user-interface by writing some HTML markup.</li>
					<li>complete framework for HTML5 web page.</li>
					<li>easyui save your time and scales while developing your
						products.</li>
					<li>easyui is very easy but powerful.</li>
				</ul>
			</div>
			<div title="My Documents" style="padding: 10px">
				<ul class="easyui-tree"
					data-options="url:'tree_data1.json',method:'get',animate:true"></ul>
			</div>
			<div title="Help" data-options="iconCls:'icon-help',closable:true"
				style="padding: 10px">This is the help content.</div>
		</div>
	</div>

	<script type="text/javascript">
		function convert(rows) {
			function exists(rows, parentId) {
				for ( var i = 0; i < rows.length; i++) {
					if (rows[i].id == parentId)
						return true;
				}
				return false;
			}

			var nodes = [];
			// get the top level nodes
			for ( var i = 0; i < rows.length; i++) {
				var row = rows[i];
				if (!exists(rows, row.parentId)) {
					nodes.push({
						id : row.id,
						text : row.name
					});
				}
			}

			var toDo = [];
			for ( var i = 0; i < nodes.length; i++) {
				toDo.push(nodes[i]);
			}
			while (toDo.length) {
				var node = toDo.shift(); // the parent node
				// get the children nodes
				for ( var i = 0; i < rows.length; i++) {
					var row = rows[i];
					if (row.parentId == node.id) {
						var child = {
							id : row.id,
							text : row.name
						};
						if (node.children) {
							node.children.push(child);
						} else {
							node.children = [ child ];
						}
						toDo.push(child);
					}
				}
			}
			return nodes;
		}

		$(function() {
			$('#tt').tree({
				url : '/easyui/menu_data.json',
				loadFilter : function(rows) {
					return convert(rows);
				}
			});
		});
	</script>

</body>
</html>