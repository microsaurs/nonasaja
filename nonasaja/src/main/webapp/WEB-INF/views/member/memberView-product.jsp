<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/cart.js"></script>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypageProduct.css">
<div class="mypage-wrap">
	<div class="mypage-left">
		<ul>
			<li><c:if test="${empty member.photo_name}">
					<img src="${pageContext.request.contextPath}/images/face.png"
						width="200" height="200" class="my-photo">
				</c:if> <c:if test="${!empty member.photo_name}">
					<img src="${pageContext.request.contextPath}/member/photoView.do"
						width="200" height="200" class="my-photo">
				</c:if></li>
			<li id="nick"><span>${member.nickname}</span></li>
			<li id="money">사자머니 &nbsp;&nbsp;&nbsp;<a href="paymentList.do">충전
					></a></li>
			<li><span><fmt:formatNumber value="${member.cash}" />원</span></li>
		</ul>
		<br>
		<ul>
			<li><a
				href="${pageContext.request.contextPath}/member/myPage.do">회원정보</a></li>
			<li><h3>
					<a href="myPageProduct.do">공동구매</a>
				</h3></li>
			<li><a href="myPageUsed.do">중고거래</a></li>
			<li><a href="myPageClub.do">동호회</a></li>
			<li><a href="myPageCommu.do">커뮤니티</a></li>
			<c:if test="${member.root == 0}">
				<li><a href="delete.do">회원탈퇴</a></li>
			</c:if>
		</ul>
	</div>
	<!----------------- ^ 페이지 좌측단 -------------->
	<!----------------- 페이지 우측단 v -------------->
	<div class="mypage-right">
		<table>
			<tr>
				<th colspan="2"><h3>공동구매</h3></th>
			</tr>
			<tr>
				<td>
				<c:if test="${type == 1}">
					<b><a href="myPageProduct.do?type=1">장바구니</a></b> |
					<a href="myPageProduct.do?type=2">참여중인 공동구매</a> |
					<a href="myPageProduct.do?type=3">구매 내역</a> |
					<a href="myPageProduct.do?type=4">내 리뷰</a>
				</c:if>
				<c:if test="${type == 2}">
					<a href="myPageProduct.do?type=1">장바구니</a> |
					<b><a href="myPageProduct.do?type=2">참여중인 공동구매</a></b> |
					<a href="myPageProduct.do?type=3">구매 내역</a> |
					<a href="myPageProduct.do?type=4">내 리뷰</a>
				</c:if>
				<c:if test="${type == 3}">
					<a href="myPageProduct.do?type=1">장바구니</a> |
					<a href="myPageProduct.do?type=2">참여중인 공동구매</a> |
					<b><a href="myPageProduct.do?type=3">구매 내역</a></b> |
					<a href="myPageProduct.do?type=4">내 리뷰</a>
				</c:if>
				<c:if test="${type == 4}">
					<a href="myPageProduct.do?type=1">장바구니</a> |
					<a href="myPageProduct.do?type=2">참여중인 공동구매</a> |
					<a href="myPageProduct.do?type=3">구매 내역</a> |
					<b><a href="myPageProduct.do?type=4">내 리뷰</a></b>
				</c:if>
				</td>
			</tr>
		</table>
		<!-- 목록 시작-->
		<!-- 장바구니 목록 -->
		<table>
			<c:if test="${type==1}">
			<c:if test="${all_total <= 0}">
				<span id="no_list">내역이 없습니다.</span>
			</c:if>
				<c:if test="${all_total > 0}">
					<form action="/order/cart_order.do" id="cartOrder" method="post">
					<ul><!-- cart UL -->
					<c:forEach var="cart" items="${list}">
						<input type="hidden" name="product_num" value="${cart.product_num}">
							<li><input type="checkbox" name="cart_num" value="${cart.cart_num}" checked="checked"
								<c:if test="${cart.productVO.status == 1 }">disabled</c:if>>
							</li>
							<li><c:if test="${cart.productVO.status == 1 }">
									<del>${cart.productVO.name}</del>
								</c:if> 
								<c:if test="${cart.productVO.status == 2 }">
									<a href="${pageContext.request.contextPath}/product/detail.do?product_num=${cart.product_num }">${cart.productVO.name}</a>
								</c:if>
							</li>
							<li>
								<input type="number" name="quantity" min="1" max="99999" value="${cart.quantity}">
								<br> 
								<input type="button" value="변경" class="cart-modify" data-cartnum="${cart.cart_num}"
								data-productnum="${cart.product_num}">
							</li>
							<li><fmt:formatNumber value="${cart.productVO.price2 }" /></li>
							<li>${cart.productVO.req_quantity }</li>
							<li><input type="button" class="cart-del" value="삭제" data-cartnum=${cart.cart_num }></li>
					</c:forEach>
					<li><b>총구매금액</b></li>
					<li>${all_total}</li>
					<li><input type="submit" value="주문하기"></li>
					</ul>
					</form>
				</c:if>
			</c:if>
		</table>
		<!-- 장바구니 목록 끝-->
		<!-- 참여중인 공동구매 시작 -->
		<c:if test="${type==2}">
			<c:if test="${count <= 0}">
				<span id="no_list">내역이 없습니다.</span>
			</c:if>
			<c:if test="${count > 0}">
				<c:forEach var="order" items="${orderList}">
				<h3>주문번호 : ${order.order_num }</h3>
					<c:forEach var="orderDetail" items="${orderDetailList}">
					<c:if test="${order.order_num == orderDetail.order_num}">
						<div id="order_box">
							<img src="${pageContext.request.contextPath}/product/imageView.do?product_num=${orderDetail.product_num}&photo_type=1"
								width="200" height="200">
							<span>${orderDetail.product_name}</span><br>
							<span>${orderDetail.order_quantity}개</span><br>
							<span><fmt:formatNumber value="${orderDetail.product_total}"/>원</span><br>
							<c:if test="${orderDetail.wait_count != 0}">
								<span>주문확정수량 ${orderDetail.req_quantity}개 중 ${orderDetail.wait_count}개 신청</span>
							</c:if>
							<c:if test="${orderDetail.wait_count == 0}">
								<span>판매중지된 상품입니다</span>
							</c:if>
						</div>
					</c:if>
					</c:forEach>
			</c:forEach>
			</c:if>
			</c:if>
		<!-- 참여중인 공동구매 끝 -->
		
		<!-- 구매 내역 -->
		<c:if test="${type==3}">
			<c:if test="${count <= 0}">
				<span id="no_list">내역이 없습니다.</span>
			</c:if>
			<c:if test="${count > 0}">
				<c:forEach var="order" items="${orderList}">
				<h3>주문번호 : ${order.order_num }</h3>
				<h4>${order.reg_date}</h4>
					<c:forEach var="orderDetail" items="${orderDetailList}">
					<c:if test="${order.order_num == orderDetail.order_num}">
						<div id="order_box">
							<img src="${pageContext.request.contextPath}/product/imageView.do?product_num=${orderDetail.product_num}&photo_type=1"
								width="200" height="200">
							<span>${orderDetail.product_name}</span><br>
							<span>${orderDetail.order_quantity}개</span><br>
							<span><fmt:formatNumber value="${orderDetail.product_total}"/>원</span><br>
							<input type="button" value="리뷰작성" 
								onclick="location.href='${pageContext.request.contextPath}/review/write_review.do?product_num=${orderDetail.product_num}'">
						</div>
					</c:if>
					</c:forEach>
			</c:forEach>
			</c:if>
			</c:if>
		<!-- 구매 내역 끝 -->
		<!-- 내 리뷰 시작 -->
		<c:if test="${type==4}">
			<c:if test="${empty reviewList}">
				<span id="no_list">내역이 없습니다.</span>
			</c:if>
			<c:if test="${!empty reviewList}">
				<c:forEach var="review" items="${reviewList}">
					<div id="review_box">
						<img src="${pageContext.request.contextPath}/product/imageView.do?product_num=${review.product_num}&photo_type=1"
							width="130" height="130">
						<span>${review.product_name}</span><br>
						<!-- 별점 -->
						<div class="rating">
						<input type="hidden" name="score" value="0" class="rate-star">
						<c:forEach var="star" begin="1" end="5" varStatus="status">
							<input type="checkbox" id="rating${status.index}"
								value="${status.index}" class="rate-check"
								<c:if test="${status.index<=review.score}">checked</c:if> disabled>
							<label for="rating${status.index}"></label>
						</c:forEach>
						</div>
						<!-- 별점 끝 -->
						<span>${review.reg_date}</span><br>
						<p>${review.content }</p>
						<input type="button" value="수정" 
							onclick="location.href='${pageContext.request.contextPath}/review/write_review.do?product_num=${review.product_num}'">
					</div>
				</c:forEach>
			</c:if>
			</c:if>
		<!-- 내 리뷰 끝 -->
	</div>
</div>
<!-- 내용 끝 -->


























