<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
	<title>Spring Boot JSP example</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<b>create 2</b>
	<br> Message: ${item.brand}
	
	<form:form method="get" enctype="multipart/form-data" modelAttribute="files" action="https://cecky.herokuapp.com/saveImages?guid=${item.brand}">
		<input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />
		<input type="submit" value="Submit" /></form>
	</form:form>	

</body>
</html>