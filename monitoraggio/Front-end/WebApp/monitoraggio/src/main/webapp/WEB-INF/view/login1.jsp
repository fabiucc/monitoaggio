<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
</head>
<body onload='document.loginForm.username.focus();'>
	<h1 style="margin-left: 40%";>Security Login Form</h1>
	<div class="box span4" style="margin:auto;float:center;border-radius:5%">
		<div class="box-content" style="background-image: url('resources/image/loginBack.png');border-radius:5%">
			<h3 align="center" style="color:white;">Login with Username and Password</h3>
			<c:if test="${not empty error}">
				<div style="color:white;" align="center">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div style="color:white;" align="center">${msg}</div>
			</c:if>
			<form name='loginForm'
				action="<c:url value='j_spring_security_check' />" method='POST'>
				<table>
					<tr align="center">
						<td style="color:white;">Username:&nbsp&nbsp</td>
						<td><input type='text' name='username' value=''></td>
					</tr>
					<tr align="center">
						<td style="color:white;">Password:&nbsp&nbsp</td>
						<td><input type='password' name='password' /></td>
					</tr>
					<tr align="center">
						<td colspan='2' style="padding-left: 23%;"><input name="submit" class="btn btn-round" type="submit"
							value="submit" /></td>
					</tr>
				</table>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>
</body>
</html>
