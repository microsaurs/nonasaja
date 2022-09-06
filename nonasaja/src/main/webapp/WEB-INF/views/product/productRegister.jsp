<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/product.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
<script type="text/javascript" src="https://stackpath.bootstarpcdn.com/bootstrap/3.4.1/js/bootstarp.min.js"></script>
<style>
.ck-editor__editable_inline{
	min-height:250px;
}	
</style>
<!-- ckeditor js 넣기 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>

<div id="product_register">

<!-- !!!config 패키지의 AppConfig에서 인터셉터 설정하기!!! -->
	<p>상품 등록</p>
	<form:form id="register_form" action="register.do" modelAttribute="productVO" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="kind" id="kind_label">카테고리</label>
				<form:radiobutton path="kind" value="0"/>식품
				<form:radiobutton path="kind" value="1"/>생활용품 &nbsp&nbsp| 
				<form:errors path="kind" cssClass="error-color"/> 
				<label for="sub_category" id="sub_label">상품종류</label>
				<form:input path="sub_category" placeholder="과일,정육,냉동,잡곡 등" style="width:400px;"/>
				<form:errors path="sub_category" cssClass="error-color"/>
			</li>
			
			<li>
				<form:input path="title" placeholder="제목 입력" id="title"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload1" id="title_label">대표사진</label>
				<form:errors path="upload1" cssClass="error-color"/>
			</li>
			<li>
				<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img1" name="img1">
				<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img2" name="img2">
				<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img3" name="img3">
			</li>
			<li>				
				<input type="file" name="upload1" id="upload1" accept="image/gif,image/png,image/jpeg">
				<input type="file" name="upload2" id="upload2" accept="image/gif,image/png,image/jpeg">
				<input type="file" name="upload3" id="upload3" accept="image/gif,image/png,image/jpeg">
			</li>
			<li>
				<label for="name"  class="clear">상품명</label>
				<form:input path="name" class="input-box"/><br>
				<form:errors path="name" cssClass="error-color"/>
			</li>
			<li>
				<label for="price1" class="clear">원가</label>
				<form:input path="price1" class="input-box"/><br>
				<form:errors path="price1" cssClass="error-color"/>
			</li>
			<li>
				<label for="price2" class="clear">판매가</label>
				<form:input path="price2" class="input-box"/><br>
				<form:errors path="price2" cssClass="error-color"/>
			</li>
			<li>
				<label for="quantity" class="clear">총 수량</label>
				<form:input path="quantity" class="input-box"/><br>
				<form:errors path="quantity" cssClass="error-color"/>
			</li>
			<li>
				<label for="req_quantity" class="clear">구매요구수량</label>
				<form:input path="req_quantity" class="input-box"/><br>
				<form:errors path="req_quantity" cssClass="error-color"/>
			</li>
			<li>
				<label for="status" class="clear" id="status_label">판매상태</label>
				<form:radiobutton path="status" value="1"/>판매중&nbsp&nbsp
				<form:radiobutton path="status" value="2"/>판매중지<br>
				<form:errors path="status" cssClass="error-color"/>
			</li>
			<li>
				<label for="deadline" class="clear">판매기한</label>
				<input type="date" name="deadline" id="deadline" class="input-box"><br>
				<form:errors path="deadline" cssClass="error-color"/>
			</li>
			<!-- ckeditor 사용 -->
			<li id="detail_li">
				<form:textarea path="detail" placeholder="상품 상세 설명" class="clear" /><br>
				<form:errors path="detail" cssClass="error-color"/>
				<!-- ckeditor가 정보를 읽어오는 script -->
				<script>
					 function MyCustomUploadAdapterPlugin(editor) {
						    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
						        return new UploadAdapter(loader);
						    }
						}
					 
					 ClassicEditor
			            .create( document.querySelector( '#detail' ),{
			            	extraPlugins: [MyCustomUploadAdapterPlugin]
			            })
			            .then( editor => {
							window.editor = editor;
						} )
			            .catch( error => {
			                console.error( error );
			            } );
			    </script>    <!-- end of script -->
			</li>
		</ul>
		
			<p>상품 필수 정보</p>
		<ul>
			<li>
				<label for="quantity_detail" class="clear">포장 단위별 용량,수량,크기</label>
				<form:input path="quantity_detail" class="input-box"/><br>
				<form:errors path="quantity_detail" cssClass="error-color"/>
			</li>
			<li>
				<label for="company" class="clear">생산자</label>
				<form:input path="company" class="input-box"/><br>
				<form:errors path="company" cssClass="error-color"/>
			</li>
			<li>
				<label for="origin" class="clear">원산지</label>
				<form:input path="origin" class="input-box"/><br>
				<form:errors path="origin" cssClass="error-color"/>
			</li>
			<li>
				<label for="exp_date" class="clear">유통기한</label>
				<form:input path="exp_date" class="input-box"/><br>
				<form:errors path="exp_date" cssClass="error-color"/>
			</li>
			<li>
				<label for="storage" class="clear">보관방법 및 취급방법</label>
				<form:input path="storage" class="input-box"/><br>
				<form:errors path="storage" cssClass="error-color"/>
			</li>
			<li>
				<label for="cus_phone" class="clear">소비자 상담 관련 전화번호</label>
				<form:input path="cus_phone" class="input-box"/><br>
				<form:errors path="cus_phone" cssClass="error-color"/>
			</li>	
		</ul>
		<form:button>등록</form:button>
		<input type="button" value="목록" onclick="location.href='list.do'">
	</form:form>
</div>



















