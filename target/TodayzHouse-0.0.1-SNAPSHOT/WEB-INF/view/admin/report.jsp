<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>

<script type="text/javascript">
	$().ready(function() {
		$(".deleteBtn").click(function() {
			var boardId = $(this).parent().parent().parent().find(".boardId").val();
			var articleId = $(this).parent().parent().parent().find(".articleId").val();
			$.get("/TodayzHouse/admin/"+boardId+"/articleDelete/" + articleId
				  , function(response) {
						window.location.reload()
					}
			); 
		})
	})
</script>

<div style="float: left; width: 23%; border-right: 1px solid black; min-height: 500px; margin-left: 3%;">
	<div>
		<h3><a href="/TodayzHouse/admin/1" style="color: #00e6ac;">게시글 관리</a></h3>
	</div>
	
	<div>
		<h3><a href="/TodayzHouse/admin/2">회원 관리</a></h3>
	</div>
</div>

<div style="float: left; width: 60%; margin-left: 5%">
	<table>
		<c:forEach items="${warnArticleList}" var="articles">
			<table style="border-bottom: 1px solid gray; border-top: 1px solid gray;">
				<tr>
					<td><input type="hidden" class="boardId" value="${articles.boardId}" /></td>
					<td><input type="hidden" class="articleId" value="${articles.articleId}" /></td>
				</tr>
				<tr>
					<td style="">${articles.title}</td>
				</tr>
				<tr>
					<td>
						<c:forEach items="${articles.fileVOList}" var="files">
							<img src="/TodayzHouse/board/${files.boardId}/${files.articleId}/download/${files.fileId}" width="20%;"/>
						</c:forEach>
					</td>
					<td>
						<input type="button" class="deleteBtn" value="삭제" />
					</td>
				</tr>
				<tr>
					<td>${articles.fileVOList[0].content}</td>
				</tr>
			</table>
		</c:forEach>
	</table>
</div>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>