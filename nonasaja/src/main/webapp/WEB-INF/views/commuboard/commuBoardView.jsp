<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commuboard.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commuboard.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/commuView.css">
<div class="page-main">
	<h2>${board.commu_title}</h2>
	<ul class="detail-info">
		<li>
			<c:if test="${!empty board.photo_name}">
			<img src="imageView.do?commu_num=${board.commu_num}&board_type=1" width="40" height="40" class="my-photo">
			</c:if>
			<c:if test="${empty board.photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
		</li>
		<li>
			<c:if test="${empty board.nickname}">${board.id}</c:if>
			<c:if test="${!empty board.nickname}">${board.nickname}</c:if>
			<br>
			<c:if test="${!empty board.commu_modify_date}">
			최근 수정일 : ${board.commu_modify_date}
			</c:if>
			<c:if test="${empty board.commu_modify_date}">
			작성일 : ${board.commu_date}
			</c:if>
			조회 : ${board.commu_hit}
		</li>
	</ul>

 	<hr size="1" width="100%">

	<p>
		${board.commu_content}
	</p>
	<div>
		<%-- 좋아요 --%>
		<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
		<span id="output_fcount"></span>
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user && user.mem_num == board.mem_num}">
		<input type="button" value="수정" onclick="location.href='update.do?commu_num=${board.commu_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠어요?');
				if(choice){
					location.replace('delete.do?commu_num=${board.commu_num}');
				}
			}
		</script>
		<input type="button" value="목록" onclick="location.href='list.do'">
		</c:if>
	</div>

	<!-- 댓글 UI 시작 -->
	<div id="reply_div1">
		<img src="${pageContext.request.contextPath}/images/댓글아이콘.png" width="25" height="25" class="replyicon">
		<span class="re-title1"><b>댓글을 작성해주세요.</b></span>
		<form id="re_form1">
			<input type="hidden" name="commu_num" value="${board.commu_num}" id="commu_num">
			<textarea rows="3" cols="50" name="reply_content" id="reply_content" class="rep-content1"
			<c:if test="${empty user}">disabled="disabled"</c:if>
			><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>
			<c:if test="${!empty user}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
				
			</div>
			<div id="re_second" class="button9-align">
				<input type="submit" value="전송" class="button9">
			</div>
			</c:if>
		</form>
	</div>
	<!-- 댓글 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="다음글 보기" class="button10">
	</div>
	<div id="loading" style="disply:none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif" width="100" height="100">
	</div>
	<!-- 댓글 UI 끝 -->



	
</div>


<!-- 내용 끝 -->