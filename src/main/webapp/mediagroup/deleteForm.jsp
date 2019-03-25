<%@ page contentType="text/html; charset=UTF-8" %> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>mediagroup/deleteForm.jsp</title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 24px; 
} 
</style> 
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head> 
<body>
	<div class="title">미디어 그룹 삭제</div>
	<form method="post" action="./delete.do">
		<input type="hidden" name="mediagroupno" value="${dto.mediagroupno }">
		<div class="content">
			<p>미디어 그룹을 삭제하시겠습니가?</p>
			<p> 관련 미이더 파일(mp3, mp4)도 전부 삭제 됩니다</p>
		</div>
		<div class="bottom">
			<input type="submit" value="삭제진행">
			<input type="button" value="목록" onclick="window.location.href='./list.do'">
	</form>
</body> 
</html> 
