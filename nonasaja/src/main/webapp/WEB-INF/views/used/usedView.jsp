<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 --> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/used.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/used.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/used.fav.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/videoAdapter.js"></script>
<div class="page-main">
	<div>
	<div class="statuskind-float">
		<c:if test="${used.status==0}"><img src="${pageContext.request.contextPath}/images/노나사자-판매중.png" width="100px" height="45px"></c:if>
		<c:if test="${used.status==1}"><img src="${pageContext.request.contextPath}/images/노나사자-판매완료.png" width="100px" height="45px"></c:if>
	</div>
	<div class="statuskind-float">
		<c:if test="${used.kind==0}"><img src="${pageContext.request.contextPath}/images/노나사자-중고거래.png" width="100px" height="45px"></c:if>
		<c:if test="${used.kind==1}"><img src="${pageContext.request.contextPath}/images/노나사자-무료나눔.png" width="100px" height="45px"></c:if>
		<c:if test="${used.kind==2}"><img src="${pageContext.request.contextPath}/images/노나사자-물물교환.png" width="100px" height="45px"></c:if>
	</div>
	</div>
	<h2 style="clear:both">${used.title}</h2>
	<div class="fav-float">
		<%-- 좋아요 --%>
		<p id="fav-text-align">찜꽁</p>
		<img id="output_fav" src="${pageContext.request.contextPath}/images/free-icon-love02.png" width="30">
	</div>
	<div class="userphoto">
		<c:if test="${!empty used.photo_name}">
		<img src="imageView.do?used_num=${used.used_num}&board_type=1" width="40" height="40" class="my-photo1">
		</c:if>
		<c:if test="${empty used.photo_name}">
		<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo1">
		</c:if>
	</div>
	<div class="userinformation">
		<c:if test="${empty used.nickname}"><b>${used.id}</b></c:if>
		<c:if test="${!empty used.nickname}"><b>${used.nickname}</b> 님의 
			<c:if test="${used.kind==0}"><font color="#D8952A"><b>중고거래</b></font></c:if>
			<c:if test="${used.kind==1}"><font color="#D8952A"><b>무료나눔</b></font></c:if>
			<c:if test="${used.kind==2}"><font color="#D8952A"><b>물물교환</b></font></c:if>
		</c:if>
	</div>
		
	<hr size="1" width="100%">
	
	<div class="usedproduct-simple">
		<div class="mainphoto-align">
			<c:if test="${fn:endsWith(used.filename,'.jpg') ||
			              fn:endsWith(used.filename,'.JPG') ||
			              fn:endsWith(used.filename,'.jpeg') ||
			              fn:endsWith(used.filename,'.JPEG') ||
			              fn:endsWith(used.filename,'.gif') ||
			              fn:endsWith(used.filename,'.GIF') ||
			              fn:endsWith(used.filename,'.png') ||
			              fn:endsWith(used.filename,'.PNG')}">
			</c:if>
			<div class="mainphoto-align">
				<img src="imageView.do?used_num=${used.used_num}&board_type=2" class="mainphoto-size">
			</div>
		
		</div>
	
		<div class="product-info-align">
			<span class="text-price">
				<c:if test="${used.price!=0 && used.price!=1}">
					<b>가격 : <fmt:formatNumber value="${used.price}"/>원</b>
				</c:if>
				<c:if test="${used.price==0}">
					<b>무료나눔&#x1f49b;</b>
				</c:if>
				<c:if test="${used.price==1}">
					<b>물물교환&#x1f499;</b>
				</c:if>
			</span> <br>
			<p class="text-1">상품종류 : ${used.category}</p>
			<p class="text-1">판매지역 : ${used.region}</p>
		</div>	
	</div>
	
	<div class="used-content">
	<p>
		${used.content}
	</p>
	</div>
	
	
	
	<hr size="1" width="100%">
	
	<div class="userwriteinformation">
		<c:if test="${!empty used.modify_date}">
			최근 수정일 | ${used.modify_date}
		</c:if>
		<c:if test="${empty used.modify_date}">
			작성일  ${used.reg_date}
		</c:if><br>
			조회  ${used.hit}
	</div>
	
	<div class="button-align">
		<c:if test="${!empty user && user.mem_num == used.mem_num}">
		<input type="button" value="수정" 
		  onclick="location.href='update.do?used_num=${used.used_num}'" class="button6">
		<input type="button" value="삭제" id="delete_btn" class="button7">
		<script type="text/javascript">
			let delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				let choice = confirm('삭제하시겠습니까?');
				if(choice){
					location.replace('delete.do?used_num=${used.used_num}');
				}
			};
		</script>  
		</c:if>
	</div>
	<div class="button-align1">
	<input type="button" value="목록"
		       onclick="location.href='list.do'" class="button8">
	</div>
	
	<!-- 댓글 UI 시작 -->
	<div id="reply_div">
		<span class="re-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="used_num" value="${used.used_num}" id="used_num">
			<textarea rows="3" cols="50" name="reply_content" id="reply_content" class="rep-content"
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
	<div id="loading" style="disply:none;">
		<img src="${pageContext.request.contextPath}/images/loading.gif" width="100" height="100">
	</div>
	<!-- 댓글 UI 끝 -->
</div>