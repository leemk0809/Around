<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 로그인 페이지</title>
<link href="<%= request.getContextPath() %>/css/main.css" type="text/css" rel="stylesheet">

<style type="text/css">
	body {
		background-image: url("<%=request.getContextPath()%>/img/bg.jpg");
	}
	#center{
		position: absolute;
		top: 40%;
		left: 48%;
		width: 100px;
		height: 100px;
		margin: -50px 0 0 -50px;
	}
	
</style>
</head>
<body>
	<div id="center">
		<c:url value="/adminLogin" var="adminLogin"/>
		<form method="post" action="${adminLogin }">
			<fieldset>
				<legend>로그인</legend>
				<input type="text" name="id" placeholder="아이디"><br>
				<input type="text" name="password" placeholder="패스워드"><br>
			</fieldset>
			<input type="submit" value="로그인">
		</form>	
	</div>
</body>
</html>