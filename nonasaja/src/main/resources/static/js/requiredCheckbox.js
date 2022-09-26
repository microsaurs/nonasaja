$(function(){
	$('#register_form').submit(function(){
		if($('#required1').is(':checked') == false){
			alert('필수 항목을 확인하고 체크하세요!');
			$('#required1').focus();
			return false;
		}
		if($('#required2').is(':checked') == false){
			alert('필수 항목을 확인하고 체크하세요!');
			$('#required2').focus();
			return false;
		}
	});
});