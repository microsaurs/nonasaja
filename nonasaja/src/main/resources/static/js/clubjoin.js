$(function(){

	//======== 동호회가입하기========//
	$('#club_join').submit(function(event){
	
		let form_data = $(this).serialize();
		
		$.ajax({
			url:'../join/write.do',
			type:'post',
			data:form_data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('동호회에 가입되었습니다.');
					location.href='../member/myPageClub.do';
				}else if(param.result == 'overmember'){
					alert('모집이 완료된 동호회입니다.');
				}else if(param.result == 'already'){
					alert('이미 가입된 동호회입니다.');
				}else{
					alert('동호회 가입 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
		//=========동호회 탈퇴==========//
	$('.button444').on('click',function(){
		$.ajax({
			url:'/join/deleteJoin.do',
			type:'post',
			data:{join_num:$(this).attr('data-joinnum')},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'success'){
					alert('동호회 탈퇴되었습니다!');
					location.href='../member/myPageClub.do';
				}else{
					alert('탈퇴 오류!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
});