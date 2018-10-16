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

				/* var x = event.offsetX;
				var y = event.offsetY;  */
				
				var x = event.pageX - $(this).offset().left;
				var y = event.pageY - $(this).offset().top;

				var fileId = $(this).closest(".fileWrapper").find(".fileId").val();
				
				var tag_html = $('<input type="hidden" class="fileId"  name="fileId" value="' + fileId + '" />'
								+ ' <input type="hidden" class="linkX" name="linkX" value="' + x + '" />'
						        + ' <input type="hidden" class="linkY" name="linkY" value="' + y + '" />')
				$(".linkWrapper").prepend(tag_html)
				
				$("#dialog").dialog({
					closeOnEscape: false,
				    open: function(event, ui) {
				        $(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
				        $(".ui-dialog .ui-dialog-content").css("background-color", "white");
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
						of: event
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
				}); 
				
			});
		}
		
		$(".alink").click(function() {
			$("#linkDialog").find(".linkId").val($(this).closest(".fileWrapper").find(".linkId").val());
			$("#linkDialog").find(".product").text($(this).closest(".fileWrapper").find(".linkProduct").val());
			$("#linkDialog").find(".address").text($(this).closest(".fileWrapper").find(".linkAddress").val());
			
			$("#linkDialog").dialog({
				closeOnEscape: false,
			    open: function(event, ui) {
			        $(this).find(".ui-dialog .ui-dialog-content").css("background-color", "white");
			    },
				resizable: false,
				title: "Link #", 
		        titleIsHtml: true ,
		        width: "230",
				height: $(this).height,
				modal: true,
				position: {
					my: "left top",
					at: "center",
					of: event
				},
				close: function(event, ui) {
					window.location.reload()
				}
			});
		})
		
		$("#addressBtn").click(function() {
			var address = $("#linkDialog").find(".address").text();
			window.open(address);
		})
		
		$(".linkDelete").click(function() {
			var linkId = $("#linkDialog").find(".linkId").val()
			$.get("/TodayzHouse/link/delete/" + linkId
				  , { token:$(".token").val() }
				  , function(response) {
					if(response) {
						window.location.reload()					
					}
				})
		})
		
		$(".linkModify").click(function() {
			var product = $(this).closest(".linkWrapper").find(".product").text();
			var address = $(this).closest(".linkWrapper").find(".address").text();
			var html = $("#dialog").html();
			$("#linkDialog").find(".linkWrapper").remove();
			$("#linkDialog").prepend(html);
			
			$("#linkDialog").find(".linkWrapper").append('<input type="button" class="linkModiBtn" value="수정" />')
			
			$("#linkDialog").find(".product").val(product);
			$("#linkDialog").find(".address").val(address);
			
		})
		
		$("#linkDialog").on("click", ".linkModiBtn", function() {
			$.post("/TodayzHouse/link/modify/" + $("#linkDialog").find(".linkId").val()
			  , $(this).parent().parent(".linkData").serialize()
			  , function(response) {
					if(response) {
						window.location.reload()					
					}
			}); 

		})
		
		$("#chatBtn").click(function() {
			var email = $("#chatData").find(".email").val();
			var name = $("#chatData").find(".name").val();
			
			window.open("http://127.0.0.1:3000/chat?email="+email +"&name="+name , "new window", "width=400, height=800");
		})
	})
</script>

<input type="hidden" class="token" name="token" value="${sessionScope._CSRF_TOKEN_}" />
	<h1>DETAIL2</h1>
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
	
	<div style="text-align: center;">
	<c:forEach items="${requestScope.filesVOMap}" var="filesList" >
		<div>
			<div class="imgWrapper" >
			<c:forEach items="${filesList.value}" var="files">
				<c:if test="${not empty files.originFileName}">
					<div style="position: relative;display: inline-block;" class="fileWrapper">
						<input type="hidden" class="fileId" name="fileId" value="${files.fileId}"> 
						<img src="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}"
							 width="300" class="img" >
						<c:forEach items="${files.linkList}" var="links">
							<a class="alink">
								<input type="hidden" class="linkId" name="linkId" value="${links.linkId}"  />
								<input type="hidden" class="linkProduct" name="linkProduct" value="${links.product}"  />
								<input type="hidden" class="linkAddress" name="linkAddress" value="${links.address}"  />
								<img src="/TodayzHouse/img/plus.png" width="20" 
				    			     style="position: absolute; left: ${links.linkX-9}px; top: ${links.linkY-9}px;"/>
							</a>
						</c:forEach>
					</div>
				</c:if>	
			</c:forEach>
			</div>
			
			<div>
				${filesList.value[0].content}
			</div>
		</div>
	</c:forEach>
	</div>
	
	<div id="dialog" style="display: none;">
	<form:form class="linkData" modelAttribute="linkVO">
		<div class="linkWrapper" >
			<input type="hidden" class="token" name="token" value="${sessionScope._CSRF_TOKEN_}" />
			<label>제품명 : </label>
			<input type="text" class="product" name="product"/><br/>
			<label>&nbsp;주&nbsp;소 : </label>
			<input type="text" class="address" name="address" placeholder="http:// 까지 입력하세요"/>
		</div>
	</form:form>
	</div>
	
	<div id="linkDialog" style="display: none;">
		<input type="hidden" class="linkId" value="" />
		<div class="linkWrapper">
			제품명 : <span class="product"></span><br/>
			주소 : <a id="addressBtn"><span class="address"></span></a>
			<c:if test="${articleVO.email eq sessionScope._MEMBER_.email }">
				<div class="linkMoDe" style="text-align: right;">
					<a class="linkModify" >수정</a>
					<a class="linkDelete" >삭제</a>
				</div>
			</c:if> 
		</div>
	</div>
	<hr/>
	
<jsp:include page="/WEB-INF/view/common/detail_footer.jsp"/>