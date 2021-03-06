var TT = JT = {
	checkLogin : function(){
		var _ticket = $.cookie("JT_TICKET");
		console.log('ticket:'+_ticket);
		if(!_ticket){
			return ;
		}
		//当dataType类型为jsonp时，jQuery就会自动在请求链接上增加一个callback的参数
		$.ajax({
			url : "http://front.jt.com/user/checkTicket/" + _ticket,
			dataType : "json",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var _data = JSON.parse(data.data);
					if(!_data.username){return};
					var html =_data.username+"，欢迎来到京淘！<a href=\"http://front.jt.com/user/doLogout.html\" class=\"link-logout\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});