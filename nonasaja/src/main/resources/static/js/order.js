$(function(){
	$('#order_form').submit(function(){
		if($('#receive_name').val().trim()==''){
			alert('수령인을 입력하세요');
			$('#receive_name').val('').focus();
			return false;
		}
		if($('#zipcode').val().trim()==''){
			alert('우편번호를 입력하세요');
			$('#zipcode').val('').focus();
			return false;
		}
		if($('#address1').val().trim()==''){
			alert('주소를 입력하세요');
			$('#address1').val('').focus();
			return false;
		}
		if($('#address2').val().trim()==''){
			alert('상세 주소를 입력하세요');
			$('#address2').val('').focus();
			return false;
		}
		if($('#receive_phone').val().trim()==''){
			alert('전화번호를 입력하세요');
			$('#receive_phone').val('').focus();
			return false;
		}
	});
	
});