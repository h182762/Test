<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
	<style>
	input:required:valid {
	  border-color: green;
	}
	input:invalid {
	  border-color: red;
	}
	input:required:focus:invalid {
	  border-color: red;
	}
	input:required:focus:valid {
	  border-color: green;
	}
	</style>
<title>Påmelding</title>
</head>
<body>
	<h2>Påmelding</h2>

	<form method="post" action="paamelding"
		class="pure-form pure-form-aligned">
		<fieldset>
		
			<div class="pure-control-group">
				<label for="fornavn">Fornavn:</label> 
				<input type="text" name="fornavn" value="${fornavn}" 
					required pattern="^[A-ZØÆÅ](?=.{1,19}$)[A-ZØÆÅa-zøæå]+(?:(\\s|-)[A-ZØÆÅa-zøæå]+)?$"> 
				<font color="red"><c:out value="${fornavnError}" /></font>
			</div>
			
			<div class="pure-control-group">
				<label for="etternavn">Etternavn:</label> 
				<input type="text" name="etternavn" value="${etternavn}"
					required pattern="^[A-ZØÆÅ](?=.{1,19}$)[A-ZØÆÅa-zøæå]+?$"> 
				<font color="red"><c:out value="${etternavnError}" /></font>
			</div>
			
			<div class="pure-control-group">
				<label for="mobil">Mobil (8 siffer):</label> 
				<input type="text" name="mobil" value="${mobilnr}"
					required pattern="^[0-9]{8}$"> 
				<font color="red"><c:out value="${mobilError}" /></font>
			</div>
			
			<div class="pure-control-group">
				<label for="passord">Passord:</label> 
				<input type="password" name="passord" value="${passord}"
					required pattern="{7,99}"> 
				<font color="red"><c:out value="${passordError}" /></font>
			</div>
			
			<div class="pure-control-group">
				<label for="passordRepetert">Passord repetert:</label> 
				<input type="password" name="passordRepetert" value="${passordRepetert}">
					
					<font color="red"><c:out value="${passordRepetertError}" /></font>
			</div>
			
			<div class="pure-control-group">
				<label for="kjonn">Kjønn:</label> <input type="radio" name="kjonn"
					value="m" ${kjonn eq "m" ? "checked=\"checked\"" : ""}>Mann
				<input type="radio" name="kjonn" value="f"
					${kjonn eq "f" ? "checked=\"checked\"" : ""}>Kvinne <font
					color="red"><c:out value="${kjonnError}" /></font>
			</div>
			
			<div class="pure-controls">
				<button type="submit" class="pure-button pure-button-primary">Meld
					meg på</button>
			</div>
			
		</fieldset>

		<p>
			Allerede påmeldt? Trykk <a href="logginn">her</a> for å logge inn.
		</p>
		
	</form>
</body>
</html>