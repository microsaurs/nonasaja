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
<div class="page-main1">


	<div class="align-center">
	<img src="${pageContext.request.contextPath}/images/자취생끼리같이해요.JPG" width="700px">
	
	</div>
	<!-- 로그인 되어있으면 글쓰기로 -->
	<c:if test="${!empty user}">	          
	<div class="align-center">

	<input class="button2" type="button" value="동호인 모집하기" onclick="location.href='write.do'">
	</div>
	</c:if>
	<div class="align-center">
	<a href="list.do?keyfield=1"><img id="img" src="${pageContext.request.contextPath}/images/free-icon-dumbbell-8009699.png" width="80px"></a>
	<a href="list.do?keyfield=2"><img id="img" src="${pageContext.request.contextPath}/images/free-icon-game-controller-7358397.png" width="70px"></a>
	<a href="list.do?keyfield=3"><img id="img" src="${pageContext.request.contextPath}/images/free-icon-rice-bowl-681276.png" width="70px"></a>
	<a href="list.do?keyfield=4"><img id="img" src="${pageContext.request.contextPath}/images/free-icon-mic-665909.png" width="70px"></a>
	<a href="list.do?keyfield=5"><img id="img" src="${pageContext.request.contextPath}/images/free-icon-airplane-3230483.png" width="70px"></a>   
	<a href="list.do?keyfield=6"><img id="img" src="${pageContext.request.contextPath}/images/free-icon-open-book-2280294.png" width="70px"></a>
	<a href="list.do?keyfield=7"><img id="img" src="${pageContext.request.contextPath}/images/free-icon-more-5859335.png" width="70px"></a>
	</div>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>	
	</c:if>
	<c:if test="${count > 0}">


	<c:forEach var="board" items="${list}">
		<table>
		<tr>
			<th id="th-1">
			<c:if test="${board.club_recruit==0}">모집중</c:if>
			<c:if test="${board.club_recruit==1}">모집완료</c:if>
			</th>
			<th width="400" id="th-2"><a href="detail.do?club_num=${board.club_num}">${board.club_title}</a></th>
		</tr>
		<tr>
			<td id="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-gender.png" width="50px">
			</td>
			<td id="td-2">
			<c:if test="${board.club_gender==0}">누구나 참여가능</c:if>
			<c:if test="${board.club_gender==1}">남자만 참여가능</c:if>
			<c:if test="${board.club_gender==2}">여자만 참여가능</c:if>
			</td>
		</tr>
		<tr>
			<td id="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-age.png" width="50px">
			</td>
			<td id="td-2">
			<c:if test="${board.club_age == '10대,20대,30대,40대,50대,60대'}">
			누구나 참여가능
			</c:if>
			<c:if test="${board.club_age != '10대,20대,30대,40대,50대,60대'}">
			${board.club_age} 참여가능
			</c:if>
			</td>
		</tr>
		<tr>
			<td id="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-calendar-date.png" width="50px">
			</td>
			<td id="td-2">
			${board.club_date}
			</td>
		</tr>
		<tr>
			<td id="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-location.png" width="50px">
			</td>
			<td id="td-2">
			${board.region_num}
			</td>
		</tr>
		<tr>
			<td id="td-3">
			<img src="${pageContext.request.contextPath}/images/icon-multiple-users-silhouette.png" width="50px">
			</td>
			<td id="td-4">
			${board.club_pre}/${board.club_limit}명 참여
			</td>
		</tr>
		</table>
		</c:forEach>
	
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 내용 끝 -->



