<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="/css/main.css" rel="stylesheet"></link>

<title>Validator</title>
</head>
<body>

	<form:form method="POST" modelAttribute="requestInfo">
		<table>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>NIP:</td>
				<td><form:input path="nip" /></td>
				<td><form:errors path="nip" cssClass="error" /></td>
			</tr>
			<tr>
				<td>PESEL:</td>
				<td><form:input path="pesel" /></td>
				<td><form:errors path="pesel" cssClass="error" /></td>
			</tr>
			<tr>
				<td>REGON:</td>
				<td><form:input path="regon" /></td>
				<td><form:errors path="regon" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Kod pocztowy:</td>
				<td><form:input path="code" /></td>
				<td><form:errors path="code" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form:form>

</body>
</html>