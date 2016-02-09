<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<html>
<body>
	<div class="row-fluid">
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2 style="color:white;">${title}</h2>
				<h2 style="color:white;">${message}</h2>
			</div>
			<div class="box-content">
				<div class="control-group table-responsive">
					<table class="table">
						<tr>
							<td style="width: 30%">
								<h4>Benvenuto nella console di monitoraggio </h4>
								<ul>
									<li>Implementa le tue classi per ottenere le funzionalita'
										richieste</li>
									<li>Configurazione dinamica delle pagine </li>
									<li>Configuarazione di spring security per
										l'Authentication and Authorization</li>
								</ul>
							</td>
							<td><a href="/monitoraggio/index"  class="visible-phone visible-tablet btn btn-round" style="color: black;">Login</a></td>
							<td width="60%"><img src="resources/image/martellHome.png" align="right" width="50%"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
