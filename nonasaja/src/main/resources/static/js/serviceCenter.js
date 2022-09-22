$(function(){
	//쿼리스트링 값 받아내기
	let urlSearch = new URLSearchParams(location.search);
	let type = urlSearch.get('type');
	
	//type 값에 따라 분기
	if(type==null){
		alert('type은 ' + type);
	}else if(type==1){
		alert('type은 ' + type);
	}else if(type==2){
		alert('type은 ' + type);	
	}else if(type==3){
		alert('type은 ' + type);
	}else if(type==4){
		alert('type은 ' + type);
	}else if(type==5){
		alert('type은 ' + type);
	}else if(type==6){
		alert('type은 ' + type);
	}else if(type==7){
		alert('type은 ' + type);
	}else if(type==8){
		alert('type은 ' + type);
	}
});