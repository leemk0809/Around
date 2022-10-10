<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<title>친구 찾기</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Darx Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript">

	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<script src="js/jquery.min.js"></script>
<style>
body {
	background-color: #1B1B1D;
}

table {
	width: 83%;
	margin-top: 5%;
	margin-left: 7%;
	color: #777777;
}

table tr {
	border-bottom: 1px solid gray;
	padding-bottom: 3%;
}

table th {
	font-size: 1.2em;
	color: white;
	text-align: center;
	padding: 2%;
	color: white;
}

select {
	color: silver;
}

#btnViewAllUsers {
	margin-left: 75%;
	margin-top: 1%;
}

.content-top {
	height: 90%;
}
.container>#search{
	margin-top: 6%;
}

.searchFriend{
	float: right;
	width: 40%;
}

#search>.search2 {
	background: rgba(92, 85, 85, 0.79);
	width: 50%;
	margin-top: 10%;
	hegiht: 8%;
	border: 1px solid black;
	margin-left: 10%;
	float: left;
}
td>img {
	height: 75px;
	width: 75px;
	padding: 2px;
}
.search2 input[type="submit"] {
	background: url('<%=request.getContextPath()%>/img/sprit-1.png')
		no-repeat 2px 4px rgba(78, 73, 73, 0.99);
	padding: 10px 18px;
	border: none;
	cursor: pointer;
	outline: none;
	top: 0px;
	right: 0px;
}
@media screen and (min-width: 0px) and (max-width:400px) {
	tr>th{
		font-size : 0.7em;
	}
	tr>td{
		font-size : 0.7em;
	}
	.search2>form{
		width : 100%;
	}
	#search{
		height : 20%;
	}
	#btnViewAllUsers{
		font-size : 0.7em;
		width : 20%;
		margin-top : -25%; 
	}
}
#tableScroll{
	overflow: auto;
	width: 100%;
	height: 70%;
}
</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<!-- header -->
	<div class="content-top">
		<div class="container">
			<div id="search">
				<div class="search2">
					<c:url value="/searchFriend" var="searchFriend" />
					<form action="searchFriend" method="post">
						<input type="text" id="nickname" name="nickname"
							placeholder="닉네임 입력" >
						<input type="submit" id="btnSearch" value="">
					</form>
				</div>
			</div>
			<c:url value="/initSearchFriend" var="initSearchFriend" />
				<a href="initSearchFriend">
					<button id="btnViewAllUsers">모든 유저 보기</button>
				</a>
			<div id="tableScroll">
				<table>
				<tr>
					<th align="center">유저 목록</th>
					<th align="center">이름</th>
					<th align="center">닉네임</th>
				</tr>
				<c:forEach var="users" items="${users}">
					<tr>
						<td align="center">
								<img src="<%=request.getContextPath()%>/upload/${users.profilePath}" >
						</td>
						<td align="center"><label>${users.userName}</label></td>
						<td align="center"><label>${users.nickname}</label></td>
						<td align="center"><c:url value="/insertFriend"
								var="insertFriend" /> <a
							href="${insertFriend}?userNo=${users.userNo}">
								<button>친구 추가</button>
						</a></td>
					</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="../layout/footer2.jsp"></jsp:include>
	<!-- footer -->
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
</script>
</html>