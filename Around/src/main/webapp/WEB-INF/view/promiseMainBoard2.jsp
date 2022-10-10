<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>約束取り</title>
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
	margin-top: 10%;
	margin: 0 auto;
	clear: both;
}

.content_top{
	height: 100%;
}

#map {
	margin: 0 auto;
	margin-left: 10%;
}

#contents_bottom {
	overflow: auto;
	margin: 0 auto;
	margin-top: 5%;
}

#bottomBtns {
	clear: both;
	margin: 0 auto;
	text-align: center;
	margin-bottom: 10%;
}

#staticMap {
	width: 500px;
	height: 400px;
}

#contents_bottom>h3 {
	color: white;
	font-size: 1.2em;
}

table {
	width: 100%;
}

.inviteePromises{
	margin-bottom: 1%; 
}

.input {
	width: 300px;
	margin-right: 10px;
}

@media screen and (min-width: 100px) and (max-width:400px) {
	#staticMap {
		margin-top: 15%;
		margin-left: -25%;
		width: 330px;
		height: 200px;
	}
	table {
		margin-botton: 20%;
	}
}
[input="text"] {
	width: 300px;
}

</style>
<script src="http://code.jquery.com/jquery.js"></script>
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<!-- header -->
	<div class="content-top">
		<div class="container">
			<section id="content">
				<div id="map">
					<div id="staticMap"></div>
				</div>
				<div id="contents_bottom">
					<h3>受けた約束</h3>
					<c:forEach items="${Inviteepromises }" var="userpromise">
					<p class="inviteePromises">
						<a
							onclick="promiseFunction('${userpromise.promiseTitle }',${userpromise.promiseLatitude },${userpromise.promiseLongitude })"><input
							id="str" type="text" readonly="readonly" class="input"
							value="${userpromise.promiseTitle }"> 
						</a>
						<select id="promiseStatusId" data-item="${userpromise.promiseId }"
							name="${userpromise.promiseStatus}">

							<option
								<c:if test='${userpromise.promiseStatus == "보류"}'>selected="selected"</c:if>
								value="보류">保留</option>
							<option
								<c:if test='${userpromise.promiseStatus == "거절"}'>selected="selected"</c:if>
								value="거절">断る</option>
							<option
								<c:if test='${userpromise.promiseStatus == "수락"}'>selected="selected"</c:if>
								value="수락">受け入れ</option>
						</select>
					<p>	
					</c:forEach>
					<br> <br>
					<h3>私の約束</h3>
					<c:url value="/deletePromise" var="deletePromise" />
					<c:forEach items="${Promotepromises }" var="userpromise">
						<table>
							<tr>
								<td><a
									onclick="promiseFunction('${userpromise.promiseTitle }',${userpromise.promiseLatitude },${userpromise.promiseLongitude })">
										<input id="str" type="text" readonly="readonly" class="input"
										value="${userpromise.promiseTitle }">
								</a>
								<a id="cancel"
									href="${deletePromise}?promiseId=${userpromise.promiseId}">
										<button>キャンセル</button>
								</a> 
							</tr>
						</table>
					</c:forEach>

				</div>
				<div id="bottomBtns">
					<c:url value="/promise" var="promise" />
					<a href="${promise }"><button>約束する</button></a>
					<c:url value="/returnMainBoard" var="returnMainBoard" />
					<a href="${returnMainBoard }"><button id="ok">確認</button></a>
				</div>
			</section>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="../layout/footer2.jsp"></jsp:include>
	<!-- footer -->
</body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=57b5102d7ef555237de225d53a65e93e"></script>
<script type="text/javascript">
var x = document.getElementById("demo");
var latitude;
var longitude;
var promiseName;


<c:url value="/updatepromiseStatus" var="updatepromiseStatus"/>
	$(document).on("change", "#promiseStatusId" ,function(){
			$.ajax({
			type : "post",
			url : "${updatepromiseStatus}",
			data : {
				status : $(this).val(),
				promiseId : $(this).attr("data-item")
			},
			success : function(res){
				if(res == "delete"){
					location.reload();
				} else {
					$(this).val(res);	
				}
			},
			error:function(xhr, status, error){
				alert("잘못된 접근입니다");
			}
		});  
/* 		console.log($(this).attr("data-item"));
		console.log($(this).val()); */
	});


$("#ok").on("click", function(){		
	console.log("확인 눌림");
});


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
	var staticMapContainer = document.getElementById('staticMap'), // 이미지 지도를 표시할 div  
	staticMapOption = {
		center : new daum.maps.LatLng(position.coords.latitude,
				position.coords.longitude), // 이미지 지도의 중심좌표
		level : 9, // 이미지 지도의 확대 레벨
		marker : marker
	// 이미지 지도에 표시할 마커 
	};
	var staticMap = new daum.maps.StaticMap(staticMapContainer,
			staticMapOption);

}


function promiseFunction(stateName,userLatitude,userLongitude){
	
	$("#map").empty();
	$("#map").append("<div id='staticMap'></div>");
	
	console.log("클리시 : " +userLatitude+","+userLongitude);
	
	promiseName= stateName;
	latitude = userLatitude;
	longitude = userLongitude;		
	
	console.log("저장되는 : " +latitude+","+longitude);
	
	console.log("저장된는 이름 : "+stateName);
	
	var markers = [
	       	    
	       	    {
	       	        position: new daum.maps.LatLng(userLatitude, userLongitude), 
	       	        text: stateName // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다     
	       	    }
	       	];

	       	var staticMapContainer  = document.getElementById('staticMap'), // 이미지 지도를 표시할 div  
	       	    staticMapOption = { 
	       	        center: new daum.maps.LatLng(userLatitude, userLongitude), // 이미지 지도의 중심좌표
	       	        level: 3, // 이미지 지도의 확대 레벨
	       	        marker: markers // 이미지 지도에 표시할 마커 
	       	    };    

	       	// 이미지 지도를 생성합니다
	       	var staticMap = new daum.maps.StaticMap(staticMapContainer, staticMapOption);
	
	
	
}


</script>



</html>