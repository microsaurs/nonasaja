$(function(){
	//======MYPage 프로필 사진 처리======//
	//확인 버튼 처리
	$('#photo_btn').click(function(){
		$('#photo_choice').show();//프로필 수정 폼 노출
		$(this).hide();//수정 버튼 감추기
	});
	
	//처음 화면에 보여지는 이미지 읽기
	let photo_path = $('.my-photo1').attr('src');
	let my_photo;//업로드하고자 선택한 이미지 저장
	$('#upload').change(function(){
		my_photo = this.files[0];
		if(!my_photo){//파일 정보가 없는 경우
			$('.my-photo1').attr('src',photo_path);
			return;
		}
		
		//파일 용량 체크
		if(my_photo.size > 1024*1024){
			alert(Math.round(my_photo.size/1024) + 'kytes(1024kbytes까지만 업로드 가능)');
			//원래 이미지로 교체
			$('.my-photo1').attr('src',photo_path);
			$(this).val('');//파일명 지우기
			return;
		}
		
		//이미지 미리보기 처리
		let reader = new FileReader();
		//선택한 이미지 읽기
		reader.readAsDataURL(my_photo);
		
		reader.onload=function(){
			//읽어들인 이미지 표시
			$('.my-photo1').attr('src',reader.result);
		};
	});//end of change
	
	//이미지 파일 전송
	$('#photo_submit').click(function(){
		if($('#upload').val()==''){
			alert('파일을 선택하세요');
			$('#upload').focus();
			return;
		}
		
		//파일 전송 , 파일 업로드할꺼면 FormData 객체 생성해야함
		let form_data = new FormData();
		form_data.append('upload',my_photo);
		$.ajax({
			url:'updateMyPhoto.do',
			data:form_data,
			type:'post',
			dataType:'json',
			contentType:false,
			enctype:'multipart/form-data',
			processData:false,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					photo_path = $('.my-photo').attr('src');
					$('#upload').val('');
					$('#photo_choice').hide();
					$('#photo_btn').show();
					location.reload();
					alert('프로필 사진이 수정되었습니다');
				}else{
					alert('파일 전송 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//취소 버튼 처리
	$('#photo_reset').click(function(){
		$('.my-photo1').attr('src',photo_path);
		$('#upload').val('');
		$('#photo_choice').hide();
		$('#photo_btn').show();
	});
	
		//===========비밀번호 변경=================//
	$('#passwd').keyup(function(){
		if($('#confirm_passwd').val()!='' &&$('#confirm_passwd').val()!=$(this).val()){
			$('message_id').text('비밀번호 불일치').css('color','red');
		}else if($('#confirm_passwd').val()!='' && $('#confirm_passwd').val()==$(this).val()){
			$('message_id').text('비밀번호 일치').css('color','green');
		}
	});
	
	$('#confirm_passwd').keyup(function(){
		if($('#passwd').val()!='' &&$('#passwd').val()!=$(this).val()){
			$('message_id').text('비밀번호 불일치').css('color','red');
		}else if($('#passwd').val()!='' && $('#passwd').val()==$(this).val()){
			$('message_id').text('비밀번호 일치').css('color','green');
		}
	});
	
	$('#change_form').submit(function(){
		if($('#now_passwd').val().trim()==''){
			alert('현재 비밀번호를 입력하세요');
			$('#now.passwd').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('새 비밀번호를 입력하세요');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#confirm_passwd').val().trim()==''){
			alert('새 비밀번호 확인을 입력하세요');
			$('#confirm_passwd').val('').focus();
			return false;
		}
		if($('#passwd').val()!=$('#confirm_passwd').val()){
			$('#message_id').text('비밀번호 불일치').css('color','red');
			return false;
		}
	});
});






