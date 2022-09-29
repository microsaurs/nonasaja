$(function(){
	$('#btn').click(function(){
		if($('#name').val().trim()==''){
			$('#name').val('').focus();
			alert('이름을 입력하세요');
			return false;
		}
		if($('#phone').val().trim()==''){
			$('#phone').val('').focus();
			alert('전화번호를 입력하세요');
			return false;
		}
		
		let name = $('#name').val();
		let phone = $('#phone').val();
		$.ajax({
			url:'findID.do',
			type:'post',
			dataType:'json',
			data:{name:name,phone:phone},
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='success'){
					alert('아이디 : ' + param.id);
					location.href='/member/login.do';
				}else if(param.result=='NotFound'){
					alert('등록된 아이디가 없습니다.');
					location.reload();
				}else{
					alert('통신 오류');
					location.reload();
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
});