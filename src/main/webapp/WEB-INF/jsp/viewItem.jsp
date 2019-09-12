	<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<html lang="en">
<head>
	<title>CreateItem1</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>

	<center>
		<table width="400" bgcolor="yellow" border="1">
			<tr>
                <td>
                    <tr>
                        <td>Item brand: <c:out value="${itemWithFiles.getItemResponse().getBrand()}"/></td>
                            <td><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/updateItem1?guid=${itemWithFiles.getItemResponse().getGuid()}"> Update item </a></td>
                            <td style="display: none;"><a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/removeItemWithFiles?guid=${itemWithFiles.getItemResponse().getGuid()}"> Remove item </a></td>
                        </tr>

                        <br>

                        <c:forEach items="${itemWithFiles.getFileResponses()}" var="fileResponse">
                            <tr>
                                <td><img alt="img" height="42" width="42" src="${fileResponse.getImgPath()}"/></td>
                                <td>File name: <c:out value="${fileResponse.getFileName()}"/></td>
                            </tr>
                            <br>
                        </c:forEach>
                </td>
			</tr>
		</table>
	</center>	

</body>
</html>