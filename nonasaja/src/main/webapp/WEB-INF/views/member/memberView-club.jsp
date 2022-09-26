<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/clubjoin.js"></script>
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
			<li><h3><a href="myPageClub.do">동호회</a></h3></li>
			<li><a href="myPageCommu.do">커뮤니티</a></li>
			<c:if test="${member.root == 0}">
			<li><a href="delete.do">회원탈퇴</a></li>
			</c:if>
		</ul>
	</div>
	<div class="mypage-right">
		<table>
			<tr>
				<th colspan="5"><h3>동호회</h3></th> 
			</tr>
			<tr  class="club-category-width">
				<td  class="club-category-width">
					<c:if test="${type == 1}">
					<b><a href="myPageClub.do?type=1">참여중</a></b> |
					<a href="myPageClub.do?type=2">찜</a>
					</c:if>
					<c:if test="${type == 2}">
					<a href="myPageClub.do?type=1">참여중</a> |
					<b><a href="myPageClub.do?type=2">찜</a></b>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<!-- 참여중인 동호회 -->
				<c:if test="${!empty clubList}">
				<td class="club-status"><b>모집여부</b></td>
				<td><b>카테고리</b></td>
				<td><b>동호회명</b></td>
				<td><b>동호회제목</b></td>
				</c:if>
				<!-- 찜한 동호회 -->
				<c:if test="${!empty clubFavList}">
				<td><b>모집여부</b></td>
				<td><b>카테고리</b></td>
				<td><b>동호회명</b></td>
				<td><b>동호회제목</b></td>
				</c:if>
				<!-- 결과 없음 -->
				<c:if test="${count<=0}">
				<td>
					가입한 동호회가 없습니다.
				</td>
				</c:if>
			</tr>
				<!-- 참여중인 동호회 -->
			<c:if test="${count>0}">
			<c:if test="${!empty clubList}">
			<c:forEach var="board" items="${clubList}">
			<tr>
				
				<td>
					<c:if test="${board.club_recruit==0}">모집중</c:if>
					<c:if test="${board.club_recruit==1}">모집완료</c:if>
				</td>
				<td>
					<c:if test="${board.club_code==1}"><strong>운동</strong></c:if>
					<c:if test="${board.club_code==2}"><strong>오락</strong></c:if>
					<c:if test="${board.club_code==3}"><strong>맛집</strong></c:if>
					<c:if test="${board.club_code==4}"><strong>노래</strong></c:if>
					<c:if test="${board.club_code==5}"><strong>여행</strong></c:if>
					<c:if test="${board.club_code==6}"><strong>스터디</strong></c:if>
					<c:if test="${board.club_code==7}"><strong>기타</strong></c:if>
				</td>
				<td>
					${board.club_name}
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/clubboard/detail.do?club_num=${board.club_num}">
						${board.club_title}
					</a>
				</td>
				<td>
						<input type="button" class="button444" value="탈퇴" 
						   data-joinnum="${board.join_num}">
				</td>
			</tr>
			</c:forEach>
			</c:if>
			</c:if>
			
			<!-- 찜한 동호회 -->
			<c:if test="${count>0}">
			<c:if test="${!empty clubFavList}">
			<c:forEach var="board" items="${clubFavList}">
			<tr>
				
				<td>
					<c:if test="${board.club_recruit==0}">모집중</c:if>
					<c:if test="${board.club_recruit==1}">모집완료</c:if>
				</td>
				<td>
					<c:if test="${board.club_code==1}"><strong>운동</strong></c:if>
					<c:if test="${board.club_code==2}"><strong>오락</strong></c:if>
					<c:if test="${board.club_code==3}"><strong>맛집</strong></c:if>
					<c:if test="${board.club_code==4}"><strong>노래</strong></c:if>
					<c:if test="${board.club_code==5}"><strong>여행</strong></c:if>
					<c:if test="${board.club_code==6}"><strong>스터디</strong></c:if>
					<c:if test="${board.club_code==7}"><strong>기타</strong></c:if>
				</td>
				<td>
					${board.club_name}
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/clubboard/detail.do?club_num=${board.club_num}">
						${board.club_title}
					</a>
				</td>
				
			</tr>
			</c:forEach>
			</c:if>
			</c:if>
		</table>
	</div>
</div>
<!-- 내용 끝 -->