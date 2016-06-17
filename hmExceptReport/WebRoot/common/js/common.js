// JavaScript source code
(function () {
	/*
	 * 屏蔽浏览器选中事件
	 */
	document.body.onselectstart = document.body.ondrag = function () {
		return false;
	}
	

	$(".delete_tip").css({
		"left": (document.body.clientWidth - 415) / 2 + "px",
		"top": (document.body.clientHeight - 215) / 2 + "px"
	});
	$(".offline_tip").css({
		"left": (document.body.clientWidth - 415) / 2 + "px",
		"top": (document.body.clientHeight - 215) / 2 + "px"
	});

	var eWidth = $(".error_tip").width();
	var eHeight = $(".error_tip").height();
	$(".error_tip").css({
		"left": (document.body.clientWidth - eWidth) / 2 + "px",
		"top":"130px"
	});


	$('#role_select2').change(function () {
		if($("#role_select3").css("display")=="none"){
			$("#role_select3").css("display", "block");
			$("#role_content1").css("height", "157px");
		}
	});
	$('#role_select3').change(function () {
		if ($("#role_select4").css("display") == "none") {
			$("#role_select4").css("display", "block");
			$("#role_content1").css("height", "123px");
		}
	});
	$('#role_select4').change(function () {
		$("#role_select3").css("display", "none");
		$("#role_select4").css("display", "none");
		$("#role_content1").css("height", "187px");
	});

	$(".menuMain:first").css("border", "0");

	//账号在其它地方登陆弹出
	$("a[name='exit']").click(function () {
		$(".div_lock").css({
			"opacity": "0.3",
			"display": "block"
		});
		$(".offline_tip").css("display", "block");
	});
	$("#offline_tip_close").click(function () {
		$(".div_lock").css("display", "none");
		$(".offline_tip").css("display", "none");
	});


	//右选项卡悬浮、点击事件
	$(".titleChild").hover(function () {
		var oThis = $(this);
		$(".titleChild").removeClass("title_hover");
		if (!oThis.hasClass("title_click")) {
			oThis.addClass("title_hover");
		}

	}, function () {
		$(this).removeClass("title_hover");
	});
	$(".titleChild").mousedown(function () {
		var oThis = $(this);
		$(".titleChild").removeClass("title_click")
		$(".titleChild").removeClass("title_hover");;
		if (!oThis.hasClass("title_click")) {
			oThis.addClass("title_click");
		}
	});


	//模块选项卡点击事件
	$(".moudule_title").on("mousedown", ".mouduleTitleChild", function () {
		var oThis = $(this);
		$(".mouduleTitleChild").removeClass("mouduleTitleChild_click");
		if (!oThis.hasClass("mouduleTitleChild_click")) {
			oThis.addClass("mouduleTitleChild_click");
		}
	});

	//左菜单栏的悬浮、点击事件
	$(".menuChild").hover(function () {
		var oThis = $(this);
		$(".menuChild").removeClass("menu_mouseover");
		if (!oThis.hasClass("menu_mousedown")) {
			oThis.addClass("menu_mouseover");
		}

	}, function () {
		$(this).removeClass("menu_mouseover");
	});
	$(".menuChild").mousedown(function () {
		var oThis = $(this);
		$(".menuChild").removeClass("menu_mousedown")
		$(".menuChild").removeClass("menu_mouseover");;
		if (!oThis.hasClass("menu_mousedown")) {
			oThis.addClass("menu_mousedown");
		}
	});

	//按钮的悬浮、点击事件
	$(".input_button").hover(function () {
		$(this).css("background-position-y", "-32px");
	}, function () {
		$(this).css("background-position-y", "0");
	});
	$('.input_button').mousedown(function () {
		$(this).css("background-position-y", "-64px");
	});

	//删除弹出框按钮的悬浮、点击事件
	$(".tip_button").hover(function () {
		$(this).css("background-position-y", "-30px");
	}, function () {
		$(this).css("background-position-y", "0");
	});
	$('.tip_button').mousedown(function () {
		$(this).css("background-position-y", "-60px");
	});


	//审核按钮的悬浮、点击、移开事件
	var i = 0;
	$("#btn_review1").hover(function () {
		if (i == 0) {
			$(this).css("background-position-y", "-32px");
		}
	}, function () {
		if (i == 0) {
			$(this).css("background-position-y", "0");
		}
	});

	$("#btn_review1").click(function () {
		$(this).css("background-position-y", "-96px");
		$(this).attr({ "disabled": "disabled" });
		i = 1;

		var rWidth = $("#tip_loading").width();
		var rHeight = $("#tip_loading").height();
		$("#tip_loading").css({
			"left": (document.body.clientWidth - rWidth) / 2 + "px",
			"top": (document.body.clientHeight - rHeight) / 2 + "px"
		});
		$(".div_lock").css({
			"opacity": "0.4",
			"display": "block"
		});
		$("#tip_loading").css("display", "block");
	});

	$("#btn_review1").mouseleave(function () {
		if (i == 1) {
			$(this).css("background-position-y", "-96px");
		}
	});

	var j = 0;
	$("#btn_review2").hover(function () {
		if (j == 0) {
			$(this).css("background-position-y", "-32px");
		}
	}, function () {
		if (j == 0) {
			$(this).css("background-position-y", "0");
		}
	});

	$("#btn_review2").click(function () {
		$(this).css("background-position-y", "-96px");
		$(this).attr({ "disabled": "disabled" });
		j = 1;
		
		var rWidth = $("#tip_fail").width();
		var fWidth = $("#fail_tip").width();
		var rHeight = $("#tip_fail").height();
		$("#tip_fail").css({
			"left": (document.body.clientWidth - rWidth) / 2 + "px",
			"top": (document.body.clientHeight - rHeight) / 2 + "px"
		});
		$(".tip_content").css({
			"margin-left": ((rWidth - fWidth) / 2)-90 + "px"
		});
		
		$(".div_lock").css({
			"opacity": "0.4",
			"display": "block"
		});
		$("#tip_fail").css("display", "block");
	});
	$("#btn_review2").mouseleave(function () {
		if (j == 1) {
			$(this).css("background-position-y", "-96px");
		}
	});



	//箭头按钮的悬浮、点击事件
	$(".arrow").hover(function () {
		$(this).css("background-position-y", "-64px");
	}, function () {
		$(this).css("background-position-y", "-96px");
	});
	$('.arrow').mousedown(function () {
		$(this).css("background-position-y", "-32px");
	});
	$('.arrow').click(function () {
		if ($(this).attr("id") == "arrow1") {
			$("#role_content1 .role_item").each(function () {
				if ($(this).hasClass("role_item_mousedown")) {
					$("#role_content2").append("<div class='role_item'>" + $(this).html() + "</div>");
					$(this).remove();
				}
			});

		} else if ($(this).attr("id") == "arrow2") {
			$("#role_content2 .role_item").each(function () {
				if ($(this).hasClass("role_item_mousedown")) {
					$("#role_content1").append("<div class='role_item'>" + $(this).html() + "</div>");
					$(this).remove();
				}
			});

		} else if ($(this).attr("id") == "arrow3") {
			if ($("#role_content3").html() != "") {
				$("#role_content4").append($("#role_content3").html());
				$("#role_content3").html("");
			}
		} else if ($(this).attr("id") == "arrow4") {
			$("#role_content3 .role_item").each(function () {
				if ($(this).hasClass("role_item_mousedown")) {
					$("#role_content4").append("<div class='role_item'>" + $(this).html() + "</div>");
					$(this).remove();
				}
			});

		} else if ($(this).attr("id") == "arrow5") {
			$("#role_content4 .role_item").each(function () {
				if ($(this).hasClass("role_item_mousedown")) {
					$("#role_content3").append("<div class='role_item'>" + $(this).html() + "</div>");
					$(this).remove();
				}
			});

		} else if ($(this).attr("id") == "arrow6") {
			if ($("#role_content4").html() != "") {
				$("#role_content3").append($("#role_content4").html());
				$("#role_content4").html("");
			}

		}
	});
	//角色分配、设备分配组的悬浮、点击事件
	$(".role_content").on("mouseover", ".role_item", function () {
		var oThis = $(this);
		$(".role_item").removeClass("role_item_hover");
		if (!oThis.hasClass("role_item_mousedown")) {
			oThis.addClass("role_item_hover");
		}
	});
	$(".role_content").on("mousedown", ".role_item", function () {
		var oThis = $(this);
		$(".role_item").removeClass("role_item_hover");;
		if (!oThis.hasClass("role_item_mousedown")) {
			oThis.addClass("role_item_mousedown");
		} else {
			oThis.removeClass("role_item_mousedown")
		}
	});

	//页面默认选择某个选项卡
	//$("#rightManageDiv").addClass("title_click");

	//提交按钮点击事件
	//$("#btn_submit").click(function () {
	//	$(".div_tips").show();
	//});


	//点击删除弹出框
	$(".del_div").click(function () {
		$(".success_tip").css("display", "none");
		$(".div_lock").css({
			"opacity": "0.3",
			"display": "block"
		});
		$(".delete_tip").css("display", "block");
	});
	$("#btn_tip_close").click(function () {
		$(".div_lock").css("display", "none");
		$(".delete_tip").css("display", "none");
	});
	$("#btn_tip_cancel").click(function () {
		$(".div_lock").css("display", "none");
		$(".delete_tip").css("display", "none");
	});

	$("#btn_tip_confirm").click(function () {
		$(".div_lock").css("display", "none");
		$(".delete_tip").css("display", "none");
		$(".success_tip").css("display", "block");
		setTimeout(function () { $(".success_tip").css("display", "none"); }, (3000));
	}); 

	//点击批量导入弹出框
	$(".div_export").click(function () {
		$(".div_lock").css({
			"opacity": "0.3",
			"display": "block"
		});
		$(".div_batchimport").css("display", "block");
	});
	$("#btn_batchimport_close").click(function () {
		$(".div_lock").css("display", "none");
		$(".div_batchimport").css("display", "none");
	});
	

	//错误提示弹出框
	$("#basicInfo_submit").click(function () {
		$(".error_tip").css("display", "block");
		setTimeout(function () { $("#error_tip_close").click() }, (8000));
	});
	$("#error_tip_close").click(function () {
		$(".error_tip").css("display", "none");
	});
	
})();
