$(function(){
	//쿼리스트링 값 받아내기
	let urlSearch = new URLSearchParams(location.search);
	
	let type;
	if(urlSearch.get('type')==null){
		type = 1;
	}else{
		type = urlSearch.get('type');		
	}
	
	/*let code;
	if(urlSearch.get('code')==null){
		code = 1;
	}else{
		code = urlSearch.get('type');		
	}*/
	
	$('#code').change(function(){
		//alert(type,code);
		let code = $('#code').val()
		location.href = 'myPageCommu.do?type='+type+'&code='+code;
	});
});