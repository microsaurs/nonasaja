$(function(){
	const grant_type = 'authorization_code';
	const client_id = 'a8abdc39c132bcec49dcef03bb7a10d1';
	const redirect_uri = 'http://localhost:8080/auth/kakao/callback';
	const code = 'code';
	let unique = "";
	const input = 'https://kauth.kakao.com/oauth/authorize?client_id=a8abdc39c132bcec49dcef03bb7a10d1&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code';
	$('#kakao').attr('href', input);
	$('#kakao').click(function(){
		$.ajax({
			url: input,
			data:{code:code},
			type:'get',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'success'){
					console.log('ajax 호출' + param.code);
					console.log('ajax 호출' + param.code2);
					unique = param.code;
				}else{
					alert('ajax 실패');
				}
			},
			error:function(){
				alert('네트워크 오류!')
			}
			
		});
	});
});