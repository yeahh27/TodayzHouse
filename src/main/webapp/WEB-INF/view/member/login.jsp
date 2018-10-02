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
					   if(response.status) {
							//alert("로그인되었습니다.")
							location.href="/TodayzHouse/"
						} else {
							alert("로그인에 실패하였습니다.")
							location.href="/TodayzHouse/member/login"
						}
					}
			)
		})
	})
</script>

	<h1>LOGIN</h1>
	<form:form id="loginData" modelAttribute="memberVO" >
	<div class="errors">
			<ul>
				<li><form:errors path="email" /></li>
				<li><form:errors path="password" /></li>
			</ul>
		</div>
	<div>
		<input type="email" name="email" id="email" placeholder="EMAIL" value="" />
	</div>
	<div>
		<input type="password" name="password" id="password" placeholder="PASSWORD" value="" />
	</div>
	<div>
		<input type="button" class="loginBtn" value="로그인" />
	</div>
	</form:form>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>