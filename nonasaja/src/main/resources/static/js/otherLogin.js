$(function(){
	$('#register_form').submit(function(){
		if($('#nickname').val().trim()==''){
			$('#nick_message').text('별명은 필수').css('color','red');
			$('#nickname').val('').focus();
			return false;
		}else{
			$('#nick_message').text('')
		}
		if($('#name').val().trim()==''){
			$('#name_message').text('이름은 필수').css('color','red');
			$('#name').val('').focus();
			return false;
		}else{
			$('#name_message').text('')
		}
		
		if($('#phone').val().trim()==''){
			$('#phone_message').text('전화번호는 필수').css('color','red');
			$('#phone').val('').focus();
			return false;
		}else{
			$('#phone_message').text('')
		}
		
		if($('#email').val().trim()==''){
			$('#email_message').text('이메일은 필수').css('color','red');
			$('#email').val('').focus();
			return false;
		}else{
			$('#email_message').text('')
		}
		if($('#zipcode').val().trim()==''){
			$('#zipcode_message').text('우편번호는 필수').css('color','red');
			$('#zipcode').val('').focus();
			return false;
		}else{
			$('#zipcode_message').text('')
		}
		
		if($('#addr1').val().trim()==''){
			$('#addr1_message').text('주소는 필수').css('color','red');
			$('#addr1').val('').focus();
			return false;
		}else{
			$('#addr1_message').text('')
		}
		if($('#addr2').val().trim()==''){
			$('#addr2_message').text('상세주소는 필수').css('color','red');
			$('#addr2').val('').focus();
			return false;
		}else{
			$('#addr2_message').text('')
		}
	});
});