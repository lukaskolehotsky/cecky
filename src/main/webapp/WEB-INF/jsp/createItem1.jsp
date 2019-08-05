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

	<form:form method="post" modelAttribute="item" action="http://localhost:8080/createItem2">
		<form:input path="brand" type="text" /> <!-- bind to user.name-->
		<form:errors path="brand" />
		<form:input path="type" type="text" /> <!-- bind to user.name-->
		<form:errors path="type" />
		<input type="submit" value="Submit" /></form>
	</form:form>
	
	

</body>
</html>