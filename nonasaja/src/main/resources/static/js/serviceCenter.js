$(function(){
	//쿼리스트링 값 받아내기
	let urlSearch = new URLSearchParams(location.search);
	let type = urlSearch.get('type');
	
	
	//type 값에 따라 분기
	if(type==null){
		type = 1;
		$('#box1').css('background','#EABF5E');
	}else if(type==1){
		$('#box1').css('background','#EABF5E');
		$('#box2').css('background','white');
		$('#box3').css('background','white');
		$('#box4').css('background','white');
		$('#box5').css('background','white');
		$('#box6').css('background','white');
		$('#box7').css('background','white');
		$('#box8').css('background','white');
	}else if(type==2){
		$('#box1').css('background','white');
		$('#box2').css('background','#EABF5E');
		$('#box3').css('background','white');
		$('#box4').css('background','white');
		$('#box5').css('background','white');
		$('#box6').css('background','white');
		$('#box7').css('background','white');
		$('#box8').css('background','white');
	}else if(type==3){
		$('#box1').css('background','white');
		$('#box2').css('background','white');
		$('#box3').css('background','#EABF5E');
		$('#box4').css('background','white');
		$('#box5').css('background','white');
		$('#box6').css('background','white');
		$('#box7').css('background','white');
		$('#box8').css('background','white');
	}else if(type==4){
		$('#box1').css('background','white');
		$('#box2').css('background','white');
		$('#box3').css('background','white');
		$('#box4').css('background','#EABF5E');
		$('#box5').css('background','white');
		$('#box6').css('background','white');
		$('#box7').css('background','white');
		$('#box8').css('background','white');
	}else if(type==5){
		$('#box1').css('background','white');
		$('#box2').css('background','white');
		$('#box3').css('background','white');
		$('#box4').css('background','white');
		$('#box5').css('background','#EABF5E');
		$('#box6').css('background','white');
		$('#box7').css('background','white');
		$('#box8').css('background','white');
	}else if(type==6){
		$('#box1').css('background','white');
		$('#box2').css('background','white');
		$('#box3').css('background','white');
		$('#box4').css('background','white');
		$('#box5').css('background','white');
		$('#box6').css('background','#EABF5E');
		$('#box7').css('background','white');
		$('#box8').css('background','white');
	}else if(type==7){
		$('#box1').css('background','white');
		$('#box2').css('background','white');
		$('#box3').css('background','white');
		$('#box4').css('background','white');
		$('#box5').css('background','white');
		$('#box6').css('background','white');
		$('#box7').css('background','#EABF5E');
		$('#box8').css('background','white');
	}else if(type==8){
		$('#box1').css('background','white');
		$('#box2').css('background','white');
		$('#box3').css('background','white');
		$('#box4').css('background','white');
		$('#box5').css('background','white');
		$('#box6').css('background','white');
		$('#box7').css('background','white');
		$('#box8').css('background','#EABF5E');
	}
});