<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안내</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/alert.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="login-wrap">
	<ul>
		<li>
			<div class="logo">
				<div class="logo-img">
					<img src="${pageContext.request.contextPath}/images/노나사자대표이미지.png" width="200" height="200">
				</div>
				<div class="logo-font">
					<span class="nona">노나</span><span class="saja">사자</span>
				</div>
			</div>
		</li>
		<li>
			<div class="alert-div">
				<span>
				<c:if test="${!empty accessMsg}">
					${accessMsg}
				</c:if>
				<c:if test="${empty accessMsg}">
					잘못된 접속입니다.
				</c:if>
				</span><br>
				<p>
				<c:if test="${!empty accessUrl}">
					<input type="button" value="${accessBtn}"
					  onclick="location.href='${accessUrl}'">	
				</c:if>
				<c:if test="${empty accessUrl}">
					<input type="button" value="확인"
					  onclick="location.href='${pageContext.request.contextPath}/main/main.do'">	
				</c:if>
			</div>
		</li>
	</ul>
</div>
</body>
</html>





