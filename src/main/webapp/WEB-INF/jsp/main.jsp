<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<html lang="en">
<head>
	<title>Spring Boot JSP example</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<center>
	    <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem1"> Create item </a>
		<c:forEach items="${itemsWithFiles}" var="itemWithFiles">
			<table width="400" bgcolor="yellow" border="1">	
			    <tr>
			        <td>Item brand: <c:out value="${itemWithFiles.getItemResponse().getBrand()}"/></td>	 
			        <td><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/updateItem1?guid=${itemWithFiles.getItemResponse().getGuid()}"> Update item </a></td>       
			    	<td><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/removeItemWithFiles?guid=${itemWithFiles.getItemResponse().getGuid()}"> Remove item </a></td> 
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