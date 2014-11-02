var Util = {
		//获取url参数
	    getUrlParam: function (name) {
	        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	        var r = window.location.search.substr(1).match(reg);
	        if (r != null) return unescape(decodeURIComponent(r[2]));
	        return null;
	    },
	    
	    getSelections: function(){
	    	var ids = [];
	    	var rows = $('#list_table').datagrid('getSelections');
	    	for(var i = 0; i < rows.length; i++) {
	    		ids.push(rows[i].id);
	    	}
	    	
	    	return ids;
	    },
	    
	    idsIsChecked: function() {
	    	var rows = $('#list_table').datagrid('getSelections');
	    	if(rows.length == 0) {
	    		alert("请选择一条数据！");
	    		return false;
	    	}
	    	
	    	return true;
	    }
};