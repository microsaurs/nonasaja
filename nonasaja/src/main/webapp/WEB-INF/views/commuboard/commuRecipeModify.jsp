<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/recipeWrite.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/recipeModify.css">
<style>
.ck-editor__editable_inline{
	min-height:400px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>

<!-- 기본 정보 입력 시작 --><br><br>
<div id="recipe_write">
	<form:form action="update.do" modelAttribute="boardVO"
	        id="update_form"
	        enctype="multipart/form-data">
	    <form:hidden path="commu_num"/>	        
	    <form:errors element="div" cssClass="error-color"/>    
		<div class="container">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingTwo">
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo"><Strong id="guide_font">기본 정보 입력</Strong><span id="guide_span">회원들과의 원활한 소통을 위해 꼼꼼하게 입력해주세요!</span></button>
				</h2>
				<div id="collapseTwo" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
					<div class="accordion-body">
		<ul>
			<li>
				<label for="commu_title">제목</label>
				<form:input path="commu_title" class="input_box_title"/>
				<form:errors path="commu_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_food">요리명</label>
				<form:input path="commu_food" class="input_box_food"/>
				<form:errors path="commu_food" cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_time">소요 시간</label>
				<form:input path="commu_time" class="input_box_time"/>
				<form:errors path="commu_time" cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_ingredient">필수 재료</label>
				<form:input path="commu_ingredient" class="input_box_ingredient"/>
				<form:errors path="commu_ingredient" cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_level">난이도&emsp;&emsp;</label>
				<form:radiobutton path="commu_level" value="상급"/>상&nbsp&nbsp
				<form:radiobutton path="commu_level" value="중급"/>중&nbsp&nbsp
				<form:radiobutton path="commu_level" value="하급"/>하
				<form:errors path="commu_level" cssClass="error-color"/>
			</li><br>
		</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br><br>

<!-- 대표사진 입력 시작 -->
		<div class="container">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingThree">
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree"><Strong id="guide_font">대표사진 입력</Strong><span id="guide_span">목록에서 보여질 음식 사진을 선택해주세요!</span></button>
				</h2>
				<div id="collapseThree" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
					<div class="accordion-body">
		<ul>
			<li>
				<img src="imageView.do?commu_num=${boardVO.commu_num}&board_type=2" style="max-width:800px;">
			</li>
			<li id="recipe_upload">
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br><br>
<!-- 대표사진 정보 입력 끝 -->

<!-- 레시피 입력 시작 -->

		<div class="container">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingFour">
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour"><Strong id="guide_font">레시피 입력</Strong><span id="guide_span">레시피를 순서대로 입력해주세요</span></button>
				</h2>
				<div id="collapseFour" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
					<div class="accordion-body">
		<ul><br>
			<li>
				<label for="upload2">1단계</label>
				<input type="file" name="upload2" id="upload2" accept="image/gif,image/png,image/jpeg">
			</li>
			<li>
				<form:textarea id="recipe_textarea" rows="5" cols="90" path="commu_content" placeholder="레시피 1단계" />
				<form:errors path="commu_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload3">2단계</label>
				<input type="file" name="upload3" id="upload3">
			</li>
			<li>
				<form:textarea id="recipe_textarea" rows="5" cols="90" path="commu_content2" placeholder="레시피 2단계" />
				<form:errors path="commu_content2" cssClass="error-color"/>
			</li>	
			<li>
				<label for="upload4">3단계</label>
				<input type="file" name="upload4" id="upload4">
			</li>
			<li>
				<form:textarea id="recipe_textarea" rows="5" cols="90" path="commu_content3" placeholder="레시피 3단계" />
				<form:errors path="commu_content3" cssClass="error-color"/>
			</li>	
			<li>
				<label for="upload5">4단계</label>
				<input type="file" name="upload5" id="upload5">
			</li>
			<li>
				<form:textarea id="recipe_textarea" rows="5" cols="90" path="commu_content4" placeholder="레시피 4단계" />
				<form:errors path="commu_content4" cssClass="error-color"/>
			</li>	
			<li>
				<label for="upload6">5단계</label>
				<input type="file" name="upload6" id="upload6">
			</li>
			<li>
				<form:textarea id="recipe_textarea" rows="5" cols="90" path="commu_content5" placeholder="레시피 5단계" />
				<form:errors path="commu_content5" cssClass="error-color"/>
			</li>	
		</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br><br>
<!-- 레시피 정보 입력 끝 -->

			
             
			
		<div class="align-center">
			<form:button class="button2">수정</form:button>
			<input type="button" value="목록"
			            onclick="location.href='list.do'" class="button1">
		</div>    
</form:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<!-- 내용 끝 -->