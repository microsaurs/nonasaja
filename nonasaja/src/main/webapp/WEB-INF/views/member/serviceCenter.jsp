<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 내용 시작 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/serviceCenter.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/serviceCenter.js"></script>
<div class="serviceCenter-wrap">
	<div class="faq">
		<div class="search-full">
			<div class="font">
				<span>FAQ</span>
			</div>
			<div class="search">
				<form action="serviceCenter.do" method="get" id="search_form">
					<input type="search" placeholder="궁금한 점을 검색해 보세요">
					<button type="submit" id="searchicon1"><img src="${pageContext.request.contextPath}/images/돋보기.png" width="20" height="25"></button>
				</form>
				<div class="quick-search">
					<a href="#">#구매</a>
					<a href="#">#충전</a>
					<a href="#">#배송지</a>
					<a href="#">#환불</a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<h3 class="question">자주묻는 질문</h3>
		<div class="buttons">
			<ul class="boxes">
				<li>
					<div class="box" id="box1">
						<a href="serviceCenter.do?type=1">공동구매</a>
					</div>
				</li>
				<li>
					<div class="box" id="box2">
						<a href="serviceCenter.do?type=2">포인트</a>
					</div>
				</li>
				<li>
					<div class="box" id="box3">
						<a href="serviceCenter.do?type=3">세일정보</a>
					</div>
				</li>
				<li>
					<div class="box" id="box4">
						<a href="serviceCenter.do?type=4">중고거래</a>
					</div>
				</li>
				<li>
					<div class="box" id="box5">
						<a href="serviceCenter.do?type=5">동호회</a>
					</div>
				</li>
				<li>
					<div class="box" id="box6">
						<a href="serviceCenter.do?type=6">커뮤니티</a>
					</div>
				</li>
				<li>
					<div class="box" id="box7">
						<a href="serviceCenter.do?type=7">레시피</a>
					</div>
				</li>
				<li>
					<div class="box" id="box8">
						<strong><a href="serviceCenter.do?type=8">계정</a></strong>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
	<div class="summary">
		<c:if test="${empty param.type or param.type==1}">
		<details>
			<summary>공동구매에 어떻게 참여하나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>구매 신청했는데 구매 확정 처리가 안됩니다.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>한 사람이 확정 수량만큼 구매시 바로 배송되나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>판매처가 누구인가요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>회원정보에 저장된 주소말고 다른 주소에 배송 받을 수 있나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==2}">
		<details>
			<summary>사자머니 충전은 어떻게 하나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>사자머니 충전은 카카오 페이만 가능한가요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>배송 받아보고 환불이 가능한가요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>화장실 다녀온사이 저희집 고양이가 결제를 했습니다.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>왜 사자머니인가요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==3}">
		<details>
			<summary>세일 정보가 업로드 되었는데 적용이 되지않아요.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>세일 정보가 유용하지 않아요.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>우리동네 세일 정보를 업로드해도 되나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>할인폭이 너무 적어요!</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==4}">
		<details>
			<summary>중고거래는 어떻게 진행하나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>중고거래 사기는 어떻게 보상해주나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>판매자가 불친절해요!</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>안전 거래하는 방법이 있나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>상대방 연락이 끊어졌어요.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==5}">
		<details>
			<summary>동호회 가입하는 방법이 뭔가요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>동호회에서 공지도 없이 강퇴당했어요!</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>잘못들어간 동호회에서 자꾸 연락 와요!</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>동호회 인원 모집을 효과적으로 하고싶어요!</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>동호회 종류가 너무 적어요!</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==6}">
		<details>
			<summary>불건전한 게시글이 자꾸 올라와요!</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>정치글 써도 되나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>제가 글 올릴 때마다 매번 특정회원이 악성댓글을 달아요.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>사자머니 충전은 어떻게 하나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>사자머니 충전은 어떻게 하나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==7}">
		<details>
			<summary>나만의 레시피를 올려도 되나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>특정회원이 제 레시피를 불펌했어요.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>레시피 따라했는데 맛이 이상해요!</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>사자머니 충전은 어떻게 하나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>사자머니 충전은 어떻게 하나요?</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==8}">
		<details>
			<summary>회원가입이 안됩니다.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>회원탈퇴 후 재가입이 안됩니다.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>아이디가 기억나지 않아요.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>비밀번호가 기억나지 않아요.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		<details>
			<summary>네이버계정 회원탈퇴하고 싶어요.</summary>
				<div class="hide">
					<p>내용</p>
				</div>
		</details>
		</c:if>
	</div>
</div>
<!-- 내용 끝 -->