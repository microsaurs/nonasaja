<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/used.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>

<div class="page-main">
	<div class="writebutton-float">
		<c:if test="${!empty user}">
		<a href="${pageContext.request.contextPath}/used/write.do"><img src="${pageContext.request.contextPath}/images/writebutton.png" class="writebutton"></a>
		</c:if>
		<form action="list.do" id="search_form1" method="get">
			<ul class="search-align">
				<li>
					<select name="keyfield" id="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option> 
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>ID+별명</option>
						<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
						<option value="4" <c:if test="${param.keyfield==4}">selected</c:if>>제목+내용</option>
					</select>
				</li>
				<li>
					
					<input type="search" name="keyword" id="searchbar" value="${parma.keyword}">
				</li>
				<li>
					<input type="submit" value="찾기" class="button5">
					<input type="button" value="전체목록" onclick="location.href='list.do'" class="button4">
				</li>
			</ul>
		</form>
	</div>
	
	<c:if test="${count == 0}">
	<div class="result-display1">표시할 게시물이 없습니다.</div>
	</c:if>
	
	<c:if test="${count > 0}">
	<div class="item-space float-clear">
		<c:forEach var="used" items="${list}">
			<div class="status-img-float" >
				<c:if test="${used.status==0}"><img src="${pageContext.request.contextPath}/images/노나사자-판매중.png" width="100px" height="45px"></c:if>
				<c:if test="${used.status==1}"><img src="${pageContext.request.contextPath}/images/노나사자-판매완료.png" width="100px" height="45px"></c:if>
				
				<br>
				<div class="horizontal-area">
				<a href="detail.do?used_num=${used.used_num}">
					<img id ="usedproduct_photo" src="${pageContext.request.contextPath}/used/imageView.do?used_num=${used.used_num}&board_type=2">
					<br>
					<span>${used.title}</span>
					<br>
					<span><b><fmt:formatNumber value="${used.price}"/>원</b></span>
					<br>
					<%--<span id="usedproduct_region">${used.region}</span> --%>
				</a>
				</div>
			</div>
		</c:forEach>

	<div class="float-clear align-center">${page}</div>
	</div>
	</c:if>
</div>
<!-- 내용 끝 -->