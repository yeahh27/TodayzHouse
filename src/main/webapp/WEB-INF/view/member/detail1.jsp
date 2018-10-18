<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>

<div style="display: flex;">
	<div style="display: inline-block; float: left; width: 40%; text-align: center;">
		<img src="/TodayzHouse/img/user.png" width="50%"  />
	</div>
	<div style="display: inline-block; float: left; width: 60%">
		<div>
			<h2>${memberVO.name} (${memberVO.email})</h2>
		</div>
		<div>
			<label>포인트</label>
			<span>${memberVO.point}</span>
			|
			<label>채팅</label>
			<c:choose>
				<c:when test="${memberVO.chatOk eq 1}">
					<span>OK</span>
				</c:when>
				<c:otherwise>
					<span>NO</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<div>
	<nav style="background-color: white;">
		<ul style="border-top: 1px solid gray; padding-top: 10px;">
			<li><a href="/TodayzHouse/member/my/1" style="color: #00e6ac;">프로필</a></li>
			<li><a href="/TodayzHouse/member/my/2">설정</a></li>
		</ul>
	</nav>
</div>

<div style="padding: 15px;">
	<div style="text-align: center;">
		<div style="background-color: #DCDCDC; font-size: 20px; padding: 2px; text-align: center; width: 100%">내가 쓴 글</div>
		<c:choose>
			<c:when test="${not empty articles.my}">
				<c:forEach items="${articles.my}" var="my">
					<div style="padding: 2px;"><a href="/TodayzHouse/read/${my.boardId}/${my.articleId}">${my.title}</a></div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				등록한 게시물이 없습니다 ㅠ.ㅠ
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div style="display: flex;">
	<div style="display: inline-block; float: left; width: 50%; text-align: center; border-right: 1px solid gray;">
		<div style="background-color: #DCDCDC; font-size: 20px; padding: 2px;">좋아요</div>
		<c:choose>
			<c:when test="${not empty articles.recommends}">
				<c:forEach items="${articles.recommends}" var="recommends">
					<div style="padding: 2px;"><a href="/TodayzHouse/read/${recommends.boardId}/${recommends.articleId}">${recommends.title}</a></div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				좋아요 누른 사진이 없습니당..
			</c:otherwise>
		</c:choose>
	</div>
	
	<div style="display: inline-block; float: left; width: 50%; text-align: center;">
		<div style="background-color: #DCDCDC; font-size: 20px; padding: 2px;">싫어요</div>
		<c:choose>
			<c:when test="${not empty articles.reports}">
				<c:forEach items="${articles.reports}" var="reports">
					<div style="padding: 2px;"><a href="/TodayzHouse/read/${reports.boardId}/${reports.articleId}">${reports.title}</a></div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				싫어요 누른 사진이 없습니당 ^____^
			</c:otherwise>
		</c:choose>
	</div>
</div>
<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>