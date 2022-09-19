<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 내용 시작 -->
<!-- include libraries(jquery,bootstrap) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/club.css">
<!-- <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Noto+Sans+KR&family=Poor+Story&display=swap" rel="stylesheet"> -->
<style>
.ck-editor__editable_inline{
	min-height:400px;
}
</style>
<!-- include ckeditor js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadAdapter.js"></script>
<div class="page-main1">
	<div class="align-center">
	<h2 class="button"><b>동호인 글쓰기</b></h2>
	</div>
	<form:form action="write.do" modelAttribute="clubVO"
	        id="register_form"
	        enctype="multipart/form-data">
	    <form:errors element="div" cssClass="error-color"/>    
		<ul>
			<li>
				<label for="club_code">카테고리</label>
				<form:radiobutton path="club_code" value="1" checked="checked"/>운동
				<form:radiobutton path="club_code" value="2"/>오락
				<form:radiobutton path="club_code" value="3"/>맛집
				<form:radiobutton path="club_code" value="4"/>노래
				<form:radiobutton path="club_code" value="5"/>여행
				<form:radiobutton path="club_code" value="6"/>스터디
				<form:radiobutton path="club_code" value="7"/>기타
				<form:errors path="club_code" 
				             cssClass="error-color"/>
			</li>
			<li style="clear:both;">
				<label for="club_title">제목</label>
				<form:input class="textarea" path="club_title"/>
				<form:errors path="club_title" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label for="club_name">동호회 이름</label>
				<form:input class="textarea" path="club_name"/>
				<form:errors path="club_name" 
				             cssClass="error-color"/>
			</li>
			<li><label for="club_content">상세 내용</label>
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
			<li>
				<label for="club_limit">총 인원수</label>
				<form:input class="textarea" path="club_limit" value="1"/>
				<form:errors path="club_limit" 
				             cssClass="error-color"/>
			</li>
			<li>
				<label>나이</label>
				<form:checkbox path="f_club_age" value="10대" checked="checked"/>10대
				<form:checkbox path="f_club_age" value="20대"/>20대
				<form:checkbox path="f_club_age" value="30대"/>30대
				<form:checkbox path="f_club_age" value="40대"/>40대
				<form:checkbox path="f_club_age" value="50대"/>50대
				<form:checkbox path="f_club_age" value="60대"/>60대
				<form:errors path="f_club_age" 
				             cssClass="error-color"/>
			</li>
			<li style="clear:both;">
				<label for="club_gender">성별</label>
				<form:radiobutton path="club_gender" value="0" checked="checked"/>누구나
				<form:radiobutton path="club_gender" value="1"/>남자만
				<form:radiobutton path="club_gender" value="2"/>여자만
				<form:errors path="club_gender" 
				             cssClass="error-color"/>
			</li>
			<li style="clear:both;">
				<label for="club_recruit">모집여부</label>
				<form:radiobutton path="club_recruit" value="0" checked="checked"/>모집중
				<form:radiobutton path="club_recruit" value="1"/>모집완료
				<form:errors path="club_recruit" 
				             cssClass="error-color"/>
			</li>
	 	    <li style="clear:both;">
				<label for="club_date">모집일정</label>
				<input type="date" name="club_date" id="club_date">
				<form:errors path="club_date" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">대표사진</label>
				<input type="file" name="upload" id="upload">
			</li>	
			<li  style="clear:both;">
			<label for="region_num">판매지역</label>
				<form:input class="textarea"  path="region_num" value="1"/>
				<form:errors path="region_num" 
				             cssClass="error-color"/>
			</li>
			<li>
			<label for="club_code">판매지역</label>
		    	<select name="si" id="si">
					<option value="1">서울특별시</option> 
					<option value="2">부산광역시</option> 
					<option value="3">인천광역시</option> 
					<option value="4">대전광역시</option> 
					<option value="5">울산광역시</option> 
				</select>
			</li>	
		</ul>    
		<div class="align-right">
			<form:button class="button">등록</form:button>
			<input class="button" type="button" value="목록가기"
			            onclick="location.href='list.do'">
		</div>    
	</form:form>
</div>
<!-- 내용 끝 -->