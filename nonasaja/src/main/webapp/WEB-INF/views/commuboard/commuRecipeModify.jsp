<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/recipeWrite.css">
<style>
.ck-editor__editable_inline{
	min-height:400px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>

<div id="recipe_write">
	<h2>레시피 글수정</h2>
	<form:form action="update.do" modelAttribute="boardVO" id="update_form" enctype="multipart/form-data">
	    <form:hidden path="commu_num"/>
	    <form:errors element="div" cssClass="error-color"/>    
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
				<label for="commu_level">난이도</label>
				<form:radiobutton path="commu_level" value="상급"/>상&nbsp&nbsp
				<form:radiobutton path="commu_level" value="중급"/>중&nbsp&nbsp
				<form:radiobutton path="commu_level" value="하급"/>하
				<form:errors path="commu_level" cssClass="error-color"/>
			</li><br>
			
			<p class="title_upload">대표사진</p>
			<li>
				<img src="imageView.do?commu_num=${boardVO.commu_num}&board_type=2" style="max-width:800px;">
			</li>
			<li id="recipe_upload">
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
			</li>
			<c:if test="${!empty boardVO.filename}">
				<div id="file_detail">
					<input type="button" value="파일삭제"
					                      id="file_del">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{commu_num:${recipeVO.commu_num}},
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
				</c:if>
			<p class="title_upload">레시피 과정</p>
			<li>
				<img id="recipe_photo" src="imageView.do?commu_num=${boardVO.commu_num}&board_type=3" style="max-width:500px;">
				<form:textarea id="recipe_textarea" rows="4" cols="70" path="commu_content" placeholder="레시피 1단계" />
				<form:errors path="commu_content" cssClass="error-color"/>
			</li>
			<li id="recipe_upload">
				<input type="file" name="upload2" id="upload2" accept="image/gif,image/png,image/jpeg">
			</li>
			<c:if test="${!empty boardVO.filename2}">
				<div id="file_detail">
					<input type="button" value="파일삭제"
					                      id="file_del2">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del2').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{commu_num:${recipeVO.commu_num}},
									type:'post',
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail2').hide();
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
				</c:if>			
			<li>
				<img id="recipe_photo" src="imageView.do?commu_num=${boardVO.commu_num}&board_type=4" style="max-width:500px;">
				<form:textarea  rows="4" cols="70" path="commu_content2" placeholder="레피시 2단계" />
				<form:errors path="commu_content2" cssClass="error-color"/>
			</li>
			<li id="recipe_upload">
				<input type="file" name="upload3" id="upload3" accept="image/gif,image/png,image/jpeg">
			</li>
			<c:if test="${!empty boardVO.filename3}">
				<div id="file_detail">
					<input type="button" value="파일삭제"
					                      id="file_del3">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del3').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{commu_num:${recipeVO.commu_num}},
									type:'post',
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail3').hide();
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
				</c:if>
			<li>
				<img id="recipe_photo" src="imageView.do?commu_num=${boardVO.commu_num}&board_type=5" style="max-width:500px;">
				<form:textarea  rows="4" cols="70" path="commu_content3" placeholder="레피시 3단계" />
				<form:errors path="commu_content3" cssClass="error-color"/>
			</li>
			<li id="recipe_upload">
				<input type="file" name="upload4" id="upload4" accept="image/gif,image/png,image/jpeg">
			</li>
			<c:if test="${!empty boardVO.filename4}">
				<div id="file_detail">
					<input type="button" value="파일삭제"
					                      id="file_del4">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del4').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{commu_num:${recipeVO.commu_num}},
									type:'post',
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail4').hide();
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
				</c:if>
			<li>
				<img id="recipe_photo" src="imageView.do?commu_num=${boardVO.commu_num}&board_type=6" style="max-width:500px;">
				<form:textarea  rows="4" cols="70" path="commu_content4" placeholder="레피시 4단계" />
				<form:errors path="commu_content4" cssClass="error-color"/>
			</li>
			<li id="recipe_upload">
				<input type="file" name="upload5" id="upload5" accept="image/gif,image/png,image/jpeg">
			</li>
			<c:if test="${!empty boardVO.filename5}">
				<div id="file_detail">
					<input type="button" value="파일삭제"
					                      id="file_del5">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del5').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{commu_num:${recipeVO.commu_num}},
									type:'post',
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail5').hide();
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
				</c:if>
			<li>
				<img id="recipe_photo" src="imageView.do?commu_num=${boardVO.commu_num}&board_type=7" style="max-width:500px;">
				<form:textarea  rows="4" cols="70" path="commu_content5" placeholder="레피시 5단계" />
				<form:errors path="commu_content5" cssClass="error-color"/>
			</li>
			<li id="recipe_upload">
				<input type="file" name="upload6" id="upload6" accept="image/gif,image/png,image/jpeg">
			</li>
			<c:if test="${!empty boardVO.filename6}">
				<div id="file_detail">
					<input type="button" value="파일삭제"
					                      id="file_del6">
				</div>
				<script type="text/javascript">
					$(function(){
						$('#file_del6').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									data:{commu_num:${recipeVO.commu_num}},
									type:'post',
									dataType:'json',
									cache:false,
									timeout:30000,
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요!');
										}else if(param.result == 'success'){
											$('#file_detail6').hide();
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
				</c:if>			
			
			
			
			
			
			<!-- <li><b>조리법</b></li> -->
			<li>
				<!-- <script>
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
			    </script>  -->              
			</li>
			
		</ul>    
		<div class="align-center">
			<form:button>전송</form:button>
			<input type="button" value="목록"
			            onclick="location.href='list.do'">
		</div>    
	</form:form>
</div>
<!-- 내용 끝 -->