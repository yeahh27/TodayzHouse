<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"> 
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script> 
<script type="text/javascript">
	$().ready(function() {
		var slider = $('.bxslider').bxSlider({ 
			mode: 'fade',
			auto: false,
			controls: true,
			autoControls: true,
			page: true,
			pause: 3000,
			autoDelay: 0,
			slideWidth: 250,
			speed: 500,
			stopAutoOnClick: true
		});
		 
	})	
</script>

	<h2><a href="/TodayzHouse/board/1/">사진</a>BEST3</h2>
	
	<div>
		<c:forEach items="${best1Articles}" var="articles" varStatus="a">
			<div class="home__slider" >
				<c:if test="${a.index eq 0}" >
					<div class="bxslider" >
						<c:forEach items="${articles.fileVOList}" var="files">
							<div class="">
								<img src="/TodayzHouse/board/${files.boardId}/${files.articleId}/download/${files.fileId}" >
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<c:if test="${a.index eq 1}" >
					<div class="bxslider">
						<c:forEach items="${articles.fileVOList}" var="files">
							<div class="">
								<img src="/TodayzHouse/board/${files.boardId}/${files.articleId}/download/${files.fileId}" >
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<c:if test="${a.index eq 2}" >
					<div class="bxslider">
						<c:forEach items="${articles.fileVOList}" var="files">
							<div class="">
								<img src="/TodayzHouse/board/${files.boardId}/${files.articleId}/download/${files.fileId}" >
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
	
	<h2><a href="/TodayzHouse/board/2/">집들이</a>BEST3</h2>
	
	<div>
		<c:forEach items="${best2Articles}" var="articles" varStatus="a">
			<div class="home__slider" >
				<c:if test="${a.index eq 0}" >
					<div class="bxslider" >
						<c:forEach items="${articles.fileVOList}" var="files">
							<div class="">
								<img src="/TodayzHouse/board/${files.boardId}/${files.articleId}/download/${files.fileId}" >
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<c:if test="${a.index eq 1}" >
					<div class="bxslider">
						<c:forEach items="${articles.fileVOList}" var="files">
							<div class="">
								<img src="/TodayzHouse/board/${files.boardId}/${files.articleId}/download/${files.fileId}" >
							</div>
						</c:forEach>
					</div>
				</c:if>
				
				<c:if test="${a.index eq 2}" >
					<div class="bxslider">
						<c:forEach items="${articles.fileVOList}" var="files">
							<div class="">
								<img src="/TodayzHouse/board/${files.boardId}/${files.articleId}/download/${files.fileId}" >
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
	
<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>