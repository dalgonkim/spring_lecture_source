function btnClick() {
	location.href = "addProduct";
};
function searchProduct(form) {
	form.action="searchProduct";
	form.method="get";
	form.target="_self";
	form.submit();
};

function product_kind(pseq,kindNum){
	
	var kindList = [ "0", "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale" ];
	document.getElementById("kind"+pseq).innerHTML=kindList[kindNum];
	
};

