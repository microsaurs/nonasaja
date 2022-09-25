<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
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
			<li><h3><a href="myPageUsed.do">중고거래</a></h3></li>
			<li><a href="myPageClub.do">동호회</a></li>
			<li><a href="myPageCommu.do">커뮤니티</a></li>
			<c:if test="${member.root == 0}">
			<li><a href="delete.do">회원탈퇴</a></li>
			</c:if>
		</ul>
	</div>
	<div class="mypage-right">
		<table>
			<tr>
				<th colspan="6"><h3>중고거래</h3></th>
			</tr>
			<tr>
				<td colspan="6">
					<c:if test="${type == 1}">
					<b><a href="myPageUsed.do?type=1">내가 올린 거래</a></b> |
					<a href="myPageUsed.do?type=2">내가 쓴 댓글</a> |
					<a href="myPageUsed.do?type=3">찜한물건</a> 
					</c:if>
					<c:if test="${type == 2}">
					<a href="myPageUsed.do?type=1">내가 올린 거래</a> |
					<b><a href="myPageUsed.do?type=2">내가 쓴 댓글</a></b> |
					<a href="myPageUsed.do?type=3">찜한물건</a>
					</c:if>
					<c:if test="${type == 3}">
					<a href="myPageUsed.do?type=1">내가 올린 거래</a> |
					<a href="myPageUsed.do?type=2">내가 쓴 댓글</a> |
					<b><a href="myPageUsed.do?type=3">찜한물건</a></b> 
					</c:if>
				</td>
			</tr>
			<tr>
				<!-- 내가 올린 거래 -->
				<c:if test="${!empty usedList}">
				<td>사진</td>
				<td>상태</td>
				<td>종류</td>
				<td>카테고리</td>
				<td>제목</td>
				<td>가격</td>
				</c:if>
				<!-- 내가 쓴 댓글 -->
				<c:if test="${!empty usedReplyList}">
				<td>내용</td>
				<td>작성일</td>
				</c:if>
				<!-- 찜한 물건 -->
				<c:if test="${!empty usedFavList}">
				<td>사진</td>
				<td>상태</td>
				<td>종류</td>
				<td>카테고리</td>
				<td>제목</td>
				<td>가격</td>
				</c:if>
				<!-- 1대1대화 -->
				
				<!-- 결과 없음 -->
				<c:if test="${count<=0}">
				<td>
					작성한 글이 없습니다.
				</td>
				</c:if>
			</tr>
			<!-- 내가 올린 거래 -->
			<c:if test="${count>0}">
			<c:if test="${!empty usedList}">
			<c:forEach var="board" items="${usedList}">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/used/detail.do?used_num=${board.used_num}"><img src="${pageContext.request.contextPath}/used/imageView.do?used_num=${board.used_num}&board_type=2" width="50" height="50"></a>
				</td>
				<td>
					<c:if test="${board.status==0}"><font color="green"><strong>판매중</strong></font></c:if>
					<c:if test="${board.status==1}"><font color="orange"><strong>판매완료</strong></font></c:if>
				</td>
				<td>
					<c:if test="${board.kind==0}"><strong>중고거래</strong></c:if>
					<c:if test="${board.kind==2}"><strong>무료나눔</strong></c:if>
					<c:if test="${board.kind==1}"><strong>물물교환</strong></c:if>
				</td>
				<td>
					${board.category}
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/used/detail.do?used_num=${board.used_num}">
						${board.title}
					</a>
				</td>
				<td>
					${board.price}
				</td>
			</tr>
			</c:forEach>
			</c:if>
			</c:if>
			<!-- 내가 쓴 댓글 -->
			<c:if test="${!empty usedReplyList}">
			<c:if test="${count>0}">
			<c:forEach var="reply" items="${usedReplyList}">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/used/detail.do?used_num=${reply.used_num}">${reply.reply_content}</a>
				</td>
				<td>
					${reply.reply_date}
				</td>
			</tr>
			</c:forEach>
			</c:if>
			</c:if>
			<!-- 찜한 물건 -->
			<c:if test="${count>0}">
			<c:if test="${!empty usedFavList}">
			<c:forEach var="board" items="${usedFavList}">
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/used/detail.do?used_num=${board.used_num}"><img src="${pageContext.request.contextPath}/used/imageView.do?used_num=${board.used_num}&board_type=2" width="50" height="50"></a>
				</td>
				<td>
					<c:if test="${board.status==0}"><font color="green"><strong>판매중</strong></font></c:if>
					<c:if test="${board.status==1}"><font color="orange"><strong>판매완료</strong></font></c:if>
				</td>
				<td>
					<c:if test="${board.kind==0}"><strong>중고거래</strong></c:if>
					<c:if test="${board.kind==2}"><strong>무료나눔</strong></c:if>
					<c:if test="${board.kind==1}"><strong>물물교환</strong></c:if>
				</td>
				<td>
					${board.category}
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/used/detail.do?used_num=${board.used_num}">
						${board.title}
					</a>
				</td>
				<td>
					<fmt:formatNumber value="${board.price}"/>원
				</td>
			</tr>
			</c:forEach>
			</c:if>
			</c:if>
			<!-- 1대1대화 -->
			<!-- 결과 없음 -->
			<tr>
				<td colspan="6" class="align-center">${page}</td>
			</tr>
		</table>
	</div>
</div>
<!-- 내용 끝 -->