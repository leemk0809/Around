<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>카테고리  관리</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link href="<%=request.getContextPath()%>/css/main.css" type="text/css"
	rel="stylesheet">
<style>
	body {
	background-image: url("<%=request.getContextPath()%>/img/bg.jpg");
}
#logo {
	margin-top: 10%;
	text-align: center;
	/* border: solid 1px green; */
}

label {
	display: inline;
}

#logo>button {
	margin-left: 70%;
}

table {
	width: 700px;	
	margin-right:auto;
	margin-left:auto;
	margin-top: 2%;
	/* 	border: solid 1px yellow; */
}

table th {
	text-align: center;
	padding: 0;
}

select {
	color: silver;
}
#main {
		height: 200px;
		display:block;
		margin-right:auto;
		margin-left:auto;
		width: 750px;		
	}
	#str1{
		width : 550px;
		display: inline-block;
	}	
	#headers{		
		margin-right:auto;
		margin-left:auto;
		width: 700px;		
	}
	#headers2{
		width: 100px;
	}
	#heade{
		text-align:left;
	}
</style>
</head>
<body>
	<br>
<section>
	<div id = "heade">	
		<div id="headers">		
			
			<c:url value="/adminMain" var="adminMain"/>
			<a href="${adminMain }"><button>메인 페이지</button></a>	
			
			<c:url value="/adminMemberInit" var="adminMemberInit"/>
			<a href="${adminMemberInit }"><button>회원 관리</button></a>			
			
			<c:url value="/adminCategory" var="adminCategory"/>
			<a href="${adminCategory }"><button>카테고리 관리</button></a>
			
			<c:url value="/adminHotBoard" var="adminHotBoard"/>
			<a href="${adminHotBoard }"><button>광고 관리</button></a>
			
			<c:url value="/adminwriteHotBoard" var="adminwriteHotBoard"/>
			<a href="${adminwriteHotBoard }"><button>광고 추가</button></a>
			
			<c:url value="/adminLogout" var="adminLogout"/>
			<a href="${adminLogout }"><button>로그 아웃</button></a>		
			
								
		</div>
		<br>
		<div id="headers">
			<input id="str1"type="text" placeholder="카테고리 검색 및  추가">
			<button id = "searchBtn">검색</button>			
			<button id = "appendBtn">추가</button>
		</div>
	</div>
</section>
<hr>
	
	<div>
		<table id ="test">
			<tr>
				<th align="center">카테고리 목록</th>
			</tr>					
				
		</table>
	</div>
</body>
<script src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">

	var index = 1;
	var loadData = true;
	var searchMode = false;

	window.onload = function() { loadAllCategory(); };	
	
	$("#searchBtn").on("click",function(){
		searchMode = true;
		claer();
		search();		
	});
	
	$("#appendBtn").on("click",function(){
		searchMode = false;
		claer();		
		appendCategory();
		
	});
	 
	 $(window).scroll(function(){
			if  ($(window).scrollTop() >= $(document).height() - $(window).height()){
				console.log("스크롤 감지");
				
				console.log(index);
				if(loadData){
					if(!searchMode){
						loadAllCategory();
					}else{
						search();
					}
					
				}else{
					console.log("데이터가 끝입니다");
				}
			}
		});	
	 
	
	
	
		
	function loadAllCategory(){	
		<c:url value = "/adminCategoryLoad" var="adminCategoryLoad"/>
		console.log("로드 데이터");
			$.ajax({
				type : "get",
				url : "${adminCategoryLoad}",
				data : {
					index : index,					
				},
				success:function(res){
					console.log(res);
					if(res.length==0){
						alert("끝");
						loadData = false;
					}else{
						++index;
					}		
					
					
					
					
					$test = $("#test");					
					$(res).each(function(idx,data){											
						<c:url value="/deleteAdminUser" var="deleteAdminUser"/>								
					    $newOne = "<tr>" 
					     			+"<td align='userNo'><label>"+data.categoryNo +"</label></td>"
					     			+"<td align='userNo'><label>"+data.categoryName +"</label></td>";
					    	 		
					   	
					$test.append($newOne);
							
										
						
					});
				},
				error:function(request,status,error){
				    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});

	} 	 
	 
	 function search(){
		 <c:url value = "/searchCategory" var="searchCategory"/>
				console.log("로드 데이터");
					$.ajax({
						type : "get",
						url : "${searchCategory}",
						data : {
							index : index,
							searchStr :$("#str1").val() 
						},
						success:function(res){
							console.log(res);
							if(res.length==0){
								alert("끝");
								loadData = false;
							}else{
								++index;
							}
							
							$test = $("#test");
							$(res).each(function(idx,data){											
								<c:url value="/deleteAdminUser" var="deleteAdminUser"/>								
								$newOne = "<tr>" 
						     			+"<td align='userNo'><label>"+data.categoryNo +"</label></td>"
						     			+"<td align='userNo'><label>"+data.categoryName +"</label></td>";
						     			
							$test.append($newOne);
										
								
						});
					},
					error:function(request,status,error){
						    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
				});

		}	

	 
	 
	 function appendCategory(){
		 <c:url value = "/insertCategory" var="insertCategory"/>
				console.log("로드 데이터");
					$.ajax({
						type : "get",
						url : "${insertCategory}",
						data : {
							index : index,
							searchStr :$("#str1").val() 
						},
						success:function(res){
							console.log(res);
							if(res.length==0){
								alert("끝");
								loadData = false;
							}else{
								++index;
							}		
							
							$test = $("#test");
							$(res).each(function(idx,data){											
								<c:url value="/deleteAdminUser" var="deleteAdminUser"/>								
							    $newOne = "<tr>" 
							     			+"<td align='userNo'><label>"+data.categoryNo +"</label></td>"
							     			+"<td align='userNo'><label>"+data.categoryName +"</label></td>";
							    	 		
							   	
							$test.append($newOne);
									
												
								
							});
						},
						error:function(request,status,error){
						    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
						}
					});
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		function claer(){
			index = 1;			
			$test = $("#test");		
					
			$test.empty();		
		}
	 	
	 

</script>
</html>