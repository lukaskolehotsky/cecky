<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html lang="en">
<head>
	<title>UpdateItem2</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<b>UpdateItem2</b>
	<br> Message: ${item.getBrand()}
	
	<form:form method="put" enctype="multipart/form-data" modelAttribute="files" action="https://cecky.herokuapp.com/updateFiles?guid=${item.getGuid()}">
    		<input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />
    		<input type="submit" value="Submit" /></form>
    	</form:form>

</body>
</html>