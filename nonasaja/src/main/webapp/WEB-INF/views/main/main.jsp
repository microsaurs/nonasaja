<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 메인 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product_list.css">
<div class="page-main">
	<div class="slideshow-container">
		<div class="mySlides fade">
		  <img src="${pageContext.request.contextPath}/images/main1.png" style="width:100%">
		</div>
		
		<div class="mySlides fade">
		  <img src="${pageContext.request.contextPath}/images/main2.jpg" style="width:100%">
		</div>
		
		<div class="mySlides fade">
		  <img src="${pageContext.request.contextPath}/images/main3.jpg" style="width:100%">
		</div>
	</div>
	
	<br>
	<!-- <div id="middle_text">
		진행 중인 공동구매
	</div> -->
	<br>
	<div id="product_main">
		<c:forEach var="product" items="${list }">
			<div class="horizontal-area">
				<a href="${context.request.contextPath}/product/detail.do?product_num=${product.product_num }">
					<img class="view-img2" src="${context.request.contextPath}/product/imageView.do?product_num=${product.product_num}&photo_type=1">
					<br>
					<span id="title2">${product.title}</span>
					<br>
					<span id="price"><fmt:formatNumber value="${product.price2 }"/></span>원
					<br>
					<span>★ ${product.scoreAvg}/5 (리뷰:${product.reviewCount}개)</span>
					<br>
					<span id="req">${product.waitCount}/${product.req_quantity} 명 구매중</span>
					<br>
				</a>
			</div>
		</c:forEach>	
	</div>
	
	<div class="float-clear align-center">
		<input id="list_btn" type="button" value="공동구매 상품 더보기" onclick="location.href='${pageContext.request.contextPath}/product/list.do'">
	</div>
</div>

<script>
	var slideIndex = 0;
	showSlides();
	
	function showSlides() {
	    var i;
	    var slides = document.getElementsByClassName('mySlides');
	    for (i = 0; i < slides.length; i++) {
	       slides[i].style.display = 'none';  
	    }
	    slideIndex++;
	    if (slideIndex > slides.length) {slideIndex = 1}    
	    slides[slideIndex-1].style.display = 'block';  
	    setTimeout(showSlides, 2000); // Change image every 2 seconds
	}
</script>
<!-- 메인 끝  -->