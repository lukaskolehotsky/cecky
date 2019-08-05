	<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<html lang="en">
<head>
	<title>UpdateItem1</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

    <form:form method="post" modelAttribute="item" action="https://cecky.herokuapp.com/updateItem2?guid=${item.getGuid()}">
		<form:input path="brand" type="text" placeholder="${item.getBrand()}"/>
		<form:errors path="brand" />
		<form:input path="type" type="text" placeholder="${item.getType()}"/>
		<form:errors path="type" />
		<form:input path="guid" type="text" placeholder="${item.getGuid()}"/>
        <form:errors path="guid" />
		<input type="submit" value="Submit" /></form>
	</form:form>
	
	

</body>
</html>