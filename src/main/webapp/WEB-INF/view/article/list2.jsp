<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
	<div id="headerWrapper">
		<div class="number header box">글 번호</div><!-- 
		 --><div class="subject header box">제목</div><!-- 
		 --><div class="writer header box">작성자</div><!-- 
		 --><div class="create-date header box">작성일</div>
	</div>

	<c:choose>
		<c:when test="${not empty articleList}">
			<c:forEach items="${articleList}" var="article">
			<div>
				<div class="number box">
					<a href="/TodayzHouse/read/${boardId}/${article.articleId}">
						${article.articleId}
					</a>
				</div><!--  
				 --><div class="subject box">
				 	<a href="/TodayzHouse/read/${boardId}/${article.articleId}">${article.title}</a>
				 </div><!-- 
				 --><div class="writer box">${article.memberVO.name}</div><!-- 
				 --><div class="create-date box">${article.regDate}</div>
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