<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel='shortcut icon' type='image/x-icon' href='favicon.ico' />
<title>Monitoring Console</title>
<link href="<c:url value="/resources/css/metro.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/metro-icons.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.3.min.js" />"></script>
<script src="<c:url value="/resources/js/metro.js" />"></script>
<style>
.tile-area-controls {
	position: fixed;
	right: 40px;
	top: 40px;
}

.tile-group {
	left: 100px;
}

.tile,.tile-small,.tile-sqaure,.tile-wide,.tile-large,.tile-big,.tile-super
	{
	opacity: 0;
	-webkit-transform: scale(.8);
	transform: scale(.8);
}

.charm.right-side {
	width: 300px;
	right: -300px;
}

#charmSettings .button {
	margin: 5px;
}

@media screen and (max-width: 640px) {
	.tile-area {
		overflow-y: scroll;
		overflow-x: scroll;
	}
	.tile-area-controls {
		display: none;
	}
}

@media screen and (max-width: 320px) {
	.tile-area {
		overflow-y: scroll;
		overflow-x: scroll;
	}
	.tile-area-controls {
		display: none;
	}
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
</script>
<script>
	(function($) {
		$.StartScreen = function() {
			var plugin = this;
			var width = (window.innerWidth > 0) ? window.innerWidth
					: screen.width;
			plugin.init = function() {
				setTilesAreaSize();
				if (width > 640)
					addMouseWheel();
			};
			var setTilesAreaSize = function() {
				var groups = $(".tile-group");
				var tileAreaWidth = 80;
				$.each(groups, function(i, t) {
					if (width <= 640) {
						tileAreaWidth = width;
					} else {
						tileAreaWidth += $(t).outerWidth() + 80;
					}
				});
				$(".tile-area").css({
					width : tileAreaWidth
				});
			};
			var addMouseWheel = function() {
				$("body").mousewheel(function(event, delta, deltaX, deltaY) {
					var page = $(document);
					var scroll_value = delta * 50;
					page.scrollLeft(page.scrollLeft() - scroll_value);
					return false;
				});
			};
			plugin.init();
		}
	})(jQuery);
	$(function() {
		$.StartScreen();
		var tiles = $(".tile, .tile-small, .tile-sqaure, .tile-wide, .tile-large, .tile-big, .tile-super");
		$.each(tiles, function() {
			var tile = $(this);
			setTimeout(function() {
				tile.css({
					opacity : 1,
					"-webkit-transform" : "scale(1)",
					"transform" : "scale(1)",
					"-webkit-transition" : ".3s",
					"transition" : ".3s"
				});
			}, Math.floor(Math.random() * 500));
		});
		$(".tile-group").animate({
			left : 0
		});
	});
	function showSearch() {
		var charm = $("#charmSearch");
		if (charm.data('hidden') == undefined) {
			charm.data('hidden', true);
		}
		if (!charm.data('hidden')) {
			charm.animate({
				right : -300
			});
			charm.data('hidden', true);
		} else {
			charm.animate({
				right : 0
			});
			charm.data('hidden', false);
		}
	}
	function setSearchPlace(el) {
		var a = $(el);
		var text = a.text();
		var toggle = a.parents('label').children('.dropdown-toggle');
		toggle.text(text);
	}
	$(function() {
		var current_tile_area_scheme = localStorage.getItem('tile-area-scheme')
				|| "tile-area-scheme-darkCobalt";
		$(".tile-area").removeClass(function(index, css) {
			return (css.match(/(^|\s)tile-area-scheme-\S+/g) || []).join(' ');
		}).addClass(current_tile_area_scheme);
		$(".schemeButtons .button")
				.hover(
						function() {
							var b = $(this);
							var scheme = "tile-area-scheme-" + b.data('scheme');
							$(".tile-area")
									.removeClass(
											function(index, css) {
												return (css
														.match(/(^|\s)tile-area-scheme-\S+/g) || [])
														.join(' ');
											}).addClass(scheme);
						},
						function() {
							$(".tile-area")
									.removeClass(
											function(index, css) {
												return (css
														.match(/(^|\s)tile-area-scheme-\S+/g) || [])
														.join(' ');
											}).addClass(
											current_tile_area_scheme);
						});
		$(".schemeButtons .button")
				.on(
						"click",
						function() {
							var b = $(this);
							var scheme = "tile-area-scheme-" + b.data('scheme');
							$(".tile-area")
									.removeClass(
											function(index, css) {
												return (css
														.match(/(^|\s)tile-area-scheme-\S+/g) || [])
														.join(' ');
											}).addClass(scheme);
							current_tile_area_scheme = scheme;
							localStorage.setItem('tile-area-scheme', scheme);
							showSettings();
						});
	});
</script>
</head>
<body>
	<div class="charm right-side padding20 bg-grayDark" id="charmSearch">
		<button
			class="square-button bg-transparent fg-white no-border place-right small-button"
			onclick="showSearch()">
			<span class="mif-cross"></span>
		</button>
		<h1 class="text-light">Search</h1>
		<hr class="thin" />
		<br />
		<div class="input-control text full-size">
			<label> <span class="dropdown-toggle drop-marker-light">Anywhere</span>
				<ul class="d-menu" data-role="dropdown">
					<li><a onclick="setSearchPlace(this)">Anywhere</a></li>
					<li><a onclick="setSearchPlace(this)">Options</a></li>
					<li><a onclick="setSearchPlace(this)">Files</a></li>
					<li><a onclick="setSearchPlace(this)">Internet</a></li>
				</ul>
			</label> <input type="text">
			<button class="button">
				<span class="mif-search"></span>
			</button>
		</div>
	</div>
	<div class="tile-area tile-area-scheme-darkCrimson fg-white">
		<h1 class="tile-area-title">Lista telefoni</h1>
		<div class="tile-area-controls">
			<button
				class="image-button icon-right bg-transparent fg-white bg-hover-dark no-border">
				<span class="sub-header no-margin text-light">${pageContext.request.userPrincipal.name}</span>
				<span class="icon mif-user"></span>
			</button>
			<button
				class="square-button bg-transparent fg-white bg-hover-dark no-border"
				onclick="showSearch()">
				<span class="mif-search"></span>
			</button>
			<!-- per accedere al tab search -->
		</div>
		<table class="table striped hovered cell-hovered border bordered"
			id="tabellaTelefoni">
			<thead style="background-color: #00598c;">
				<tr>
					<th class="sortable-column"><h7>ID</h7></th>
					<th class="sortable-column sort-asc"><h7>Nome</h7></th>
					<th class="sortable-column sort-asc"><h7>Dealer</h7></th>
					<th class="sortable-column sort-asc"><h7>Prezzo</h7></th>
					<th class="sortable-column sort-asc"><h7>Descrizione</h7></th>
				</tr>
			</thead>
			<tbody id="myTable">
				<c:set var="k" value="0" />
				<c:forEach var="phone" items="${phones}">
					<tr style="background-color: #EFEFEF; cursor: pointer;">
						<td style="text-align: center; color: black;"><b> <c:out
									value="${phone.id}" />
						</b></td>

						<td style="text-align: center; color: black;"><b> <c:out
									value="${phone.nome}" />
						</b></td>

						<td style="text-align: center; color: black;"><b><c:out
									value="${phone.dealer}" /> </b></td>

						<td style="text-align: center; color: black;"><b><c:out
									value="${phone.prezzo}" /> </b></td>

						<td style="text-align: center; color: black;"><b><c:out
									value="${phone.descrizione}" /> </b></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


	</div>
</body>
</html>