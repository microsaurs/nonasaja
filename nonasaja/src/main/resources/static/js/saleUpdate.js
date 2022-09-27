$(function(){
	$('#update_form').submit(function(){
		if($('#deadline').val()==''){
			alert('기간을 설정해주세요!');
			$('#deadline').val('').focus();
			return false;
		}
	});
	
	// ==========상품 등록 이미지 미리보기==============//

	//처음 화면에 보여지는 이미지 읽기
	let img1 = $('#img1').attr('src');

	
	let upload;//업로드하고자 선택한 이미지 저장

	
	$('#upload').change(function(){
		upload = this.files[0];
		if(!upload){//파일 정보가 없는 경우
			$('#upload').attr('src',img1);
			return;
		}
		//파일 용량 체크
		if(img1.size > 1024*1024*20){
			alert(Math.round(my_photo.size/1024) + 'kytes(20mbytes까지만 업로드 가능)');
			//원래 이미지로 교체
			$('#upload').attr('src',img1);
			$(this).val('');//파일명 지우기
			return;
		}
		//이미지 미리보기 처리
		let reader1 = new FileReader();
		//선택한 이미지 읽기
		reader1.readAsDataURL(upload);
		
		reader1.onload=function(){
			//읽어들인 이미지 표시
			$('#img1').attr('src',reader1.result);
		};
	});
});