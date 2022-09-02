<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="align-center">
	<div id="product_register">
		<form:form id="register_form" action="register.do" modelAttribute="productVO">
			<form:errors element="div" cssClass="error-color"/>
			<ul>
				<li>
					<label for="name">상품명</label>
					<form:input path="name"/>
					<form:errors path="name" cssClass="error-color"/>
				</li>
				<li>
					<label for="kind">카테고리</label>
					<form:radiobutton path="kind" value="0"/>식품
					<form:radiobutton path="kind" value="1"/>생활용품
					<form:errors path="name" cssClass="error-color"/>
				</li>
				<li>
					<label for="sub_category">상품종류</label>
					<form:input path="sub_category" placeholder="과일,정육,냉동,잡곡 등"/>
					<form:errors path="name" cssClass="error-color"/>
				</li>
			</ul>
		</form:form>
	</div>
</div>
