<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>media/readMP4.jsp</title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 24px;
}
</style>
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<DIV class="title">음악듣기</DIV>
	<div class='content'>
	${dto.title }<br>
	<video src="./storage/${dto.filename }" poster="./storage/${dto.poster } width="500px" controls autoplay></video>
	</div>
		
	<div class="bottom">
		<input type="button" value="음원목록" onclick="location.href='./list.do?mediagroupno=${dto.mediagroupno}'">
		
	</div>
	
</body>
</html>
