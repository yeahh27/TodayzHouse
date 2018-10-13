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
		
		if (${articleVO.email eq sessionScope._MEMBER_.email }) {
			$(".img").click(function() {
				var x = $(this).offsetLeft;
				var y = $(this).offsetTop;
				alert(x + "  " + y)

				/*var x = event.offsetX;
				var y = event.offsetY; 
				
				var fileId = $(this).closest(".imgWrapper").find(".fileId").val();
				
				var tag_html = $('<input type="hidden" class="fileId"  name="fileId" value="' + fileId + '" />'
								+ ' <input type="hidden" class="linkX" name="linkX" value="' + x + '" />'
						        + ' <input type="hidden" class="linkY" name="linkY" value="' + y + '" />')
				$(".linkWrapper").prepend(tag_html)
				
				$("#dialog").dialog({
					closeOnEscape: false,
				    open: function(event, ui) {
				        $(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
				    },
				    title: "Link +", 
			        titleIsHtml: true , 
			        width: "230",
					height: "90",
					resizable: false,
					modal: true,
					position: {
						my: "left top",
						at: "center",
						of: $(this)
					},
					buttons: {
						"확인":function() {
							$.post(
									"/TodayzHouse/link/write"	
									, $(".linkData").serialize()
									, function(response) {
										if(response.status == 'ok') {
											window.location.reload()
										} 
									}
							)
						}, "취소": function() {
							$(this).dialog("close")
						}
					}
				}); */
				
				/*
				$(this).closest(".imgWrapper").find(".linkWrapper").css({
					"position": "absolute",
					"margin-left": x,
					"margin-top": y
				}) */
			})
		}
		
		$(".linkWrapper").on("click", ".linkBtn", function() {
			$.post(
					"/TodayzHouse/link/write"	
					, $(".linkData").serialize()
					, function(response) {
						if(response.status == 'ok') {
							window.location.reload()
						} 
					}
			)
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
				<input type="hidden" class="fileId"  name="fileId" value="${files.fileId}" />
				<img src="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}"
					 width="300" class="img" >
				<c:forEach items="${files.linkList}" var="links">
				<div>
					<img src="/TodayzHouse/img/plus.png" width="20" value="${links.fileId}"
					     style="position: relative; left: ${links.linkX}px; top: ${links.linkY}px;"/>
				</div>
			</c:forEach>
			</div>
			</c:if>	
		</c:forEach>
		</div>
		${filesList.value[0].content}
	</c:forEach>
	
	<div id="dialog" style="display: none;">
	<form:form class="linkData" modelAttribute="linkVO">
		<div class="linkWrapper" >
			<label>제품명 : </label>
			<input type="text" class="product" name="product"/><br/>
			<label>&nbsp;주&nbsp;소 : </label>
			<input type="text" class="address" name="address" />
		</div>
	</form:form>
	</div>
	<hr/>
	
<jsp:include page="/WEB-INF/view/common/detail_footer.jsp"/>