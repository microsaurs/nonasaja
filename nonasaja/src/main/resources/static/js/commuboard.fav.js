$(function(){
	//좋아요 읽기
	//좋아요 선택 여부와 총개수 표시
	function selectData(commu_num){
		$.ajax({
			url:'getFav.do',
			type:'post',
			data:{commu_num:commu_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				displayFav(param);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	
	
	
	//좋아요 등록
	//js에서는 el이 동작을 안한다. jsp에서만 동작한다.
	//그래서 jquery로 태그에 접근해 정보를 읽어오는 방식을 쓴다.
	//앞으로 el로부터 데이터를 읽어다가 쓰고싶으면 jsp에 스크립트를 명시해야한다.
	$('#output_fav').click(function(){
		$.ajax({
			url:'writeFav.do',
			type:'post',
			data:{commu_num:$('#commu_num').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 좋아요를 눌러주세요!');
				}else if(param.result == 'success'){
					displayFav(param);
				}else{
					alert('좋아요 등록시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}); //등록 끝
	
	//좋아요 표시
	function displayFav(param){
		let output;
		if(param.status == 'noFav'){
			output = '../images/fav01.gif';
		}else{
			output = '../images/fav02.gif';
		}
		//문서 객체에 추가
		$('#output_fav').attr('src',output);
		$('#output_fcount').text(param.count);
	}//좋아요 표시 끝
	
	
	
	//초기 데이터 표시
	selectData($('#commu_num').val());
		

});