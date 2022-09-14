<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>

	<h2>레시피 글쓰기</h2>
	<form:form action="write.do" modelAttribute="recipeVO" id="register_form" enctype="multipart/form-data">
	    <form:errors element="div" cssClass="error-color"/>    
		<ul>
			<li>
				<label for="commu_title">제목</label>
				<form:input path="commu_title"/>
				<form:errors path="commu_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_food">요리명</label>
				<form:input path="commu_food"/>
				<form:errors path="commu_food" cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_time">소요 시간</label>
				<form:input path="commu_time"/>
				<form:errors path="commu_time" cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_ingredient">필수 재료</label>
				<form:input path="commu_ingredient"/>
				<form:errors path="commu_ingredient" cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_level">난이도</label>
				<form:radiobutton path="commu_level" value="상"/>상&nbsp&nbsp
				<form:radiobutton path="commu_level" value="중"/>중&nbsp&nbsp
				<form:radiobutton path="commu_level" value="하"/>하
				<form:errors path="commu_level" cssClass="error-color"/>
			</li>
			<li>
				<form:textarea path="commu_content"/>
				<form:errors path="commu_content" cssClass="error-color"/>
				<script>
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
			<li>
				<label for="upload">파일업로드</label>
				<input type="file" name="upload" id="upload">
			</li>
			<li>
				<label for="upload2">파일업로드</label>
				<input type="file" name="upload2" id="upload">
			</li>
			<li>
				<label for="upload3">파일업로드</label>
				<input type="file" name="upload3" id="upload">
			</li>
		</ul>    
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="목록"
			            onclick="location.href='list.do'">
		</div>    
	</form:form>
<!-- 내용 끝 -->