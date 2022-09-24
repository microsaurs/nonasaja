<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/used.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>

<div class="page-main">
	
	<div class="align-center">
	<img src="${pageContext.request.contextPath}/images/우리동네중고거래.png" width="700px">
	
	</div>
	
	<div class="writebutton-float">
		<c:if test="${!empty user}">
		<a href="${pageContext.request.contextPath}/used/write.do"><img src="${pageContext.request.contextPath}/images/writebutton.png" class="writebutton"></a>
		</c:if>
		<!-- kind 이미지 클릭 시 keyfield 검색 -->
		<div class="image-align">
		<figure class="category-align"><a href="list.do?keyfield=0"><img id="commuList_img" src="${pageContext.request.contextPath}/images/schedule2.png"></a><figcaption><b>중고거래</b></figcaption></figure>
		<figure class="category-align"><a href="list.do?keyfield=1"><img id="commuList_img" src="${pageContext.request.contextPath}/images/sale2.png"></a><figcaption><b>무료나눔</b></figcaption></figure>
		<figure class="category-align"><a href="list.do?keyfield=2"><img id="commuList_img" src="${pageContext.request.contextPath}/images/book2.png"></a><figcaption><b>물물교환</b></figcaption></figure><br><br><br><br><br>
		</div><br><br>
		
		<!-- 검색창 keyfield+keyword 검색 -->
		<form action="list.do" id="search_form1" method="get">
			<ul class="search-align">
				<li>
					<select name="keyfield" id="keyfield">
						<option value="" <c:if test="${param.keyfield==null}">selected</c:if>>전체</option>
						<option value="0" <c:if test="${param.keyfield==0}">selected</c:if>>중고거래</option> 
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>무료나눔</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>물물교환</option>
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
				<div class="status-img-float1">
				<c:if test="${used.status==0}"><img src="${pageContext.request.contextPath}/images/노나사자-판매중.png" width="100px" height="45px"></c:if>
				<c:if test="${used.status==1}"><img src="${pageContext.request.contextPath}/images/노나사자-판매완료.png" width="100px" height="45px"></c:if>
				</div>
				<div class="status-img-float1">
				<c:if test="${used.kind==0}"><img src="${pageContext.request.contextPath}/images/노나사자-중고거래.png" width="100px" height="45px"></c:if>
				<c:if test="${used.kind==1}"><img src="${pageContext.request.contextPath}/images/노나사자-무료나눔.png" width="100px" height="45px"></c:if>
				<c:if test="${used.kind==2}"><img src="${pageContext.request.contextPath}/images/노나사자-물물교환.png" width="100px" height="45px"></c:if>
				</div>
				<br>
				<div class="horizontal-area">
				<a href="detail.do?used_num=${used.used_num}">
					<img id ="usedproduct_photo" src="${pageContext.request.contextPath}/used/imageView.do?used_num=${used.used_num}&board_type=2">
					<br>
					<span>${used.title}</span>
					<br>
					<span>
					<c:if test="${used.price!=0 && used.price!=1}">
						<b><fmt:formatNumber value="${used.price}"/>원</b>
					</c:if>
					<c:if test="${used.price==0}">
						<b>무료나눔&#x1f49b;</b>
					</c:if>
					<c:if test="${used.price==1}">
						<b>물물교환&#x1f499;</b>
					</c:if>
					</span>
					<br>
					<span id="usedproduct_region">${used.region}</span>
				</a>
				</div>
			</div>
		</c:forEach>

	<div class="float-clear align-center">${page}</div>
	</div>
	</c:if>
</div>
<!-- 내용 끝 -->