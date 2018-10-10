<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	$().ready(function() {
		
		$(".replyPlus").click(function() {
			var parentID = $(this).closest(".replyHead").children(".replyId").val();
			var parent_html = '<form:form class="replyData" modelAttribute="replyVO"> <input type="hidden" id="parentId" class="parentId" name="parentId" value="' + parentID + '" />';
			
			var plus_html = parent_html + '<div id="replyWrapper">' + $("#replyWrapper").html() + '</div> </form:form>';
			$(this).after(plus_html)
		})
		
		$(".replyHead").on("click", ".replyBtn", function() {
			if($(this).closest("#replyWrapper").find(".content").val() == "") {
				alert("내용을 입력하세요!");
				$(".content").focus();
				return;
			}
			
			$(this).closest("#replyWrapper").closest(".replyData").attr( {
				action: "/TodayzHouse/reply/write",
	            method: "post",
	            enctype: "multipart/form-data"
			} ).submit()
		})
		
		$(".replyHead").on("click", ".replyModify", function() {
			var content_data = $(this).closest(".replyMoDe").closest(".replyHead").find(".content").data("con")
			var modify_content = $('<textarea id="content" name="content" class="content">' + content_data + '</textarea> <input type="button" class="replyModifyBtn" value="수정" />')
			$(this).closest(".replyMoDe").closest(".replyHead").find(".content").remove()
			$(this).closest(".replyMoDe").before(modify_content)
			
			$(this).closest(".replyMoDe").closest(".replyHead").wrap('<form:form class="replyData" modelAttribute="replyVO"></form:form>');
		})
		
		$(".replyHead").on("click", ".replyModifyBtn", function() {
			if($(this).parent(".replyHead").find(".content").val() == "") {
				alert("수정할 내용을 입력하세요!");
				$(".content").focus();
				return;
			}
			
			$.post($(this).parent(".replyHead").find(".replyModify").data("modi")
				   , $(this).parent(".replyHead").closest(".replyData").serialize()
				   , function(response) {
						if(response.status == 'ok') {
							window.location.reload()
						} 
					}
			) 
		}) 
		
	})
</script>

    <div>
		<c:forEach items="${articleVO.replyList}" var="reply">
		<div style="margin-left: ${(reply.level - 1) * 30}px" class="replyHead" >
			<input type="hidden" class="replyId" value="${reply.replyId}" />
			<input type="hidden" class="parentId" value="${reply.parentId}" />
			<input type="hidden" name="token" value="${sessionScope._CSRF_TOKEN_}" />
			<div>${reply.memberVO.name}   ${reply.regDate}</div>
			<div class="content" data-con="${reply.content}">${reply.content}</div>
			<c:if test="${reply.email eq sessionScope._MEMBER_.email }">
				<div class="replyMoDe">
					<a class="replyModify" >수정</a>
					<a href="/TodayzHouse/reply/delete/${reply.boardId}/${reply.articleId}/${reply.replyId}">삭제</a>
				</div>
			</c:if>
			<input type="button" value="+" class="replyPlus" />
		</div>
		</c:forEach>
	</div> 
	
	<form:form class="replyData" modelAttribute="replyVO">
		<div class="replyHead">
			<input type="hidden" id="parentId" class="parentId" name="parentId" value="0" />
			<div id="replyWrapper">
				<input type="hidden" name="token" value="${sessionScope._CSRF_TOKEN_}" />
				<input type="hidden" name="boardId" value="${articleVO.boardId}"/>
				<input type="hidden" name="articleId" value="${articleVO.articleId}"/>
				<textarea id="content" name="content" class="content"></textarea>
				<input type="button" class="replyBtn" value="등록" />
			</div>
		</div>
	</form:form>
    
    <hr/>
	<div>
		<c:if test="${articleVO.email eq sessionScope._MEMBER_.email }">
			<a href="/TodayzHouse/board/${articleVO.boardId}/articleModify/${articleVO.articleId}">수정</a>
			<a href="/TodayzHouse/board/${articleVO.boardId}/articleDelete/${articleVO.articleId}">삭제</a>
		</c:if>
		<a href="/TodayzHouse/board/${articleVO.boardId}">목록</a>
	</div>
	
<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>