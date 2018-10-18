<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>
<style type="text/css">
.linkImg:hover{
	opacity:0.6; 
	text-align:justify; 
}
</style>
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

				var imgWidth = $(this).width();
				var imgHeight = $(this).height();

				var x = event.pageX - $(this).offset().left;
				var y = event.pageY - $(this).offset().top;
				
				var percentX = x/imgWidth*100;
				var percentY = y/imgHeight*100;

				var fileId = $(this).closest(".fileWrapper").find(".fileId").val();
				
				var tag_html = $('<input type="hidden" class="fileId"  name="fileId" value="' + fileId + '" />'
								+ ' <input type="hidden" class="linkX" name="linkX" value="' + percentX + '" />'
						        + ' <input type="hidden" class="linkY" name="linkY" value="' + percentY + '" />')
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
			var from_email = $("#chatData").find(".fromEmail").val();
			var to_email = $("#chatData").find(".toEmail").val();
			
			var room_name = from_email + ":" + to_email;
			
			$("#messData").attr( {
				action: "/TodayzHouse/message/send",
	            method: "post"
			} ).submit();
			
			window.open("http://192.168.43.254:3000/chat?email="+from_email +"&rm="+room_name, "new window", "width=400, height=800")
		})
	})
</script>

<input type="hidden" class="token" name="token" value="${sessionScope._CSRF_TOKEN_}" />
<h1>사진</h1>

<c:choose>
	<c:when test="${articleVO.deleteYn eq 'Y'}">
		<div style="border: 2px solid black; width: 100%; text-align: center;">
			<img src="/TodayzHouse/img/warn.png" width="8%" style="padding-top: 20px;" />
			<p><h3>삭제된 게시글 입니다.</h3></p>
		</div>
	</c:when>
	
	<c:otherwise>
		<div>
			<div>
				<h2>${articleVO.title}</h2>	
			</div>
			
			<div style="display: flex;">
				<div style="text-align: center; width: 75%; float: left; margin-right: 5%" >
					<div class="imgWrapper" >
					<c:forEach items="${articleVO.fileVOList}" var="files">
						<c:if test="${not empty files.originFileName}">
							<div class="fileWrapper" style="position: relative;display: inline-block; width: 60%; height: auto; ">
								<input type="hidden" class="fileId" name="fileId" value="${files.fileId}"> 
								<img src="/TodayzHouse/board/${articleVO.boardId}/${articleVO.articleId}/download/${files.fileId}"
									 width="100%" class="img" >
								<c:forEach items="${files.linkList}" var="links">
									<a class="alink" >
										<input type="hidden" class="linkId" name="linkId" value="${links.linkId}"  />
										<input type="hidden" class="linkProduct" name="linkProduct" value="${links.product}"  />
										<input type="hidden" class="linkAddress" name="linkAddress" value="${links.address}"  />
										<img src="/TodayzHouse/img/plus2.png" width="4%" class="linkImg"
							    			     style="position: absolute; left: ${links.linkX-2}%; top: ${links.linkY-2}%;"/>
									</a>
								</c:forEach>
							</div>
						</c:if>	
					</c:forEach>
					</div>
				
					<div>
						<h4>${articleVO.fileVOList["0"].content }</h4>
					</div>
				</div>
				
				<div style="display: inline-block; width: 20%; float: left;">
					<div>
						<h3>
							<img src="/TodayzHouse/img/user.png" width="10%" style="vertical-align: bottom;"/>
							<c:choose>
								<c:when test="${articleVO.memberVO.chatOk eq 1 and isWriterLogin and articleVO.email ne sessionScope._MEMBER_.email }"> 
									<a id="chatBtn">${articleVO.memberVO.name}</a>
									<img src="/TodayzHouse/img/green.png" width="10" />
								</c:when>
								<c:otherwise>
									${articleVO.memberVO.name} <img src="/TodayzHouse/img/red.png" width="10" />
								</c:otherwise>
							</c:choose>
							(${articleVO.email})
						</h3>
						
						<form:form id="messData" modelAttribute="messageVO">
							<div id="chatData" >
								<input type="hidden" class="fromEmail" name="fromEmail" value="${sessionScope._MEMBER_.email}"/>
								<input type="hidden" class="toEmail" name="toEmail" value="${articleVO.email}"/>
							</div>	
						</form:form>
					</div>	
					<div>
						<div style="text-align: right;">
							조회수 <span style="margin-right: 3%">${articleVO.viewCount}</span>
							추천 <span class="recommendCount" style="margin-right: 3%">${articleVO.recommend}</span>
							신고 <span class="reportCount" style="margin-right: 3%">${articleVO.report}</span>
						</div>
						
						<div style="text-align: right;">
							<a id="recommend" style="display: none;" ><img src="/TodayzHouse/img/unrecommend2.png" width="25"></a>
							<a id="unrecommend" style="display: none;"><img src="/TodayzHouse/img/recommend2.png" width="25"></a>
							
							<a id="report" style="display: none;" ><img src="/TodayzHouse/img/report.png" width="30"></a>
							<a id="unreport" style="display: none;"><img src="/TodayzHouse/img/unreport.png" width="30"></a>
						</div>
					</div>
				</div>
				
			</div>
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
	</c:otherwise>
</c:choose>

<jsp:include page="/WEB-INF/view/common/detail_footer.jsp"/>