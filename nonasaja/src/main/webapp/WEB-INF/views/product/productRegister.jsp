<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="product_register">
<!-- !!!config 패키지의 AppConfig에서 인터셉터 설정하기!!! -->
	<form:form id="register_form" action="register.do" modelAttribute="productVO" enctype="multipart/form-data">
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
				<form:errors path="kind" cssClass="error-color"/>
			</li>
			<li>
				<label for="sub_category">상품종류</label>
				<form:input path="sub_category" placeholder="과일,정육,냉동,잡곡 등"/>
				<form:errors path="sub_category" cssClass="error-color"/>
			</li>
			<li>
				<label for="title">판매제목</label>
				<form:input path="title"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<label for="price1">원가</label>
				<form:input path="price1"/>
				<form:errors path="price1" cssClass="error-color"/>
			</li>
			<li>
				<label for="price2">판매가</label>
				<form:input path="price2"/>
				<form:errors path="price2" cssClass="error-color"/>
			</li>
			<li>
				<label for="quantity">총 수량</label>
				<form:input path="quantity"/>
				<form:errors path="quantity" cssClass="error-color"/>
			</li>
			<li>
				<label for="req_quantity">구매요구수량</label>
				<form:input path="req_quantity"/>
				<form:errors path="req_quantity" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload1">대표사진</label>
				<input type="file" name="upload1" id="upload1">
			</li>
			<li>
				<label for="upload2">사진2</label>
				<input type="file" name="upload2" id="upload2">
			</li>
			<li>
				<label for="upload3">사진3</label>
				<input type="file" name="upload3" id="upload3">
			</li>
			<li>
				<label for="company">제조사</label>
				<form:input path="company"/>
				<form:errors path="company" cssClass="error-color"/>
			</li>
			<li>
				<label for="origin">원산지</label>
				<form:input path="origin"/>
				<form:errors path="origin" cssClass="error-color"/>
			</li>
			<li>
				<label for="status">판매상태</label>
				<form:radiobutton path="status" value="0"/>판매중
				<form:radiobutton path="status" value="1"/>판매마감
				<form:errors path="status" cssClass="error-color"/>
			</li>
			<li>
				<label for="deadline">판매기한</label>
				<input type="date" name="deadline" id="deadline">
				<form:errors path="deadline" cssClass="error-color"/>
			</li>	
		</ul>
		<form:button>등록</form:button>
		<input type="button" value="목록" onclick="location.href='list.do'">
	</form:form>
</div>



















