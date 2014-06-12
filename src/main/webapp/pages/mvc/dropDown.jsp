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
<script type="text/javascript"
	src="/SampleWebApp/resources/project-theme/js/jquery-1.11.0.min.js"></script>
<script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script>
<link rel="stylesheet"
	href="/SampleWebApp/resources/project-theme/css/style.css" />
	
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
			hbs: "./libs/handlebars/hbs"
		},
		hbs : {
	        templateExtension : 'hbs',
	        // if disableI18n is `true` it won't load locales and the i18n helper
	        // won't work as well.
	        disableI18n : true
	    },
		packages: [
			{
				name: "jquery",
				location: "./libs/jquery",
				main: "jquery"
			},
			{
				name: "json2",
				location: "./libs/json2",
				main: "json2"
			},
			{
				name: "underscore",
				location: "./libs/underscore",
				main: "underscore"
			},
			{
				name: "handlebars",
				location: "./libs/handlebars",
				main: "handlebars"
			}
		]
	});

	// start this mug
	//require(["com/jessewarden/handlebarsexample/HandlebarsExample.js"]);

	/*
	require(["jquery"], function(jqueryLibrary)
	{
		logger.debug("jqueryLibrary: ", jqueryLibrary);
	});

	require(["json2"], function(json2)
	{
		logger.debug("json2: ", json2);
	});

	require(["underscore"], function(underscore)
	{
		logger.debug("underscore: ", underscore);
	});
	*/

	require(["hbs!com/jessewarden/handlebarsexample/templates/SimpleTemplate", "jquery"], function(simpleTemplate, $)
	{
		logger.debug("simpleTemplate: ", simpleTemplate);
		var data = {firstName: "Jesse Warden", description: "Uber crackead ready to rock the mic."};
		var html = simpleTemplate(data);
		logger.debug("html: ", html);
		$("#content").html(html);
		
	});
	



</script>	
<script>
	$(document).ready(function() {
		$('#country').change(function() {
			//var url = 'chooseCity/get?';
			//var selector = $(this).attr('id');
			$.ajax({
				type : "GET",
				//url : "/SampleWebApp/mvc/chooseCity/get/anyFormat/state.xml?id="
				url : "/SampleWebApp/mvc/chooseCity/get/state?id="+$(this).val(),
				dataType : "json",
				contentType : "application/json",
				success : function(data) {
					addOption('#State','#StateDiv',data);
				}
			});
		});
		$('#State').change(function() {
			//var url = 'chooseCity/get?';
			//var selector = $(this).attr('id');
			$.ajax({
				type : "GET",
				url : "/SampleWebApp/mvc/chooseCity/get/city?id="+ $(this).val(),
				dataType : "json",
				contentType : "application/json",
				success : function(data) {
					addOption('#City','#CityDiv',data);
				}
			});
		});

	});

	function addOption(selector, showDiv, data) {
		$(selector).html('');
		$(selector).append('<option value="select">--Select--</option>');
		for (i = 0; i < data.length; i++) {
			if (selector == '#State')
				$(selector).append(
						'<option value="'+data[i].stateId+'">'
								+ data[i].stateName + '</option>');
			else
				$(selector).append(
						'<option value="'+data[i].cityId+'">'
								+ data[i].cityName + '</option>');
		}
		$(showDiv).show();
	}
</script>
</head>
<body>
	<form:form>
		<div id="wrap">
			<h1>${message}</h1>
			<br />
			<table>
				<tr>
					<td><label>Country</label></td>
					<td><form:select path="countryList" multiple="single"
							id="country">
							<form:option value="select" label="--Please Select--" />
							<form:options items="${countryList}" itemValue="countryId"
								itemLabel="countryName" />
						</form:select></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr id="StateDiv" style="display: none;">
					<td><label>State</label></td>

					<td><form:select path="stateList" multiple="single" id="State">
							<form:option value="select" label="--Please Select--" />
							<form:options items="${stateList}" itemValue="stateId"
								itemLabel="stateName" />
						</form:select></td>
				</tr>
				<tr id="CityDiv" style="display: none;">
						<td><label>City</label></td>
						<td><form:select path="cityList" multiple="single" id="City">
								<form:option value="select" label="--Please Select--" />
								<form:options items="${cityList}" itemValue="cityId"
									itemLabel="cityName" />
							</form:select>
						</td>
			</table>
			
			
			<h3>Spring Controller Methods</h3> 
			<span>
				@RequestMapping  - annotation has an value attribute which takes unique URL value<br />
				Method			- tells this method accepts only get request <br />
				headers - Specifies the header request should have key value pair of  Accept=application/json
			</span>
			<pre class="prettyprint lang-java" style="background-color: #C3C3C3; width: 860px; font : normal 14px Arial">
@RequestMapping(value="/get/state", method = RequestMethod.GET, headers="Accept=application/json")
public @ResponseBody List< State > getState(@RequestParam String id,ModelMap model) {
	if(id!=null && id.equals("Select"))
		return null;
	else
		return geoInfoWorkerImpl.getState(Integer.parseInt(id));
}

@RequestMapping(value="/get/city", method = RequestMethod.GET, headers="Accept=application/json")
public @ResponseBody List< City > getCity(@RequestParam String id,ModelMap model) {
	if(id!=null && id.equals("Select"))
		return null;
	else
		return geoInfoWorkerImpl.getCity(Integer.parseInt(id));
}			
	</pre>	
	<h3>jQuery ajax calls</h3> 
	
	 <pre class="prettyprint lang-js" style="background-color: #C3C3C3; width: 860px; font : normal 14px Arial">

	$(document).ready(function() {
		$('#country').change(function() {
			$.ajax({
				type : "GET",
				url : "/SampleWebApp/mvc/chooseCity/get/state?id="+$(this).val(),
				dataType : "json",
				contentType : "application/json",
				success : function(data) {
					addOption('#State','#StateDiv',data);
				}
			});
		});
		$('#State').change(function() {
			$.ajax({
				type : "GET",
				url : "/SampleWebApp/mvc/chooseCity/get/city?id="+ $(this).val(),
				dataType : "json",
				contentType : "application/json",
				success : function(data) {
					addOption('#City','#CityDiv',data);
				}
			});
		});

	});

	function addOption(selector, showDiv, data) {
		$(selector).html('');
		$(selector).append('< option value="select">--Select--< /option>');
		for (i = 0; i < data.length; i++) {
			if (selector == '#State')
				$(selector).append('< option value="'+data[i].stateId+'">'+ data[i].stateName + '< /option>');
			else
				$(selector).append('< option value="'+data[i].cityId+'">'+ data[i].cityName + '< /option>');
		}
		$(showDiv).show();
	}

			</pre>
		</div>
		<div id="content"></div>
	</form:form>
</body>
</html>

<%-- <select  id="country" name="country">
		<option value="select">--Select--</option>
		<c:forEach items="${countryList}" var="countryList">
			<option value='<c:out value="${countryList.countryId}"/>'><c:out value="${countryList.countryName}"/></option>
		</c:forEach>
	</select> --%>
<!-- <select  id="State" name="next">
			<option value="select">--Select--</option>
		</select> -->
<!-- <select  id="City" name="city">
			<option value="select">--Select--</option>
		</select> -->