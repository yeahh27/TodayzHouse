<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>

<script type="text/javascript">
	$().ready(function() {
		
		if(${isRecommend}) {
			$("#unrecommend").show();
		} else {
			$("#recommend").show();
		}
		
		if(${isReport}) {
			$("#unreport").show();
		} else {
			$("#report").show();
		}
		
		$("#recommend").click(function() {
			$.post("/TodayzHouse/recommend/${articleVO.boardId}/${articleVO.articleId}"
					, { token:$(".token").val() }
					, function(response) {
						if(response.status == "ok") {
							$("#recommend").hide();
							$("#unrecommend").show();
							$(".recommendCount").text(response.recommendCount);
						}
					}
			)
		})

		$("#unrecommend").click(function() {
			$.post("/TodayzHouse/unrecommend/${articleVO.boardId}/${articleVO.articleId}"
					, { token:$(".token").val() }
					, function(response) {
						if(response.status == "ok") {
							$("#unrecommend").hide();
							$("#recommend").show();
							$(".recommendCount").text(response.recommendCount);
						}
					}
			)
		})
		
		$("#report").click(function() {
			$.post("/TodayzHouse/report/${articleVO.boardId}/${articleVO.articleId}"
					, { token:$(".token").val() }
					, function(response) {
						if(response.status == "ok") {
							$("#report").hide();
							$("#unreport").show();
							$(".reportCount").text(response.reportCount);
						}
					}
			)
		})

		$("#unreport").click(function() {
			$.post("/TodayzHouse/unreport/${articleVO.boardId}/${articleVO.articleId}"
					, { token:$(".token").val() }
					, function(response) {
						if(response.status == "ok") {
							$("#unreport").hide();
							$("#report").show();
							$(".reportCount").text(response.reportCount);
						}
					}
			)
		})
		
		$("#chatBtn").click(function() {
			var email = $("#chatData").find(".email").val();
			var name = $("#chatData").find(".name").val();
			
			window.open("http://127.0.0.1:3000/chat?email="+email +"&name="+name , "new window", "width=400, height=800")
		})
	})
</script>

<input type="hidden" class="token" name="token" value="${sessionScope._CSRF_TOKEN_}" />
	<h1>DETAIL1</h1>
	<h2>${articleVO.title}
		<span style="font-size: 10pt">${articleVO.articleId}</span>
	</h2>	
	
	<h3>작성자 :<c:choose>
					<c:when test="${articleVO.memberVO.chatOk eq 1}">
						<a id="chatBtn">${articleVO.memberVO.name}</a>
						<img src="/TodayzHouse/img/green.png" width="10" />
					</c:when>
					<c:otherwise>
						${articleVO.memberVO.name} <img src="/TodayzHouse/img/red.png" width="10" />
					</c:otherwise>
				</c:choose>
		(${articleVO.email})
	</h3>

	<div id="chatData" name="chatData">
		<input type="hidden" class="email" name="email" value="${sessionScope._MEMBER_.email}"/>
		<input type="hidden" class="name" name="name" value="${sessionScope._MEMBER_.name}"/>
	</div>	
	
	<a id="recommend" style="display: none;" ><img src="/TodayzHouse/img/unrecommend2.png" width="25"></a>
	<a id="unrecommend" style="display: none;"><img src="/TodayzHouse/img/recommend2.png" width="25"></a>
	
	<a id="report" style="display: none;" ><img src="/TodayzHouse/img/report.png" width="30"></a>
	<a id="unreport" style="display: none;"><img src="/TodayzHouse/img/unreport.png" width="30"></a>

	조회수 : ${articleVO.viewCount}
	추천수 : <span class="recommendCount">${articleVO.recommend}</span>
	신고수 : <span class="reportCount">${articleVO.report}</span>
	
	<c:forEach items="${articleVO.fileVOList}" var="files">
		<c:if test="${not empty files.originFileName}">
			<p>
				<a href="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}">
					<img src="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}" width="250">
				</a>
			</p>
		</c:if>	
	</c:forEach>

	<div>
		<h4>${articleVO.fileVOList["0"].content }</h4>
	</div>
	
	<hr/>
<jsp:include page="/WEB-INF/view/common/detail_footer.jsp"/>