/*$(function(){
	var accessToken = sessionStorage.getItem("accessToken");
	var id = sessionStorage.getItem("id");
	var email = sessionStorage.getItem("email");
	var name = sessionStorage.getItem("name");
	var nickname = sessionStorage.getItem("nickname");
	var mobile = sessionStorage.getItem("mobile");

	$.ajax({
		url:'/member/useLoginapi.do',
		type:'post',
		data:{id:id, 
			name:name, 
			email:email, 
			nickname:nickname, 
			mobile:mobile
			},
		dataType:'json',
		cache:false,
		timeout:30000,
		success:function(param){
			$('#phone').attr('value', param.phone);
			alert(param.phone);
		},
		error:function(){
			alert('네트웍 오류 발생');
		}
	});
});*/