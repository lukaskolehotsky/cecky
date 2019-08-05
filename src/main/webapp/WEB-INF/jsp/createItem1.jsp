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
	<title>CreateItem1</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<center>
		<table width="400" bgcolor="yellow" border="1">
			<tr>
					<td>
						<form:form method="post" modelAttribute="item" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem2">
							<form:input path="brand" type="text" /> <!-- bind to user.name-->
							<form:errors path="brand" />
							<form:input path="type" type="text" /> <!-- bind to user.name-->
							<form:errors path="type" />
							<input type="submit" value="Submit" /></form>
						</form:form>
					</td>
			</tr>
		</table>
	</center>	

</body>
</html>