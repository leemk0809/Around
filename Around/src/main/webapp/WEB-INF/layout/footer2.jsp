<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="footer-bottom">
	<div class="container">
		<div class="foot-left">
			<div class="foot-nav">
				<ul>
					<li class="active">
					<c:url value="/category" var="category"/>
					<a href="${category }">Category</a></li>
					<c:url value="/writeBoard" var="writeBoard" />
					<li><a href="${writeBoard }">WriteBoard</a></li>
					<li><a href="blog.html">MyBoard</a></li>
					<c:url value="/mylocation" var="myLocation"/>
					<li><a href="${myLocation }">SaveLocation</a></li>
					<li><a id="btnGetAllBoard">AllBoard</a></li>
					<c:url value="/promiseBoard" var="promiseBoard"/>
					<li><a href="${promiseBoard }">Promise</a></li>
					<div class="clearfix"></div>
				</ul>
			</div>
		</div>
		<div class="foot-right">
			<p>© Arround 2016 Project 3 team</p>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
<script>
$("#btnGetAllBoard").on("click", function() {
	<c:url value="/returnMainBoard" var="returnMainBoard"/>
	document.location.href = "returnMainBoard";
});
</script>