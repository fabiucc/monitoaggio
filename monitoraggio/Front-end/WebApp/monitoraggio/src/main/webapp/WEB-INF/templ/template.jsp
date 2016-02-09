<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html lang="en">
<head>
<tiles:insertAttribute name="meta" />
</head>
<body background="resources/image/OndeBlu.png">
    <a href="/monitoraggio/" class="hidden-tablet hidden-phone"> <img
		align="left" width="180"
		src="resources/image/logoReplyMod.png"
		style="margin-right: 1%;margin-left: 2%">
	</a>
	<a href="/monitoraggio/" class="visible-phone visible-tablet"> <img
		align="left" width="60px"
		src="resources/image/martello.png"
		style="margin-right: 1%;margin-left: 2%">
	</a>
	<tiles:insertAttribute name="navbar" />
	<div class="container-fluid">
		<div id="content">
			<tiles:insertAttribute name="content" />
		</div>
		<hr>
		<tiles:insertAttribute name="footer" />
	</div>
	<script type='text/javascript' src="resources/js/bootstrap-dropdown.js"></script>
	<script type='text/javascript' src="resources/js/bootstrap-button.js"></script>
	<script type='text/javascript' src="resources/js/bootstrap-collapse.js"></script>
	<script type='text/javascript' src="resources/js/bootstrap-carousel.js"></script>
	<script type='text/javascript'
		src='resources/js/jquery.timepicker.min.js'></script>
	<script type='text/javascript'
		src='resources/js/jquery.dataTables.min.js'></script>
	<script type='text/javascript' src="resources/js/project.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery-ui-1.10.4.min.js"></script>
</body>
</html>
