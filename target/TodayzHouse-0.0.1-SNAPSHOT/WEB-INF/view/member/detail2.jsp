<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>

<script type="text/javascript">
	$().ready(function() {
		if(${memberVO.chatOk eq 1}) {
			$("input[value='1']").attr('checked', true);
		} else {
			$("input[value='0']").attr('checked', true);
		}
		
		$(".modiBtn").click(function() {
			
			if($("#name").val() == "") {
				alert("이름을 입력하세요!");
				$("#name").focus();
				return;
			}
			
			$.post("/TodayzHouse/member/modify"
					, $("#modiData").serialize()
					, function(response) {
						if(response.status) {
							alert("회원 정보가 수정되었습니다.")
							location.href="/TodayzHouse/member/my/1"
						} else {
							//alert("회원등록에 실패하였습니다.")
							alert(response.message)
							location.href="/TodayzHouse/member/my/2"
						}
					}
			)
		})
	})
</script>

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
			<label>채팅 허용</label>
			<span>${memberVO.chatOk}</span>
		</div>
	</div>
</div>
<div>
	<nav style="background-color: white;">
		<ul style="border-top: 1px solid gray; padding-top: 10px;">
			<li><a href="/TodayzHouse/member/my/1">프로필</a></li>
			<li><a href="/TodayzHouse/member/my/2" style="color: #00e6ac;">설정</a></li>
		</ul>
	</nav>
</div>

<div style="text-align: center;">
	<form:form id="modiData" modelAttribute="memberVO" >
		<div style="padding-left: 15px; padding-top: 15px;">
			<label for="email" style="font-size: 20px;">Email</label>
			${memberVO.email}<input type="hidden" name="email" class="email" value="${memberVO.email}" />
		</div>
		<div style="padding-left: 15px; padding-top: 15px;">
			<label for="name" style="font-size: 20px;">Name</label>
			<input type="text" name="name" id="name" placeholder="NAME" value="${memberVO.name}" />
		</div>
		<div style="padding-left: 15px; padding-top: 15px;">
			<label for="chatOk" style="font-size: 20px;">채팅 허용 여부</label>
			<input type="radio" name="chatOk" value="1" />허용
			<input type="radio" name="chatOk" value="0" />거부		
		</div>
		<div style="padding: 15px">
			<input type="button" class="modiBtn" value="수정" />
		</div>
	</form:form>
</div>
<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>