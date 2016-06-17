
//输入页面跳转时只能输入数字
//onkeyup="return ValidateNumber($(this),value)"
function ValidateNumber(e, pnumber){
	if (!/^\d+$/.test(pnumber)){
		$(e).val(/^\d+/.exec($(e).val()));
	}
	return false;
}

//输入页码值跳转时，只能输入数字
//onkeypress="return is_number(event)"
function is_number(e){
	//IE 中 Event 对象使用 keyCode 获得键入字符的 Unicode 编码  
	//DOM 中 Event 对象 使用 charCode 获得键入字符的 Unicode 编码  
	var char_code = e.charCode ? e.charCode : e.keyCode;  
	//Unicode 编码中， 0~9 的编码的十进制 是 48~57 之间 ， 0为 48， 9 为57
	if(char_code<48 || char_code >57){ 
	    return false;  
	} else{ 
	    return true;      
	}  
}


