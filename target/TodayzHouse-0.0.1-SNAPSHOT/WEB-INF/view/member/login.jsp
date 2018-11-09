<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>

<script type="text/javascript">
	$().ready(function() {
		$(".loginBtn").click(function() {
			
			if($("#email").val() == "") {
				alert("이메일을 입력하세요!");
				$("#email").focus();
				return;
			}
			
			if($("#password").val() == "") {
				alert("비밀번호를 입력하세요!");
				$("#password").focus();
				return;
			}
			
			$.post("/TodayzHouse/member/login"
				   , $("#loginData").serialize()
				   , function(response) {
						console.log(response.status)
					   if(response.status == 'ok') {
							//alert("로그인되었습니다.")
							location.href="/TodayzHouse/"
						} else if (response.status == 'block') {
							alert(response.message)
							location.href="/TodayzHouse/member/login"
						} else {
							alert(response.message)
							location.href="/TodayzHouse/member/login"
						}
					}
			)
		})
	})
</script>
<div style="text-align: center; vertical-align: middle;">
	<div style="display: inline-block; width: 60%; text-align: center; margin-bottom: 20px; border: 1px solid gray;">
		<h1>LOGIN</h1>
		<form:form id="loginData" modelAttribute="memberVO" >
		<div style="padding-left: 15px; padding-top: 15px;">
			<label>이메일 </label>
			<input type="email" name="email" id="email" placeholder="EMAIL" value="" style="width: 30%; height: 20px;" />
		</div>
		<div style="padding-left: 15px; padding-top: 15px;">
			<label>비밀번호 </label>
			<input type="password" name="password" id="password" placeholder="PASSWORD" value="" style="width: 30%; height: 20px;" />
		</div>
		<div style="padding: 15px">
			<input type="button" class="loginBtn" value="로그인" />
		</div>
		</form:form>
	</div>
</div>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>