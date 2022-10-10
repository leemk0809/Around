<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>자세히 보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="<%=request.getContextPath()%>/css/main.css" type="text/css"
	rel="stylesheet">
<style>
body {
	background-image: url("<%=request.getContextPath()%>/img/bg.jpg");
}

#detailWrapper {
	margin: 100px;
	padding: 20px;
}

#title {
	padding: 20px;
}

#leftSide {
	padding: 20px;
	float: left;
	width: 60%;
	margin-bottom: 20px;
}

#rightSide {
	padding: 20px;
	float: right;
	width: 40%;
	margin-bottom: 20px;
}

#bottom {
	margin: 0 auto;
	padding: 20px;
	clear: both;
	text-align: center;
}

#bottom > a{
	border-bottom: none;
}

#bottom > a img{
	margin-right: 20px;
}

#bottom > button{
	margin-right: 20px;
}

table tbody tr {
	border: solid 1px rgba(255, 255, 255, 0.0);
	border-left: 0;
	border-right: 0;
}

table tbody tr:nth-child(2n + 1) {
	background-color: rgba(255, 255, 255, 0.0);
}
table{
	width: 30%;
	margin-left: 25%;
}

#hidden{
	display: none;
}
</style>
</head>
<body>
	<div id="wrapper">
		<div id="detailWrapper">
			<c:url value="/updateBoard" var="updateBoard"></c:url>
			<sform:form method="post" action="updateBoard" modelAttribute="board">		
				<div id="title">
					제목<sform:input path="title"/>
				</div>		
				<div id="title">
					글쓴 날짜<sform:input path="writedDate"/><br>
					내용 <sform:textarea path="content"/>
				</div>					
			<div id="bottom">				
				<c:url value="/adminDeleteBoard" var="adminDeleteBoard" /> 
				<a href="${adminDeleteBoard }?boardNo=${board.boardNo}"><img src="<%=request.getContextPath()%>/img/button/delete.png"></a>
				<c:url value="/returnAdminMainBoard" var="returnAdminMainBoard"></c:url>
				<a href="${returnAdminMainBoard }"><img src="<%=request.getContextPath()%>/img/button/close.png"></a>
			</div>
			</sform:form>
		</div>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
</script>
</html>