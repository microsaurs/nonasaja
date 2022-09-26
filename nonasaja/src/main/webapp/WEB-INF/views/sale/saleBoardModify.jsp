<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/saleUpdate.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sale.css">
<style>
.ck-editor__editable_inline{
	min-height:250px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div>
	<h2>글수정</h2>
	<form:form action="update.do" modelAttribute="saleVO"
	        id="update_form"
	        enctype="multipart/form-data">
	    <form:hidden path="board_num"/>    
	    <form:errors element="div" cssClass="error-color"/>    
	    
	    <div>
	    	<div class="float_image">
				<label for="upload">대표사진</label>
				
				<c:if test="${!empty saleVO.imgname}">
					<img class="product-img" src="${context.request.contextPath}/sale/imageView.do?board_num=${saleVO.board_num}" style="max-width:400px;">
				</c:if>
				<c:if test="${empty saleVO.imgname}">	
					<img src="${pageContext.request.contextPath}/images/empty.jpg" class="product-img" id="img2" name="img2" width="350" height="300">
				</c:if>
				
				<input type="file" name="upload" id="upload"  class="location1">
				<div id="file_detail"> (${saleVO.imgname})
					<input type="button" value="파일삭제" id="file_del" class="button15">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{board_num:${saleVO.board_num}},
									type:'post',
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail').hide();
										}else{
											alert('파일 삭제 오류 발생');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								});
							}
						});
					});
				</script>
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
			<li>
				<label for="content">내용</label>
				<form:textarea path="content"/>
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
<!-- 내용 끝 -->