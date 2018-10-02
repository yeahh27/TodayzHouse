<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<nav>
<ul>
	<li><a href="/TodaysHouse/">Home</a></li>
	<li><a href="/TodaysHouse/board/1/">List1</a></li>
	<c:choose>
		<c:when test="${empty sessionScope._MEMBER_.name}">
			<li><a href="/TodaysHouse/member/login">Login</a></li>
			<li><a href="/TodaysHouse/member/regist">Regist</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="/TodaysHouse/member/logout">Logout</a></li>
			<li class="point">point : ${sessionScope._MEMBER_.point}</li>
		</c:otherwise>
	</c:choose>
</ul>
</nav>