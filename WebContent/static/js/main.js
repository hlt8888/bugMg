﻿(function($, exports) {
	var MainPage = {
			_url: "http://192.168.1.99:8080",
			_menus: {
				"menus" : [
						{
							"menuid" : "1",
							"icon" : "icon-sys",
							"menuname" : "Bug管理",
							"menus" : [
									{
										"menuid" : "11",
										"menuname" : "未修复Bug",
										"icon" : "icon-log",
										"url" : "/views/bug/buglist.jsp?action=1"
									},
									{
										"menuid" : "12",
										"menuname" : "修复中Bug",
										"icon" : "icon-log",
										"url" : "/views/bug/buglist.jsp?action=2"
									},
									{
										"menuid" : "13",
										"menuname" : "已修复Bug",
										"icon" : "icon-log",
										"url" : "/views/bug/buglist.jsp?action=3"
									} ]
						},
						{
							"menuid" : "2",
							"icon" : "icon-sys",
							"menuname" : "项目成员列表",
							"menus" : [
									{
										"menuid" : "21",
										"menuname" : "正式员工",
										"icon" : "icon-log",
										"url" : "/"
									},
									{
										"menuid" : "22",
										"menuname" : "实习生",
										"icon" : "icon-log",
										"url" : "/"
									}]
						}
				]
			},
			
			addTab: function( subtitle, url, icon ) {
				var _this = MainPage;
				
				//添加tab时传递当前页面所在tab的名称
				var $sTab = $("#tabs").tabs("getSelected");
				if ($sTab && url.indexOf(".html") == -1){
					if (url.indexOf("?") != -1) {
						url += "&parentTab=" + $sTab.panel('options').title;
					}else{
						url += "?parentTab=" + $sTab.panel('options').title;
					}
				}

				if (url.indexOf('http') === -1) {
					if (url[0] !== '/'){
						url = _this._url + url;
					} else {
						url = _this._url + url;
					}
				}
				
				_this.closeTab(subtitle);
				
				if(!$('#tabs').tabs('exists',subtitle)){
					$('#tabs').tabs('add',{
						title:subtitle,
						content:_this.createFrame(url),
						closable:true,
						closed:true,
						icon:icon
					});
				}else{
					$('#tabs').tabs('select',subtitle);
				}
				
				_this.initRMenuEvent();
				
				return true;
				
			},
			
			closeTab: function( subtitle ) {
				
				$('#tabs').tabs('close',subtitle);
				
			},
			
			getIcon: function( menuid ) {
				var _this = MainPage, icon = 'icon ';
				$.each(_this._menus.menus, function(i, n) {
					 $.each(n.menus, function(j, o) {
					 	if(o.menuid==menuid){
							icon += o.icon;
						}
					 });
				});

				return icon;
			},
			
			createFrame: function( url ) {
				var s = '<iframe scrolling="yes" id="firstLevel" frameborder="0" name="firstLevel" src="'+url+'" style="margin:0px;width:100%;height:100%;overflow:hidden;"></iframe>';
				return s;
			},
			
			msgShow: function( title, msgString, msgType ) {
				$.messager.alert(title, msgString, msgType);
			},
			
			initPageMeta: function() {
				var _this = MainPage;
				
				$.each(_this._menus.menus, function(i, n) {
					var menulist ='';
					menulist +='<ul>';
			        $.each(n.menus, function(j, o) {
						menulist += '<li><div><a ref="'+o.menuid+'" href="javascript:void(0)" rel="' + o.url + '" ><span class="icon '+o.icon+'" ></span><span class="nav">' + o.menuname + '</span></a></div></li> ';
						if( o.menuid != null && o.menuid == 11 ) {
							_this.addTab(o.menuname,o.url,n.icon);
						}
			        });
					menulist += '</ul>';
					$('#nav').accordion('add', {
			            title: n.menuname,
			            content: menulist,
			            iconCls: 'icon ' + n.icon
			        });
					
			    });

				$('.easyui-accordion li a').click(function(){
					var tabTitle = $(this).children('.nav').text();

					var url = $(this).attr("rel");
					var menuid = $(this).attr("ref");
					var icon = _this.getIcon(menuid,"");
					
					_this.addTab(tabTitle,url,icon);
					
					$('.easyui-accordion li div').removeClass("selected");
					$(this).parent().addClass("selected");
				}).hover(function(){
					$(this).parent().addClass("hover");
				},function(){
					$(this).parent().removeClass("hover");
				});

				//选中第一个
				var panels = $('#nav').accordion('panels');
				var t = panels[0].panel('options').title;
			    $('#nav').accordion('select', t);
			},
			
			initRMenuEvent: function() {
				/*双击关闭TAB选项卡*/
				$(".tabs-inner").on("dblclick",function(){
					var subtitle = $(this).children(".tabs-closable").text();
					$('#tabs').tabs('close',subtitle);
				});
				/*为选项卡绑定右键*/
				$(".tabs-inner").bind('contextmenu',function(e){
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});

					var subtitle =$(this).children(".tabs-closable").text();

					$('#mm').data("currtab",subtitle);
					$('#tabs').tabs('select',subtitle);
					return false;
				});
			},
			
			initEvent: function() {
				var _this = MainPage;
				//refresh
				$('#mm-tabupdate').click(function(){
					var currTab = $('#tabs').tabs('getSelected');
					var title = currTab.panel('options').title;
					var url = $(currTab.panel('options').content).attr('src');
					$('#tabs').tabs('update',{
						tab:currTab,
						options:{
							content:_this.createFrame(url)
						}
					});
				});
				// close current tab
				$('#mm-tabclose').click(function(){
					var currtab_title = $('#mm').data("currtab");
					$('#tabs').tabs('close',currtab_title);
				});
				// close all TABs
				$('#mm-tabcloseall').click(function(){
					$('.tabs-inner span').each(function(i,n){
						var t = $(n).text();
						$('#tabs').tabs('close',t);
					});
				});
				// clse all TABs except current tab
				$('#mm-tabcloseother').click(function(){
					$('#mm-tabcloseright').click();
					$('#mm-tabcloseleft').click();
				});
				//close current tab right's TAB
				$('#mm-tabcloseright').click(function(){
					var nextall = $('.tabs-selected').nextAll();
					if(nextall.length==0){
						alert('后边没有啦~~');
						return false;
					}
					nextall.each(function(i,n){
						var t=$('a:eq(0) span',$(n)).text();
						$('#tabs').tabs('close',t);
					});
					return false;
				});
				//close current tab left's TAB
				$('#mm-tabcloseleft').click(function(){
					var prevall = $('.tabs-selected').prevAll();
					if(prevall.length==0){
						alert('到头了，前边没有啦~~');
						return false;
					}
					prevall.each(function(i,n){
						var t=$('a:eq(0) span',$(n)).text();
						$('#tabs').tabs('close',t);
					});
					return false;
				});

				//hidden
				$("#mm-exit").click(function(){
					$('#mm').menu('hide');
				});
				
				// log out system
				$("#logout").on("click", function() {
					$.ajax({ type: "POST", url: "/views/logout" })
						.done(function( msg ) {
							window.location.reload();
						});
				});
			},
			
			init: function() {
				var _this = MainPage;
				
				_this.initPageMeta();
				
				_this.initEvent();
			}
	};
	$(document).ready(MainPage.init);
	exports.app = function() {
		return $.extend({}, MainPage);
	};
	exports.mainPage = MainPage;
})(jQuery, window);