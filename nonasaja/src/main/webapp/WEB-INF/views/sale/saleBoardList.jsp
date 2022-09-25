<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 내용 시작 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/sale.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

<div class="page-main">
<div class="align-center">
	<img src="${pageContext.request.contextPath}/images/saleinfomain.png" width="700px">
</div>
<br>
	<div class="writebutton-float">
		<c:if test="${!empty user}">
			<a href="${pageContext.request.contextPath}/sale/saleBoardWrite.do"><img src="${pageContext.request.contextPath}/images/salewrite.png" class="writebutton"></a>
		</c:if>

		<form action="saleBoardList.do" id="search_form1" method="get">
			<ul class="search-align">
				<li><select name="keyfield" id="keyfield">
						<option value="1"
							<c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
						<option value="2"
							<c:if test="${param.keyfield == 2}">selected</c:if>>ID+별명</option>
						<option value="3"
							<c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
						<option value="4"
							<c:if test="${param.keyfield == 4}">selected</c:if>>제목+내용</option>
				</select></li>
				<li><input type="search" name="keyword" id="searchbar"
					value="${param.keyword}"></li>
				<li><input type="submit" value="찾기" class="button5"> <input
					type="button" value="목록" onclick="location.href='saleBoardList.do'"
					class="button4"></li>
			</ul>
		</form>
	</div>
</div>
	<c:if test="${count == 0}">
		<div class="result-display1">표시할 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<div class="container">
			<table class="table">
				<tr>
					<th>번호</th>
					<th width="400">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.board_num}</td>
						<td><a href="saleBoardDetail.do?board_num=${board.board_num}">${board.title}</a></td>
						<td>${board.nick_name}</td>
						<td>${board.reg_date}</td>
						<td>${board.hit}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="align-center">${page}</div>
	</c:if>

<!-- 내용 끝 -->



