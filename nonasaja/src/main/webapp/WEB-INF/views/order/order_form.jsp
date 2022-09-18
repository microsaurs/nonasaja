<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">

<div id="product_register">
	<p>주문하기</p>

	<c:forEach var="cartVO" items="${cartList}">
		<ul>
			<li>${cartVO.productVO.title}</li>
			<li>${cartVO.quantity}개</li>
			<li><fmt:formatNumber value="${cartVO.sub_total}"/>원</li>
		</ul>
	</c:forEach>
	<form:form id="order_form" action="order.do" modelAttribute="orderVO" method="post" >
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="receive_name" id="name_label">받으실 이름</label>
				<form:input path="receive_name" placeholder="받으실 이름" id="receive_name"/>
				<form:errors path="receive_name" cssClass="error-color"/>
			</li>
			<li>
				<label for="receive_phone" id="phone_label">상세주소</label>
				<form:input path="receive_phone" placeholder="배송지 상세 주소" id="receive_phone"/>
				<form:errors path="receive_phone" cssClass="error-color"/>
			</li>
			<li>
				<label for="receive_post" id="post_label">배송지 우편번호</label>
				<form:input path="receive_post" placeholder="우편번호" id="receive_post"/>
				<form:errors path="receive_post" cssClass="error-color"/>
			</li>
			<li>
				<label for="receive_address1" id="address1_label">주소</label>
				<form:input path="receive_address1" placeholder="배송지 주소" id="receive_address1"/>
				<form:errors path="receive_address1" cssClass="error-color"/>
			</li>
			<li>
				<label for="receive_address2" id="address2_label">상세주소</label>
				<form:input path="receive_address2" placeholder="배송지 상세 주소" id="receive_address2"/>
				<form:errors path="receive_address2" cssClass="error-color"/>
			</li>
			<li>
				<label for="notice" id="notice_label">배송 메모</label>
				<form:textarea path="notice" placeholder="배송시 남기실 메모" id="notice"/>
				<form:errors path="notice" cssClass="error-color"/>
			</li>
		</ul>
		<form:button>등록</form:button>
		<input type="button" value="장바구니" onclick="location.href='${pageContext.request.contextPath}/cart/cart_list.do'">
	</form:form>
</div>



















