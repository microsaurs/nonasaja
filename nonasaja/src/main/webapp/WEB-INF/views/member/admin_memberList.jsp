<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/member.admin.js"></script>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
<div class="page-main">
	<h2>회원목록(관리자용)</h2>
	<form action="admin_list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>ID</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>닉네임</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>이메일</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>전체</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='admin_list.do'">
			</li>
		</ul>
	</form>
	<c:if test="${count == 0}">
	<div class="align-center">표시할 회원정보가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table id="admin_table">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>권한</th>
			<th>연동</th>
		</tr>
		<c:forEach var="member" items="${list}">
		<tr>
			<td>
				<c:if test="${member.auth == 0}">${member.id}</c:if>
				<c:if test="${member.auth > 0}"><a href="admin_update.do?mem_num=${member.mem_num}">${member.id}</a></c:if>
			</td>
			<td>${member.name}</td>
			<td>${member.phone}</td>
			<td>${member.email}</td>
			<td>
				${member.reg_date}
			</td>
			<td>
				<c:if test="${member.auth == 0}"><font color="orange">탈퇴</font></c:if>
				<c:if test="${member.auth == 1}"><font color="red">정지</font></c:if>
				<c:if test="${member.auth == 2}">일반</c:if>
				<c:if test="${member.auth == 3}"><font color="blue">관리</font></c:if>
			</td>
			<td>
				<c:if test="${empty member.root}"></c:if>
				<c:if test="${member.root == 0}">노나사자</c:if>
				<c:if test="${member.root == 1}">네이버</c:if>
				<c:if test="${member.root == 2}">카카오</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${page}</div>
	</c:if>
</div>

<!-- 내용 끝 -->