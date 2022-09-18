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
	<h2>커뮤니티 글쓰기</h2><br>
<!-- 작성 가이드 시작 -->
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
							에디터의 섭외 없이 작성해주시는 경우엔 확인 후 게시글 오픈이 반려될 수도 있습니다. 오픈 및 반려 여부는 댓글로 안내 드립니다.
							</li>
							<li id="guide_li">
							오픈 및 반려 여부 확인은 작성해주신 시점을 기준으로 일주일-10일 가량 소요되며, 댓글로 결과를 안내 드립니다.
							</li>
							<li id="guide_li">
							간단한 자기 소개 후 집에 관한 이야기를 들려주세요. (집 공간 사진 최소 15장 이상)
							</li>
							<li id="guide_li">
							집 사진/소개글 관련해서 고민이 될 땐 이 링크를 참고해주세요.
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br>
<!-- 작성 가이드 끝 -->

<!-- 기본 정보 입력 시작 -->
	<form:form action="humorwrite.do" modelAttribute="communityVO"
	        id="register_form"
	        enctype="multipart/form-data">
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
				<label for="commu_code">카테고리</label>
				<form:radiobutton  path="commu_code" value="1" checked="checked"/>자유
				<form:radiobutton path="commu_code" value="2"/>지역세일
				<form:radiobutton path="commu_code" value="3"/>자취백과
				<form:errors path="commu_code" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_title">제목</label>
				<form:input path="commu_title"/>
				<form:errors path="commu_title" 
				             cssClass="error-color"/>
			</li>
		</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br>
<!-- 지역 세일 정보 입력 시작 -->
		<div class="container">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingThree">
					<button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree"><Strong id="guide_font">지역 세일 정보 입력</Strong><span id="guide_span">지역세일 선택시 지역정보를 입력해주세요!</span></button>
				</h2>
				<div id="collapseThree" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
					<div class="accordion-body">
		<ul>
			<li>
				<label for="region_num">지역 정보</label>
				<form:input path="region_num"/>
				<form:errors path="region_num" 
				             cssClass="error-color"/>
			</li>
		</ul>
					</div>
				</div>
			</div>
		</div>	<!-- end of parent -->
	</div><!--  end of container -->
	<br>
<!-- 지역 세일 정보 입력 끝 -->

<!-- ckeditor 시작 -->
		<ul>
			<li>
				<form:textarea path="commu_content"/>
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
			    </script>               
			</li>
		</ul>
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="목록"
			            onclick="location.href='list.do'">
		</div> 	
<!-- ckeditor 끝 -->			
	</form:form>
	<br>	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<!-- 내용 끝 -->