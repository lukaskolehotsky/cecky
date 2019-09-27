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

    <style> </style>
    <title>chcemto.eu</title>
</head>

<body>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

	<form:form class="container register-form" method="post" modelAttribute="item" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/updateItem2?guid=${item.getGuid()}">
		<div class="container">      
      	<br>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Overovaci kod:
        	</div>
        	<div class="col-md-6 text-justify font-weight-bold">
            	<form:input type="text" path="authenticationCode" class="form-control" placeholder="${item.getAuthenticationCode().get()}" value=""/>
        	</div>  
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Znacka:
        	</div>
        	<div class="col-md-6 text-justify font-weight-bold">
            	<form:input type="text" path="brand" class="form-control" placeholder="${item.getBrand()}" value=""/>
        	</div>  
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Model:
        	</div>  
        	<div class="col-md-6 text-justify font-weight-bold">
            	<form:input type="text" path="type" class="form-control" placeholder="${item.getType()}" value=""/>
        	</div>
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Typ paliva:
        	</div> 
        	<div class="col-md-6 text-justify font-weight-bold">
            	<form:input type="text" path="fuelType" class="form-control" placeholder="${item.getFuelType()}" value=""/>
        	</div> 
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Rok vyroby:
        	</div>
        	<div class="col-md-6 text-justify font-weight-bold">
            	<form:input type="number" path="productionYear" class="form-control" placeholder="${item.getProductionYear()}" value=""/>
        	</div>  
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Pocet kilometrov:
        	</div>  
        	<div class="col-md-6 text-justify font-weight-bold">
            	<form:input type="number" path="speedometerCondition" class="form-control" placeholder="${item.getSpeedometerCondition()}" value=""/>
        	</div>
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Cena:
        	</div>  
        	<div class="col-md-6 text-justify font-weight-bold">
            	<form:input type="number" path="price" class="form-control" placeholder="${item.getPrice()}" value=""/>
        	</div>
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Email:
        	</div>  
        	<div class="col-md-6 text-justify font-weight-bold">
            	<form:input type="text" path="email" class="form-control" placeholder="${item.getEmail()}" value=""/>
        	</div>
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto">
            	Popis vozidla:
        	</div>  
        	<div class="col-md-6 text-justify font-weight-bold">
          	<form:textarea path="description" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="${item.getDescription()}"></form:textarea>
        	</div>
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
      	<div class="row">
        	<div class="col-md-2"></div> 
        	<div class="col-md-2 text-justify font-weight-bold my-auto"></div>
        	<div class="col-md-6 text-center font-weight-bold my-auto">
            	<button type="submit" class="btn btn-success">Uprav</button>
        	</div>
        	<div class="col-md-2"></div>    
      	</div>
      	<br>
    	</div>
	</form:form>
</body>

</html>