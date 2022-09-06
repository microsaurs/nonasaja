$(function(){
	//==========상품 등록 이미지 미리보기==============//
	
	//처음 화면에 보여지는 이미지 읽기
	let img1 = $('#img1').attr('src');
	let img2 = $('#img2').attr('src');
	let img3 = $('#img3').attr('src');
	
	let upload1;//업로드하고자 선택한 이미지 저장
	let upload2;//업로드하고자 선택한 이미지 저장
	let upload3;//업로드하고자 선택한 이미지 저장
	
	$('#upload1').change(function(){
		upload1 = this.files[0];
		if(!upload1){//파일 정보가 없는 경우
			$('#upload1').attr('src',img1);
			return;
		}
		//파일 용량 체크
		if(img1.size > 1024*1024*20){
			alert(Math.round(my_photo.size/1024) + 'kytes(20mbytes까지만 업로드 가능)');
			//원래 이미지로 교체
			$('#upload1').attr('src',img1);
			$(this).val('');//파일명 지우기
			return;
		}
		//이미지 미리보기 처리
		let reader1 = new FileReader();
		//선택한 이미지 읽기
		reader1.readAsDataURL(upload1);
		
		reader1.onload=function(){
			//읽어들인 이미지 표시
			$('#img1').attr('src',reader1.result);
		};
	});
	$('#upload2').change(function(){
		upload2 = this.files[0];
		if(!upload2){//파일 정보가 없는 경우
			$('#upload2').attr('src',img2);
			return;
		}
		//파일 용량 체크
		if(img2.size > 1024*1024*20){
			alert(Math.round(my_photo.size/1024) + 'kytes(20mbytes까지만 업로드 가능)');
			//원래 이미지로 교체
			$('#upload2').attr('src',img2);
			$(this).val('');//파일명 지우기
			return;
		}
		//이미지 미리보기 처리
		let reader2 = new FileReader();
		//선택한 이미지 읽기
		reader2.readAsDataURL(upload2);
		
		reader2.onload=function(){
			//읽어들인 이미지 표시
			$('#img2').attr('src',reader2.result);
		};
	});
	$('#upload3').change(function(){
		upload3 = this.files[0];
		if(!upload3){//파일 정보가 없는 경우
			$('#upload3').attr('src',img3);
			return;
		}
		//파일 용량 체크
		if(img3.size > 1024*1024*20){
			alert(Math.round(my_photo.size/1024) + 'kytes(20mbytes까지만 업로드 가능)');
			//원래 이미지로 교체
			$('#upload3').attr('src',img1);
			$(this).val('');//파일명 지우기
			return;
		}
		//이미지 미리보기 처리
		let reader3 = new FileReader();
		//선택한 이미지 읽기
		reader3.readAsDataURL(upload3);
		
		reader3.onload=function(){
			//읽어들인 이미지 표시
			$('#img3').attr('src',reader3.result);
		};
	});
	
	
	
	
	//이미지 파일 전송
	/*$('#photo_submit').click(function(){
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
					alert('프로필 사진이 수정되었습니다');
					photo_path = $('.my-photo').attr('src');
					$('#upload').val('');
					$('#photo_choice').hide();
					$('#photo_btn').show();
				}else{
					alert('파일 전송 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	*/
	

});





