<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>

<div class="container">
	<div class="usercontainer">
	<%
if(session.getAttribute("userid")==null){

%>
	<div class="signup">
		<a href="signUp">회원가입</a>
	</div>
	<div class="signin">
		<a href="signIn">로그인</a>
	</div>
<% }else{%>
	<div class="logout">
		<a href="logOut">로그아웃</a>
	</div>
<%
}
%>

	</div>
	<div class="innercontainer">
		<div class="head">
			<h3 class="discription">영화의<b>리뷰</b>부터<b>스포일러</b>까지</h3>
			<h1 class = "title">MoSpo</h1>
		</div>
		<div class="content">
			<form action="searchAction" method="POST">
				<div class="searchcontainer">
					<div class="searchinput"><input type="text" name="moviename" class ="inputsearch"></div>
					<div class="searchbutton"><input type="submit" value="GO" class="buttonsearch"></div>	
					
				</div>
			</form>
		</div>
	</div>
</div>

</body>
</html>