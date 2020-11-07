<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
</head>
<body>
<h2>Deltagerliste</h2>

<table class="pure-table">
		<tbody><tr bgcolor="#cccccc">
			<th>Kjonn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
		
		<c:forEach items="${deltakerliste}" var="p">
		<tr bgcolor="#ffffff">
			
			
			
			<c:if test="${p.kjonn.equals('m')}">
			<td align="center">&#9794</td>
			</c:if>
			
			<c:if test="${p.kjonn.equals('f')}">
			<td align="center">&#9792</td>
			</c:if>
			
			<td>
			
			<c:out value="${p.fornavn}"/>
			<c:out value=" ${p.etternavn}"/>
			</td>
			
			<td>
			<c:out value="${p.mobilNr}"/>
			</td>
		</tr>
		</c:forEach>
	</tbody></table>	


<!--<table class="pure-table">
		
 <c:forEach items="${deltakerliste}" var="person">
		<p>	<c:out value="${person.fornavn}"/>:</p>
    </c:forEach></table>!-->
    
    
	<p>
		<a href="loggut.html">Ferdig</a>
	</p>
</body>
</html>