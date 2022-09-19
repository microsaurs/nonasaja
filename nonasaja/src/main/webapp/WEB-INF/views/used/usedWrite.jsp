<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/used.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/used.js"></script>
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
	<h2>중고거래 상품 등록</h2><br>
	<form:form action="write.do" modelAttribute="usedVO" id="register_form" enctype="multipart/form-data"> 
		<form:errors element="div" cssClass="error-color"/>
		<div>
			<div class="float_image">
				<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img1" name="img1" width="350" height="300">
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg" class="location1">
			</div>
			<div class="float_text"> <br>
				<ul class="radio-button">
					<li>
						<label for="kind" class="input-box2">거래종류</label>
						<form:radiobutton path="kind" value="0"/>&nbsp;&nbsp;중고&nbsp;&nbsp;
						<form:radiobutton path="kind" value="1"/>&nbsp;&nbsp;무료나눔&nbsp;&nbsp;
						<form:radiobutton path="kind" value="2"/>&nbsp;&nbsp;교환&nbsp;&nbsp;
						<form:errors path="kind" cssClass="error-color"/>
					</li>
					<li>
						<label for="status" class="input-box2">거래상태</label>
						<form:radiobutton path="status" value="0"/>&nbsp;&nbsp;판매중&nbsp;&nbsp;
						<form:radiobutton path="status" value="1"/>&nbsp;&nbsp;판매완료&nbsp;&nbsp;
						<form:errors path="status" cssClass="error-color"/>
					</li>
				</ul><br>
				<ul class="ul-text">
					<li>
						<label for="title">제목</label>
						<form:input path="title" placeholder="제목을 입력하세요." class="input-box"/>
						<form:errors path="title" cssClass="error-color"/>
					</li>
					
					
					<li>
						<label for="category">상품종류</label>
						<form:input path="category" placeholder="주방,생활,식품 등" class="input-box1"/>
						<form:errors path="category" cssClass="error-color"/>
					</li>
					<li>
						<label for="price">가격</label>
						<form:input path="price" placeholder="가격은 1원~99,999,999원 사이로 입력하세요." class="input-box"/>
						<form:errors path="price" cssClass="error-color"/>
					</li>
					
					<li>
						<label for="trade">교환물품</label>
						<form:input path="trade" placeholder="교환일 경우에만 입력하세요." class="input-box1"/>
						<form:errors path="trade" cssClass="error-color"/>
					</li>
					<li>
						<label for="region_num">판매지역</label>
							<form:input path="region_num" class="input-box1"/>
							<form:errors path="region_num" cssClass="error-color"/>
					</li>
				</ul>
			</div>
		</div><br><br>
		<div class="location">
			<form:textarea path="content" placeholder="거래 물품에 대한 내용을 자세하게 작성하세요" class="location"/>
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
		</div>
		
		<div class="align-right">
			<form:button>등록</form:button>
			<input type="button" value="목록" onclick="location.href='list.do'" class="button1">
		</div>
	</form:form>
	<script type="text/javascript">
	    if($('#price').val()==0){
	    	$('#price').val('');
	    }
	</script>
</div>
<!-- 내용 끝 -->