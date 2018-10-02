<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>
	<h1>DETAIL</h1>

	<h2>${articleVO.title}
		<span style="font-size: 10pt">${articleVO.articleId}</span>
	</h2>	
	
	<h3>작성자 : ${articleVO.memberVO.name} (${articleVO.email})</h3>
	
	<c:if test="${not empty articleVO.originFileName}">
		<p>
			<a href="/TodayzHouse/board/${articleVO.boardId}/download/${articleVO.articleId}">
				<img src="/TodayzHouse/board/${articleVO.boardId}/download/${articleVO.articleId}" width="120">
			</a>
		</p>
	</c:if>	
	<div>
		${articleVO.content }
	</div>
	
	<hr/>
	
	<div>
		<c:if test="${articleVO.email eq sessionScope._MEMBER_.email }">
			<a href="/TodayzHouse/board/${articleVO.boardId}/articleModify/${articleVO.articleId}">수정</a>
			<a href="/TodayzHouse/board/${articleVO.boardId}/articleDelete/${articleVO.articleId}">삭제</a>
		</c:if>
		<a href="/TodayzHouse/board/${articleVO.boardId}">목록</a>
	</div>
	
<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>