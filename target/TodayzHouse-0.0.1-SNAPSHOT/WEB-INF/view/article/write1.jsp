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
			
			if($("#file").val() == "" && $("#content").val() == "") {
				alert("파일이나 내용을 입력하세요!");
				return;
			}
			
			$("#writeData").attr( {
				action: actionPath,
	            method: "post",
	            enctype: "multipart/form-data"
			} ).submit()
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
	
		$("#append-tag").click(function() {
	          var tag = $('<input type="text" class="tag" name="tags" placeholder="Tag" style="margin-right:4px;" />');
	          $(this).before( tag );
	          tag.focus();
	     });

	        $(".tags").on("keydown", ".tag", function(e) {
	          if ( e.key == "," ) {
	            if ( $(this).val() != "" ) {
	                $("#append-tag").click();
	            }
	            return false;
	          }
	        });
		
	})
</script>
<div style="text-align: center; vertical-align: middle;">
	<h1>사진 등록</h1>
	<form:form id="writeData" modelAttribute="articleVO" enctype="multipart/form-data">
	<input type="hidden" name="token" value="${sessionScope._CSRF_TOKEN_}" />
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
	<div style="padding-left: 15px; padding-top: 15px;">
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
		<input type="hidden" id="index" name="fileMap['0'].idx" value="0"/>
		<input type="file" id="file" name="fileMap['0'].fileList" name="file" multiple="multiple" accept="image"/>
	</div>
	<div style="padding-left: 15px; padding-top: 15px;">
		<textarea name="fileMap['0'].content" id="content" name="content" placeholder="사진에 대한 설명을 적어주세요 :)" style="width: 60%; height: 50px">${articleVO.fileVOList[0].content}</textarea>	
	</div>
	<!-- <div class="tags">
        <input type="text" class="tag" name="tags" placeholder="Tag" />
        <input type="button" id="append-tag" value="+" />
    </div> -->
	<div style="padding: 15px;">
		<input type="button" class="sendBtn" value="Send" />
		<input type="button" class="sencCBtn" value="취소" />
	</div>
	</form:form>
</div>
<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>