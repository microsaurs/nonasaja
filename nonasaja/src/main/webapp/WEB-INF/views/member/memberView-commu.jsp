<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypage-commu.js"></script>
<div class="mypage-wrap">
	<div class="mypage-left">
		<ul>
			<li>
				<c:if test="${empty member.photo_name}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="200" height="200" class="my-photo">
				</c:if>
				<c:if test="${!empty member.photo_name}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" height="200" class="my-photo">
				</c:if>
			</li>
			<li id="nick"><span>${member.nickname}</span></li>
			<li id="money">사자머니 &nbsp;&nbsp;&nbsp;<a href="paymentList.do">충전 ></a></li>
			<li><span><fmt:formatNumber value="${member.cash}"/>원</span></li>
		</ul>
		<br>
		<ul>
			<li><a href="${pageContext.request.contextPath}/member/myPage.do">회원정보</a></li>
			<li><a href="myPageProduct.do">공동구매</a></li>
			<li><a href="myPageUsed.do">중고거래</a></li>
			<li><a href="myPageClub.do">동호회</a></li>
			<li><h3><a href="myPageCommu.do">커뮤니티</a></h3></li>
			<c:if test="${member.root == 0}">
			<li><a href="delete.do">회원탈퇴</a></li>
			</c:if>
		</ul>
	</div>
	<div class="mypage-right">
		<table>
			<tr>
				<th colspan="5"><h3>커뮤니티</h3></th>
			</tr>
			<tr>
				<td>
					<c:if test="${type == 1}">
					<b><a href="myPageCommu.do?type=1">내가 작성한 글</a></b> |
					<a href="myPageCommu.do?type=2">내가 작성한 댓글</a>
					</c:if>
					<c:if test="${type == 2}">
					<a href="myPageCommu.do?type=1">내가 작성한 글</a> |
					<b><a href="myPageCommu.do?type=1">내가 작성한 댓글</a></b>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<select id="code">
						<option value="1" <c:if test="${code==1}">selected</c:if>>커뮤니티
						<option value="2" <c:if test="${code==2}">selected</c:if>>레시피
					</select>
				<td>
			</tr>
			<tr>
				<td>사진</td>
				<td>제목</td>
				<td>음식</td>
				<td>추천</td>
				<td>조회수</td>
			</tr>
			<!-- 커뮤니티 목록 시작 -->
			<c:if test="${!empty commuList}">
			<c:if test="${count<=0}">
				작성한 글이 없습니다.
			</c:if>
			<c:if test="${count>0}">
			<c:forEach var="commuboard" items="${commuList}">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/commuRecipe/detail.do?commu_num=${recipeboard.commu_num}">
						<img src="${pageContext.request.contextPath}/commuRecipe/imageView.do?commu_num=${recipeboard.commu_num}&board_type=2" width="50" height="50">
					</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/commuRecipe/detail.do?commu_num=${commuboard.commu_num}">${commuboard.commu_title}</a>
				</td>
				<td>
					${commuboard.commu_code}
				</td>
				<td>
					${commuboard.commu_recommend}
				</td>
				<td>
					${commuboard.commu_hit}
				</td>
			</tr>
			</c:forEach>
			</c:if>
			</c:if>
			<!-- 커뮤니티 목록 끝 -->
			<!-- 레시피 목록 시작 -->
			<c:if test="${!empty recipeList}">
			<c:if test="${count<=0}">
				작성한 글이 없습니다.
			</c:if>
			<c:if test="${count>0}">
			<c:forEach var="recipeboard" items="${recipeList}">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/commuRecipe/detail.do?commu_num=${recipeboard.commu_num}"><img src="${pageContext.request.contextPath}/commuRecipe/imageView.do?commu_num=${recipeboard.commu_num}&board_type=2" width="50" height="50"></a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/commuRecipe/detail.do?commu_num=${recipeboard.commu_num}">${recipeboard.commu_title}</a>
				</td>
				<td>
					${recipeboard.commu_food}
				</td>
				<td>
					${recipeboard.commu_recommend}
				</td>
				<td>
					${recipeboard.commu_hit}
				</td>
			</tr>
			</c:forEach>
			</c:if>
			</c:if>
			<!-- 레시피 목록 끝 -->
		</table>
	</div>
</div>
<!-- 내용 끝 -->