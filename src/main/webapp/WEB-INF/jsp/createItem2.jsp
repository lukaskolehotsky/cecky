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
	<title>CreateItem2</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<b>create 2</b>
	<br> Message: ${item.brand}
	
	<form:form method="post" enctype="multipart/form-data" modelAttribute="files" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/saveImages?guid=${item.getGuid()}">
		<input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />
		<input type="submit" value="Submit" /></form>
	</form:form>	

</body>
</html>