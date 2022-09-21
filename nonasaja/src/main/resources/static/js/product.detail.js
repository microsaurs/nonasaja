$(function(){
	//==========주문 수량 변경==============
	$('#order_quantity').on('keyup mouseup',function(){
		if($('#order_quantity').val()==''){
			$('#product_total_txt').text('0원');
			return;
		}
		if($('#order_quantity').val() <= 0){
			$('#order_quantity').val('');
			return;
		}
		if(Number($('#product_quantity').val()) < $('#order_quantity').val()){
			alert('수량이 부족합니다');
			$('#order_quantity').val('');
			$('#product_total_txt').text('0원');
			return;
		}
		
		let total = $('#product_price2').val() * $('#order_quantity').val();
		$('#product_total_txt').text($.number(total) +'원');
	});
	
	$('#product_cart').submit(function(){
		if($('#order_quantity').val() <= 0){
			alert('주문 수량을 입력하세요');
			return false;
		}
	});
});




















