/**
 * 위치 자바스크립트
 */

var latitude;
var longitude;
var currentLocation; 
var map;
var marker; // 중심 위치의 마커
var markers = []; // 중심 이외의 마커들
var markerDirs = []; // 마커들의 방향
var clickLine // 마우스로 클릭한 좌표로 그려질 선 객체입니다
var distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다

// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new daum.maps.InfoWindow({
	zIndex : 1
});

// 장소 검색 객체
var ps;

if (!!navigator.geolocation) {
	navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
} else {
	alert("이 브라우저는 Geolocation를 지원하지 않습니다");
}

function successCallback(position) {
	latitude = position.coords.latitude;
	longitude = position.coords.longitude;

	console.log("latitude : " + latitude + "longitude" + longitude);
	
	currentLocation = new daum.maps.LatLng(latitude,longitude);
	/*document.getElementById("initLocation").innerHTML += "위도 : " + latitude
			+ ", <br>경도 : " + longitude*/

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
		center : new daum.maps.LatLng(latitude, longitude), // 지도의 중심좌표
		// draggable: false, // 지도를 생성할때 지도 이동 및 확대/축소를 막으려면 draggable: false
		// 옵션을 추가하세요
		level : 5
	// 지도의 확대 레벨
	};

	// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
	map = new daum.maps.Map(mapContainer, mapOption);

	// 지도를 클릭한 위치에 표출할 마커입니다
	marker = new daum.maps.Marker({
		// 지도 중심좌표에 마커를 생성합니다
		position : map.getCenter()
	});

	// 지도에 마커를 표시합니다
	marker.setMap(map);

	// 마커가 드래그 가능하도록 설정합니다
	marker.setDraggable(true);

	// 장소 검색 객체를 생성합니다
	ps = new daum.maps.services.Places(map);

	// 마커에 마우스오버 이벤트를 등록합니다
	daum.maps.event.addListener(marker, 'mouseover', function() {
		// 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
		infowindow.open(map, marker);
	});

	// 마커에 마우스아웃 이벤트를 등록합니다
	daum.maps.event.addListener(marker, 'mouseout', function() {
		// 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
		infowindow.close();
	});

	// 지도에 클릭 이벤트를 등록합니다
	// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
	daum.maps.event.addListener(map, 'click',
			function(mouseEvent) {
				// 마우스 클릭 이벤트 선택자
				var selectedClickEvent = document
						.getElementById("selectedClickEvent").value;
				console.log("selectedClickEvent : " + selectedClickEvent);

				if (selectedClickEvent == "createMarker") {
					// 클릭한 위치에 마커를 표시합니다
					// removeMarker();
					addMarker(mouseEvent.latLng);
				} else if (selectedClickEvent == "calcDirecion") {
					// calcDirecion(new daum.maps.LatLng(36.815129,
					// 127.11389389999998), mouseEvent.latLng);
					calcDirecion(currentLocation, mouseEvent.latLng);
				}

				// 클릭한 위도, 경도 정보를 가져옵니다
				var latlng = mouseEvent.latLng;

				var message = '위도 : ' + latlng.getLat();
				message += '<br>경도 : ' + latlng.getLng();

				/*var resultDiv = document.getElementById('clieckedLocation');
				resultDiv.innerHTML = message;*/
			});

	// 최초 지도 레벨
	var level = map.getLevel();

	var message = "현재 지도 레벨 : " + level;
	var resultDiv = document.getElementById('currentLevel');
	/*resultDiv.innerHTML = message;*/

	// 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
	daum.maps.event.addListener(map, 'zoom_changed', function() {

		// 지도의 현재 레벨을 얻어옵니다
		var level = map.getLevel();

		var message = "현재 지도 레벨 : " + level;
		/*var resultDiv = document.getElementById('currentLevel');
		resultDiv.innerHTML = message;*/

	});

	// 지도가 이동, 확대, 축소로 인해 중심좌표가 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
	daum.maps.event.addListener(map, 'center_changed', function() {

		// 지도의 레벨을 얻어옵니다
		var level = map.getLevel();

		// 지도의 중심좌표를 얻어옵니다
		var latlng = map.getCenter();

		/*document.getElementById("initLocation").innerHTML = "위도 : "
				+ latlng.getLat() + " <br>경도 : " + latlng.getLng();*/

	});
}

function errorCallback(error) {
	alert(error.message);
}

// 마커 하나를 지도위에 표시합니다
addMarker(new daum.maps.LatLng(33.450701, 126.570667));

