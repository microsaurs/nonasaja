$(function(){
	let checkId = 0;
	
	//Id 중복 체크
	$('#confirmId').click(function(){
		if($('#id').val().trim()==''){
			$('#message_id').css('color','red').text('아이디를 입력하세요');
			$('#id').val('').focus()
			return;
		}
		
		//서버 프로그램과 통신
		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result=='idNotFound'){
					$('#message_id').css('color','green').text('사용 가능한 아이디');
					checkId = 1;
				}else if(param.result=='idDuplicated'){
					$('#message_id').css('color','red').text('중복된 아이디');
					$('#id').val('').focus();
					checkId = 0;
				}else if(param.result=='notMatchPattern'){
					$('#message_id').css('color','red').text('영문,숫자 4~20자 입력');
					$('#id').val('').focus();
					checkId = 0;
				}else{
					checkId = 0;
					//코드에 오타가 있을 확률 높음!
					alert('ID중복체크 오류');
				}
			},
			error:function(){
				checkId=0;
				//이 상황 발생시 콘솔 확인!
				alert('네트워크 오류 발생');
			}
		});
	});//end of click
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #id').keydown(function(){
		checkId = 0;
		$('#message_id').text('');
	});//end of keydown
	
	let checkPw = 0;
	//비밀번호 확인 검사
	$('#passwd').keyup(function(){
		$('#message_pw').text('');
	});
	$('#passwd_check').keyup(function(){
		if($('#passwd').val() != $('#passwd_check').val()){
			$('#message_pw').text('비밀번호 불일치');
			$('#message_pw').css('color','red');
			checkPw = 0;
		}else{
			$('#message_pw').text('비밀번호 일치');
			$('#message_pw').css('color','green');
			checkPw = 1;
		}
	});
	
	
	
	//submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#register_form').submit(function(){
		if(checkId==0){
			$('#message_id').css('color','red').text('ID중복 체크 필수');
			if($('#id').val().trim()==''){
				$('#id').val('').focus();
			}
			return false;
		}
		//submit 이벤트 발생시 비밀번호-비밀번호확인이 같은지 여부 확인
		if(checkPw==0){
			$('#message_pw').css('color','red').text('비밀번호 불일치');
			$('#passwd_check').val('').focus();
			return false;
		}
	});//end of submit

});