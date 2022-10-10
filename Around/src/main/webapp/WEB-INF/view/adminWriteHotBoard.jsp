<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="<%=request.getContextPath()%>/css/main.css" type="text/css"
	rel="stylesheet">
<style>
body {
	background-image: url("<%=request.getContextPath()%>/img/bg.jpg");
}

#head{
	margin-top: 5%;
	text-align: center;
}

#inputForm{
	margin-top: 5%;
	margin-left: 20%;
	margin-right: 20%;
}

#title{
}

#category{
	margin-bottom: 20px;
}

#content{
	margin-bottom: 20px;
}

#map{
	margin-bottom: 20px;
}

/* 
#title {
	height: 70%;
	margin-left: 30%;
}

#category {
	width: 40%;
	margin-left: 30%;
	clear: both;
 	vertical-align: middle; 
}

#content {
	width: 40%;
	margin-left: 30%;
	height: 200%;
	clear: both;
}

#content>input {
	height: 100%;
}

#insertData {
	width: 40%;
	height: 20%;
	margin-top: 2%;
	margin-left: 30%;
	clear: both;
} 
*/
#map {
	
}

#bottomBtns {
	margin-top: 5%;
	margin-left: 20%;
	clear: both;
	text-align: center;
	margin: 30px;
}

#bottomBtns > button {
	margin-right: 100px;
}

#hidden{
	display: none;
}

#bottomBtns > a {
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}

#bottomBtns > a > img {
	top: 12px;
	position: relative;
	
}

</style>
</head>
<body>
	<div id="head">
		<h2>홈페이지 소개</h2>
	</div>
	
	<c:url value="/adminwriteHotBoard" var="adminwriteHotBoard"/>
	<sform:form method="post" action="adminwriteHotBoard" modelAttribute="hot" id="inputForm" enctype="multipart/form-data">
		<div id="title">
			<sform:input path="title" placeholder="제목을 입력해 주세요."/>
		</div>
		<br>
	
		<div id="category">
			<sform:label path="categoryNo">카테고리</sform:label>
			<sform:select path="categoryNo" items="${category }" itemLabel="categoryName" itemValue="categoryNo"/>
		</div>
		<br>
		<div id="content">
			<sform:textarea path="contents" placeholder="내용을 입력해 주세요."/>
		</div>
		<div id="insertData" >
			<sform:input path="file" type="file"/>
			<!-- <input type="file" name="file"> -->
		</div>
		<br>
		
		<div id="bottomBtns">
			<button id="writeBtn">쓰기</button>
			<%-- <c:url value="/returnMainBoard" var="returnMainBoard"/>
			<a href="returnMainBoard">
			 	<img src="<%= request.getContextPath()%>/img/button/close.png">
			</a> --%>
		</div>
		
		<%-- <div id="hidden">
			<sform:input path="boardNo"/>
			<sform:input path="userNo"/>
			<sform:input path="imagePath"/>
			<sform:input path="latitude" />
			<sform:input path="longitude" />
		</div> --%>
	</sform:form>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	
	
</script>
</html>