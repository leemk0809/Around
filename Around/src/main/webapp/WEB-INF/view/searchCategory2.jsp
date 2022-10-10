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


.container>#search{
	margin-top: 6%;
}
#search>.search2 {
	background: rgba(92, 85, 85, 0.79);
	width: 40%;
	margin-top: 10%;
	hegiht: 8%;
	margin: 0 auto;
	border: 1px solid black;
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
@media screen and (max-width:389px) {
	#btn{		
		font-size: 15%;
	}

}
@media screen and (min-width:390px) and (max-width:549px){
	#btn{		
		font-size: 35%;
	}
}
@media screen and (min-width:550px) and (max-width:740px){
	#btn{		
		font-size: 70%;
	}
}
@media screen and (min-width:741px) {
	#btn{		
		font-size: 80%;
	}
}

.content-top{
	height: 610px;
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
					<c:url value="/searchCategory" var="searchCategory" />
					<form action="searchCategory" method="post">
						<input type="text" id="categoryName" name="categoryName"
							value="닉네임을 입력해 주세요" onfocus="this.value = '';"
							onblur="if (this.value == '') {this.value = '닉네임 입력..';}">
						<input type="submit" id="btnSearch" value="">
					</form>
					
				</div>
			</div>
			<c:url value="/initSearchUserCategory" var="initSearchUserCategory" />
				
			<table>
				<tr>					
					<th align="center">이름</th>	
					<th><a href="initSearchUserCategory">
					<button id="btn">모든 카테고리 보기</button>
				</a></th>				
				</tr>
				<c:forEach var="category" items="${category}">
					<tr>						
						<td align="center"><label>${category.categoryName}</label></td>						
						<td align="center"><c:url value="/insertUserCategory"	var="insertUserCategory" /> 
							<a href="${insertUserCategory}?userNo=${category.categoryNo}">
								<button id="btn">카테고리 추가</button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
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