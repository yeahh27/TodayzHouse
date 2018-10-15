<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>

<script type="text/javascript">
	$().ready(function() {
		$(".regBtn").click(function() {
			
			if($("#email").val() == "") {
				alert("이메일을 입력하세요!");
				$("#email").focus();
				return;
			}
			
			if($("#name").val() == "") {
				alert("이름을 입력하세요!");
				$("#name").focus();
				return;
			}
			
			if($("#password").val() == "") {
				alert("비밀번호를 입력하세요!");
				$("#password").focus();
				return;
			}
			
			if($("#password2").val() == "") {
				alert("비밀번호를 다시 확인하세요!");
				$("#password2").focus();
				return;
			}
			
			if(document.getElementsByName("chatOk")[0].checked == false && document.getElementsByName("chatOk")[1].checked == false) {
				alert("채팅허용여부를 확인하세요!");
				$("#chatOk").focus();
				return;
			}
			
			if($("#email").val() == "") {
				alert("이메일을 입력하세요!");
				$("#email").focus();
				return;
			}
			
			if($("#email-error").data("email") != "true"  ) {
				alert("중복확인을 하세요!");
				$("#email").focus();
				return;
			}
			
			if($("#password-error").data("password") != "true"  ) {
				alert("비밀번호를 다시 확인하세요!");
				$("#password").focus();
				return;
			}
			
			$.post("/TodayzHouse/member/regist"
					, $("#regData").serialize()
					, function(response) {
						if(response.status) {
							alert("회원 등록되었습니다.")
							location.href="/TodayzHouse/member/login"
						} else {
							//alert("회원등록에 실패하였습니다.")
							alert(response.message)
							location.href="/TodayzHouse/member/regist"
						}
					}
			)
		})
		
		$(".dupBtn").click(function() {
			if($("#email").val() == "") {
				alert("이메일을 입력하세요!");
				$("#email").focus();
				return;
			}
			
			$.post("/TodayzHouse/member/check/duplicate"		// URL
					, {											// Request Parameter
						"email" : $("#email").val()
					}, function(response) {						// Response Call back
						console.log(response)
						$("#email-error").hide(80);
						if(response.duplicated) {
							$("#email-error").text("이 이메일은 사용할 수 없습니다.");
						} else {
							$("#email-error").text("이 이메일은 사용할 수 있습니다.");
							$("#email-error").data("email", "true")
						}
						$("#email-error").slideDown(100);
					}
			)
		})
		
		$("#email").keyup(function() {
			$("#email-error").hide(80);
		})
		
		$("#password2").keyup(function() {
			var password1 = $("#password").val();
			var password2 = $("#password2").val();
			$("#password-error").hide(80);
			if(password1 == password2) {
				$("#password-error").text("비밀번호가 일치합니다.");
				$("#password-error").data("password", "true")
			} else {
				$("#password-error").text("비밀번호가 일치하지 않습니다.");
			}
			$("#password-error").slideDown(100);
		})
	})
</script>

	<h1>REGIST</h1>
	<form:form id="regData" modelAttribute="memberVO" >
		<%-- <div class="errors">
			<ul>
				<li><form:errors path="email" /></li>
				<li><form:errors path="name" /></li>
				<li><form:errors path="password" /></li>
			</ul>
		</div> --%>
		<div>
			<label for="email">Email</label>
			<input type="email" name="email" id="email" placeholder="EMAIL" value=""/>
			<input type="button" value="중복확인" class="dupBtn" />
			<div id="email-error" style="display: none;" data-email="false">
				이 이메일은 사용할 수 없습니다.
			</div>
		</div>
		<div>
			<label for="name">Name</label>
			<input type="text" name="name" id="name" placeholder="NAME" value="" />
		</div>
		<div>
			<label for="password">Password</label>
			<input type="password" name="password" id="password" placeholder="PASSWORD" value=""/>
		</div>
		<div>
			<label for="password2">Password Confirm</label>
			<input type="password" name="password2" id="password2" placeholder="PASSWORD CONFIRM" />
			<div id="password-error" style="display: none;" data-password="false">
				비밀번호가 일치하지 않습니다.
			</div>
		</div>
		<div>
			<label for="chatOk">채팅 허용 여부</label>
			<input type="radio" name="chatOk" value="true" checked="checked"/>허용
			<input type="radio" name="chatOk" value="false" />거부		
		</div>
		<div>
			<input type="button" class="regBtn" value="회원가입" />
		</div>
	</form:form>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>