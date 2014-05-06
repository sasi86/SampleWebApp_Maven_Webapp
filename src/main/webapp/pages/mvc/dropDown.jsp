<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<%@ page isELIgnored="false"%>
	<script type="text/javascript" src="/SampleWebApp/resources/project-theme/js/jquery-1.11.0.min.js"></script>
	<script>
		
		$(document).ready(
				function() {
					$('#country').change(function() {
						//var url = 'chooseCity/get?';
						var selector = $(this).attr('id');
						$.ajax({
							type : "GET",
							url : "/SampleWebApp/mvc/chooseCity/get/state?id="+$(this).val(),
							dataType : "json",	
							contentType: "application/json",					
							success : function(data) {									
								addOption('#State' , '#StateDiv',  data);
							}
						});
					});
					$('#State').change(function() {
						//var url = 'chooseCity/get?';
						var selector = $(this).attr('id');
						$.ajax({
							type : "GET",
							url : "/SampleWebApp/mvc/chooseCity/get/city?id="+$(this).val(),
							dataType : "json",	
							contentType: "application/json",					
							success : function(data) {									
								addOption('#City' , '#CityDiv',  data);
							}
						});
					});

				});
				
				
		function addOption(selector, showDiv, data) {
			$(selector).html('');
			$(selector).append('<option value="select">--Select--</option>');
			for (i = 0; i < data.length; i++) {
				if(selector == '#State')
					$(selector).append(
							'<option value="'+data[i].stateId+'">' + data[i].stateName
									+ '</option>');
				else
					$(selector).append(
							'<option value="'+data[i].cityId+'">' + data[i].cityName
									+ '</option>');
			}
			$(showDiv).show();
		}
	</script>
</head>
<body>
<form:form>
	<h1>${message}</h1> <br />
		
	<label>Country</label>
	<select  id="country" name="country">
		<option value="select">--Select--</option>
		<c:forEach items="${countryList}" var="countryList">
			<option value='<c:out value="${countryList.countryId}"/>'><c:out value="${countryList.countryName}"/></option>
		</c:forEach>
	</select>
	<div id="StateDiv" style="display: none;">
		<label>State</label>
		<select  id="State" name="next">
			<option value="select">--Select--</option>
		</select>
	</div>
	<div id="CityDiv" style="display: none;">
		<label>City</label>
		<select  id="City" name="city">
			<option value="select">--Select--</option>
		</select>
	</div>
</form:form>	
</body>
</html>
