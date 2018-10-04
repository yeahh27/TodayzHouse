<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>

<script type="text/javascript">
	$().ready(function() {
		var actionPath = "";
		if($("#title").val() == "") {
			actionPath = "/TodayzHouse/board/${boardId}/articleWrite"
		} else {
			actionPath = "/TodayzHouse/board/${boardId}/articleModify/${articleVO.articleId}"
		}
		
		$(".sendBtn").click(function() {
			
			if($("#title").val() == "") {
				alert("제목을 입력하세요!");
				$("#title").focus();
				return;
			}
			
			if($("#content").val() == "") {
				alert("내용을 입력하세요!");
				$("#content").focus();
				return;
			}
			
			$("#writeData").attr( {
				action: actionPath,
	            method: "post",
	            enctype: "multipart/form-data"
			} ).submit()
		})
		
		$("#file").change(function() {
			if(this.files && this.files[0]) {
				var reader = new FileReader();
				
				reader.onload = function(e) {
					$("#img_section").attr({
												'src': e.target.result,
												'width' : 200
											}
					);
				}
				
				reader.readAsDataURL(this.files[0]);
			}
		})
	
	})
</script>

	<h1>WRITE1</h1>
	<form:form id="writeData" modelAttribute="articleVO" enctype="multipart/form-data">
	<input type="hidden" name="token" value="${sessionScope._CSRF_TOKEN_}" />
	<div>
		<input type="hidden" name="boardId" id="boardId" value="${boardId}" />
	</div>
	<div>
		<input type="text" name="title" id="title" placeholder="TITLE" value="${articleVO.title}" />
		<div class="errors">
			<form:errors path="title" />
		</div>
	</div>
	<div>
		<textarea name="content" id="content" placeholder="CONTENT">${articleVO.fileVO.content}</textarea>
	</div>
	<div>
		<img id="img_section" src="/TodayzHouse/board/${articleVO.boardId}/download/${articleVO.articleId}" width="120">
		<input type="file" id="file" name="file" multiple="multiple" placeholder="Choose File" />
	</div>
	<div>
		<input type="button" class="sendBtn" value="Send" />
	</div>
	</form:form>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>