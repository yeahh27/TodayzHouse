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
		
		var recommendCount = $(".recommendCount").text();
		$("#recommend").click(function() {
			$.post("/TodayzHouse/recommend/${articleVO.boardId}/${articleVO.articleId}"
					, $(".token").val()
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
					, $(".token").val()
					, function(response) {
						if(response.status == "ok") {
							$("#unrecommend").hide();
							$("#recommend").show();
							$(".recommendCount").text(--recommendCount);
						}
					}
			)
		})
	})
</script>
<input type="hidden" name="token" value="${sessionScope._CSRF_TOKEN_}" />
	<h1>DETAIL2</h1>

	<h2>${articleVO.title}
		<span style="font-size: 10pt">${articleVO.articleId}</span>
	</h2>	
	
	<h3>작성자 : ${articleVO.memberVO.name} (${articleVO.email})</h3>

	<a href="#" id="recommend" style="display: none;" >좋아요:)</a>
	<a href="#" id="unrecommend" style="display: none;">좋아요취소:(</a>
	
	<h3>조회수 : ${articleVO.viewCount}</h3>
	<h3>추천수 : <span class="recommendCount">${articleVO.recommend}</span></h3>
	
	<c:forEach items="${articleVO.fileVOList}" var="files" varStatus="fileStatus" >
		<c:if test="${not empty files.originFileName}">
			<p>
				<%-- <a href="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}"> --%>
					<img src="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}" width="120">
				<!-- </a> -->
			</p>
		</c:if>	
		<h5>${files.idx} : ${files.content}</h5>
	</c:forEach> 
	
	
<jsp:include page="/WEB-INF/view/common/detail_footer.jsp"/>