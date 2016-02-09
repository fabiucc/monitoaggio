<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div class="navbar navbar-default" style="border-radius: 0px 0px 0px 10px;">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<div style="margin-top: 7px; margin-right: 2px;"
			class="pull-right hidden-phone hidden-tablet">
			<a class="btn dropdown-toggle" data-toggle="dropdown" style="cursor: default;"> <i
				class="icon-user"></i><span class="hidden-tablet">&nbsp${pageContext.request.userPrincipal.name}</span>
			</a>
			<c:if test="${pageContext.request.userPrincipal.name == null}">
				<a href="/monitoraggio/index" class="hidden-tablet hidden-phone"
					style="color: white;">Login</a>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<a href="javascript:formSubmit()" class="hidden-tablet hidden-phone"
					style="color: white;"> Logout</a>
			</c:if>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
						<li><a href="/monitoraggio/"><b><font size="4">Console
										di monitoraggio</font></b></a></li>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Funzionalita1<b class="caret"></b></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Funzionalita2<b class="caret"></b></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Funzionalita3<b class="caret"></b></a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Funzionalita1<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a>SubFunzionalita1</a></li>
							<li><a>SubFunzionalita2</a></li>
							<li><a>SubFunzionalita3</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Funzionalita4<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a>Gestione Funzionalita'</a></li>
						</ul></li>
				</c:if>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<!--/.container-fluid -->
</div>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
<!-- csrt for log out-->
<form action="${logoutUrl}" method="post" id="logoutForm">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
