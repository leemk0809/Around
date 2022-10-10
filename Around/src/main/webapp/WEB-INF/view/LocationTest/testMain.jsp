<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여러가지 테스트해보자</title>
<style>
#map {
	height: 350px;
}

#main {
	width: 50%;
	margin-left: 20%;
	margin-top: 5%;
}

#location {
	position: relative;
	top: -400px;
	border: 1px solid black;
	width: 17%;
	padding: 1%;
	overflow: hidden;
}

#event {
	position: absolute;
	top: 7%;
	left: 71%;
	border: 1px solid black;
	width: 25%;
	padding: 1%;
}
.dot {overflow:hidden;float:left;width:12px;height:12px;background: url('http://i1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png');}    
.dotOverlay {position:relative;bottom:10px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;font-size:12px;padding:5px;background:#fff;}
.dotOverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}    
.number {font-weight:bold;color:#ee6152;}
.dotOverlay:after {content:'';position:absolute;margin-left:-6px;left:50%;bottom:-8px;width:11px;height:8px;background:url('http://i1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png')}
.distanceInfo {position:relative;top:5px;left:5px;list-style:none;margin:0;}
.distanceInfo .label {display:inline-block;width:50px;}
.distanceInfo:after {content:none;}

</style>
</head>
<body>
	<!-- 지도를 표시할 div 입니다 -->
	<div id="main">
		<div id="map"></div>
		<p>
			<button onclick="setCenter()">최초 중심으로 이동시키기</button>
			<button onclick="removeMarker()">마커 전체 삭제</button>
			<button onclick="viewMyAroundFriendBoard()">주변 친구 글
				표시(visible)</button>
		</p>
		<div id="result"></div>
	</div>
	<!-- 민국 : 위치들을 표시할 div -->
	<div id="location">
		지도 정보
		<hr>
		중심위치<br>
		<div id="initLocation"></div>
		<br> 클릭한 위치<br>
		<div id="clieckedLocation"></div>
		<div id="clieckedDegree"></div>
		<br>
		<div id="currentLevel"></div>
	</div>
	<div id="event">
		이벤트 선택
		<hr>
		마우스 클릭 이벤트 : <select id="selectedClickEvent">
			<option value="exeCompass">나침반 기능 실행</option>
			<option value="calcDirecion">목표점 각도 계산</option>
			<option value="calcDistance">목표점 거리 계산</option>
			<option value="infoWindow">인포 윈도우</option>
			<option value="createMarker">마커 생성</option>
			<option value="createRect">사각형 그리기</option>
		</select><br> <br> 카테고리로 장소 검색 <select id="categoryCode">
			<option value="MT1">대형마트</option>
			<option value="CS2">편의점</option>
			<option value="PS3">유치원</option>
			<option value="SC4">학교</option>
			<option value="AC5">학원</option>
			<option value="PK6">주차장</option>
			<option value="OL7">주유소</option>
			<option value="SW8">지하철역</option>
			<option value="BK9">은행</option>
			<option value="CT1">문화시설</option>
			<option value="AG2">중개업소</option>
			<option value="PO3">공공기관</option>
			<option value="AT4">관광명소</option>
			<option value="AD5">숙박</option>
			<option value="FD6">음식점</option>
			<option value="CE7">카페</option>
			<option value="HP8">병원</option>
			<option value="PM9">약국</option>
		</select>
		<button onclick="selectCategoryByCode();">실행</button>
		<br><br>
		<fieldset>
			<legend>값으로 중심 이동</legend>
			위도 : <input type="text" id="targetLatitude" value="36.852292770282304"><br> 
			경도 : <input type="text" id="targetLongitude" value="127.15026868985895">
			<button onclick="moveCenterByValues()">이동</button>
		</fieldset>
	</div>
</body>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=eb282da5749f4321d58ffa74971044a3&libraries=services"></script>
<script src="<%=request.getContextPath()%>/js/location.js"></script>
<script>
	function viewMyAroundFriendBoard() {
		<c:url value = "/getAroundBoards" var="getAroundBoards"/>
		$.ajax({
			type : "get",
			url : "${getAroundBoards}",
			data : {
				"centerLat" : map.getCenter().getLat(),
				"centerLng" : map.getCenter().getLng()
			},
			success : function(res) {
				console.log(res);
				
				if(res.length > 0){
					$(res).each(function(idx,data){
						var marker = addMarker(new daum.maps.LatLng(data.latitude,
								data.longitude));
						
						daum.maps.event.addListener(marker, 'click', function() {
							// 마우스 클릭 이벤트가 인포 윈도우 일때만 작동
							var selectedClickEvent = document
									.getElementById("selectedClickEvent").value;
							console.log("selectedClickEvent : " + selectedClickEvent);

							if (selectedClickEvent == "infoWindow") {
								displayBoardInfo(marker, data);
							} else if (selectedClickEvent == "createRect") {
								createRectangle(data.latitude, data.longitude);
							} 
						});
					});
				}
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		});
	}
</script>
</html>