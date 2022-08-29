<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main">
	<h2>회원가입</h2>
	<form:form id="register_form" action="registerUser.do" modelAttribute="memberVO">
		<form:errors element="div" cssClass="error-color"/><%-- 필드에 의존하지 않는 예외 문구 출력 --%>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id" placeholder="영문,숫자만 4~20자" autocomplete="off"/>
				<input type="button" id="confirmId" value="ID중복체크">
				<span id="message_id"></span>
				<form:errors path="id" cssClass="error-color"/>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd" placeholder="영문,숫자만 4~15자"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
			<%--<li>
				<label for="passwd">비밀번호 확인</label>
				<form:password path="passwd" placeholder="영문,숫자만 4~15자"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>--%>
			<li>
				<label for="nickname">별명</label>
				<form:input path="nickname"/>
				<form:errors path="nickname" cssClass="error-color"/>
			</li>
			<li>
				<label for="name">이름</label>
				<form:input path="name"/>
				<form:errors path="name" cssClass="error-color"/>
			</li>
			<li>
				<label for="phone">전화번호</label>
				<form:input path="phone"/>
				<form:errors path="phone" cssClass="error-color"/>
			</li>
			<li>
				<label for="email">이메일</label>
				<form:input path="email"/>
				<form:errors path="email" cssClass="error-color"/>
			</li>
			<li>
				<label for="zipcode">우편번호</label>
				<form:input path="zipcode"/>
				<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
				<form:errors path="zipcode" cssClass="error-color"/>
			</li>
			<li>
				<label for="addr1">주소</label>
				<form:input path="addr1"/>
				<form:errors path="addr1" cssClass="error-color"/>
			</li>
			<li>
				<label for="add2">상세 주소</label>
				<form:input path="addr2"/>
				<form:errors path="addr2" cssClass="error-color"/>
			</li>
			<li>
				<label for="interest">관심사</label>
				<form:checkbox path="interest" value="운동"/>운동
				<form:checkbox path="interest" value="오락"/>오락
				<form:checkbox path="interest" value="맛집"/>맛집
				<form:checkbox path="interest" value="노래"/>노래
				<form:checkbox path="interest" value="여행"/>여행
				<form:checkbox path="interest" value="스터디"/>스터디
			</li>
		</ul>
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>