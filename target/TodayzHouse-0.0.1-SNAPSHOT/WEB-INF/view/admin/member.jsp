<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<jsp:include page="/WEB-INF/view/common/layout_header.jsp"/>

<div style="float: left; width: 23%; border-right: 1px solid black; min-height: 500px; margin-left: 3%;">
	<div>
		<h3><a href="/TodayzHouse/admin/1">게시글 관리</a></h3>
	</div>
	
	<div>
		<h3><a href="/TodayzHouse/admin/2" style="color: #00e6ac;">회원 관리</a></h3>
	</div>
</div>

<div style="float: left; width: 60%; margin-left: 5%">
	<table>
		<c:forEach items="${warnMemberList}" var="members">
			<table style="border-bottom: 1px solid gray; border-top: 1px solid gray;">
				<tr>
					<td rowspan="3"><img src="/TodayzHouse/img/user.png" width="30px" style="margin-right: 10px"/></td>
					<td style="">${members.name} (${members.email})</td>
				</tr>
				<tr>
					<td>Point ${members.point}</td>
					<td><input type="button" class="deleteBtn" value="강제탈퇴" /></td>
				</tr>
				<tr>
					<td>삭제된 게시물 수 ${members.deleteArtCount}</td>
				</tr>
			</table>
		</c:forEach>
	</table>
</div>

<jsp:include page="/WEB-INF/view/common/layout_footer.jsp"/>