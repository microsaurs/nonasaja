<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<div class="mypage-wrap">
	<div class="mypage-left">
		<ul>
			<li>
				<c:if test="${empty member.photo_name}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="200" height="200" class="my-photo">
				</c:if>
				<c:if test="${!empty member.photo_name}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" height="200" class="my-photo">
				</c:if>
			</li>
			<li id="nick"><span>${member.nickname}</span></li>
			<li id="money">사자머니 &nbsp;&nbsp;&nbsp;<a href="paymentList.do">충전 ></a></li>
			<li><span><fmt:formatNumber value="${member.cash}"/>원</span></li>
		</ul>
		<br>
		<ul>
			<li><a href="${pageContext.request.contextPath}/member/myPage.do">회원정보</a></li>
			<li><a href="myPageProduct.do">공동구매</a></li>
			<li><a href="myPageUsed.do">중고거래</a></li>
			<li><a href="myPageClub.do">동호회</a></li>
			<li><a href="myPageCommu.do">커뮤니티</a></li>
			<c:if test="${member.root == 0}">
			<li><h3><a href="delete.do">회원탈퇴</a></h3></li>
			</c:if>
		</ul>
	</div>
	<div class="mypage-right">
		<table>
			<tr>
				<th colspan="3">회원탈퇴</th>
			</tr>
			<form:form modelAttribute="memberVO" action="delete.do" id="delete_form">
			<tr>
				<form:errors element="div" cssClass="error-color"/>
			</tr>
			<tr>
				<td>
					<label for="id">아이디</label>
				</td>
				<td>
					<form:input path="id"/>
				</td>
				<td>
					<form:errors path="id" cssClass="error-color"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="passwd">비밀번호</label>
				</td>
				<td>
					<form:password path="passwd"/>
				</td>
				<td>
					<form:errors path="passwd" cssClass="error-color"/>
				</td>
			</tr>
			<tr>
				<td class='align-center' colspan="3">
					<form:button>전송</form:button>
					<input type="button" value="마이페이지" onclick="location.href='myPage.do'">
				</td>
			</tr>
			</form:form>
		</table>
	</div>
</div>
<!-- 내용 끝 -->