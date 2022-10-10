<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 화면</title>
<meta charset="utf-8" />
<link href="<%= request.getContextPath() %>/css/style.css" rel="stylesheet"
	type="text/css" media="all" />
</head>
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
	
	window.onload = function() { loadAllCategoryBoard(); };	
	
	
	$(window).scroll(function(){
		if ($(window).scrollTop() >= $(document).height() - $(window).height()){
			console.log("스크롤 감지");
			console.log(index);
			if(loadData){
				if(!searchMode){
					loadAllCategoryBoard();
				}else{
					searchCategoryBoard();
				}					
			}else{
					console.log("데이터가 끝입니다");
			}
		}
	});
	
	$("#str").on("keydown",function(e){
		console.log(e.keyCode);
		console.log("다운");
		if(e.keyCode == 13){
			searchMode = true;
			claerCategoryBoard();
			searchCategoryBoard();
			}
	});
	
	
	
	function searchCategoryBoard(){
		<c:url value = "/searchCategoryBoard" var="searchCategoryBoard"/>
		console.log("로드 데이터");
		$.ajax({
			type : "get",
			url : "${searchCategoryBoard}",
			data : {
				index : index,
				categoryNo : <%=request.getAttribute("categoryNo") %>,
				searchStr :$("#str").val() 
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
				     		$newOne= "<a href='${detailBoard}?boardNo="+data.boardNo+"'><div class='col-md-4 amaze'><div class='portfolio-wrapper'><div class='ama-top'><img src='<%=request.getContextPath()%>/upload/"+data.imagePath+"' class ='img-responsive'><ul class='social-ic'><li class='down'></li></ul></div></div><div><h3>"+data.title+"</h3></div></div></a>";
				     		
				     	
				     	
				     	}else{				     		
				     		$newOne= "<a href='${detailBoard}?boardNo="+data.boardNo+"'><div class='col-md-4 amaze'><div class='portfolio-wrapper'><div class='ama-top'><img src='<%=request.getContextPath()%>/img/thumbs/06.jpg' class ='img-responsive'><ul class='social-ic'><li class='down'></li></ul></div></div><div><h3>"+data.title+"</h3></div></div></a>";
				     	}
						$mainContents.append($newOne);
				});
			},
			error:function(request,status,error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
			});
		}
	
	
	function claerCategoryBoard(){
		index = 1;
		$mainContents = $("#mainContents");
		$mainContents.empty();	
	}
				
				
	function loadAllCategoryBoard(){
		<c:url value = "/categoryBoard" var="categoryBoard"/>
		console.log("로드 데이터");
		$.ajax({
			type : "get",
			url : "${categoryBoard}",
			data : {
				index : index,
				categoryNo : <%=request.getAttribute("categoryNo") %>,
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
				     		$newOne= "<a href='${detailBoard}?boardNo="+data.boardNo+"'><div class='col-md-4 amaze'><div class='portfolio-wrapper'><div class='ama-top'><img src='<%=request.getContextPath()%>/upload/"+data.imagePath+"' class ='img-responsive'><ul class='social-ic'><li class='down'></li></ul></div></div><div><h3>"+data.title+"</h3></div></div></a>";
				     		
				     	}else{				     		
				     		$newOne= "<a href='${detailBoard}?boardNo="+data.boardNo+"'><div class='col-md-4 amaze'><div class='portfolio-wrapper'><div class='ama-top'><img src='<%=request.getContextPath()%>/img/thumbs/06.jpg' class ='img-responsive'><ul class='social-ic'><li class='down'></li></ul></div></div><div><h3>"+data.title+"</h3></div></div></a>";
				     	}
				     	$mainContents.append($newOne);
				});
			},
			error:function(request,status,error){
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
			});
	}

</script>
</html>