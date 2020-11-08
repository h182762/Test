<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Påmeldingsbekreftelse</title>
</head>
<body>
	<h2>Påmeldingsbekreftelse</h2>
	<p>Påmeldingen er mottatt for</p>

	<p>
		<c:out value="${nyDeltaker.fornavn}" />
	<br>
		<c:out value="${nyDeltaker.etternavn}" />
	<br>
		<c:out value="${nyDeltaker.mobilNr}" />
	<br>
	<c:if test="${nyDeltaker.kjonn.equals('m')}">
	<td>Mann</td>
	</c:if>
	
	<c:if test="${nyDeltaker.kjonn.equals('f')}">
		<td align="center">Kvinne</td>
	</c:if>
	</p>
<form method="post" class="pure-form pure-form-aligned">
	<button type="submit" class="pure-button pure-button-primary">Deltakerliste</button>
	</form>
		
</body>
</html>