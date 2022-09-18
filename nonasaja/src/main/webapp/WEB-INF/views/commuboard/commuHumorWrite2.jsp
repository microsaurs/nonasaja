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

<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
	<h2>커뮤니티 글쓰기</h2>
	<form:form action="humorwrite.do" modelAttribute="communityVO"
	        id="register_form"
	        enctype="multipart/form-data">
	    <form:errors element="div" cssClass="error-color"/>    
		<ul>
			<li>
				<label for="commu_code">카테고리</label>
				<form:radiobutton  path="commu_code" value="1" checked="checked"/>유머
				<form:radiobutton path="commu_code" value="2"/>자취정보
				<form:radiobutton path="commu_code" value="3"/>내집자랑
				<form:errors path="commu_code" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="commu_title">제목</label>
				<form:input path="commu_title"/>
				<form:errors path="commu_title" 
				             cssClass="error-color"/>
			</li>
			<li><b>내용</b></li>
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