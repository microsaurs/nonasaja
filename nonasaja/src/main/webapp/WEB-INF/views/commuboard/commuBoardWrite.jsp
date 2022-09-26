<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/commuWrite.css">

<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<!-- 작성 가이드 시작 -->
<br>
		<div class="container">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingOne">
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne"><Strong id="guide_font">커뮤니티 게시판 작성 가이드</Strong><span id="guide_span">원활한 커뮤니티 게시판 운영을 위해 꼭 읽어주세요!</span></button>
				</h2>
				<div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<ul>
							<li id="guide_li">
							자유게시판은 누구나 자유롭게 게시를 하실 수 있습니다. (주제는 자유롭게 올릴 수 있으며, 도배글 또는 20자 미만의 무성의한 글은 금지입니다.) 
							</li>
							<li id="guide_li">
							아래 유형에 해당하는 게시글은 작성이 금지되며, 사전 안내 없이 삭제되거나 서비스 이용이 제한될 수 있습니다.
							</li>
							<li id="guide_li">
							본인이 아닌 다른 사람의 개인정보를 이용하여 작성한 글
							</li>
							<li id="guide_li">
							정상적인 거래 범주를 벗어난 대량게시글, 연속작성글, 동일게시글의 반복 작성글 등
							</li>
							<li id="guide_li">
							서비스를 홍보나 영리 목적으로 이용하는 경우
							</li>
							<li id="guide_li">
							저작권을 침해하거나 불법적인 내용을 담고 있는 글
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br><br>
<!-- 작성 가이드 끝 -->

<!-- 기본 정보 입력 시작 -->
	<form:form action="write.do" modelAttribute="communityVO"
	        id="register_form"
	        enctype="multipart/form-data">
	    <form:errors element="div" cssClass="error-color"/>    
		<div class="container">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingTwo">
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo"><Strong id="guide_font">기본 정보 입력</Strong><span id="guide_span">회원들과의 원활한 소통을 위해 입력해주세요!</span></button>
				</h2>
				<div id="collapseTwo" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
					<div class="accordion-body">
		<ul>
			<li>
				<label for="commu_code">카테고리&emsp;</label>
				<form:radiobutton  path="commu_code" value="1" checked="checked"/>자유
				<form:radiobutton path="commu_code" value="2"/>지역소식
				<form:radiobutton path="commu_code" value="3"/>자취백과
				<form:errors path="commu_code" 
				             cssClass="error-color"/>
			</li><br>
			<li>
				<label for="commu_title">제목</label>
				<form:input path="commu_title" id="commu_title"/>
				<form:errors path="commu_title" 
				             cssClass="error-color"/>
			</li>
		</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br><br>
<!-- 기본 정보 입력 끝 -->

<!-- 지역 정보 입력 시작 -->
		<div class="container">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingThree">
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree"><Strong id="guide_font">지역 정보 입력</Strong><span id="guide_span">지역소식 선택시 지역정보를 입력해주세요!</span></button>
				</h2>
				<div id="collapseThree" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
					<div class="accordion-body">
		<ul>
			<li>
				<label for="region">지역 정보&emsp;</label>
				<form:input path="region" placeholder=" 예)ㅇㅇ시ㅇㅇ구ㅇㅇ동" id="region"/>
				<form:errors path="region" 
				             cssClass="error-color"/>
			</li>
		</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br><br>
<!-- 지역 세일 정보 입력 끝 -->

<!-- ckeditor 시작 -->

				<form:textarea path="commu_content" placeholder="내용을 입력해주세요."/>
				<form:errors path="commu_content" 
				             cssClass="error-color"/>
				<script>
				
			/* region 넣어 */
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#commu_content' ),{
		            	extraPlugins: [MyCustomUploadAdapterPlugin]
		            })
		            .then( editor => {
						window.editor = editor;
					} )
		            .catch( error => {
		                console.error( error );
		            } );
			    </script> <br>              

		<div class="align-center">
			<form:button class="button">작성</form:button>
			<input class="button" type="button" value="취소"
			            onclick="location.href='list.do'">
		</div>
<!-- ckeditor 끝 -->			
	</form:form><br>
	<br>	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<!-- 내용 끝 -->