<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/kakaopay.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js"></script>
<div class="page-main">
	<div class="mypage-div">
		<h2>프로필 사진</h2>
		<ul>
			<li>
				<c:if test="${empty member.photo_name}">
				<img src="${pageContext.request.contextPath}/images/face.png" width="200" height="200" class="my-photo">
				</c:if>
				<c:if test="${!empty member.photo_name}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="200" height="200" class="my-photo">
				</c:if>
			</li>
			<li>
				<div class="align-center">
					<input type="button" value="수정"
					                   id="photo_btn">
				</div>
				<div id="photo_choice" style="display:none;">
					<input type="file" id="upload" 
					   accept="image/gif,image/png,image/jpeg"><br>
					<input type="button" value="전송" id="photo_submit">  
					<input type="button" value="취소" id="photo_reset"> 
				</div>
			</li>
		</ul>
		<p class="align-center">
			<%-- 네이버,카카오 회원은 비밀번호변경 불가
				 탈퇴는 네이버,카카오 측에서 연동 해제해야 함 --%>
			<c:if test="${member.root == 0}">
			<input type="button" value="비밀번호변경"
			 onclick="location.href='changePassword.do'">
			<input type="button" value="회원탈퇴"
			 onclick="location.href='delete.do'"> 
			</c:if>
		</p>
	</div>
	<div class="mypage-div">
		<h2>회원상세정보</h2>
		<ul>
			<li>이름 : <span id="name">${member.name}</span></li>
			<li>별명 : ${member.nickname}</li>
			<li>전화번호 : <span id="phone">${member.phone}</span></li>
			<li>이메일 : <span id="email">${member.email}</span></li>
			<li>우편번호 : ${member.zipcode}</li>
			<li>주소 : ${member.addr1}</li>
			<li>상세주소 : ${member.addr2}</li>
			<c:if test="${!empty member.interest}">
			<li>관심사 : ${member.interest}</li>
			</c:if>
			<li>가입날짜 : ${member.reg_date}</li>
			<c:if test="${!empty member.modify_date}">
			<li>정보 수정일 : ${member.modify_date}</li>
			</c:if>
		</ul>
		<div class="align-center">
			<input type="button" value="회원정보수정" onclick="location.href='update.do'">
			<form>
				<input type="radio" name="money" value="5000">5천원
				<input type="radio" name="money" value="10000">1만원
				<input type="radio" name="money" value="30000">3만원
				<input type="radio" name="money" value="50000">5만원
    			<button id="kpay_btn">카카오페이로 결제하기</button>
    		</form>
    		<span>보유 포인트 : <b><fmt:formatNumber value="${member.cash}"/> 원</b></span>
    		<a href="paymentList.do">충전/결제 내역</a>
		</div>
	</div>
</div>
<!-- 내용 끝 -->



