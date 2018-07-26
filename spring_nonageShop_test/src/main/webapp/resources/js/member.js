function btnClick() {
	location.href = "addMember";
};
function searchMember(form) {
	form.action="searchMember";
	form.method="get";
	form.target="_self";
	form.submit();
};

function mail_btn(formm){
	var count = 0;
	for (var i = 0; i < formm.userid.length; i++) {
		if (formm.userid[i].checked == true) {
			count++;
		}
	}
	if (count == 0) {
		alert("회원을 선택해 주세요.");

	} else {
		formm.action = "mailToMember";
		formm.methodd="post";
		formm.submit();
	}
}

function mail_send(formm){
	formm.action = "sendToMember";
	formm.methodd="post";
	formm.submit();
}