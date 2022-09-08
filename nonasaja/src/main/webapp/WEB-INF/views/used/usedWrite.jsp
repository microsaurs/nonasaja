<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
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

<div id="page-main">
	<h2>중고거래 상품 등록</h2>
	<form:form action="write.do" modelAttribute="usedVO" id="register_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img1" name="img1">
				<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img2" name="img2">
				<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img3" name="img3">
			</li>
			<li>				
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
				<input type="file" name="upload2" id="upload" accept="image/gif,image/png,image/jpeg">
				<input type="file" name="upload3" id="upload" accept="image/gif,image/png,image/jpeg">
			</li>
			<li>
				<label for="kind">거래종류</label>
				<form:radiobutton path="kind" value="0"/>중고
				<form:radiobutton path="kind" value="1"/>무료나눔
				<form:radiobutton path="kind" value="2"/>교환
				<form:errors path="kind" cssClass="error-color"/>
			</li>
			<li>
				<label for="category">상품종류</label>
				<form:input path="category" placeholder="주방,생활,식품 등"/>
				<form:errors path="category" cssClass="error-color"/>
			</li>
			<li>
				<label for="price">가격</label>
				<form:input path="price"/>
				<form:errors path="price" cssClass="error-color"/>
			</li>
			<li>
				<label for="status">거래상태</label>
				<form:radiobutton path="status" value="0"/>판매중
				<form:radiobutton path="status" value="1"/>판매완료
				<form:errors path="status" cssClass="error-color"/>
			</li>
			<li>
				<label for="trade">교환물품</label>
				<form:input path="trade"/>
				<form:errors path="trade" cssClass="error-color"/>
			</li>
			<li>
				<label for="region_num">판매지역</label>
					<form:input path="region_num"/>
					<form:errors path="region_num" cssClass="error-color"/>
			</li>
			<li><b>내용</b></li>
			<li>
				<form:textarea path="content"/>
				<form:errors path="content" cssClass="error-color"/>
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

			
		</ul>
		<div class="align-center">
			<form:button>등록</form:button>
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>
<!-- 내용 끝 -->