<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signin.css"/>
</head>
<body>
	<div class="container">
		<div class="innercontainer">
			<div class="signindesc">
				로그인
			</div>
			<div class="signinform">
				<form action="signinAction" method="POST">
					<div class="useridinput">
						<div class="useriddesc">
							<label for="userid" >아이디:</label>
						</div>
						<div class="inputuserid">
							<input name="userid">
						</div>
					</div>
					<div class="userpasswordinput">
						<div class="userpassworddesc">
							<label for="userpassword" >비밀번호:</label>
						</div>
						<div class="inputuserpassword">
							<input name="userpassword" type="password">
						</div>

					</div>

					<div class="confirmbutton">
						<input type="button" class="" value="회원가입" onclick="location.href='signUp'"> 
						<input class="buttonconfirm" type="submit" value="로그인">
						
					</div>
				</form>

			</div>
		</div>
	</div>

</body>
</html>