<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commuboard.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/commuboard.recipe.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/recipeView.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Dongle&display=swap" rel="stylesheet">
<div class="page-main">
	<p id="recipe_start">레시피 작성자</p>
	<ul class="detail-info">
		<li>
			<c:if test="${!empty board.photo_name}">
			<img src="imageView.do?commu_num=${board.commu_num}&board_type=1" width="100" height="100" class="my-photo">
			</c:if>
			<c:if test="${empty board.photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="100" height="100" class="my-photo">
			</c:if>
		</li>
		<li>
			<c:if test="${empty board.nickname}">${board.id}</c:if>
			<c:if test="${!empty board.nickname}">${board.nickname}</c:if>
			<c:if test="${!empty board.commu_modify_date}">
			최근 수정일 : ${board.commu_modify_date}
			</c:if><br>
			<c:if test="${empty board.commu_modify_date}">
			작성일 : ${board.commu_date}
			</c:if><br>
			조회 : ${board.commu_hit}
		</li>
	</ul>
<%--  	<ul>
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
	</ul>  --%>
	<hr id="recipe_hr" size="1" width="92%">
	<c:if test="${fn:endsWith(board.filename,'.jpg') ||
	              fn:endsWith(board.filename,'.JPG') ||
	              fn:endsWith(board.filename,'.jpeg') ||
	              fn:endsWith(board.filename,'.JPEG') ||
	              fn:endsWith(board.filename,'.gif') ||
	              fn:endsWith(board.filename,'.GIF') ||
	              fn:endsWith(board.filename,'.png') ||
	              fn:endsWith(board.filename,'.PNG')}">
	
	<c:if test="${!empty board.filename}">
	<div class="align-center">
		<img src="imageView.do?commu_num=${board.commu_num}&board_type=2" style="max-width:800px;">
	</div>
	</c:if>
	
	<div id="recipe_data1">
		<h3 id="recipe_title">${board.commu_title}</h3>
		<h2 id="recipe_food">${board.commu_food}</h2>
	</div>
	<ul id="recipe_data2">
		<li>
		<b>난이도</b>&emsp; ${board.commu_level}
		</li>
		<li>
		<b>소요시간</b>&emsp;  ${board.commu_time}
		</li>
	</ul><br><br><br><br>
	<ul id="recipe_data3">
		<li>
		<b>필수재료</b>&emsp;  ${board.commu_ingredient}
		</li>
	</ul><br>
	<p id="recipe_start"><img src="${pageContext.request.contextPath}/images/lionHead.png" width="60" height="60">레시피</p>
	<hr id="recipe_hr" size="1" width="92%">


	<c:if test="${!empty board.filename2}">
	<div class="align-center">
		<p id="recipe1"><img id="recipe_number" src="${pageContext.request.contextPath}/images/1.png">${board.commu_content}</p>
		<img id="recipe_photo" src="imageView.do?commu_num=${board.commu_num}&board_type=3" style="max-width:500px;">
	</div>
	</c:if>

	<c:if test="${!empty board.filename3}">
	<div class="align-center">
		<p id="recipe1"><img id="recipe_number" src="${pageContext.request.contextPath}/images/2.png">${board.commu_content2}</p>
		<img id="recipe_photo" src="imageView.do?commu_num=${board.commu_num}&board_type=4" style="max-width:500px;">
	</div>
	</c:if>

	<c:if test="${!empty board.filename4}">
	<div class="align-center">
		<p id="recipe1"><img id="recipe_number" src="${pageContext.request.contextPath}/images/3.png">${board.commu_content3}</p>
		<img id="recipe_photo" src="imageView.do?commu_num=${board.commu_num}&board_type=5" style="max-width:500px;">
	</div>
	</c:if>

	<c:if test="${!empty board.filename5}">
	<div class="align-center">
		<p id="recipe1"><img id="recipe_number" src="${pageContext.request.contextPath}/images/4.png">${board.commu_content4}</p>
		<img id="recipe_photo" src="imageView.do?commu_num=${board.commu_num}&board_type=6" style="max-width:500px;">
	</div>
	</c:if>
	
	<c:if test="${!empty board.filename6}">
	<div class="align-center">
		<p id="recipe1"><img id="recipe_number" src="${pageContext.request.contextPath}/images/5.png">${board.commu_content5}</p>
		<img id="recipe_photo" src="imageView.do?commu_num=${board.commu_num}&board_type=7" style="max-width:500px;">
	</div>
	</c:if>
	</c:if> 

	
	<div>
		<%-- 좋아요 --%>
		<img id="output_fav" src="${pageContext.request.contextPath}/images/fav01.gif" width="40">
		<span id="output_fcount"></span>
	</div>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user && user.mem_num == board.mem_num}">
		<input class="button" type="button" value="수정" onclick="location.href='update.do?commu_num=${board.commu_num}'">
		<input class="button" type="button" value="삭제" id="delete_btn">
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
		</c:if>
		<input class="button" type="button" value="목록" onclick="location.href='list.do'">
	</div>
	<hr size="1" width="100%">
	<!-- 댓글 UI 시작 -->
	<div id="reply_div">
		<span class="re-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="commu_num"
			   value="${board.commu_num}" id="commu_num">
			<textarea rows="3" cols="50" 
			  name="reply_content" id="reply_content"
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