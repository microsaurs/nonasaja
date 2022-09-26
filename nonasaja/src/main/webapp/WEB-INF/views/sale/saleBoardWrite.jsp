<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/saleWrite.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sale.css">
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div id="page-main">
	<form:form action="saleBoardWrite.do" modelAttribute="saleVO" id="register_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<div>
			<div class="float_image">
				<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img1" name="img1" width="350" height="300">
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg" class="location1">
			</div>
		<div class="float_text"> <br>
			<ul class="ul-text">
			<li>
				<label for="title">제목</label>
				<form:input path="title" placeholder="제목을 입력하세요." class="input-box"/>
				<form:errors path="title" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="deadline">세일 기한</label>
				<input type="date" name="deadline" id="deadline" class="input-box1">
			</li>
			</ul>
			</div>
		</div>
		<br>
		<div class="location2">

				<form:textarea path="content" placeholder="세일 정보를 알려 주세요!"/>
				<form:errors path="content" 
				             cssClass="error-color"/>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#content' ),{
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
		</div>
		
		<div class="align-center">
		<form:button class="button2">등록</form:button>
		<input type="button" value="목록" onclick="location.href='saleBoardList.do'" class="button1">
		</div>
	</form:form>
</div>
