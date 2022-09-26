let sOriginImgUrl = window.location.href;
let arSplitUrl = sOriginImgUrl.split("/");    //   "/" 로 전체 url 을 나눈다
let nArLength = arSplitUrl.length;
let url = arSplitUrl[nArLength-2];   // 나누어진 배열의 맨 끝에서 두번째가 경로명


$(function(){
	if(url == 'main'){
		$('#header_main').css('color', '#E2B15A');
	}
	if(url == 'product'){
		$('#header_product').css('color', '#E2B15A');
	}
	if(url == 'sale'){
		$('#header_sale').css('color', '#E2B15A');
	}
	if(url == 'used'){
		$('#header_used').css('color', '#E2B15A');
	}
	if(url == 'clubboard'){
		$('#header_clubboard').css('color', '#E2B15A');
	}
	if(url == 'commuboard'){
		$('#header_commuboard').css('color', '#E2B15A');
	}
	if(url == 'commuRecipe'){
		$('#header_commuRecipe').css('color', '#E2B15A');
	}
});
