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
		
		var count = 1
		
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
	            enctype: "multipart/form-data",
			} ).submit()   
		})
		
		$(".plusBtn").click(function() {
			var index_html = '<input type="hidden" id="index" name="fileMap[' + count + '].idx" value="' + count + '"/>';
			var file_html = '<input type="file" id="file" name="fileMap[' + count + '].fileList" name="file" multiple="multiple" accept="image"/>';
			var content_html = '<textarea name="fileMap['+ count +'].content" id="content" name="content" placeholder="CONTENT"></textarea>';
			
			var file_duo = '<div class="fileDuo">' + file_html + content_html + '</div>'
			var shadow = '<div class="fileContent">' + index_html + file_duo + '</div>'
			$(this).before(shadow)
			count++
		})
		
		var ck_files = [];
		$("#file").change(function(e) {
			ck_files = [];
			$(".imgWrapper").empty();
			
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);
			
			filesArr.forEach(function(f) {
				ck_files.push(f);
				
				var reader = new FileReader();
				reader.onload = function(e) {
					var img_html = "<img src=\"" + e.target.result + "\" width='200'/>";
					$(".imgWrapper").append(img_html);
				}
				reader.readAsDataURL(f);
			})
		}) 
	})
</script>

	<h1>WRITE2</h1>
	<form:form id="writeData" modelAttribute="articleVO" enctype="multipart/form-data">
	<input type="hidden" name="token" id="token" value="${sessionScope._CSRF_TOKEN_}" />
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
		<c:forEach items="${articleVO.fileVOList}" var="files">
			<c:if test="${not empty files.originFileName}">
				<p>
					<a href="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}">
						<img src="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}" width="120">
					</a>
				</p>
			</c:if>	
		</c:forEach>
		
		<div class="imgWrapper">
				<img id="img_section" />
			</div>
		<div class="fileContent" >
			<input type="hidden" id="index" name="fileMap['0'].idx" value="0"/>
			<div class="fileDuo">
				<input type="file" id="file" name="fileMap['0'].fileList" name="file" multiple="multiple" accept="image"/>
				<textarea name="fileMap['0'].content" id="content" name="content" placeholder="CONTENT"></textarea>
			</div>
		</div>
		
		<input type="button" class="plusBtn" value="+" />
	</div>
	<div>
		<input type="button" class="sendBtn" value="Send" />
	</div>
	</form:form>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>