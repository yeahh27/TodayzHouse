<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TODAYZHOUSE</title>
<!-- <link href="/TodayzHouse/css/layout.css" rel="stylesheet" type="text/css">  -->
<script src="/TodayzHouse/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="/TodayzHouse/js/jquery-ui.min.js" type="text/javascript"></script>
<link href="/TodayzHouse/css/jquery-ui-themes-1.12.1/jquery-ui.theme.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
  padding: 0px;
  margin: 0px;
  font-size: 9pt;
}

nav {
  background-color: #00e6ac;
  padding: 15px;
  margin-bottom: 15px;
}

nav ul {
    padding: 0px;
    margin: 0px;
}

nav ul > li {
   display: inline-block;
   margin-left: 30px;
}

nav ul > li:first-child {
   margin-left: 0px;
}

a, a:visited {
   text-decoration: none;
   color: #333;
}

a:active {
   text-decoration: underline;
}

nav a:hover {
   color: #ffffff;
}
 
.point {
	text-decoration: underline;
} 

#wrapper {
	width: 100%;
	text-align: center;
}

.inline {
	display: inline-block;
}

header {
  text-align: center;
}

#replyBox {
	text-align: left;
}

.replyMoDe {
	color: #808080;
}

/* The switch - the box around the slider */
.switch {
  position: relative;
  display: inline-block;
  width: 30px;
  height: 17px;
  vertical-align:middle;
}

/* Hide default HTML checkbox */
.switch input {display:none;}

/* The slider */
.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 13px;
  width: 13px;
  left: 2px;
  bottom: 2px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(13px);
  -ms-transform: translateX(13px);
  transform: translateX(13px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 17px;
}

.slider.round:before {
  border-radius: 50%;
}

.toggle {
	margin:0px;
	display:inline-block;
	font-weight:bold;
}
</style>
<script type="text/javascript">
	$().ready(function() {
		var check = $("input[type='checkbox']");
		check.click(function(){
			$(".toggle").toggle();
		});
	})
</script>
</head>
<body>

	<div id="wrapper">	<!-- html5에서 새로나온 시멘트태그이고 div를 대체하기 위해서 나온 것 -->
		<header id="header">
			<nav>
			<ul>
				<li><a href="/TodayzHouse/">Home</a></li>
				<li><a href="/TodayzHouse/board/1/">List1</a></li>
				<li><a href="/TodayzHouse/board/2/">List2</a></li>
				<c:choose>
					<c:when test="${empty sessionScope._MEMBER_.name}">
						<li><a href="/TodayzHouse/member/login">Login</a></li>
						<li><a href="/TodayzHouse/member/regist">Regist</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="/TodayzHouse/member/logout">Logout</a></li>
						<li class="point">${sessionScope._MEMBER_.name} (${sessionScope._MEMBER_.point})</li>
						<li>
						<label class="switch">
						  <input type="checkbox">
						  <span class="slider round"></span>
						</label>
						<p class="toggle">OFF</p>
						<p class="toggle" style="display:none;">ON</p>
						<c:choose>
							<c:when test="${sessionScope._MEMBER_.chatOk eq 1}">
								<p class="toggle">ON</p>
								허용
							</c:when>
							<c:otherwise>
								<p class="toggle">OFF</p>
								거부
							</c:otherwise>
						</c:choose>
						<c:if test="${sessionScope._MEMBER_.admin eq 1}">
							<li><a href="">관리자</a></li>
						</c:if>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
			</nav>
		</header>
		<section class="inline">	<!-- 구역(영역)에다가 -->
			
			<section>