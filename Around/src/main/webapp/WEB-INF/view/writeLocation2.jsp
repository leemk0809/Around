<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<title>글 쓰기</title>
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
#loginView {
	float: right;
	width: 50%;
	display: block;
}

#content {
	width: 60%;
	margin-left: 20%;
	height: 100%;
	clear: both;
}

#list {
	margin-top: 10%;
	float: right;
	width: 40%;
	height: 20%;
	overflow: auto;
	margin-right: 30%;
}

#bottomBtns {
	clear: both;
	text-align: center;
	margin-left: -10%;
}

#staticMap {
	margin-top: 15%;
	margin-left: 0%;
	width: 90%;
	height: 60%;
}

@media screen and (min-width: 0px) and (max-width:400px) {
	#staticMap {
		margin-top: 15%;
		margin-left : -25%;
		width: 150%;
		height: 30%;
	}
	label{
		padding-top : 10%;
		padding-left :30%;
	}
	#list{
		width : 120%;
		margin-left : 20%;
		padding-left : 30%;
		margin : 0 auto;
		margin-bottom : 20%;
	}
	#bottomBtns>button{
		margin: 0 auto;
	}
	
}

label {
	color: white;
	margin-top: 10%;
	margin-left: 14%;
	margin: 0 auto;
}

button {
	margin: 5%;
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
				<div id="map">
					<div id="staticMap"></div>
				</div>
				<label>저장된 장소</label>
				<div id="list">
					<c:forEach items="${userLocations}" var="userLocation">
						<a
							onclick="locationaaa('${userLocation.locationName }',${userLocation.latitude },${userLocation.longitude })"><input
							type="text" readonly="readonly"
							value="${userLocation.locationName }"> </a>

					</c:forEach>

				</div>
				<div id="bottomBtns">
					<%-- <a href="${writeBoardLocation}?latitude=<%=latitude%>&longitude=<%=longitude%>"><button>현위치 저장</button></a> --%>
					<button id="nowLocation">현위치 저장</button>
					<button id="ok">확인</button>
					<button>닫기</button>
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
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=57b5102d7ef555237de225d53a65e93e"></script>
<script>
	var x = document.getElementById("demo");
 	var latitude;
	var longitude;
	var locationName;
	
	$("#nowLocation").on("click", function(){		
		document.location.href="writeBoardLocation?latitude="+latitude+"&longitude="+longitude;
	});
	
	$("#location").on("click",function(){
		console.log($("#location").html());
		console.log("click");
	});
	$("#ok").on("click", function(){		
		document.location.href="writeBoardLocations?latitude="+latitude+"&longitude="+longitude+"&locationName="+locationName;
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
			level : 3, // 이미지 지도의 확대 레벨
			marker : marker
		// 이미지 지도에 표시할 마커 
		};
		var staticMap = new daum.maps.StaticMap(staticMapContainer,
				staticMapOption);

	}
	
	$("#btn2").on("click",function(){
		console.log("클릭");
		//<c:url value = "/savelocation" var="savelocation"/>
		$.ajax({
			type:"get",
			url:"${savelocation}",
			data :{
				latitude : latitude,
				longitude : longitude,				
			},
			success:function(res){
				console.log("전송");
				$locationDiv = $("#locationDiv");
				$locationDiv.empty();
				$(res).each(function(idx,data){					
					$newOne = "<h3>"+ data.userNo +"    "+ data.latitude + "  " + data.longitude+ " <h3>"; 					
					$locationDiv.append($newOne);
				});
			},
			error:function(request,status,error){
			    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		
		});
	});
	
	function locationaaa(stateName,userLatitude,userLongitude){
		
		$("#map").empty();
		$("#map").append("<div id='staticMap'></div>");
		
		console.log("클리시 : " +userLatitude+","+userLongitude);
		
		locationName= stateName;
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