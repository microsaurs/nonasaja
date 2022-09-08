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
	<h2>${used.title}</h2>
	<ul class="detail-info">
		<li>
			<c:if test="${!empty used.photo_name}">
			<img src="imageView.do?used_num=${used.used_num}&board_type=1" width="40" height="40" class="my-photo">
			</c:if>
			<c:if test="${empty used.photo_name}">
			<img src="${pageContext.request.contextPath}/images/face.png" width="40" height="40" class="my-photo">
			</c:if>
		</li>
		<li>
			<c:if test="${empty used.nickname}">${used.id}</c:if>
			<c:if test="${!empty used.nickname}">${used.nickname}</c:if>
			<br>
			<c:if test="${!empty used.modify_date}">
			최근 수정일 : ${used.modify_date}
			</c:if>
			<c:if test="${empty used.modify_date}">
			작성일 : ${used.reg_date}
			</c:if>
			조회 : ${used.hit}
		</li>
	</ul>
	<hr size="1" width="100%">
	<div>
	<!-- 거래종류 -->
	<c:if test="${used.kind==0}">중고</c:if>
	<c:if test="${used.kind==1}">무료나눔</c:if>
	<c:if test="${used.kind==2}">교환</c:if>
	<!-- 거래상태 -->
	<c:if test="${used.kind==0}">거래중</c:if>
	<c:if test="${used.kind==1}">거래완료</c:if>
	</div>
	<ul>
		<li>
		상품종류 : ${used.category}
		</li>
		<li>
		가격 : ${used.price}
		</li>
		<li>
		판매지역 : ${used.region_num}
		</li>
	</ul>
	<c:if test="${fn:endsWith(used.filename,'.jpg') ||
	              fn:endsWith(used.filename,'.JPG') ||
	              fn:endsWith(used.filename,'.jpeg') ||
	              fn:endsWith(used.filename,'.JPEG') ||
	              fn:endsWith(used.filename,'.gif') ||
	              fn:endsWith(used.filename,'.GIF') ||
	              fn:endsWith(used.filename,'.png') ||
	              fn:endsWith(used.filename,'.PNG')}">
	<div class="align-center">
		<img src="imageView.do?used_num=${used.used_num}&board_type=2" style="max-width:500px;">
	</div>
	<div class="align-center">
		<img src="imageView.do?used_num=${used.used_num}&board_type=3" style="max-width:500px;">
	</div>
	<div class="align-center">
		<img src="imageView.do?used_num=${used.used_num}&board_type=4" style="max-width:500px;">
	</div>
	</c:if>
	<p>
		${used.content}
	</p>
	<div>
		<%-- 좋아요 --%>
	</div>
</div>