<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main">
	<h2>글쓰기</h2>
	<form:form action="write.do" modelAttribute="clubVO"
	        id="register_form"
	        enctype="multipart/form-data">
	    <form:errors element="div" cssClass="error-color"/>    
		<ul>
			<li>
				<label for="club_code">카테고리</label>
				<form:radiobutton path="club_code" value="1"/>운동
				<form:radiobutton path="club_code" value="2"/>오락
				<form:radiobutton path="club_code" value="3"/>맛집
				<form:radiobutton path="club_code" value="4"/>노래
				<form:radiobutton path="club_code" value="5"/>여행
				<form:radiobutton path="club_code" value="6"/>스터디
				<form:radiobutton path="club_code" value="7"/>기타
				<form:errors path="club_code" 
				             cssClass="error-color"/>
			</li>
			<br>
			<li>
				<label for="club_title">동호회 이름</label>
				<form:input path="club_title"/>
				<form:errors path="club_title" 
				             cssClass="error-color"/>
			</li>
			<br>
			<li><b>상세 내용</b></li><br>
			<li>
				<form:textarea path="club_content"/>
				<form:errors path="club_content" 
				             cssClass="error-color"/>
				<script>
				 function MyCustomUploadAdapterPlugin(editor) {
					    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
					        return new UploadAdapter(loader);
					    }
					}
				 
				 ClassicEditor
		            .create( document.querySelector( '#club_content' ),{
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
			<br>
			<li>
				<label for="club_limit">총 인원수</label>
				<form:input path="club_limit"/>
				<form:errors path="club_limit" 
				             cssClass="error-color"/>
			</li>
			<br>
			<li>
				<label for="club_age">나이</label>
				<form:checkbox path="club_code" value="1" checked="checked"/>누구나
				<form:checkbox path="club_code" value="2"/>20대
				<form:checkbox path="club_code" value="3"/>30대
				<form:checkbox path="club_code" value="4"/>40대
				<form:checkbox path="club_code" value="2"/>50대
				<form:checkbox path="club_code" value="3"/>60대
				<form:errors path="club_age" 
				             cssClass="error-color"/>
			</li>
			<br>
			<li>
				<label for="club_gender">성별</label>
				<form:radiobutton path="club_gender" value="1" checked="checked"/>누구나
				<form:radiobutton path="club_gender" value="2"/>남자만
				<form:radiobutton path="club_gender" value="3"/>여자만
				<form:errors path="club_gender" 
				             cssClass="error-color"/>
			</li>
			<br>
			<li>
				<label for="club_recruit">모집여부</label>
				<form:radiobutton path="club_recruit" value="1" checked="checked"/>모집중
				<form:radiobutton path="club_recruit" value="2"/>모집완료
				<form:errors path="club_recruit" 
				             cssClass="error-color"/>
			</li>
			<br>
	 	    <li>
				<label for="club_date">모집일정</label>
				<input type="date" name="club_date" id="club_date">
				<form:errors path="club_date" cssClass="error-color"/>
			</li>
			<br>
			<li>
				<label for="club_img">대표사진</label>
				<input type="file" name="club_img" id="club_img">
			</li>
			<br>	
			<li>
			<label for="region_num">판매지역</label>
				<form:input path="region_num"/>
				<form:errors path="region_num" 
				             cssClass="error-color"/>
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