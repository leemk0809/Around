<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<html>
<head>
<title>位置セーブ</title>
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
#content {
	width: 80%;
	margin-left: 15%;
	margin-top: 10%;
	clear: both;
}

#map {
	margin-top: 15%;
	margin-left: 10%;
	width: 80%;
	height: 60%;
}

label {
	color: white;
	padding-left: 15%;
	margin-right: 7%;
}

#btn2 {
	margin-left: 7%;
}

#locationDiv {
	width: 100%;
	margin-left: 15%;
}

table {
	width: 60%;
}

tr>th {
	color: white;
	text-align: center;
}

td {
	text-align: center;
	color: #A6A6A6;
	padding-right: 10%;
}

a>button {
	border-bottom: 1px solid gray;
}
@media screen and (min-width: 0px) and (max-width:400px) {
	#map{
		margin-top: 15%;
		margin-left : -15%;
		width: 120%;
		height: 30%;
	}
	#content>label{
		margin-left : 15%; 
	}
	#locationDiv{
		margin : 0 auto;
		margin-left : -20%;
		width : 130%;
	}
	#locationDiv>table{
		padding-left : 1%;
		width : 100%;
	}
}
</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<!-- header -->
	<div class="content-top">
		<div class="container">
			<p id="demo"></p>
			<section id="content">
				<div id="map"></div>
				<br> <label>位置セーブ</label> <input type="text"
					placeholder="位置セーブの名前" id="userLocationName" name="userLocationName">
				<button id="btn2">セーブ</button>
				<div></div>
				<br> <br>
				<div id="locationDiv">
					<table id="table" style="text-align: center;">
						<tr>
							<th>位置の名前</th>
							<th>緯度</th>
							<th>経度</th>
						</tr>
						<c:forEach items="${locations }" var="location">
							<tr>
								<td align="center">${location.locationName }</td>
								<td align="center">${location.latitude }</td>
								<td align="center">${location.longitude }</td>
								<td align="center"><c:url value="/deleteLocation"
										var="deleteLocation" /> <a
									href="${deleteLocation }?userLocationNo=${location.userLocationNo}"><button>削除</button></a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<%-- <c:forEach items="${locations}" var="loaction">
					<h3>${loaction.locationName },  ${loaction.latitude },  ${loaction.longitude }</h3>					
				</c:forEach> --%>
				</div>
			</section>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="../layout/footer2.jsp"></jsp:include>
	<!-- footer -->



</body>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.poptrox.min.js"></script>
<script src="<%=request.getContextPath()%>/js/skel.min.js"></script>
<script src="<%=request.getContextPath()%>/js/main.js"></script>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=57b5102d7ef555237de225d53a65e93e"></script>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
var x = document.getElementById("demo");
var latitude;
var longitude;

window.onload = function() {
	getLocation();
};
function getLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPosition);
	} else {
		x.innerHTML = "Geolocation is not supported by this browser.";
	}
}

function showPosition(position) {		
	latitude=position.coords.latitude;
	longitude=position.coords.longitude;
	var markerPosition = new daum.maps.LatLng(position.coords.latitude,
			position.coords.longitude);
	var marker = {
		position : markerPosition
	};
	var staticMapContainer = document.getElementById('map'), // 이미지 지도를 표시할 div  
	staticMapOption = {
		center : new daum.maps.LatLng(position.coords.latitude,
				position.coords.longitude), // 이미지 지도의 중심좌표
		level : 3, // 이미지 지도의 확대 레벨
		marker : marker
	// 이미지 지도에 표시할 마커 
	};
	var staticMap = new daum.maps.StaticMap(staticMapContainer,
			staticMapOption);

}
	<c:url value="/deleteLocation" var="deleteLocation"/>
	$("#btn2")
			.on(
					"click",
					function() {
						console.log("클릭");
						<c:url value = "/savelocation" var="savelocation"/>
						$
								.ajax({
									type : "get",
									url : "${savelocation}",
									data : {
										latitude : latitude,
										longitude : longitude,
										userLocationName : $(
												"#userLocationName").val()
									},
									success : function(res) {
										console.log("전송");
										//$locationDiv = $("#locationDiv");
										//$locationDiv.empty();
										$(res)
												.each(
														function(idx, data) {
															$newOne = "<tr><td align='center'>"
																	+ data.locationName
																	+ "</td>"
																	+ "<td align='center'>"
																	+ data.latitude
																	+ "</td>"
																	+ "<td align='center'>"
																	+ data.longitude
																	+ "</td>"
																	+ "<td align='center'><a href="
																	+ "${deleteLocation}"
																	+ "?userLocationNo="
																	+ data.userLocationNo
																	+ "><button>삭제</button></a></td></tr>";
															//$newOne = "<h3>"+ data.locationName +"    "+ data.latitude + "  " + data.longitude+ " <h3>"; 					
															$("tr:first")
																	.after(
																			$newOne);
															$(
																	"#userLocationName")
																	.val("");
														});
									},
									error : function(request, status, error) {
										alert("code:" + request.status + "\n"
												+ "message:"
												+ request.responseText + "\n"
												+ "error:" + error);
									}

								});
					});

	//전체글 보기
	$("#btnGetAllBoard").on("click", function() {
		<c:url value="/returnMainBoard" var="returnMainBoard"/>
		document.location.href = "returnMainBoard";
	});
</script>

</html>