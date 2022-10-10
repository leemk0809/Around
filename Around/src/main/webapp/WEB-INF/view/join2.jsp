<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>회원가입</title>
		<meta charset="utf-8" />
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href='http://fonts.googleapis.com/css?family=Nunito:400,300'
			rel='stylesheet' type='text/css'>
	</head>
	<body>
		<jsp:include page="../layout/headerDoor.jsp"></jsp:include>
			<div class="content-top">
				<div class="container">
					<br><br>
						
						<sform:form method="post" action="joinPage" modelAttribute="user" enctype="multipart/form-data">
							<fieldset>
								<legend>
									<span class="number">1</span>기본 정보
								</legend>
								<label for="title">아이디:</label>
								<sform:input path="userId" placeholder="ID"/>
								<label for="title">비밀번호:</label>
								<sform:input path="password"  placeholder="비밀번호"  />
								<label for="title">닉네임</label>
								<sform:input path="nickname" placeholder="닉네임"  /><br>
								<label for="title">이름:</label>
								<sform:input path="userName" placeholder="이름"  /><br>
								<label for="title">전화번호:</label>
								<sform:input path="tel" placeholder="***-****-****" /><br>
								<label for="title">이메일:</label>
								<sform:input path="email" placeholder="이메일"  /><br>
								<label for="title">생년월일:</label>
								<sform:input type="date" path="birthdate" placeholder="yyyy-MM-dd" value="2016-10-04" /><br>
							</fieldset>
							<fieldset>
								<legend>
									<span class="number">2</span>프로필 사진 등록
								</legend>
							<sform:input path="file" type="file"/><br>
							</fieldset>
							<button type="submit">작성하기</button>
							<button type="button" onclick="backBtn()">돌아가기</button>
						</sform:form>
							
						
						
				<!-- Footer -->
					<!-- <footer id="footer">
						<p>&copy; Untitled. All rights reserved. Design: <a href="http://templated.co">TEMPLATED</a>. </p>
					</footer> -->
				
				
				</div>
			</div>









	</body>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/validation/dist/jquery.validate.js"></script>
	<script>
		function backBtn() {
			document.location.href = "index.jsp";
		}
		$("#join").validate({
			rules:{
				name: "required",
				email: {
				      required: true,
				      email: true
				}
			},
			messages:{
				name: "아이디가 비었습니다.",
				email: {
				      required: "이메일을 입력해 주세요.",
				      email: "이메일 형식이 안맞습니다. (ex: name@domain.com)"
				}
			}
		});
	</script>
	<style>
#hidden {
	display: none;
}

#getMyLocation {
	width: 10%;
	height: 10%;
}

#viewStatus {
	width: 80%;
}

#targetDate {
	display: none;
}

.contentsWrap {
	margin: 5%;
}

*, *:before, *:after {
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	font-family: 'Nunito', sans-serif;
	color: #384047;
}

form {
	max-width: 300px;
	margin: 10px auto;
	padding: 10px 20px;
	background: #f4f7f8;
	border-radius: 8px;
}

h1 {
	margin: 0 0 30px 0;
	text-align: center;
}

input[type="text"], input[type="password"], input[type="date"], input[type="datetime"],
	input[type="email"], input[type="number"], input[type="search"], input[type="tel"],
	input[type="time"], input[type="url"], textarea, select {
	background: rgba(255, 255, 255, 0.1);
	border: none;
	font-size: 16px;
	height: auto;
	margin: 0;
	outline: 0;
	padding: 15px;
	width: 100%;
	background-color: #e8eeef;
	color: #8a97a0;
	box-shadow: 0 1px 0 rgba(0, 0, 0, 0.03) inset;
	margin-bottom: 30px;
}

input[type="radio"], input[type="checkbox"] {
	margin: 0 4px 8px 0;
}

select {
	padding: 6px;
	height: 32px;
	border-radius: 2px;
}

button {
	padding: 19px 39px 18px 39px;
	color: #FFF;
	background-color: #4bc970;
	font-size: 18px;
	text-align: center;
	font-style: normal;
	border-radius: 5px;
	width: 100%;
	border: 1px solid #3ac162;
	border-width: 1px 1px 3px;
	box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
	margin:0%;
	margin-bottom: 10px;
}

fieldset {
	margin-bottom: 30px;
	border: none;
}

legend {
	font-size: 1.4em;
	margin-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 8px;
}

label.light {
	font-weight: 300;
	display: inline;
}

.number {
	background-color: #5fcf80;
	color: #fff;
	height: 30px;
	width: 30px;
	display: inline-block;
	font-size: 0.8em;
	margin-right: 4px;
	line-height: 30px;
	text-align: center;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.2);
	border-radius: 100%;
}

@media screen and (min-width: 480px) {
	form {
		max-width: 480px;
	}
}
</style>
</html>