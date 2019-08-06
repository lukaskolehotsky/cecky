	<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<html lang="en">
<head>
	<title>UpdateItem1</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<center>
		<table width="400" bgcolor="yellow" border="1">
			<tr>
					<td>
						<form:form method="post" modelAttribute="item" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/updateItem2?guid=${item.getGuid()}">
							<form:input name="Brand:" path="brand" type="text" placeholder="${item.getBrand()}"/>
							<form:errors path="brand" />
							<form:input name="Type:" path="type" type="text" placeholder="${item.getType()}"/>
							<form:errors path="type" />
							<form:input name="Email:" path="email" type="text" placeholder="${item.getEmail()}"/>
                            <form:errors path="email" />
                            <form:input name="Code:" path="authenticationCode" type="text" placeholder="${item.getAuthenticationCode().get()}"/>
                            <form:errors path="authenticationCode" />
							<input type="submit" value="Submit" /></form>
						</form:form>
					</td>
			</tr>
		</table>
	</center>	

</body>
</html>