<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		
		var recommendCount = $(".recommendCount").text();
		$("#recommend").click(function() {
			$.post("/TodayzHouse/recommend/${articleVO.boardId}/${articleVO.articleId}"
					, { token:$(".token").val() }
					, function(response) {
						if(response.status == "ok") {
							$("#recommend").hide();
							$("#unrecommend").show();
							$(".recommendCount").text(++recommendCount);
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
							$(".recommendCount").text(--recommendCount);
						}
					}
			)
		})
		
		var reportCount = $(".reportCount").text();
		$("#report").click(function() {
			$.post("/TodayzHouse/report/${articleVO.boardId}/${articleVO.articleId}"
					, { token:$(".token").val() }
					, function(response) {
						if(response.status == "ok") {
							$("#report").hide();
							$("#unreport").show();
							$(".reportCount").text(++reportCount);
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
							$(".reportCount").text(--reportCount);
						}
					}
			)
		})
		
		$(".img").click(function() {
			var x = event.offsetX;
			var y = event.offsetY;
			//alert("postition = " + x + " : " + y);
			var tagPlus = $('<img src="/TodayzHouse/img/plus.png" class="overlay" width="20"/>');
			$(this).before(tagPlus)
			$(this).closest(".imgWrapper").find(".overlay").css({
				"position": "absolute",
				"margin-left": x,
				"margin-top": y
			})
		})
	})
</script>

<input type="hidden" class="token" name="token" value="${sessionScope._CSRF_TOKEN_}" />
	<h1>DETAIL2</h1>
	<h2>${articleVO.title}
		<span style="font-size: 10pt">${articleVO.articleId}</span>
	</h2>	
	
	<h3>작성자 : ${articleVO.memberVO.name} (${articleVO.email})</h3>
	
	<a href="#" id="recommend" style="display: none;" ><img src="/TodayzHouse/img/unrecommend2.png" width="25"></a>
	<a href="#" id="unrecommend" style="display: none;"><img src="/TodayzHouse/img/recommend2.png" width="25"></a>
	
	<a href="#" id="report" style="display: none;" ><img src="/TodayzHouse/img/report.png" width="30"></a>
	<a href="#" id="unreport" style="display: none;"><img src="/TodayzHouse/img/unreport.png" width="30"></a>

	조회수 : ${articleVO.viewCount}
	추천수 : <span class="recommendCount">${articleVO.recommend}</span>
	신고수 : <span class="reportCount">${articleVO.report}</span>
	
	<c:forEach items="${requestScope.filesVOMap}" var="filesList" >
		<div>
		<c:forEach items="${filesList.value}" var="files">
			<c:if test="${not empty files.originFileName}">
			<div style="display: inline-block;" class="imgWrapper" >
				<img src="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}" width="200" class="img">
			</div>
			</c:if>	
		</c:forEach>
		</div>
		${filesList.value[0].content}
	</c:forEach>
	
	<hr/>
	
<jsp:include page="/WEB-INF/view/common/detail_footer.jsp"/>