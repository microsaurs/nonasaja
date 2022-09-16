$(function(){
//=================== 별점주기 ========================
	//등록 버튼 누르면 이 값을 보낼 것 
	
	let star = $('.rate-star').val();
	$('.rate-check').click(function(){
		
		
		/*
		$.ajax({
			url:'giveStar.do',
			type:'post',
			data:{star:star, c_num:c_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(param.result == 'success'){
					//별점 총점을 다시 호출함
					$('#star_avg').text('평균별점 : ★'+param.starAvg);
					starCheck = 1;

					showReviewForm(user_num,starCheck,reviewCheck);
				}
			},
			error:function(){
				alert('별점 등록에서 네트워크 오류 발생');	
			}
		});*/
	});
	
});