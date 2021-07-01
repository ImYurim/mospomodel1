<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signup.css"/>
</head>
<body>
	<div class="container">
		<div class="innercontainer">
			<div class="signupdesc">
				회원가입
			</div>
			<div class="signupform">
				<form action="signupAction">
					<div class="useridinput">
						<div class="useriddesc">
							<label for="userid" >아이디:</label>
						</div>
						<div class="inputuserid">
							<input type="text" name="userid">
						</div>
					</div>
					<div class="userpasswordinput">
						<div class="userpassworddesc">
							<label for="userpassword" >비밀번호:</label>
						</div>
						<div class="inputuserpassword">
							<input type="password" name="userpassword">
						</div>

					</div>
					<div class="usernameinput">
						<div class="usernamedesc">
							<label for="username" >별명:</label>
						</div>
						<div class="inputusername">
							<input type="text" name="username">
						</div>
	
					</div>
					<div class="confirmbutton">
						<input class="buttonconfirm" type="submit" value="가입">
					</div>
				</form>

			</div>
		</div>
	</div>

</body>
</html>