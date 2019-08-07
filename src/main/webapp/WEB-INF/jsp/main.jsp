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

	<style>
	.container {
      list-style:none;
      margin: 0;
      padding: 0;
    }
    .item {
      background: tomato;
      padding: 5px;
      width: 200px;
      height: 150px;
      margin: 10px;

      line-height: 150px;
      color: white;
      font-weight: bold;
      font-size: 3em;
      text-align: center;
    }

    /*float layout*/
    .float {
      max-width: 1200px;
      margin: 0 auto;
    }
    .float:after {
      content: ".";
      display: block;
      height: 0;
      clear: both;
      visibility: hidden;
    }
    .float-item {
      float: left;
    }
	</style>
</head>
<body>

<h2>Float Layout</h2>
<ul class="container float">
  <li class="item float-item">1</li>
  <li class="item float-item">2</li>
  <li class="item float-item">3</li>
  <li class="item float-item">4</li>
  <li class="item float-item">5</li>
</ul>

	<center>
	    <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem1"> Create item </a>
		<c:forEach items="${itemsWithFiles}" var="itemWithFiles">
			<table width="400" bgcolor="yellow" border="1">	
			    <tr>
			        <td>Item brand: <c:out value="${itemWithFiles.getItemResponse().getBrand()}"/></td>
			        <td><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getItemWithFiles?guid=${itemWithFiles.getItemResponse().getGuid()}"> View Item </a></td>
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

		<center>
		<a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAllItemsWithFiles?page=0"> 0 </a>
		<a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAllItemsWithFiles?page=1"> 1 </a>
		<a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAllItemsWithFiles?page=2"> 2 </a>
		<a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAllItemsWithFiles?page=3"> 3 </a>
		</center>
	</center>

</body>
</html>