// 마커를 생성하고 지도위에 표시하는 함수입니다
function addMarker(position) {

	// 마커를 생성합니다
	var marker = new daum.maps.Marker({
		position : position
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);

	// 생성된 마커를 배열에 추가합니다

	markers.push(marker);

	return marker;
}

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

// "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
function showMarkers() {
	setMarkers(map)
}

// "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
function hideMarkers() {
	setMarkers(null);
}

function setCenter() {
	console.log("lat : " + latitude + "lng : " + longitude);
	// 이동할 위도 경도 위치를 생성합니다
	var moveLatLon = new daum.maps.LatLng(latitude, longitude);

	// 지도 중심을 이동 시킵니다
	map.setCenter(moveLatLon);
}

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB(status, data, pagination) {
	console.log("placeSearch 시작");
	if (status === daum.maps.services.Status.OK) {
		console.log("data.places.length : " + data.places.length);
		// 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
		console.log("data : " + data.places);
		displayPlaces(data.places);
	} else if (status === daum.maps.services.Status.ZERO_RESULT) {
		// 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

	} else if (status === daum.maps.services.Status.ERROR) {
		// 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
	}
}

// 지도에 마커를 표출하는 함수입니다
function displayPlaces(places) {
	for (var i = 0; i < places.length; i++) {

		// 마커를 생성하고 지도에 표시합니다
		var marker = addMarker(new daum.maps.LatLng(places[i].latitude,
				places[i].longitude));

		// 마커와 검색결과 항목을 클릭 했을 때
		// 장소정보를 표출하도록 클릭 이벤트를 등록합니다
		(function(marker, place) {
			daum.maps.event.addListener(marker, 'click', function() {
				// 마우스 클릭 이벤트가 인포 윈도우 일때만 작동
				var selectedClickEvent = document
						.getElementById("selectedClickEvent").value;
				console.log("selectedClickEvent : " + selectedClickEvent);

				if (selectedClickEvent == "infoWindow") {
					displayPlaceInfo(marker, place);
				} else if (selectedClickEvent == "createRect") {
					createRectangle(place.latitude, place.longitude);
				} else if (selectedClickEvent == "calcDistance") {
					drawLineByTargetLocation(place);
				} else if (selectedClickEvent == "exeCompass") {
					displayPlaceInfo(marker, place);
					calcDirecion(currentLocation, place);
					drawLineByTargetLocation(place);
				}

			});
		})(marker, places[i]);
	}
}

function removeMarker() {
	console.log("removeMarker 시작");
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	markers = [];
}

function displayPlaceInfo(marker, place) {
	// 인포윈도우에 띄울 내용 작성
	var content = '<div class="placeinfo">' + '   <a class="title" href="'
			+ place.placeUrl + '" target="_blank" title="' + place.title + '">'
			+ place.title + '</a>';

	if (place.newAddress) {
		content += '    <span title="' + place.newAddress + '">'
				+ place.newAddress + '</span>'
				+ '  <span class="jibun" title="' + place.address + '">(지번 : '
				+ place.address + ')</span>';
	} else {
		content += '    <span title="' + place.address + '">' + place.address
				+ '</span>';
	}

	content += '    <span class="tel">' + place.phone + '</span>' + '</div>'
			+ '<div class="after"></div>';

	// 인포윈도우에 컨텐츠 장착
	infowindow.setContent(content);

	var infowindowPosition = new daum.maps.LatLng(place.latitude,
			place.longitude);

	infowindow.setPosition(infowindowPosition);

	infowindow.open(map, marker);
	setTimeout(function() {
		infowindow.close();
	}, 2000);

}

function displayBoardInfo(marker, board) {
	// 인포윈도우에 띄울 내용 작성
	var content = '<div class="boardInfo">' + board.title + '<br> '
			+ board.content + '</div>';

	// 인포윈도우에 컨텐츠 장착
	infowindow.setContent(content);

	var infowindowPosition = new daum.maps.LatLng(board.latitude,
			board.longitude);
	infowindow.setPosition(infowindowPosition);

	infowindow.open(map, marker);
	setTimeout(function() {
		infowindow.close();
	}, 1000);
}

function selectCategoryByCode() {
	console.log("selectCategoryByCode 시작");

	var categoryCode = document.getElementById("categoryCode").value;
	console.log("categoryCode : " + categoryCode);

	if (categoryCode != null) {
		removeMarker();
		console.log(map);
		switch (categoryCode) {
		case "MT1":
			ps.categorySearch('MT1', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "CS2":
			ps.categorySearch('CS2', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "PS3":
			ps.categorySearch('PS3', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "SC4":
			ps.categorySearch('SC4', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "AC5":
			ps.categorySearch('AC5', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "PK6":
			ps.categorySearch('PK6', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "OL7":
			ps.categorySearch('OL7', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "SW8":
			ps.categorySearch('SW8', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "BK9":
			ps.categorySearch('BK9', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "CT1":
			ps.categorySearch('CT1', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "AG2":
			ps.categorySearch('AG2', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "PO3":
			ps.categorySearch('PO3', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "AT4":
			ps.categorySearch('AT4', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "AD5":
			ps.categorySearch('AD5', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "FD6":
			ps.categorySearch('FD6', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "CE7":
			ps.categorySearch('CE7', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "HP8":
			ps.categorySearch('HP8', placesSearchCB, {
				useMapBounds : true
			});
			break;
		case "PM9":
			ps.categorySearch('PM9', placesSearchCB, {
				useMapBounds : true
			});
			break;
		}
	}
}

function createRectangle(latitude, longitude) {
	console.log("createRectangle 시작");
	var rectRange = 0.0025; // 중심점에서 정사각형 변 까지의 거리, 0.0025 = 약 250미터

	var sw = new daum.maps.LatLng(latitude - rectRange, longitude - rectRange); // 사각형
	// 영역의
	// 남서쪽
	// 좌표
	var ne = new daum.maps.LatLng(parseFloat(latitude) + rectRange,
			parseFloat(longitude) + rectRange); // 사각형 영역의 북동쪽 좌표

	console.log("sw : " + sw + ", ne : " + ne);
	// 사각형을 구성하는 영역정보를 생성합니다
	// 사각형을 생성할 때 영역정보는 LatLngBounds 객체로 넘겨줘야 합니다
	var rectangleBounds = new daum.maps.LatLngBounds(sw, ne);

	// 지도에 표시할 사각형을 생성합니다
	var rectangle = new daum.maps.Rectangle({
		bounds : rectangleBounds, // 그려질 사각형의 영역정보입니다
		strokeWeight : 4, // 선의 두께입니다
		strokeColor : '#FF3DE5', // 선의 색깔입니다
		strokeOpacity : 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		strokeStyle : 'shortdashdot', // 선의 스타일입니다
		fillColor : '#FF8AEF', // 채우기 색깔입니다
		fillOpacity : 0.8
	// 채우기 불투명도 입니다
	});

	// 지도에 사각형을 표시합니다
	rectangle.setMap(map);

	var downCount = 0;
	// 사각형에 마우스다운 이벤트를 등록합니다
	daum.maps.event.addListener(rectangle, 'mousedown', function() {
		console.log(event);
		var resultDiv = document.getElementById('result');
		resultDiv.innerHTML = '사각형에 mousedown 이벤트가 발생했습니다!' + (++downCount);
	});
}

function moveCenterByValues() {
	var targetLatitude = document.getElementById("targetLatitude").value;
	var targetLongitude = document.getElementById("targetLongitude").value;

	var moveLatLon = new daum.maps.LatLng(targetLatitude, targetLongitude);

	// 지도 중심을 이동 시킵니다
	map.setCenter(moveLatLon);
}

// 클릭한 좌표 까지 투명선 그리기
function drawLineByTargetLocation(place) {

	// 이미 그려진 라인이 있다면 삭제.
	if (clickLine) {
		deleteClickLine();
	}

	// 마우스로 클릭한 위치입니다
	var clickPosition = new daum.maps.LatLng(place.latitude, place.longitude);
	// 맵의 중심 마커 좌표
	var centerMakerPosition = marker.getPosition();

	clickLine = new daum.maps.Polyline({
		map : map, // 선을 표시할 지도입니다
		path : [ centerMakerPosition, clickPosition ], // 선을 구성하는 좌표 배열입니다 클릭한
		// 위치를 넣어줍니다
		strokeWeight : 3, // 선의 두께입니다
		strokeColor : '#db4040', // 선의 색깔입니다
		strokeOpacity : 0, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
		strokeStyle : 'solid' // 선의 스타일입니다
	});

	var distance = Math.round(clickLine.getLength()), content = '<div class="dotOverlay distanceInfo">남은 거리 <span class="number">'
			+ distance + '</span>m</div>'; // 커스텀오버레이에 추가될 내용입니다

	clickLine.setMap(map);
	
	var socPosition = new daum.maps.LatLng(latitude, longitude);

	showDistance(content, socPosition);
}

// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
function deleteClickLine() {
	if (clickLine) {
		clickLine.setMap(null);
		clickLine = null;
	}
}

// 그려지고 있는 선의 총거리 정보와
// 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
function deleteDistnce() {
	if (distanceOverlay) {
		distanceOverlay.setMap(null);
		distanceOverlay = null;
	}
}

// 선의 총거리 정보를 표시하기
function showDistance(content, position) {

	if (distanceOverlay) { // 커스텀오버레이가 생성된 상태이면
		// 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
		distanceOverlay.setPosition(position);
		distanceOverlay.setContent(content);

	} else { // 커스텀 오버레이가 생성되지 않은 상태이면

		// 커스텀 오버레이를 생성하고 지도에 표시합니다
		distanceOverlay = new daum.maps.CustomOverlay({
			map : map, // 커스텀오버레이를 표시할 지도입니다
			content : content, // 커스텀오버레이에 표시할 내용입니다
			position : position, // 커스텀오버레이를 표시할 위치입니다.
			xAnchor : 0,
			yAnchor : 0,
			zIndex : 3
		});
	}
}

function calcDirecion(socLoc, place) {

	// 마우스로 클릭한 위치입니다
	var targetLoc = new daum.maps.LatLng(place.latitude, place.longitude);

	// 기준좌표점과 목표좌표점 사이의 각도
	var centerDegree = getDegree(socLoc, targetLoc);

	var message = "각도 : " + centerDegree, resultDiv = document
			.getElementById("clieckedDegree");

	//resultDiv.innerHTML = message;

	// drawCenterX,Y : 그려질 삼각형의 중심좌표(x,y) , distance : 기준 좌표점과의 거리
	var drawCenterX, drawCenterY, distanceCenter;

	distanceCenter = 0.0005;
	drawCenterX = Math.sin(centerDegree / 180 * Math.PI) * distanceCenter;
	drawCenterY = Math.cos(centerDegree / 180 * Math.PI) * distanceCenter;

	drawCenterX += socLoc.getLat();
	drawCenterY += socLoc.getLng();

	var drawLightX, drawLightY, distanceLight, degreeDistance;
	degreeDistance = 50;

	distanceLight = Math.sin(degreeDistance / 180 * Math.PI) * distanceCenter;

	drawLightX = Math.sin((centerDegree - degreeDistance) / 180 * Math.PI)
			* distanceLight;
	drawLightY = Math.cos((centerDegree - degreeDistance) / 180 * Math.PI)
			* distanceLight;

	drawLightX += socLoc.getLat();
	drawLightY += socLoc.getLng();

	var drawLeftX, drawLeftY, distanceLeft;

	distanceLeft = Math.sin(degreeDistance / 180 * Math.PI) * distanceCenter;

	drawLeftX = Math.sin((centerDegree + degreeDistance) / 180 * Math.PI)
			* distanceLeft;
	drawLeftY = Math.cos((centerDegree + degreeDistance) / 180 * Math.PI)
			* distanceLeft;

	drawLeftX += socLoc.getLat();
	drawLeftY += socLoc.getLng();

	console.log("drawLightX : " + drawLightX + " drawLightY : " + drawLightY);
	console.log("drawLeftX : " + drawLeftX + " drawLeftY : " + drawLeftY);

	// 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
	var linePath = [ new daum.maps.LatLng(drawLeftX, drawLeftY),
			new daum.maps.LatLng(drawCenterX, drawCenterY),
			new daum.maps.LatLng(drawLightX, drawLightY) ];

	// 지도에 표시할 선을 생성합니다
	var polyline = new daum.maps.Polyline({
		path : linePath, // 선을 구성하는 좌표배열 입니다
		strokeWeight : 5, // 선의 두께 입니다
		strokeColor : '#FFAE00', // 선의 색깔입니다
		strokeOpacity : 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		strokeStyle : 'solid' // 선의 스타일입니다
	});
	removeMarkerDir();
	markerDirs.push(polyline);

	// 지도에 선을 표시합니다
	polyline.setMap(map);

}

function getDegree(socLoc, targetLoc) {
	var socX = socLoc.getLat(), socY = socLoc.getLng(), targetX = targetLoc
			.getLat(), targetY = targetLoc.getLng(), degree;

	degree = Math.atan2(targetX - socX, targetY - socY) * 180 / Math.PI;
	return degree;
}

function removeMarkerDir() {
	if (markerDirs) {
		for (var i = 0; i < markerDirs.length; i++) {
			markerDirs[i].setMap(null);
		}
		markerDirs = [];
	}
}