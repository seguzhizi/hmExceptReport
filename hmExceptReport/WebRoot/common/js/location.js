//function changeContent_bak(locale){
//	location.href=locale;
//}

function changeContent(locale,id){
	location.href=locale + "?menuId=" + id;
}

function changeBg(id){
	var target = ".menuChild:eq(" + id + ")";
	$(target).addClass("menu_mousedown");
}



