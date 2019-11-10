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

<form:form class="container register-form" method="post" modelAttribute="item" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem2">
    <div class="container">
      
      <br>
      <br>
      
      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2">
            
        </div>
        <div class="col-md-6 text-justify font-weight-bold text-success text-center">
            <h4>Krok 1 - Informácie o vozidle</h4>
        </div>  
        <div class="col-md-2"></div>    
      </div>
      
      <br>

      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2 text-justify font-weight-bold my-auto">
            Značka:
        </div>
        <div class="col-md-6 text-justify font-weight-bold">
            <form:input type="text" path="brand" class="form-control" value=""/>
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
            <form:input type="text" path="type" class="form-control" value=""/>
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
            <form:select class="form-control" id="exampleFormControlSelect1" path="fuelType">
                <form:option value="NONE">vyber</form:option>
                <form:option value="Diesel">Diesel</form:option>
                <form:option value="Benzín">Benzín</form:option>
                <form:option value="Plyn">Plyn</form:option>
                <form:option value="Elektrika">Elektrika</form:option>
                <form:option value="Vodík">Vodík</form:option>
                <form:option value="Hybrid">Hybrid</form:option>
            </form:select>
        </div> 
        <div class="col-md-2"></div>    
      </div>  

      <br>    

      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2 text-justify font-weight-bold my-auto">
            Rok výroby:
        </div>
        <div class="col-md-6 text-justify font-weight-bold">            
            <form:select class="form-control" id="exampleFormControlSelect1" path="productionYear">
	            <c:forEach var="i" begin="0" end="2019" step="1">
	            	<c:if test="${2019 - i >= 1990}">
	            		<form:option value="${2019 - i}">${2019 - i}</form:option>
	            	</c:if>	
				</c:forEach> 
            </form:select>
        </div>
            
        </div>  
        <div class="col-md-2"></div>    
      </div>

      <br>

      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2 text-justify font-weight-bold my-auto">
            Počet kilometrov:
        </div>  
        <div class="col-md-6 text-justify font-weight-bold">
            <form:input type="number" path="speedometerCondition" class="form-control" value=""/>
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
            <form:input type="number" path="price" class="form-control" value=""/>
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
            <form:input type="text" path="email" class="form-control" value=""/>
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
          <form:textarea path="description" class="form-control" id="exampleFormControlTextarea1" rows="3"></form:textarea>
        </div>
        <div class="col-md-2"></div>    
      </div>      

      <br>
      
      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2 text-justify font-weight-bold my-auto">
            Telefón:
        </div>  
        <div class="col-md-6 text-justify font-weight-bold">
          <form:input type="number" path="mobileNumber" class="form-control" value=""/>
        </div>
        <div class="col-md-2"></div>    
      </div>
      
      <br>

      <div class="row">
        <div class="col-md-2"></div> 
        <div class="col-md-2 text-justify font-weight-bold my-auto">            
        </div>
        <div class="col-md-6 text-center font-weight-bold my-auto">
            <button type="submit" class="btn btn-success">Pridaj</button>
        </div>
        <div class="col-md-2"></div>    
      </div>

      <br>

    </div>

</form:form>

</body>
</html>