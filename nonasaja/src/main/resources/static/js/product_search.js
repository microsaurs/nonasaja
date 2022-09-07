$(function(){
	$('#search_form').submit(function(){
		alert('진입');
		if($('#keyword').val().trim() == ''){
			alert('검색어를 입력하세요');
			$('#keyword').val().focus();
			return false;
		}
	});
});





