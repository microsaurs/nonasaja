<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 내용 시작 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/review.js"></script>
<script>
	$(document).ready(function() {
		$(document).on('click', '.rating', function(e) {
			let elem = e.target;
			if (elem.classList.contains('rate-check')) {
				$(this).find('.rate-check').each(function(index, item) {
					if (index < elem.value) {
						item.checked = true;
					} else {
						item.checked = false;
					}
				});
				$(this).find('.rate-star').val(elem.value);
			}
		});
	});
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/review.css">
<div class="review-wrap">
<form:form id="review_form" action="write_review.do" method="post"
			modelAttribute="reviewVO">
	<h2>구매후기</h2>
	<br>
	<div id="product_box">
		<img class="left" id="product_img"
			src="${pageContext.request.contextPath}/product/imageView.do?product_num=${product.product_num}&photo_type=1"
			width="200" height="200">
		<div id="product_data" class="left">
			<p id="product_name">${product.name}</p>
			<p id="product_title">${product.title}</p>
			<p id="product_reg_date">${product.reg_date}</p>
			<div id="score" class="rating left">
				<input type="hidden" name="score" value="0" class="rate-star">
				<!-- 해당  별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
				<c:forEach var="star" begin="1" end="5" varStatus="status">
					<input type="checkbox" id="rating${status.index}"
						value="${status.index}" class="rate-check"
						<c:if test="${status.index<=review.score}">checked</c:if>>
					<label for="rating${status.index}"></label>
				</c:forEach>
			</div>
		</div>
		
	</div>
	<div id="review_box" class="clear">
			<form:hidden path="product_num" value="${product.product_num}" />
			<form:textarea id="content" path="content"
				placeholder="상품의 구매 후기를 작성해주세요" rows="10" />
			<br>
			<form:errors path="content" cssClass="error-color" />
			<input id="btn1" class="right" type="button" value="목록"
				onclick="location.href='${pageContext.request.contextPath}/order/order_list.do'">
			<form:button id="btn2" class="right">등록</form:button>	
	</div>
	</form:form>
</div>
<!-- 내용 끝 -->









