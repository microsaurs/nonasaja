$(function(){
	$('#search_form1').submit(function(){
		if($('#searchbar').val().trim() == ''){
			alert('검색어를 입력하세요');
			$('#searchbar').val().focus();
			return false;
		}
	});
	
	$('#food_img').click(function(){
		location.href='list.do?keyfield=0';
	});
	$('#living_img').click(function(){
		location.href='list.do?keyfield=1';
	});
});





