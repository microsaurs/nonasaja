<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/product.detail.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.number.min.js"></script>
<script>
$(document).ready(function(){
   $(document).on('click','.rating',function(e){
      let elem = e.target;
        if(elem.classList.contains('rate-check')){
           $(this).find('.rate-check').each(function(index, item){
                if(index < elem.value){
                    item.checked = true;
                }else{
                    item.checked = false;
                }
            });
            $(this).find('.rate-star').val(elem.value);
        }
   });
});
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
<div class="page-main">
	<!-- 판매 중지 상품 -->
	<c:if test="${product.status==1}">
		<div class="result-display">
			<div class="align-center">s
				본 상품은 판매 중지되었습니다.<br>
				<input type="button" value="판매상품 보기" onclick="location.href='list.do'">
			</div>
		</div>
	</c:if>
	
	<c:if test="${product.status==2 }">
		<img class="product-image" src="imageView.do?product_num=${product.product_num}&photo_type=1" width="400" height="400">
		<!-- 사진이 2,3개 있으면 슬라이드로 만들기 -->
		<div class="product-detail">
			<form id="product_cart" method="post" action="${pageContext.request.contextPath}/cart/cart_insert.do">
				<input type="hidden" name="product_num" value="${product.product_num}" id="product_num">
				<input type="hidden" name="product_price2" value="${product.price2}" id="product_price2">
				<input type="hidden" name="product_quantity" value="${product.quantity}" id="product_quantity">
				<input type="hidden" name="product_req_quantity" value="${product.req_quantity}" id="product_req_quantity">
				
				<ul>
					<li><img src="${pageContext.request.contextPath}/images/tag.jpg" width="60px"></li>
					<li><span id="sub_category">${product.sub_category}</span></li>
					<li><span id="title">${product.title}</span></li>
					<li><span id="origin">원산지 : ${product.origin}</span></li>
					<li><span id="price"><fmt:formatNumber value="${product.price2 }" /></span> 원</li>
				</ul>
				<ul id="price_box">
					<c:if test="${product.quantity>0 }">
					<li><span id="div_quantity">${product.div_quantity}</span></li>
					<li class="req-quantity"><span class="req-quantity">주문확정수량 ${product.req_quantity}개 중 00개 판매</span></li>
					<li>
						<label for="order_quantity">구매 수량</label>
						<input type="number" name="order_quantity" min="1" max=${product.quantity} 
								id="order_quantity" class="quantity-width">	
					</li>
				</ul>
				<ul>
					<li>
						<span id="product_total_txt">총 주문 금액 : 0원</span>
					</li>
					<li>
						<input type="submit" value="장바구니">
					</li>
				</ul>	
					<!-- 품절 시 -->
					</c:if>
					<c:if test="${product.quantity <= 0 }">
					<li class="align-center">
						<span class="sold-out">품절</span>
					</li>
					</c:if>
					
				</ul>
			</form>
		</div>
		<hr size="1" noshade="noshade" width="100%">
		
		<div>
			<h3>함께 보면 좋은 상품</h3> 
			<br><br>
		</div>
		<p>
			<h3>상품 상세 정보</h3>
			${product.detail}
		</p>
		
		<h2>상품필수정보</h2>
		<table id="must">
			<tr>
				<td>품목 또는 명칭</td>
				<td>${product.name }</td>
			</tr>
			<tr>
				<td>생산자</td>
				<td>${product.company }</td>
			</tr>
			<tr>
				<td>원산지</td>
				<td>${product.origin }</td>
			</tr>
			<tr>
				<td>유통기한</td>
				<td>${product.exp_date}</td>
			</tr>
			<tr>
				<td>보관방법 및 취급 방법</td>
				<td>${product.storage }</td>
			</tr>
			<tr>
				<td>소비자 상담 관련 전화번호</td>
				<td>${product.cus_phone }</td>
			</tr>
		</table>
	</c:if><!-- 판매중 상품 -->
	
	<div id="review_space">
	<h2>고객리뷰</h2>
	<div id="total_star">
		<span id="star_text">구매고객 총별점</span>
		<span id="big_star">${score}</span><span id="small_star">/5</span>
	</div>
	<span id="notice">리뷰등록,수정,삭제 및 상세 내용은 마이페이지>마이리뷰에서 확인하실 수 있습니다.</span>
	</div>
	
	<c:forEach var="review" items="${reviewList}">
		<div id="review_box">
			<ul>
				<li>${review.id}</li>
				<li>
					<img src="${pageContext.request.contextPath}/member/viewProfile.do?mem_num=${review.mem_num}" width="25" height="25" class="my-photo">
				</li>
				<li>
					<div class="rating">
						<input type="hidden" name="score" value="0" class="rate-star">
						<c:forEach var="star" begin="1" end="5" varStatus="status">
							<input type="checkbox" id="rating${status.index}"
								value="${status.index}" class="rate-check"
								<c:if test="${status.index<=review.score}">checked</c:if> disabled>
							<label for="rating${status.index}"></label>
						</c:forEach>
					</div>
				</li>
				<li>${review.reg_date}</li>
				<li>
				<p>${review.content}</p>
				</li>
			</ul>
		</div>
	</c:forEach>
</div>
























