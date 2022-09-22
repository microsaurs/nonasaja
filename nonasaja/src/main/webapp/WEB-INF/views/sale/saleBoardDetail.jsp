<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/sale.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
<div class="page-main">
	<h2>${board.title}</h2>
		<div class="userphoto">
			<c:if test="${!empty board.photo_name}">
			<img src="${pageContext.request.contextPath}/member/viewProfile.do?mem_num=${board.mem_num}" width="50px">
			</c:if>
			<c:if test="${empty board.photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
		</div>
		<div class="userinformation">
			${board.nick_name}
			<br>
		</div>	
		
			<hr size="1" width="100%">
			<p>
			세일 기한 : ${board.deadline}
			</p>

		<br>
	
	<div class="sale-simple">
	<div class="mainphoto-align">

	<c:if test="${!empty board.imgname}">
		<img src="imageView.do?board_num=${board.board_num}" width="900">
	</c:if>
	
	</div>

	
	<p>${board.content}</p>
	</div>
	
	<div class="userwriteinformation">
	<c:if test="${!empty board.modify_date}">
			최근 수정일 : ${board.modify_date}	
			</c:if>
	<c:if test="${empty board.modify_date}">
			작성일 : ${board.reg_date}	
			</c:if>
	<p>조회수 : ${board.hit}</p>
	</div>

	<div class="button-align">
		<c:if test="${!empty user && user.mem_num == board.mem_num}">
		<input type="button" value="수정" onclick="location.href='update.do?board_num=${board.board_num}'" class="button4">
		<input type="button" value="삭제" id="delete_btn" class="button5">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?board_num=${board.board_num}');
				}
			};
		</script>
		</c:if>
	</div>
	<div>
		<input type="button" value="목록" onclick="location.href='saleBoardList.do'" class="button4">
	</div>
	<hr size="1" width="100%">
	<!-- 댓글 UI 시작 -->
	<div id="reply_div">
		<span class="reply-title">댓글 달기</span>
		<form id="reply_form">
			<input type="hidden" name="board_num"
			   value="${board.board_num}" id="board_num">
			<textarea rows="3" cols="50" 
			  name="reply_content" id="reply_content"
			  class="reply_content"
			  <c:if test="${empty user}">disabled="disabled"</c:if>
			  ><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>
			<c:if test="${!empty user}">
			<div id="reply_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="reply_second" class="align-right">
				<input type="submit" value="전송">
			</div>
			</c:if>
		</form>
	</div>
	<!-- 댓글 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif" width="100" height="100">
	</div>
	<!-- 댓글 UI 끝 -->
</div>
<!-- 내용 끝 -->


