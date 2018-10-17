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
			var imgbox_html = '<div class="imgWrapper"> </div>';
			var index_html = '<input type="hidden" id="index" name="fileMap[' + count + '].idx" value="' + count + '"/>';
			var file_html = '<input type="file" id="file" class="file" name="fileMap[' + count + '].fileList" multiple="multiple" accept="image"/>';
			var content_html = '<textarea name="fileMap['+ count +'].content" id="content" name="content" placeholder="사진에 대한 설명을 적어주세요 :)" style="width: 30%; height: 50px"></textarea>';
			
			var file_duo = '<div class="fileDuo">' + file_html + content_html + '</div>'
			var shadow = '<div class="fileContent">' + imgbox_html + index_html + file_duo + '</div>'
			$(this).before(shadow)
			count++
		})
		
		var ck_files = [];
		$(".contentWrapper").on("change", ".fileContent .file", function(e) {
			var where = $(this).closest(".fileContent").find(".imgWrapper")
			ck_files = [];
			
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);
			
			filesArr.forEach(function(f) {
				ck_files.push(f);
				
				var reader = new FileReader();
				reader.onload = function(e) {
					var img_html = "<img src=\"" + e.target.result + "\" width='200'/>";
					where.append(img_html);
				}
				reader.readAsDataURL(f);
			})
		})
	})
</script>
<div style="text-align: center; vertical-align: middle;">
	<h1>집들이 초대</h1>
	<form:form id="writeData" modelAttribute="articleVO" enctype="multipart/form-data">
	<input type="hidden" name="token" id="token" value="${sessionScope._CSRF_TOKEN_}" />
	<div>
		<input type="hidden" name="boardId" id="boardId" value="${boardId}" />
	</div>
	<div style="padding-left: 15px; padding-top: 15px;">
		<label>제목 </label>
		<input type="text" name="title" id="title" placeholder="TITLE" value="${articleVO.title}" style="width: 30%; height: 20px;"  />
		<div class="errors">
			<form:errors path="title" />
		</div>
	</div>
	<div class="contentWrapper" style="padding-left: 15px; padding-top: 15px;">
		<c:forEach items="${articleVO.fileVOList}" var="files">
			<c:if test="${not empty files.originFileName}">
				<p>
					<a href="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}">
						<img src="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}" width="120">
					</a>
				</p>
			</c:if>	
		</c:forEach>
		
		<div class="fileContent" >
			<div class="imgWrapper">
			</div>
			<input type="hidden" id="index" name="fileMap['0'].idx" value="0"/>
			<div class="fileDuo">
				<input type="file" id="file" class="file" name="fileMap['0'].fileList" multiple="multiple" accept="image"/>
				<textarea name="fileMap['0'].content" id="content" name="content" placeholder="사진에 대한 설명을 적어주세요 :)" style="width: 30%; height: 50px"></textarea>
			</div>
		</div>
		
		<input type="button" class="plusBtn" value="+" />
		<input type="button" class="minBtn" value="--" />
	</div>
	<div style="padding: 15px;">
		<input type="button" class="sendBtn" value="Send" />
		<input type="button" class="sencCBtn" value="취소" />
	</div>
	</form:form>
</div>
<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>