<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!-- paulirish.com/2008/conditional-stylesheets-vs-css-hacks-answer-neither/ -->
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!-- Consider adding a manifest.appcache: h5bp.com/d/Offline -->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">

  <!-- Use the .htaccess and remove these lines to avoid edge case issues.
       More info: h5bp.com/b/378 -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

  <title>Vota el logo de Madrid JS</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Mobile viewport optimized: h5bp.com/viewport -->
  <meta name="viewport" content="width=device-width,initial-scale=1">

  <!-- Place favicon.ico and apple-touch-icon.png in the root directory: mathiasbynens.be/notes/touch-icons -->

<link rel="stylesheet" href="resources/css/bootstrap.css">  
<link rel="stylesheet" href="resources/css/style.css">
  
  <!-- More ideas for your <head> here: h5bp.com/d/head-Tips -->

  <!-- All JavaScript at the bottom, except this Modernizr build incl. Respond.js
       Respond is a polyfill for min/max-width media queries. Modernizr enables HTML5 elements & feature detects; 
       for optimal performance, create your own custom Modernizr build: www.modernizr.com/download/ -->
  <script src="resources/js/libs/modernizr.min.js"></script>
</head>

<body>
	<div id="messages"></div>
	<section class="container">
		<article id="step_1" class="step hero-unit">
			<h1>Vota el logo de MadridJS</h1>
			<p>Bienvenido a la encuesta para elegir el logo de MadridJS. Introduce tu email para entrar en el sistema de votación</p>
			<form method="post" action="/login">
				<div class="clearfix">
					<input class="xlarge" id="email" name="email" size="30" type="email" required>
					<button type="submit" class="btn primary" data-loading-text="verificando...">verificar</button>
				</div>
			</form>
		</article>
		<article id="step_2" class="step">
			<div class="hero-unit">
				<h1>Elige <strong>3 logos</strong></h1>
			</div>
			<form method="post" action="/vote">
				<div id="logoList" data-getdata="/logos"></div>
				<button type="submit" class="btn primary" data-loading-text="votando...">votar</button>
			</form>
		</article>
		<article id="step_3" class="step">
			<div class="hero-unit">
				<h1>¡Gracias por participar!</h1>
				<h2>Te hemos enviado un mail para que confirmes tu voto.</h2>
			</div>
		</article>
		<footer>
			<p>Estamos en <a href="http://madridjs.org">http://madridjs.org</a></p>
		</footer>
	</div>
	<!-- Tempaltes -->

	<script id="errorTmpl" type="text/x-jquery-tmpl"> 
		<div id="\${id}" class="alert-message block-message error fade" data-alert="alert">
			<div class="container close">
				<p><strong>Error, el mail ya existe</strong> \${errorMsg}</p>
			</div>
		</div>
	</script>
	
	<script id="logoListTmpl" type="text/x-jquery-tmpl"> 
		<p>Vota 3 de los \${count} logos presentados.</p>
	    <ul id="logos" class="media-grid">{{tmpl(items) "#logoItemTmpl"}}</ul>
	</script>

	<script id="logoItemTmpl" type="text/x-jquery-tmpl">
		<li>
			<input type="checkbox" name="votes" id="logo_\${id}" value="\${id}">
			<label for="logo_\${id}">
				<img class="thumbnail" src="\${url}" alt="Proposición de logo \${id}" title="\${description}" height="\${height}" width="210"/>
			</label>
		</li>
	</script>
  <!-- JavaScript at the bottom for fast page loading -->

  <!-- Grab Google CDN's jQuery, with a protocol relative URL; fall back to local if offline -->
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
  <script>window.jQuery || document.write('<script src="resources/js/libs/jquery/jquery.min.js"><\/script>')</script>

  <!-- Jquery templates -->
  <script defer src="resources/js/libs/jquery/jquery.tmpl.min.js"></script>
  <script defer src="resources/js/libs/jquery/jquery.masonry.min.js"></script>

  <!-- BootStrap scripts -->
  <script defer src="resources/js/libs/bootstrap/bootstrap-alerts.js"></script>
  <script defer src="resources/js/libs/bootstrap/bootstrap-buttons.js"></script>
  <script defer src="resources/js/libs/bootstrap/bootstrap-twipsy.js"></script>

  <!-- scripts concatenated and minified via build script -->
  <script defer src="resources/js/plugins.js"></script>
  <script defer src="resources/js/script.js"></script>
  <!-- end scripts -->


  <!-- Asynchronous Google Analytics snippet. Change UA-XXXXX-X to be your site's ID.
       mathiasbynens.be/notes/async-analytics-snippet -->
  <script>
    var _gaq=[['_setAccount','UA-XXXXX-X'],['_trackPageview']];
    (function(d,t){var g=d.createElement(t),s=d.getElementsByTagName(t)[0];
    g.src=('https:'==location.protocol?'//ssl':'//www')+'.google-analytics.com/ga.js';
    s.parentNode.insertBefore(g,s)}(document,'script'));
  </script>

  <!-- Prompt IE 6 users to install Chrome Frame. Remove this if you want to support IE 6.
       chromium.org/developers/how-tos/chrome-frame-getting-started -->
  <!--[if lt IE 7 ]>
    <script defer src="//ajax.googleapis.com/ajax/libs/chrome-frame/1.0.3/CFInstall.min.js"></script>
    <script defer>window.attachEvent('onload',function(){CFInstall.check({mode:'overlay'})})</script>
  <![endif]-->

  <!--[if lt IE 9]>
    <script type="text/javascript" src="resources/js/libs/selectivizr.min.js"></script>
  <![endif]-->

</body>
</html>
