<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 내용 시작 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/commuList.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

<div class="page-main">
	
	<div class="align-center">
	<img src="${pageContext.request.contextPath}/images/우리동네커뮤니티.png" width="700px" class="commu-logo">
	</div>
<%-- 	<div class="align-center">
	<a href="commuList.do?keyfield=1"><img id="commuList_img" src="${pageContext.request.contextPath}/images/schedule.png"></a>
	<a href="commuList.do?keyfield=2"><img id="commuList_img" src="${pageContext.request.contextPath}/images/sale.png"></a>
	<a href="commuList.do?keyfield=3"><img id="commuList_img" src="${pageContext.request.contextPath}/images/book.png"></a>
	</div> --%>
	<c:if test="${!empty user}">	          
		<div class="align-center">
	
		<a href="${pageContext.request.contextPath}/commuboard/write.do"><img src="${pageContext.request.contextPath}/images/커뮤니티글쓰기.png" class="writebutton"></a>
		</div>
	</c:if>
		
	<div class="image-align">
	<figure class="category-align"><a href="commuList.do?keyfield=1"><img id="commuList_img" src="${pageContext.request.contextPath}/images/schedule2.png"></a><figcaption><b>자유게시판</b></figcaption></figure>
	<figure class="category-align"><a href="commuList.do?keyfield=2"><img id="commuList_img" src="${pageContext.request.contextPath}/images/sale2.png"></a><figcaption><b>지역소식</b></figcaption></figure>
	<figure class="category-align"><a href="commuList.do?keyfield=3"><img id="commuList_img" src="${pageContext.request.contextPath}/images/book2.png"></a><figcaption><b>자취백과</b></figcaption></figure><br><br><br><br><br>
	</div><br><br>
	
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option> 
					<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>지역</option>
					<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
					<option value="4" <c:if test="${param.keyfield==4}">selected</c:if>>ID+별명</option>
				</select>
			</li>
			<li>
				<input type="search" name="keyword" id="searchbar" value="${param.keyword}">
			</li>
			<li>
				<input type="submit" class="button5" value="찾기">
				<input type="button" class="button4" value="목록" onclick="location.href='list.do'">
			</li>
		</ul><br>
	</form>


</div>
	<c:if test="${count == 0}">
	<div class="result-display">표시할 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
<body>
	<div class="container">
		<table class="table">
			<thead class="table-light">
				<tr>
					<th>카테고리</th>
					<th width="400">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<c:forEach var="board" items="${list}">
			<tbody>
				<tr>
					<td>
					<c:if test="${board.commu_code==1}"><strong>자유</strong></c:if>
					<c:if test="${board.commu_code==2}"><font color="blue"><strong>지역소식</strong></c:if>
					<c:if test="${board.commu_code==3}"><font color="green"><strong>자취백과</strong></c:if>
					</td>
					<td><a href="detail.do?commu_num=${board.commu_num}">${board.commu_title}</a></td>
					<td>
						<!-- 별명이 등록안되어있으면 아이디를 보여줌 -->
						<c:if test="${empty board.nickname}">${board.id}</c:if>
						<c:if test="${!empty board.nickname}">${board.nickname}</c:if>
					</td>
					<td>${board.commu_date}</td>
					<td>${board.commu_hit}</td>
				</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

</body>
	<div class="align-center">${page}</div>
	</c:if>



<!-- 내용 끝 -->