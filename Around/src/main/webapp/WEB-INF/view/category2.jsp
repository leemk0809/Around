<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사용자 카테고리</title>
<style>
/* .catagers li a {
	font-size: 1.2em;
} */
body {
	background-color: #1B1B1D;
	height: 100%;
}

#searchFriend {
	margin-top: 2%;
	margin-bottom: -15%;
	margin-left: 77%;
}

@media screen and (min-width: 0px) and (max-width:400px) {
	a>button {
		font-size: 0.6em;
	}
}

@media screen and (max-width:389px) {
	#searchFriend {
		margin-left: 50%;
	}
}

@media screen and (min-width:390px) and (max-width:549px) {
	#searchFriend {
		margin-left: 56%;
	}
}

@media screen and (min-width:550px) and (max-width:654px) {
	#searchFriend {
		margin-left: 65%;
	}
}

@media screen and (min-width:650px) and (max-width:959px) {
	#searchFriend {
		margin-left: 70%;
	}
}

@media screen and (min-width:960px) {
	#searchFriend {
		margin-left: 77%;
	}
}

.content-top {
	height: 610px;
}
.catagers li a {
		font-size: 1.4em;
}
</style>
</head>
<body>
	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<div class="content-top">
		<br> <br> <br>
		<div class="container">
			<a href="initSearchUserCategory">
				<button id="searchFriend">카테고리 찾기</button>
			</a>
			<div class="catagers">
				<h2>
					<span>YOUR</span> CATEGORIES
				</h2>
				<c:url value="/scategoryBoard" var="scategoryBoard" />
				<c:url value="/deleteUserCategory" var="deleteUserCategory" />
				<ul>
					<c:forEach items="${categories}" var="usercategory">
						<li><a
							href="${scategoryBoard}?categoryNo=${usercategory.categoryNo}">${usercategory.category.categoryName }</a>
							<a	style="float: right; font-size: 0.4em; margin-right: 2%; height: 65% "
							href="${deleteUserCategory}?categoryNo=${usercategory.categoryNo}">삭제</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<jsp:include page="../layout/footer2.jsp"></jsp:include>
</body>
<script>
	//전체글 보기
	$("#btnGetAllBoard").on("click", function() {
		<c:url value="/returnMainBoard" var="returnMainBoard"/>
		document.location.href = "returnMainBoard";
	});
</script>
</html>