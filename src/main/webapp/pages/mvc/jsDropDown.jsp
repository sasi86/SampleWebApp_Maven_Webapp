<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<%@ page isELIgnored="false"%>
<style type="text/css">
select {
	background-color: #FFFFFF;
	border: 1px solid #ACACAC;
	border-radius: 3px;
	color: #666666 !important;
	padding: 4px 5px;
	width: 180px;
}

#wrap {
	text-align: left;
	background: none repeat scroll 0 0 ghostwhite;
    padding: 10px;
    font : 14px/1.6em Arial;
	/*border: 1px solid black;*/
}
label{
	font: bold 14px Tahoma;
}
</style>
<script src="/SampleWebApp/resources/project-theme/js/libs/require/require.js"></script>
<script src="/SampleWebApp/resources/project-theme/js/libs/log4javascript/log4javascript_uncompressed.js"></script>
<!-- <script type="text/javascript"
	src="/SampleWebApp/resources/project-theme/js/jquery-1.11.0.min.js"></script>
<script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script> -->
<link rel="stylesheet"	href="/SampleWebApp/resources/project-theme/css/style.css" />
	
<script>

	// setup logging
	var logger = log4javascript.getLogger("HandlebarsExmaple");
	logger.setLevel(log4javascript.Level.ALL);
	logger.addAppender(new log4javascript.BrowserConsoleAppender());
	window.logger = logger;
	logger.debug("index.html::log4javascript ready to rock.");

	// configure require
	require.config({
		baseUrl: "/SampleWebApp/resources/project-theme/js/",
		optimize: "uglify",
		waitSeconds: 10,
		paths: {
			hbs : "./libs/hbs",
			'jsDropDown' : "./app/jsDropDown"
			
		},
		
		hbs : {
	        templateExtension : 'hbs'
	    },
	    packages: [
	   			{
	   				name: "jquery",
	   				location: "./libs/jquery",
	   				main: "jquery"
	   			},
	   			{
	   				name: "underscore",
	   				location: "./libs/underscore",
	   				main: "underscore"
	   			},
	   			{
	   				name: "log4javascript",
	   				location: "./libs/log4javascript",
	   				main: "log4javascript_uncompressed"
	   			}
	   	]
	});


	require(["jquery","jsDropDown"], function($,jsDropDown)
	{
		
		
	});
	



</script>	
</head>
<body>
	<form:form>
		<div id="wrap">
			<h1>${message}</h1>
			<br />
			<table>
				<tr id="CountryDiv">
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr id="StateDiv">
				</tr>
				<tr id="CityDiv">
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>