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
	})
</script>

	<h1>WRITE2</h1>
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
		<textarea name="content" id="content" placeholder="CONTENT">${articleVO.content}</textarea>
		<div class="errors">
			<form:errors path="content" />
		</div>
	</div>
	<div>
		<input type="file" id="file" name="file" multiple="multiple" placeholder="Choose File"  />
		<input type="button" class="plusBtn" value="+" />
	</div>
	<div>
		<input type="button" class="sendBtn" value="Send" />
	</div>
	</form:form>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>