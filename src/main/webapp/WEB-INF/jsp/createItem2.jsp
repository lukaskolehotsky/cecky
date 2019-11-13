<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<! DOCTYPE HTML PUBLIC "- // W3C / DTD HTML 4.01 Transitional // SK">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<html lang="sk">
<head>
    <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <style> </style>
        <title>chcemto.eu</title>
</head>
<body>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

    
 <form:form method="post" enctype="multipart/form-data" modelAttribute="files" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/saveImages?guid=${item.getGuid()}">
    <div class="container">
      
      <br>
      <br>
      
      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2">
            
        </div>
        <div class="col-md-6 text-justify font-weight-bold text-success text-center">
            <h4>Krok 2: Pridanie obrázkov</h4>
        </div>  
        <div class="col-md-2"></div>    
      </div>
      
      <br>
      
      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2">
            
        </div>
        <div class="col-md-6 text-justify font-weight-bold text-danger text-center">
            <h5>Pre úspešné vytvorenie inzerátu je potrebné vybrať minimálne 1 obrázok a maximálne 8 obrázkov.</h5>
        </div>  
        <div class="col-md-2"></div>    
      </div>
      
      <br>

      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2 text-justify font-weight-bold my-auto">
            Vyber obrázky:
        </div>
        <div class="col-md-6 text-justify font-weight-bold">
            <input id="multipleFileUploadInput" type="file" name="files" class="file-input" multiple required />
        </div>  
        <div class="col-md-2">
            <button type="submit" class="btn btn-success">Pridaj</button>
        </div>    
      </div>

      <br>           

    </div>

</form:form>

</body>
</html>