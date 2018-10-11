<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="wrapper">
		<!-- html5에서 새로나온 시멘트태그이고 div를 대체하기 위해서 나온 것 -->
		<header>
			<nav>
				<ul>
					<li><a href="/TodayzHouse/">Home</a></li>
					<li><a href="/TodayzHouse/board/1/">List1</a></li>
					<li><a href="/TodayzHouse/board/2/">List2</a></li>
					<c:choose>
						<c:when test="${empty sessionScope._MEMBER_.name}">
							<li><a href="/TodayzHouse/member/login">Login</a></li>
							<li><a href="/TodayzHouse/member/regist">Regist</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/TodayzHouse/member/logout">Logout</a></li>
							<li class="point">${sessionScope._MEMBER_.name}
								(${sessionScope._MEMBER_.point})</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
		</header>

		<section class="inline">
			<!-- 구역(영역)에다가 -->

			<section>
				<h1>LIST1</h1>
				<div style="text-align: center;">

					<div id="headerWrapper">
						<div class="number header box">글 번호</div>
						<div class="subject header box">제목</div>
						<div class="writer header box">작성자</div>
						<div class="create-date header box">작성일</div>
					</div>

					<c:choose>
						<c:when test="${not empty articleList}">
							<c:forEach items="${articleList}" var="article">
								<div>
									<div class="number box">
										<a href="/TodayzHouse/board/${boardId}/${article.articleId}">
											${article.articleId} </a>
									</div>
									<div class="subject box">
										<a href="/TodayzHouse/board/${boardId}/${article.articleId}">${article.title}</a>
									</div>
									<div class="writer box">${article.memberVO.name}</div>
									<div class="create-date box">${article.regDate}</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div>등록된 게시물이 없습니다.</div>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="padded">
					<form id="searchForm" onsubmit="javascript:movePage(0);">
						<!-- 반드시 id="searchForm" -->
						<div style="text-align: center;">${pagenation}</div>
						<div style="text-align: right; padding-right: 15px">
							<input type="text" name="searchKeyword"
								value="${articleSearchVO.searchKeyword}" /> <a
								href="/TodayzHouse/board/${boardId}/init">검색 초기화</a>
						</div>
					</form>
				</div>

				<hr>
				<a href="/TodayzHouse/board/${boardId}/articleWrite">글쓰기1</a>
			</section>

		</section>

		<footer>
			<!-- 밑에다가 -->
			<hr />
			Footer
		</footer>
	</div>
</body>
</html>