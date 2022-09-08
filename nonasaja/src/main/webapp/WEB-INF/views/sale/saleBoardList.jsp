<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<div>
	<form action="saleBoardList.do" id="search_form" method="get">
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
				    onclick="location.href='saleBoardList.do'">
			</li>
		</ul>
	</form>
	
	<c:if test="${!empty user}">
	<div>
		<input type="button" value="글쓰기"
		          onclick="location.href='saleBoardWrite.do'">
	</div>
	</c:if>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>	
	</c:if>
	<c:if test="${count > 0}">
	<table>
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
	<div class="align-center">${page}</div>
	</c:if>
</div>
<!-- 내용 끝 -->



