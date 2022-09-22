<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clubboard.fav.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/club.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clubjoin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main1">
<!-- 헤드 이미지 -->

	<ul class="detail-info">
		<li> 
			<img id="img" src="${pageContext.request.contextPath}/member/viewProfile.do?mem_num=${board.mem_num}" width="50px" class="my-photo">
		</li>
		<li>
			<c:if test="${empty board.nickname}"><b>${board.id}</b></c:if>
			<c:if test="${!empty board.nickname}"><b>${board.nickname}</b></c:if>
			<br>
			<c:if test="${!empty board.club_modify_date}">
			최근 수정일 : ${board.club_modify_date}
			</c:if>
			<br>
			<c:if test="${empty board.club_modify_date}">
			작성일 : ${board.club_reg_date}
			</c:if>
			조회 : ${board.club_hit}
		</li>
	</ul>
	<hr size="1" width="100%">
	<div class="align-right">
		<%-- 좋아요 --%>
		LIKE
		<img id="output_fav" src="${pageContext.request.contextPath}/images/free-icon-love02.png" width="40">
	</div>
	
	<!-- 표 시작 -->
		<table>
		<tr>
			<th class="th-3">
			<c:if test="${board.club_recruit==0}">모집중</c:if>
			<c:if test="${board.club_recruit==1}">모집완료</c:if>
			</th>
			<th width="400" class="th-4">${board.club_title}</th>
		</tr>
		<tr>
			<td class="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-gender.png" width="50px">
			</td>
			<td class="td-2">
			<c:if test="${board.club_gender==0}">누구나 참여가능</c:if>
			<c:if test="${board.club_gender==1}">남자만 참여가능</c:if>
			<c:if test="${board.club_gender==2}">여자만 참여가능</c:if>
			</td>
		</tr>
		<tr>
			<td class="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-age.png" width="50px">
			</td>
			<td class="td-2">
			<c:if test="${board.club_age == '10대,20대,30대,40대,50대,60대'}">
			누구나 참여가능
			</c:if>
			<c:if test="${board.club_age != '10대,20대,30대,40대,50대,60대'}">
			${board.club_age} 참여가능
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-calendar-date.png" width="50px">
			</td>
			<td class="td-2">
			${board.club_date}
			</td>
		</tr>
		<tr>
			<td class="td-1">
			<img src="${pageContext.request.contextPath}/images/icon-location.png" width="50px">
			</td>
			<td class="td-2">
			${board.region_num}
			</td>
		</tr>
		<tr>
			<td class="td-3">
			<img src="${pageContext.request.contextPath}/images/icon-multiple-users-silhouette.png" width="50px">
			</td>
			<td class="td-4">
			${board.club_pre}/${board.club_limit}명 참여
			</td>
		</tr>
		</table>
	
	<table>
		<tr>
			<th class="th-3">
			동호회 이름
			</th>
			<th class="th-4">
			${board.club_name}
			</th>
		</tr>
	</table>
	
	<table>
		<tr>
			<th class="th-5" colspan="2">
			상세내용
			</th>
		</tr>
		<tr>
			<td class="td-1">
			${board.club_content}
			<br>
			<c:if test="${!empty board.club_img_name}">
			<img src="imageView.do?club_num=${board.club_num}" width="400" height="400">
			</c:if>
			</td>
		</tr>
	</table>
	
	<!-- 표 끝 -->		
	<%-- <c:if test="${!empty user && user.mem_num != board.club_leader}">
	<div class="align-left">
		<input class="button3" type="button" value="신고" id="noti_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('noti_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('신고하시겠습니까?');
				if(choice){
				 //코드입력해야함 
				}
			};
		</script> 
	</div>
	</c:if>
 --%>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty user && user.mem_num == board.club_leader}">
		<input class="button" type="button" value="수정" 
		  onclick="location.href='update.do?club_num=${board.club_num}'">
		<input class="button" type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?club_num=${board.club_num}');
				}
			};
		</script>  
		</c:if>
		<form id="club_join" method="post">
		<input type="hidden" name="club_num" value="${board.club_num}">
		<c:if test="${!empty user && user.mem_num != board.club_leader}">
		<input class="button" type="submit"  value="가입하기">
		</c:if>
		</form>
		<input class="button" type="button" value="목록가기"  onclick="location.href='list.do'">
	</div>

	<hr size="1" width="100%">
	
</div>
<!-- 내용 끝 -->


