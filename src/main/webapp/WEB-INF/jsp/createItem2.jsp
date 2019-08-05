<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
	<title>CreateItem2</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<b>create 2</b>
	<br> Message: ${item.brand}
	
	<form:form method="post" enctype="multipart/form-data" modelAttribute="files" action="https://cecky.herokuapp.com/saveImages?guid=${item.getGuid()}">
		<input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />
		<input type="submit" value="Submit" /></form>
	</form:form>	

</body>
</html>