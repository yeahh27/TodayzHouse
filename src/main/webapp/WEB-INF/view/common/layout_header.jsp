<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/TodayzHouse/js/jquery-3.3.1.min.js" type="text/javascript"></script>
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
</style>
</head>
<body>
	<div id="wrapper">	<!-- html5에서 새로나온 시멘트태그이고 div를 대체하기 위해서 나온 것 -->
		<header>
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
					</c:otherwise>
				</c:choose>
			</ul>
			</nav>
		</header>
		<section class="inline">	<!-- 구역(영역)에다가 -->
			
			<section>