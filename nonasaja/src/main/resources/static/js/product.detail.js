$(function(){
	//==========사진 넘기기============
	 $(".next").click(function(){
      // 위에 동일 
      var imgOn = $(".imgBox").find(".on").index();
      var imgLen = $(".imgBox .product-image").length;
      
	// 위에 동일
      $(".imgBox .product-image").eq(imgOn).removeClass("on");
      $(".imgBox .product-image").eq(imgOn).addClass("off");
      
      // 다음의 위치로 알아야 되기 때문에 
      imgOn = imgOn + 1;
      
      if( imgOn === imgLen ){
        // 다음의 위치가 총 개수보다 클 경우
        // 처음의 위치로 돌아간다
        $(".imgBox .product-image").eq(0).removeClass("off");
        $(".imgBox .product-image").eq(0).addClass("on");
      }else{
        // 다음 위치가 있는 경우 
        $(".imgBox .product-image").eq(imgOn).removeClass("off");
        $(".imgBox .product-image").eq(imgOn).addClass("on");
      }
	
	});
	
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




















