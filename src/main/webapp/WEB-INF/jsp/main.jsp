<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
	<title>Spring Boot JSP example</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<c:forEach items="${itemsWithFiles}" var="itemWithFiles">
	    <tr>
	        <td>Item brand: <c:out value="${itemWithFiles.getItemResponse().getBrand()}"/></td>	        
	    </tr>
	    
	    <br>
	    
	    <c:forEach items="${itemWithFiles.getFileResponses()}" var="fileResponse">
		    <tr>
		        <td>File name: <c:out value="${fileResponse.getFileName()}"/></td>
		    </tr>
		    <br>
		</c:forEach>
	
	</c:forEach>

</body>
</html>