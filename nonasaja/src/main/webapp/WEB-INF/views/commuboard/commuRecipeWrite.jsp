<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/recipeWrite.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/recipeWrite.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<style>
.ck-editor__editable_inline{
	min-height:400px;
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
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne"><Strong id="guide_font">레시피 게시판 작성 가이드</Strong><span id="guide_span">원활한 커뮤니티 게시판 운영을 위해 꼭 읽어주세요!</span></button>
				</h2>
				<div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<ul>
							<li id="guide_li">
							레시피 게시판은 누구나 자유롭게 레시피를 게시 하실 수 있습니다. (주제는 자유롭게 올릴 수 있으며, 도배글 또는 20자 미만의 무성의한 글은 금지입니다.) 
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


<!-- 기본 정보 입력 시작 --><br><br>
<div id="recipe_write">
	<form:form action="write.do" modelAttribute="recipeVO"
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
				<img src="${pageContext.request.contextPath}/images/empty.jpg" style="max-width:800px;" id="img1" name="img1">
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
			<form:button class="button">작성</form:button>
			<input class="button" type="button" value="취소"
			            onclick="location.href='list.do'">
		</div>    
</form:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<!-- 내용 끝 -->