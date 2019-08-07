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

    <center>
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem1"> Create item </a>
    </center>

    <ul class="container float">
        <c:forEach items="${itemsWithFiles}" var="itemWithFiles">
            <li class="item float-item"><img alt="img" height="150" width="200" src="data:image/jpeg;base64,${itemWithFiles.getFileResponses().get(0).getData()}"/></li>
        </c:forEach>
    </ul>

    <center>
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAllItemsWithFiles?page=0"> 0 </a>
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAllItemsWithFiles?page=1"> 1 </a>
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAllItemsWithFiles?page=2"> 2 </a>
        <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAllItemsWithFiles?page=3"> 3 </a>
    </center>

</body>
</html>