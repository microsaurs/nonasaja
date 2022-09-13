<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/club.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR&family=Poor+Story&display=swap" rel="stylesheet">
<div class="page-main">

	<form action="list.do" id="search_form" 
	                                   method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
					<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>ID+별명</option>
					<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					<option value="4" <c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="keyword"
				               value="${param.keyword}">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록"
				    onclick="location.href='list.do'">
			</li>
		</ul>
	</form>
	<div class="align-center">
	<img src="${pageContext.request.contextPath}/images/자취생끼리같이해요.JPG" width="900px">
	</div>
	<!-- 로그인 되어있으면 글쓰기로 -->
	<c:if test="${!empty user}">	          
	<div class="align-center">
	<img src="${pageContext.request.contextPath}/images/동호인 모집하기.JPG" width="300px" 
		          onclick="location.href='write.do'">
	</div>
	</c:if>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>	
	</c:if>
	<c:if test="${count > 0}">


	<c:forEach var="board" items="${list}">
		<table>
		<tr>
			<th class="th-1">
			<c:if test="${board.club_recruit==0}">모집중</c:if>
			<c:if test="${board.club_recruit==1}">모집완료</c:if>
			</th>
			<th width="400" class="th-2"><a href="detail.do?club_num=${board.club_num}">${board.club_title}</a></th>
		</tr>
		<tr>
			<td>
			<img src="${pageContext.request.contextPath}/images/icon-gender.png" width="50px">
			</td>
			<td class="th-2">
			<c:if test="${board.club_gender==0}">누구나 참여가능</c:if>
			<c:if test="${board.club_gender==1}">남자만 참여가능</c:if>
			<c:if test="${board.club_gender==2}">여자만 참여가능</c:if>
			</td>
		</tr>
		<tr>
			<td>
			<img src="${pageContext.request.contextPath}/images/icon-age.png" width="50px">
			</td>
			<td class="th-2">
			<c:if test="${board.club_age == '10대,20대,30대,40대,50대,60대'}">
			누구나 참여가능
			</c:if>
			<c:if test="${board.club_age != '10대,20대,30대,40대,50대,60대'}">
			${board.club_age} 참여가능
			</c:if>
			</td>
		</tr>
		<tr>
			<td>
			<img src="${pageContext.request.contextPath}/images/icon-calendar-date.png" width="50px">
			</td>
			<td class="th-2">
			${board.club_date}
			</td>
		</tr>
		<tr>
			<td>
			<img src="${pageContext.request.contextPath}/images/icon-location.png" width="50px">
			</td>
			<td class="th-2">
			${board.region_num}
			</td>
		</tr>
		<tr>
			<td>
			<img src="${pageContext.request.contextPath}/images/icon-multiple-users-silhouette.png" width="50px">
			</td>
			<td class="th-2">
			${board.club_pre}/${board.club_limit}명 참여
			</td>
		</tr>
		</table><br><br>
		</c:forEach>
	
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 내용 끝 -->



