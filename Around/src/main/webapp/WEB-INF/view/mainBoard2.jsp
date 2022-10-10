<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>메인 화면</title>
<meta charset="utf-8" />
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet"
	type="text/css" media="all" />
</head>
<style>
	
</style>
<body>
	<jsp:include page="../layout/header2.jsp"></jsp:include>
	<!-- content -->
	<div class="content-top">
		<div class="container">
			 <div id="mainContents" class="amazing">
				
				
				
			</div> 
		</div>
	</div>
	<!-- content -->
	<!-- footer -->
	<jsp:include page="../layout/footer2.jsp"></jsp:include>
</body>
<script>

var index = 1;
var loadData = true;
var searchMode = false;
var categoring;

window.onload = function() { loadAllBoard(); };

//전체글 보기
$("#btnGetAllBoard").on("click", function(){ clearBoard(); loadAllBoard(); loadData=true;}); 
// 내글보기
$("#btnGetMyBoard").on("click", function(){ clearBoard(); loadMyBoard(); }); 





$(window).scroll(function(){
	if  ($(window).scrollTop() >= $(document).height() - $(window).height()){
		console.log("스크롤 감지");
		
		console.log(index);
		if(loadData){
			if(!searchMode){
				loadAllBoard();
			}else{
				searchBoard();
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
					console.log("끝");
					loadData = false;
				}else{
					++index;
				}					
				$mainContents = $("#mainContents");				
				$(res).each(function(idx,data){
					<c:url value = "/detailBoard" var="detailBoard"/>
					
				     	if(data.imagePath!=null){				     		
				     		console.log("data.boardNo:"+data.boardNo);
				     		$newOne= "<a href='${detailBoard}?boardNo="+data.boardNo+"'><div class='col-md-4 amaze'><div class='portfolio-wrapper'><div class='ama-top'><img src='<%=request.getContextPath()%>/images/"+data.imagePath+".jpg' class ='img-responsive'><ul class='social-ic'><li class='down'></li></ul></div></div><div><h3>"+data.title+"</h3></div></div></a>";
				     		
				     	
				     	
				     	}else{				     		
				     		$newOne= "<a href='${detailBoard}?boardNo="+data.boardNo+"'><div class='col-md-4 amaze'><div class='portfolio-wrapper'><div class='ama-top'><img src='<%=request.getContextPath()%>/images/thumbs/06.jpg' class ='img-responsive'><ul class='social-ic'><li class='down'></li></ul></div></div><div><h3>"+data.title+"</h3></div></div></a>";
				     	}
				     	
				    	
				     
		
					
						$mainContents.append($newOne);
									
					
			});
			},
			error:function(request,status,error){
			    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
		});

}

function loadMyBoard(){	
	<c:url value = "/selectMyBoard" var="selectMyBoard"/>
	console.log("로드 데이터");
		$.ajax({
			type : "get",
			url : "${selectMyBoard}",
			data : {
				index : index,					
			},
			success:function(res){
				console.log(res);
				if(res.length==0){
					console.log("끝");
					loadData = false;
				}else{
					++index;
				}					
				$mainContents = $("#mainContents");	
				$(res).each(function(idx,data){
					if(data.imagePath!=null){				     		
			     		console.log("data.boardNo:"+data.boardNo);
			     		$newOne= "<a href='${detailBoard}?boardNo="+data.boardNo+"'><div class='col-md-4 amaze'><div class='portfolio-wrapper'><div class='ama-top'><img src='<%=request.getContextPath()%>/upload/"+data.imagePath+"' class ='img-responsive'><ul class='social-ic'><li class='down'></li></ul></div></div><div><h1>"+data.title+"</h1></div></div></a>";
			     		
			     	
			     	
			     	}else{				     		
			     		$newOne= "<a href='${detailBoard}?boardNo="+data.boardNo+"'><div class='col-md-4 amaze'><div class='portfolio-wrapper'><div class='ama-top'><img src='<%=request.getContextPath()%>/img/thumbs/06.jpg' class ='img-responsive'><ul class='social-ic'><li class='down'></li></ul></div></div><div><h1>"+data.title+"</h1></div></div></a>";
			     	}
			     	
			    	
			     
	
				
					$mainContents.append($newOne);					
					
			});
			},
			error:function(request,status,error){
			    console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
		});

}




function clearBoard(){
	index = 1;
	$mainContents = $("#mainContents");
	$mainContents.empty();	
}


</script>
</html>