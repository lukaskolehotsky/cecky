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
    <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <style>           

            @media (min-width: 768px) {
            .carousel-multi-item-2 .col-md-3 {
            float: left;
            width: 25%;
            max-width: 100%; } }

            .carousel-multi-item-2 .card img {
            border-radius: 2px; }
        </style>
        
        <title>Nahlad</title>
</head>

<body>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>


    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <script>
      $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').trigger('focus')
      })
    </script>

    <div class="container">
      
      <br>
      <br>

      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-6 text-justify font-weight-bold">
            <h4><c:out value="${itemWithFiles.getItemResponse().getBrand()}"/> <c:out value="${itemWithFiles.getItemResponse().getType()}"/></h4>
        </div>  
        <div class="col-md-2"></div>    
      </div>

      <br>

      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-8 text-justify"><c:out value="${itemWithFiles.getItemResponse().getSpeedometerCondition()}"/> km, r.v.: <c:out value="${itemWithFiles.getItemResponse().getProductionYear()}"/>, <c:out value="${itemWithFiles.getItemResponse().getFuelType()}"/></div>          
        <div class="col-md-2"></div>    
      </div>

      <br>
      <br>

    <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-8 text-justify">
            <c:out value="${itemWithFiles.getItemResponse().getDescription()}"/>
        </div>  
        <div class="col-md-2"></div>    
    </div>

       <br>
       <br>

       <div class="row">
            <div class="col-md-2"></div> 
            <div class="col-md-8">
                <div id="multi-item-example" class="carousel slide carousel-multi-item carousel-multi-item-2" data-ride="carousel">
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">  
                            <c:forEach items="${itemWithFiles.getFileResponses()}" var="fileResponse">
                                <div class="col-md-3 mb-3">
                                    <div class="card">
                                        <img class="img-fluid" src="${fileResponse.getImgPath()}" alt="Card image cap">
                                    </div>
                                </div>
                            </c:forEach>                                               
                        </div>
                    </div>
                </div>
            </div>  
            <div class="col-md-2"></div>
       </div>        
       <br>
       <br>

        <div class="row">
            <div class="col-md-2"></div> 
            <div class="col-md-8 text-center">
              <div class="btn-group" role="group" aria-label="Basic example">
				<button type="button" class="btn btn-secondary btn-warning" onclick="location.href='${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/updateItem1?guid=${itemWithFiles.getItemResponse().getGuid()}';">Upravit</button>
				
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-secondary btn-danger" data-toggle="modal" data-target="#exampleModal">
				  Odstranit
				</button>                
                </div>
            </div>  
            <div class="col-md-2"></div>    
        </div>

        <br>
       <br>
       
    </div> 
   
   	<form:form class="container register-form" method="post" modelAttribute="removeItemWithFilesRequest" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/removeItemWithFiles?guid=${itemWithFiles.getItemResponse().getGuid()}">
 		<!-- Modal -->
	    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	      <div class="modal-dialog" role="document">
	        <div class="modal-content">
	        
	          <div class="modal-header">
	            <h5 class="modal-title" id="exampleModalLabel">Naozaj odstranit?</h5>
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	              <span aria-hidden="true">&times;</span>
	            </button>
	          </div>
	          <div class="modal-body">
	            <div class="form-group">
	              <label for="authCode">Autentifikacny kod:</label>
	              <form:input type="text" id="authCode" path="authCode" class="form-control" value=""></form:input>
	            </div>            
	          </div>
	          <div class="modal-footer">
	            <!--<button type="button" class="btn btn-danger" data-dismiss="modal">Nie</button>-->
	            <button type="submit" class="btn btn-success">Ano</button>
	          </div>
	        </div>
	      </div>
	    </div>
 	</form:form>

</body>

</html>