$(function(){
	InitLeftMenu();
	tabClose();
	tabCloseEven();
});

//初始化左侧
function InitLeftMenu() {
	$("#nav").accordion({animate:false});

    $.each(_menus.menus, function(i, n) {
		var menulist ='';
		menulist +='<ul>';
        $.each(n.menus, function(j, o) {
			menulist += '<li><div><a ref="'+o.menuid+'" href="javascript:void(0)" rel="' + o.url + '" ><span class="icon '+o.icon+'" ></span><span class="nav">' + o.menuname + '</span></a></div></li> ';
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
		var icon = getIcon(menuid,"");
		addTab(tabTitle,url,icon);
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
}
//获取左侧导航的图标
function getIcon(menuid){
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		 $.each(n.menus, function(j, o) {
		 	if(o.menuid==menuid){
				icon += o.icon;
			}
		 });
	});

	return icon;
}


// 询单菜单定时器
var leadMenuTime = 0;

function addTab(subtitle,url,icon){
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
			url = _url + url;
		} else {
			url = _url + url;
		}
	}
	
	$('#tabs').tabs('close',subtitle);
	if(!$('#tabs').tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			closed:true,
			icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
	}
	
	tabClose();
	return true;
}

/**
 * 先关闭同名的页签再打开
 * @param {} subtitle
 * @param {} url
 * @param {} icon
 */
function addMustTab(subtitle,url,icon){
	if($("#tabs").tabs("exists",subtitle)){
		closeTab(subtitle);
	}
	addTab(subtitle,url,icon);	
}

 function closeTabForActivity(subtitle){
        	$('#tabs').tabs('close',subtitle);
        }
        
        

function addTabMust(subtitle,url,icon){
	$('#tabs').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true,
			icon:icon
	});
}

function createFrame(url)
{
	var s = '<iframe scrolling="yes" id="firstLevel" frameborder="0" name="firstLevel" src="'+url+'" style="margin:0px;width:100%;height:100%;overflow:hidden;"></iframe>';
	return s;
}

function tabClose()
{
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
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
}
// 刷新当前tab
function refreshCurrenTab(){
	var currTab = $('#tabs').tabs('getSelected');
	var title = currTab.panel('options').title;
	var url = $(currTab.panel('options').content).attr('src');
	$('#tabs').tabs('update',{
		tab:currTab,
		options:{
			content:createFrame(url)
		}
	});
}
//绑定右键菜单事件
function tabCloseEven()
{
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var title = currTab.panel('options').title;
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		});
	});
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	});
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
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

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	});
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
