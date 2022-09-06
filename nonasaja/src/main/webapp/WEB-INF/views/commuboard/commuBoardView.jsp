<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commuboard.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
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
 	<ul>
		<c:if test="${!empty board.filename}">
		<li>
			첨부파일 : <a href="file.do?commu_num=${board.commu_num}">${board.filename}</a>
		</li>
		</c:if>
		<c:if test="${!empty board.filename2}">
		<li>
			첨부파일 : <a href="file.do?commu_num=${board.commu_num}">${board.filename2}</a>
		</li>
		</c:if>
		<c:if test="${!empty board.filename3}">
		<li>
			첨부파일 : <a href="file.do?commu_num=${board.commu_num}">${board.filename3}</a>
		</li>
		</c:if>
	</ul> 
 	<hr size="1" width="100%">
	<c:if test="${fn:endsWith(board.filename,'.jpg') ||
	              fn:endsWith(board.filename,'.JPG') ||
	              fn:endsWith(board.filename,'.jpeg') ||
	              fn:endsWith(board.filename,'.JPEG') ||
	              fn:endsWith(board.filename,'.gif') ||
	              fn:endsWith(board.filename,'.GIF') ||
	              fn:endsWith(board.filename,'.png') ||
	              fn:endsWith(board.filename,'.PNG')}">
	<div class="align-center">
		<img src="imageView.do?commu_num=${board.commu_num}&board_type=2" style="max-width:500px;">
	</div>
	<div class="align-center">
		<img src="imageView.do?commu_num=${board.commu_num}&board_type=3" style="max-width:500px;">
	</div>
	<div class="align-center">
		<img src="imageView.do?commu_num=${board.commu_num}&board_type=4" style="max-width:500px;">
	</div>
	</c:if> 
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
	<hr size="1" width="100%">
	<!-- 댓글 UI 시작 -->
	<div id="reply_div">
		<span class="re-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="commu_num"
			   value="${board.commu_num}" id="commu_num">
			<textarea rows="3" cols="50" 
			  name="re_content" id="re_content"
			  class="rep-content"
			  <c:if test="${empty user}">disabled="disabled"</c:if>
			  ><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>
			<c:if test="${!empty user}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second" class="align-right">
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