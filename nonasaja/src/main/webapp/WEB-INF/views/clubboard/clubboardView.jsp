<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<h2>${club.club_title}</h2>
	<ul class="detail-info">
		<li> 
			<c:if test="${!empty club.photo_name}">
			<img src="imageView.do?club_num=${club.club_num}&board_type=1" width="40" height="40" class="my-photo">
			</c:if>
			<c:if test="${empty club.photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
		</li>
		<li>
			<c:if test="${empty club.nickname}">${club.id}</c:if>
			<c:if test="${!empty club.nickname}">${club.nickname}</c:if>
			<br>>
			<c:if test="${!empty club.club_modify_date}">
			최근 수정일 : ${club.club_modify_date}
			</c:if>
			<c:if test="${empty club.club_modify_date}">
			작성일 : ${club.club_date}
			</c:if>
			조회 : ${club.club_hit}
		</li>
	</ul>
	
	<p>
		${club.club_content}
	</p>
	<div>
		<%-- 좋아요 --%>
		<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
		<span id="output_fcount"></span>
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user && user.mem_num == club.club_leader}">
		<input type="button" value="수정" 
		  onclick="location.href='update.do?club_num=${club.club_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?club_num=${club.club_num}');
				}
			};
		</script>  
		</c:if>
		<input type="button" value="목록"
		       onclick="location.href='list.do'">
	</div>
	<hr size="1" width="100%">
	
</div>
<!-- 내용 끝 -->

