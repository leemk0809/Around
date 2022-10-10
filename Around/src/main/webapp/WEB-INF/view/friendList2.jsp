<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dto.*, java.util.*"%>
<!DOCTYPE htm>
<html>
<head>
<title>친구 목록</title>
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

label {
	display: inline;
}

#logo>#topBtns {
	float: right;
}

table {
	width: 83%;
	margin-top: 10%;
	margin-left: 7%;
	color: #777777;
	/* 	border: solid 1px yellow; */
}

table tr {
	border-bottom: 1px solid gray;
	padding-bottom: 3%;
}

h6 {
	color: #777777;
}

table th {
	font-size: 1.2em;
	color: white;
	text-align: center;
	padding: 2%;
	color: white;
}

select {
	color: black;
}

button {
	font-size:
}

td>img {
	height: 75px;
	width: 75px;
	padding: 2px;
}

.delete {
	margin-left: 10%;
	width: 10%;
	height: 10%;
}

.content-top {
	height: 100%;
}

#searchFriend {
	margin-top: 8%;
	margin-bottom: -10%;
	margin-left: 77%;
}

.friendListTop {
	overflow: auto;
	width: 100%;
	height: 80%;
}

.friendListNav {
	height: 12%;
}

@media screen and (min-width: 0px) and (max-width:400px) {
	tr>th {
		font-size: 0.7em;
	}
	tr>td {
		font-size: 0.7em;
	}
	a>button {
		font-size: 0.7em;
	}
	.friendList>table {
		margin-top: 20%;
	}
}

#pathImg {
	height: 30%;
}
</style>
</head>
<body>
	<%-- <!-- Header -->
	<div id="logo">
		<label>로고</label>
		<div id="topBtns">
			<c:url value="/returnMainBoard" var="returnMainBoard"/>
			<a href="returnMainBoard">
			 	<button>메인 메뉴</button>
			</a>
			<c:url value="/initSearchFriend" var="initSearchFriend"/>
			<a href="initSearchFriend">
				<button>친구 찾기</button>
			</a>
			<button>로그아웃</button>
		</div>
	</div> --%>
	<!-- header -->
	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<!-- header -->

	<div class="content-top">
		<div class="container">
			<div class="friendList">
				<div class="friendListNav">
					<c:url value="/initSearchFriend" var="initSearchFriend" />
					<a href="initSearchFriend">
						<button id="searchFriend">친구 찾기</button>
					</a>
				</div>
				<div class="friendListTop">
					<table>
						<tr>
							<th align="center">친구 목록</th>
							<th align="center">이름</th>
							<th align="center">닉네임</th>
							<th align="center">알림 설정</th>
						</tr>
						<c:forEach var="friends" items="${friends}">
							<tr>
								<!-- <td align="center"><label>프로필 사진</label></td> -->
								<td align="center"><img
									src="<%=request.getContextPath()%>/upload/${friends.profilePath}">
								</td>
								<td align="center"><label>${friends.userName}</label></td>
								<td align="center"><label>${friends.nickname}</label></td>
								
								<td align="left"><select id="alarm"
									data-item="${friends.friendNo }" name="${friends.alarm}">
										<%-- <option selected="selected" value="">${friends.alarm }</option> --%>
										<option
											<c:if test='${friends.alarm == "on"}'>selected="selected"</c:if>
											value="on">on</option>
										<option
											<c:if test='${friends.alarm == "off"}'>selected="selected"</c:if>
											value="off">off</option>
								</select></td>
								<c:url value="/deleteFriend" var="deleteFriend" />
								<td align="center"><a
									href="${deleteFriend}?friendNo=${friends.friendNo}">
										<button>친구 삭제</button> <input type="hidden"
										data-item="${friends.friendNo }">
								</a></td>
							</tr>
						</c:forEach>
						<!-- 친구 삭제 할 때는 친구 번호도 items에 담겨 오기 때문에 친구 번호로 삭제 -->
						<%-- 		</c:forEach> --%>
					</table>
					<table>
						<tr>
							<th align="center" colspan="3">알수도 있는 친구</th>
						</tr>
						<c:forEach var="ifYouKnow" items="${ifYouKnows}">
							<tr>
								<!-- <td align="center"><label>프로필 사진</label></td> -->
								<td align="center"><img
									src="<%=request.getContextPath()%>/upload/${ifYouKnow.profilePath}">
								</td>
								<td align="center"><label>${ifYouKnow.userName}</label></td>
								<td align="center"><label>${ifYouKnow.nickname}</label></td>
								<td align="center"><c:url value="/insertFriend"
										var="insertFriend" /> <a
									href="${insertFriend}?userNo=${ifYouKnow.userNo}">
										<button>친구 추가</button>
								</a></td>

							</tr>
						</c:forEach>
						<!-- 친구 삭제 할 때는 친구 번호도 items에 담겨 오기 때문에 친구 번호로 삭제 -->
						<%-- 		</c:forEach> --%>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="../layout/footer2.jsp"></jsp:include>
	<!-- footer -->
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	<c:url value="/updateAlarm" var="updateAlarm"/>
	$(document).on("change", "#alarm" ,function(){
			$.ajax({
			type : "post",
			url : "${updateAlarm}",
			data : {
				alarm : $(this).val(),
				friendNo : $(this).attr("data-item")
			},
			success : function(res){
				$(this).val(res);
			},
			error:function(xhr, status, error){
				alert("잘못된 접근입니다");
			}
		});  
/* 		console.log($(this).attr("data-item"));
		console.log($(this).val()); */
	});
<%-- 	var friend = ${friends};
	<c:url value="/deleteFriend" var="deleteFriend"/>
	$("#deleteBtn").on("click",function(){
		$.ajax({
			type:"post",
			url : "${deleteFriend}",
			data : {
				friendNo : $("#deleteBtn").val()
			},
			success : function(){
				alert("삭제 되었습니다");
				location.href = "<%=request.getContextPath()%>/friendList";
				
			},
			error:function(xhr, status, error){
				alert("잘못된 접근입니다");
			}
		});
	}); --%>
</script>
</html>