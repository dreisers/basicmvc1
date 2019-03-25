<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mediagroup/createForm.jsp</title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 24px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<DIV class="title">미디어 그룹 목록</DIV>
	<TABLE>
		<TR>
			<TH>그룹번호</TH>
			<TH>그룹제목</TH>
			<TH>수정/삭제</TH>
		</TR>

		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.mediagroupno }</td>
				<td><a href="../media/list.do?mediagroupno=${dto.mediagroupno }">${dto.title }</td>
				<td>
				<input type="button" value="수정"onclick="location.href='./update.do?mediagroupno=${dto.mediagroupno }'">
				<input type="button" value="삭제"onclick="location.href='./delete.do?mediagroupno=${dto.mediagroupno }'">
				</td>
			</tr>
		</c:forEach>
	</TABLE>
	
	<div class="bottom">
		<input type="button" value="그룹등록" onclick="location.href='./create.do'">
	</div>
	
</body>
</html>
