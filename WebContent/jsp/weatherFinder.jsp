<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false" %>
    
 <!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Weather Finder</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" />

<script src="js/jquery-latest.js"></script>
<script src="js/jquery.validate.js"></script>

<script type="text/javascript">

 $(document).ready(function() {
 
 	$formObj = $("#weatherfinder_form");
		
	$formObj.validate({
		rules:{
			zipCode:{
				required:true,
				digits: true,
				minlength:5
			}
		},
		messages:{
			zipCode:{
				required:"Zip Code cannot be empty",
				digits:"Zip Code should contain only digits",
				minlength:"Zip Code should contain 5 digits"
				}
		}
	});
	

	$("#searchByZipCode").click(function() {
			if($formObj.valid())
			{
				var zipCode = $("#zipCode").val();
				
				$.ajax({
					url: 'findWeatherDetails.htm',
					type: 'POST',
					dataType: 'json',
					data: { zipCode: zipCode },
					success: function( data ){
						if(null == data.error) {
							$("#results_div").show();
							$("#error_div").hide();
							$("#city").html(data.city);
							$("#state").html(data.state);
							$("#tempInFar").html(data.temperatureFarenheit + "&nbsp;&deg;F");
						}
						else {
							$("#results_div").hide();
							$("#error_div").show();
							$("#errorMsg").html(data.error);
						}
					}, 
					error: function( data ){
					alert(data.responseText);
						$("#errorMsg").html(data.responseText);
				 },
				async: true
				});	
			}
	});
	
	$("#spinner").bind("ajaxSend", function() {
		$(this).show();
	}).bind("ajaxStop", function() {
		$(this).hide();
	}).bind("ajaxError", function() {
		$(this).hide();
	});

});
</script>

</head>

<body class="bg">

<div class="main_div">
	
	<div id="zipcode_div" class="zipcode_div">
		<form id="weatherfinder_form">
		<table width="100%">
			<tr>
				<td width="45%" style="text-align:right"> <p class="labeltext"><b>Enter Zip Code</b> :</p></td>
				<td width="55%" style="text-align:left"> <input id="zipCode" name="zipCode" type="text" /></td>
			</tr>
		</table>
				<input id="searchByZipCode" type="button" value="Search" class="searchButn"/>
		</form>
	</div>

	<div id="results_div" class="results_div" style="display:none;">
		<p class="labeltext">City: <b><label id="city" name="city"/></b></p>
		<p class="labeltext">State: <b><label id="state" name="state"/></b></p>
		<p class="labeltext">Temperature: <b><label id="tempInFar" name="tempInFar"/></b></p>
	</div>

	<div id="spinner" class="spinner" style="display:none;">
		<img id="img-spinner" src="img/ajax-loader.gif" alt="Loading"/>
	</div>

	<div id="error_div" class="error_div" style="display:none;">
		<p id="errorMsg" class="message"></p>
	<div>
	
	<p class="message">
		<c:if test="${not empty weatherFinderErrorMsg}">
			<spring:message code="${weatherFinderErrorMsg}"/> 
		</c:if> 
	</p>

</div>

</body>
</html>

