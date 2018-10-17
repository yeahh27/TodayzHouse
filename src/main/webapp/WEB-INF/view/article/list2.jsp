<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>
<style type="text/css">
.box {
	display: inline-block;
	padding-left: 15px;
	text-align: center;
}

.header {
	font-weight: bold;
	text-align: center;
}

.number {
	width: 150px;
}

.subject {
	width: 340px;
}

.writer {
	width: 170px;
}

.create-date {
	width: 170px;
}

a:hover {
    color: #00e6ac;
}
</style>
<input type="hidden" name="token" value="${sessionScope._CSRF_TOKEN_}" />
	<h1>집들이</h1>
	<div style="text-align: center;">
	
	<c:choose>
		<c:when test="${not empty articleList}">
			<c:forEach items="${articleList}" var="article">
			<div style="display: inline-block; width: 33%; margin-bottom: 15px; margin-left: 15px" >
				<div style="position: relative; height: 200px; width: auto;">
					<a href="/TodayzHouse/read/${boardId}/${article.articleId}">
						<img src="/TodayzHouse/board/${article.boardId}/${article.articleId}/download/${article.fileVOList[0].fileId}" 
							 style="vertical-align: middle; max-height: 100%; max-width: 100%"/>
						<img src="/TodayzHouse/img/heart.png" width="14%" style="position: absolute; left: 10px; bottom: 9px" >
						<span style="position: absolute; left: 15px; bottom: 9px; color: black;">${article.recommend}</span>
						<%-- <span style="position: absolute; left: 20px; bottom: 10px; color: black;"><img src="" width="9%" style="padding-right: 9px;">${fn:length(article.replyList)}</span>
						<span style="position: absolute; left: 30px; bottom: 10px; color: black;"><img src="" width="9%" style="padding-right: 9px;">${article.report}</span> --%>
						<span style="position: absolute; right: 10px; bottom: 10px; color: black;">조회수 ${article.viewCount}</span>
					</a>
				</div>
				<div style="margin: 10px;">${article.title}</div>
				<div style="font-size: 15px; margin-bottom: 8px; text-align: left; padding-left: 10%;" >
					<img src="/TodayzHouse/img/user.png" height="20px" style="padding-right: 9px;" />
					<span style="vertical-align: top;">${article.memberVO.name}</span>
				</div>
			</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>
				등록된 게시물이 없습니다.
			</div>
		</c:otherwise>
	</c:choose>
	</div>


	<div class="padded">
		<form id="searchForm" onsubmit="javascript:movePage(0);">
			<!-- 반드시 id="searchForm" -->
			<div style="text-align: center;">${pagenation}</div>
			<div style="text-align: right; padding-right: 15px">
				<input type="text" name="searchKeyword" value="${articleSearchVO.searchKeyword}" />
				<a href="/TodayzHouse/board/${boardId}/init">검색 초기화</a>
			</div>
		</form>
	</div>

	<hr>
	<a href="/TodayzHouse/board/${boardId}/articleWrite">글쓰기</a>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>