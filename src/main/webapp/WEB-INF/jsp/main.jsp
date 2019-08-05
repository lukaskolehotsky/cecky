<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
	<title>Spring Boot JSP example</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<center>
		<c:forEach items="${itemsWithFiles}" var="itemWithFiles">
			<table width="400" bgcolor="yellow" border="1">	
			    <tr>
			        <td>Item brand: <c:out value="${itemWithFiles.getItemResponse().getBrand()}"/></td>	        
			    </tr>
			    
			    <br>
			    
			    <c:forEach items="${itemWithFiles.getFileResponses()}" var="fileResponse">
				    <tr>				        
				        <td><img alt="img" height="42" width="42" src="data:image/jpeg;base64,${fileResponse.getData()}"/></td>
				    	<td>File name: <c:out value="${fileResponse.getFileName()}"/></td>
				    </tr>
				    <br>
				</c:forEach>			
			</table>
		</c:forEach>
		
	</center>

	

</body>
</html>