$(function(){
	// ==========상품 등록 이미지 미리보기==============//

	//처음 화면에 보여지는 이미지 읽기
	let img1 = $('#img1').attr('src');
	let img2 = $('#img2').attr('src');
	let img3 = $('#img3').attr('src');
	let img4 = $('#img4').attr('src');
	let img5 = $('#img5').attr('src');
	let img6 = $('#img6').attr('src');
	
	let upload1;//업로드하고자 선택한 이미지 저장
	let upload2;//업로드하고자 선택한 이미지 저장
	let upload3;//업로드하고자 선택한 이미지 저장
	let upload4;//업로드하고자 선택한 이미지 저장
	let upload5;//업로드하고자 선택한 이미지 저장
	let upload6;//업로드하고자 선택한 이미지 저장
	
	$('#upload').change(function(){
		upload1 = this.files[0];
		if(!upload1){//파일 정보가 없는 경우
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
	$('#upload4').change(function(){
		upload4 = this.files[0];
		if(!upload4){//파일 정보가 없는 경우
			$('#upload4').attr('src',img4);
			return;
		}
		//파일 용량 체크
		if(img4.size > 1024*1024*20){
			alert(Math.round(my_photo.size/1024) + 'kytes(20mbytes까지만 업로드 가능)');
			//원래 이미지로 교체
			$('#upload4').attr('src',img1);
			$(this).val('');//파일명 지우기
			return;
		}
		//이미지 미리보기 처리
		let reader4 = new FileReader();
		//선택한 이미지 읽기
		reader4.readAsDataURL(upload4);
		
		reader4.onload=function(){
			//읽어들인 이미지 표시
			$('#img4').attr('src',reader4.result);
		};
	});
	$('#upload5').change(function(){
		upload5 = this.files[0];
		if(!upload5){//파일 정보가 없는 경우
			$('#upload5').attr('src',img5);
			return;
		}
		//파일 용량 체크
		if(img5.size > 1024*1024*20){
			alert(Math.round(my_photo.size/1024) + 'kytes(20mbytes까지만 업로드 가능)');
			//원래 이미지로 교체
			$('#upload5').attr('src',img1);
			$(this).val('');//파일명 지우기
			return;
		}
		//이미지 미리보기 처리
		let reader5 = new FileReader();
		//선택한 이미지 읽기
		reader5.readAsDataURL(upload5);
		
		reader5.onload=function(){
			//읽어들인 이미지 표시
			$('#img5').attr('src',reader5.result);
		};
	});
	$('#upload6').change(function(){
		upload6 = this.files[0];
		if(!upload6){//파일 정보가 없는 경우
			$('#upload6').attr('src',img6);
			return;
		}
		//파일 용량 체크
		if(img6.size > 1024*1024*20){
			alert(Math.round(my_photo.size/1024) + 'kytes(20mbytes까지만 업로드 가능)');
			//원래 이미지로 교체
			$('#upload6').attr('src',img1);
			$(this).val('');//파일명 지우기
			return;
		}
		//이미지 미리보기 처리
		let reader6 = new FileReader();
		//선택한 이미지 읽기
		reader6.readAsDataURL(upload6);
		
		reader6.onload=function(){
			//읽어들인 이미지 표시
			$('#img6').attr('src',reader6.result);
		};
	});
	

});





