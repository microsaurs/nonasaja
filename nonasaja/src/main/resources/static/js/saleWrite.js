$(function(){
	$('#register_form').submit(function(){
		if($('#deadline').val()==''){
			alert('기간을 설정해주세요!');
			$('#deadline').val('').focus();
			return false;
		}
	});
});