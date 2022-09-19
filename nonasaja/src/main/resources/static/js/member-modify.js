$(function(){
	$('#modify_form').submit(function(){
		if($('#nickname').val().trim()==''){
			alert('닉네임을 입력해주세요.');
			$('#nickname').val('').focus();
			return false;
		}
		if($('#email').val().trim()==''){
			alert('이메일을 입력해주세요.');
			$('#email').val('').focus();
			return false;
		}
		if($('#phone').val().trim()==''){
			alert('연락처를 입력해주세요.');
			$('#phone').val('').focus();
			return false;
		}
		if($('#zipcode').val().trim()==''){
			alert('우편번호를 입력해주세요.');
			$('#zipcode').val('').focus();
			return false;
		}
		if($('#addr1').val().trim()==''){
			alert('주소를 입력해주세요.');
			$('#addr1').val('').focus();
			return false;
		}
		if($('#addr2').val().trim()==''){
			alert('상세주소를 입력해주세요.');
			$('#addr2').val('').focus();
			return false;
		}
	});
});