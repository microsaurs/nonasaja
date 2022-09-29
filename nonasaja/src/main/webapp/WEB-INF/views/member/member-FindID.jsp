<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/find.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/find-id.js"></script>
<!-- 내용 시작 -->
<div class="login-wrap">
	<ul>
		<li>
			<div class="logo">
				<div class="logo-img">
					<a href="${pageContext.request.contextPath}/">
						<img src="${pageContext.request.contextPath}/images/logo.png" width="460">
					</a>
				</div>
			</div>
		</li>
		<li>
			<div class="inputs">
				<form action="findID.do" id="find_form" method="get">
				<label for="name">이름</label>
				<input type="text" name="name" id="name"/><br>
				<label for="phone">연락처</label>
				<input type="text" name="phone" id="phone" pattern="[0-9]+"/><br>
				<div class="clear"></div>
				<button id="btn"><span>찾기</span></button>
				</form>
			</div>
			</li>
		</ul>
</div>
<!-- 내용 끝 -->