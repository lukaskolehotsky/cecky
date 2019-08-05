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
	<title>UpdateItem2</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<b>UpdateItem2</b>
	<br> Message: ${item.getBrand()}
	
	<center>
		<table width="400" bgcolor="yellow" border="1">
			<tr>
					<td>
						<form:form method="put" enctype="multipart/form-data" modelAttribute="files" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/updateFiles?guid=${item.getGuid()}">
				    		<input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />
				    		<input type="submit" value="Submit" /></form>
				    	</form:form>
					</td>
			</tr>
		</table>
	</center>

</body>
</html>