$(function(){
	
	//=========장바구니 상품 삭제============
	$('.cart-del').on('click', function(){
		$.ajax({
			url:'../cart/deleteCart.do',
			type:'post',
			data:{cart_num:$(this).attr('data-cartnum')},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					alert('선택하신 상품을 삭제했습니다.');
					location.href='myPageProduct.do';
				}else{
					alert('삭제 오류!');
				}
			},
			error:function(){
				alert('장바구니 삭제에서 네트워크 오류 발생');
			}
		});
	});
	//=========장바구니 체크에 따라 전체 가격 변경=========
	let all_total = $('#all_total').val();
	all_total = Number(all_total);
	$('.cart_check').change(function(){
		
		if($(this).is(':checked')){
			all_total += Number($(this).attr('data-price'));
		}else{
			all_total -= Number($(this).attr('data-price'));
		}
		$('#new_price').text('');
		$('#new_price').text(all_total);
	});
	
	
	//=========장바구니 상품 수량 변경==========//
	$('.cart-modify').on('click',function(){
		let input_quantity = 
		$(this).parent().find('input[name="quantity"]');
		
		if(input_quantity.val()==''){
			alert('수량을 입력하세요!');
			input_quantity.focus();
			return;
		}
		if(isNaN(input_quantity.val())){
			input_quantity.val(input_quantity.attr('value'));
			return;
		}
		if(input_quantity.val()<1){
			alert('상품의 최소 수량은 1입니다.');
			input_quantity.val(input_quantity.attr('value'));
			return;
		}
		
		$.ajax({
			url:'../cart/modifyCart.do',
			type:'post',
			data:{cart_num:$(this).attr('data-cartnum'),product_num:$(this).attr('data-productnum'),quantity:input_quantity.val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요!');
				}else if(param.result == 'noSale'){
					alert('판매 중지되었습니다.');
					location.href='myPageProduct.do';
				}else if(param.result == 'noQuantity'){
					alert('상품의 수량이 부족합니다.');
					location.href='myPageProduct.do';
				}else if(param.result == 'success'){
					alert('상품 개수가 수정되었습니다.');
					location.href='myPageProduct.do';
				}else{
					alert('수정시 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
		
	});

});





