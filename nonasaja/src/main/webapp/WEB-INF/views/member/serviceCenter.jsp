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
					<p>
					상품 상세 페이지에서 '장바구니 담기'를 선택한 후 
					MY페이지-공동구매-장바구니에서 주문하시면 됩니다. 
					상품의 주문 요구 수량이 채워지기 전까지는 결제되지 않습니다. 
					다른 고객들의 주문이 모여 상품의 주문 요구 수량이 채워지면 
					보유하고 계신 사자머니를 사용해 결제됩니다.
					</p>
				</div>
		</details>
		<details>
			<summary>구매 신청했는데 구매 확정 처리가 안됩니다.</summary>
				<div class="hide">
					<p>
					노나사자의 공동구매는 상품별로 주문 요구 수량이 있습니다. 
					여러 고객의 구매 신청이 모여 주문 요구 수량이 채워지면 그때 구매 확정 처리가 되고 보유하신 사자머니로 자동 결제가 됩니다.
				</div>
		</details>
		<details>
			<summary>한 사람이 확정 수량만큼 구매시 바로 배송되나요?</summary>
				<div class="hide">
					<p>네, 구매할 때 구매 요구 수량을 모두 채우시면 바로 결제가 되고 배송이 준비됩니다.</p>
				</div>
		</details>
		<details>
			<summary>판매처가 누구인가요?</summary>
				<div class="hide">
					<p>노나사자는 모든 물건을 매입하여 판매하고 있습니다. 직접 판매처는 노나사자입니다.</p>
				</div>
		</details>
		<details>
			<summary>회원정보에 저장된 주소말고 다른 주소에 배송 받을 수 있나요?</summary>
				<div class="hide">
					<p>구매 신청을 하실 때 배송 주소를 입력할 수 있습니다. 구매 확정이 되기 전 구매 신청 상태인 경우 배송지를 수정하실 수 있습니다.</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==2}">
		<details>
			<summary>사자머니 충전은 어떻게 하나요?</summary>
				<div class="hide">
					<p>사자머니 충전은 MY페이지-충전에서 하실 수 있습니다. 현재 사자머니는 카카오페이로 충전하실 수 있습니다.</p>
				</div>
		</details>
		<details>
			<summary>사자머니 충전은 카카오 페이만 가능한가요?</summary>
				<div class="hide">
					<p>네, 그렇습니다. MY페이지-충전에서 카카오 페이를 이용해 충전하실 수 있습니다.</p>
				</div>
		</details>
		<details>
			<summary>배송 받아보고 환불이 가능한가요?</summary>
				<div class="hide">
					<p>상품에 하자가 있는 경우 환불이 가능합니다. 자세한 문의는 고객센터 전화번호로 연락주시기 바랍니다.</p>
				</div>
		</details>
		<details>
			<summary>화장실 다녀온사이 저희집 고양이가 결제를 했습니다.</summary>
				<div class="hide">
					<p>고양이가 저희 사업장에서 아르바이트를 하는 것으로 결제 대금을 대신할 수 있습니다.</p>
				</div>
		</details>
		<details>
			<summary>왜 사자머니인가요?</summary>
				<div class="hide">
					<p>어서오세요 사자머니!</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==3}">
		<details>
			<summary>세일 정보가 업로드 되었는데 적용이 되지않아요.</summary>
				<div class="hide">
					<p>세일정보는 관리자가 직접 등록하기 때문에 실시간으로 세일 정보가 반영되지 않을 수 있습니다. 이에 관한 문의 사항은 고객센터 전화번호로 연락주시기 바랍니다.</p>
				</div>
		</details>
		<details>
			<summary>세일 정보가 유용하지 않아요.</summary>
				<div class="hide">
					<p>저희도 최선을 다해서 팝니다. 나눠파는게 그렇게 이윤이 남진 않습니다.</p>
				</div>
		</details>
		<details>
			<summary>우리동네 세일 정보를 업로드해도 되나요?</summary>
				<div class="hide">
					<p>세일 정보 게시판은 노나사자에서 판매하는 상품에 대한 세일 정보를 확인하실 수 있는 곳입니다. 해당 게시판은 노나사자 운영자만 글을 등록할 수 있습니다.</p>
				</div>
		</details>
		<details>
			<summary>할인폭이 너무 적어요!</summary>
				<div class="hide">
					<p>나눠 파는 게 쉽지 않습니다... 저희도 포도 한송이씩 팔아서 근근히 삽니다..</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==4}">
		<details>
			<summary>중고거래는 어떻게 진행하나요?</summary>
				<div class="hide">
					<p>판매자가 중고거래 게시판에 글을 올리면 개인 연락처나 댓글을 통해 거래가 진행됩니다. 민감한 개인정보가 노출되지 않도록 유의하세요.</p>
				</div>
		</details>
		<details>
			<summary>중고거래 사기는 어떻게 보상해주나요?</summary>
				<div class="hide">
					<p>저희에게 자세한 정황을 메일로 전달해주시면 야구빠다 들고 직접 찾아가 해결 봐드리겠습니다.</p>
				</div>
		</details>
		<details>
			<summary>판매자가 불친절해요!</summary>
				<div class="hide">
					<p>저런, 살다보면 그런 일도 있는 법입니다.</p>
				</div>
		</details>
		<details>
			<summary>안전 거래하는 방법이 있나요?</summary>
				<div class="hide">
					<p>거래 장소로 나갈 때 안전벨트를 잘 매시기 바랍니다.</p>
				</div>
		</details>
		<details>
			<summary>상대방 연락이 끊어졌어요.</summary>
				<div class="hide">
					<p>저런, 살다보면 그런 일 또한 있는 법입니다.</p>
				</div>
		</details>
		</c:if>
		<c:if test="${param.type==5}">
		<details>
			<summary>동호회 가입하는 방법이 뭔가요?</summary>
				<div class="hide">
					<p>'동호회' 게시판에서 동호회 모집 글을 확인하신 후 글 하단의 '가입하기'을 누르면 동호회에 가입됩니다. </p>
				</div>
		</details>
		<details>
			<summary>동호회에서 공지도 없이 강퇴당했어요!</summary>
				<div class="hide">
					<p>본인의 인성을 되돌아봅시다.</p>
				</div>
		</details>
		<details>
			<summary>잘못들어간 동호회에서 자꾸 연락 와요!</summary>
				<div class="hide">
					<p>노나사자에서는 개인 정보를 모집자에게 전달하지 않습니다. 그거는.. 다른데서 연락이 오는 겁니다..</p>
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