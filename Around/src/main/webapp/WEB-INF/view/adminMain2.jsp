<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<link href="<%= request.getContextPath() %>/css/main.css"
	type="text/css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<style type="text/css">
body {
	background-image: url("<%=request.getContextPath()%>/img/bg.jpg");
}

#main {
	height: 200px;
	display: block;
	margin-right: auto;
	margin-left: auto;
	width: 750px;
}

#str {
	width: 300px;
	display: inline-block;
}

#headers {
	margin-right: auto;
	margin-left: auto;
	width: 700px;
}

#headers2 {
	width: 100px;
}

#heade {
	text-align: left;
}
</style>
</head>
<body>
	<br>
	<section>
		<div id="heade">
			<div id="headers">

				<c:url value="/adminMain" var="adminMain" />
				<a href="${adminMain }"><button>메인 페이지</button></a>

				<c:url value="/adminMemberInit" var="adminMemberInit" />
				<a href="${adminMemberInit }"><button>회원 관리</button></a>

				<c:url value="/adminCategory" var="adminCategory" />
				<a href="${adminCategory }"><button>카테고리 관리</button></a>

				<c:url value="/adminHotBoard" var="adminHotBoard" />
				<a href="${adminHotBoard }"><button>광고 관리</button></a>

				<c:url value="/adminwriteHotBoard" var="adminwriteHotBoard" />
				<a href="${adminwriteHotBoard }"><button>광고 추가</button></a>

				<c:url value="/adminLogout" var="adminLogout" />
				<a href="${adminLogout }"><button>로그 아웃</button></a>


			</div>
			<br>
			<div id="headers">
				<input id="str1" type="text" placeholder="글 검색">
			</div>
		</div>
	</section>
	<br>
	<hr>
	<br>
	<section id="main">
		<section class="thumbnails">		    		
			<div id="left"></div>		
			<div id="center"></div>
			<div id = "right"></div>
		</section>
	</section>

</body>

<script type="text/javascript">
	var index = 1;
	var loadData = true;
	var searchMode = false;
	
	window.onload = function() { loadAllBoard(); };	
	
	$("#str").on("keydown",function(e){
		console.log(e.keyCode);
		console.log("다운");
		if(e.keyCode == 13){
			searchMode = true;
			claer();
			search();
		 }
	});
	
	
	 $(window).scroll(function(){
			if  ($(window).scrollTop() >= $(document).height() - $(window).height()){
				console.log("스크롤 감지");
				
				console.log(index);
				if(loadData){
					if(!searchMode){
						loadAllBoard();
					}else{
						search();
					}
					
				}else{
					console.log("데이터가 끝입니다");
				}
			}
		});	

	 function loadAllBoard(){	
			<c:url value = "/mainBoard" var="mainBoard"/>
			console.log("로드 데이터");
				$.ajax({
					type : "get",
					url : "${mainBoard}",
					data : {
						index : index,					
					},
					success:function(res){
						console.log(res);
						if(res.length==0){							
							loadData = false;
						}else{
							++index;
						}					
						$left = $("#left");
						$center = $("#center");
						$right = $("#right");
						$(res).each(function(idx,data){
							<c:url value = "/adminBoard" var="adminBoard"/>
							$newOne = "<a data-poptrox='iframe,600x400' href='${adminBoard}?boardNo=" + data.boardNo
							 		+ "'> <img src='<%=request.getContextPath()%>/upload/"+data.imagePath+"' alt='' />"
						     		+"<h3>" + data.title + "</h3></a>";						
				
							switch(idx%3){
							case 0:
								$left.append($newOne);
								break;
							case 1:
								$center.append($newOne);
								break;
							case 2:
								$right.append($newOne);
								break;
							}					
							
					});
					},
					error:function(request,status,error){
					    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
				});

		}
</script>
</html>