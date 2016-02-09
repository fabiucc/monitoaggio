
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="description"
	content="Metro, a sleek, intuitive, and powerful framework for faster and easier web development for Windows Metro Style.">
<meta name="keywords"
	content="HTML, CSS, JS, JavaScript, framework, metro, front-end, frontend, web development">
<meta name="author"
	content="Sergey Pimenov and Metro UI CSS contributors">

<link rel='shortcut icon' type='image/x-icon' href='../favicon.ico' />

<title>Login form</title>

<link href="resources/css/metro.css" rel="stylesheet">
<link href="resources/css/metro-icons.css" rel="stylesheet">
<link href="resources/css/metro-responsive.css" rel="stylesheet">

<script src="resources/js/jquery-2.1.3.min.js"></script>
<script src="resources/js/metro.js"></script>

<style>
.login-form {
	width: 25rem;
	height: 18.75rem;
	position: fixed;
	top: 50%;
	margin-top: -9.375rem;
	left: 50%;
	margin-left: -12.5rem;
	background-color: #ffffff;
	opacity: 0;
	-webkit-transform: scale(.8);
	transform: scale(.8);
}
</style>

<script>
	/*
	 * Do not use this is a google analytics fro Metro UI CSS
	 * */
	if (window.location.hostname !== 'localhost') {

		(function(i, s, o, g, r, a, m) {
			i['GoogleAnalyticsObject'] = r;
			i[r] = i[r] || function() {
				(i[r].q = i[r].q || []).push(arguments)
			}, i[r].l = 1 * new Date();
			a = s.createElement(o), m = s.getElementsByTagName(o)[0];
			a.async = 1;
			a.src = g;
			m.parentNode.insertBefore(a, m)
		})(window, document, 'script',
				'//www.google-analytics.com/analytics.js', 'ga');

		ga('create', 'UA-58849249-3', 'auto');
		ga('send', 'pageview');

	}

	$(function() {
		var form = $(".login-form");

		form.css({
			opacity : 1,
			"-webkit-transform" : "scale(1)",
			"transform" : "scale(1)",
			"-webkit-transition" : ".5s",
			"transition" : ".5s"
		});
	});
</script>
</head>
<body class="bg-darkTeal">
	<div class="login-form padding20 block-shadow">
		<form name='login-form'
			action="<c:url value='j_spring_security_check' />" method='POST'>
			<h1 class="text-light">Login to portal</h1>
			<hr class="thin" />
			<br />
			<div class="input-control text full-size" data-role="input">
				<label for="user_login">User email:</label> <input type="text"
					name="username" id="user_login">
				<button class="button helper-button clear">
					<span class="mif-cross"></span>
				</button>
			</div>
			<br /> <br />
			<div class="input-control password full-size" data-role="input">
				<label for="user_password">User password:</label> <input
					type="password" name="password" id="user_password">
				<button class="button helper-button reveal">
					<span class="mif-looks"></span>
				</button>
			</div>
			<br /> <br />
			<div class="form-actions">
				<button type="submit" class="button primary" onclick="sendAjax()">Login
					to...</button>
				<button type="button" class="button link">Cancel</button>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>

	<!-- hit.ua -->
	<a href='http://hit.ua/?x=136046' target='_blank'> <script
			language="javascript" type="text/javascript">
	<!--
		Cd = document;
		Cr = "&" + Math.random();
		Cp = "&s=1";
		Cd.cookie = "b=b";
		if (Cd.cookie)
			Cp += "&c=1";
		Cp += "&t=" + (new Date()).getTimezoneOffset();
		if (self != top)
			Cp += "&f=1";
	//-->
	</script> <script language="javascript1.1" type="text/javascript">
	<!--
		if (navigator.javaEnabled())
			Cp += "&j=1";
	//-->
	</script> <script language="javascript1.2" type="text/javascript">
	<!--
		if (typeof (screen) != 'undefined')
			Cp += "&w="
					+ screen.width
					+ "&h="
					+ screen.height
					+ "&d="
					+ (screen.colorDepth ? screen.colorDepth
							: screen.pixelDepth);
	//-->
	</script> <script language="javascript" type="text/javascript">
	<!--
		Cd.write("<img src='http://c.hit.ua/hit?i=136046&g=0&x=2" + Cp + Cr
				+ "&r=" + escape(Cd.referrer) + "&u="
				+ escape(window.location.href) + "' border='0' wi"
				+ "dth='1' he" + "ight='1'/>");
	//-->
	</script></a>
	<!-- / hit.ua -->
	



</body>
</html>