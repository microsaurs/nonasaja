<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- 내용 시작 -->
<div class="page-main">
	<h2>회원권한 수정</h2>
	<form:form modelAttribute="memberVO" action="admin_update.do" id="modify_form">
		<form:hidden path="mem_num"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label>회원권한</label>
				<c:if test="${memberVO.auth<3}">
				<form:radiobutton path="auth" value="1"/>정지
				<form:radiobutton path="auth" value="2"/>일반
				</c:if>
				<c:if test="${memberVO.auth==3}">
				관리
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<c:if test="${memberVO.auth!=3}">
			<input type="submit" value="전송">
			</c:if>
			<input type="button" value="목록" onclick="location.href='admin_list.do'">
		</div>
		<ul>
			<li>
				<label>이름</label>
				${memberVO.name}
			</li>
			<li>
				<label>전화번호</label>
				${memberVO.phone}
			</li>
			<li>
				<label>이메일</label>
				${memberVO.email}
			</li>
			<li>
				<label>우편번호</label>
				${memberVO.zipcode}
			</li>
			<li>
				<label>주소</label>
				${memberVO.addr1}
			</li>
			<li>
				<label>상세주소</label>
				${memberVO.addr2}
			</li>
			<li>
				<label>연동회원 여부</label>
				<c:if test="${memberVO.root==0}">노나사자</c:if>
				<c:if test="${memberVO.root==1}">네이버</c:if>
				<c:if test="${memberVO.root==2}">카카오</c:if>
			</li>
		</ul>
	</form:form>
</div>
<!-- 내용 끝 -->