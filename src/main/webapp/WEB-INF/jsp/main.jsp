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
    <!--<meta charset="utf-8">-->
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>chcemto.eu</title>

        <style>
            @media (min-width: 768px) {
                .carousel-multi-item-2 .col-md-3 {
                    float: left;
                    width: 25%;
                    max-width: 100%;
                }
            }
            .carousel-multi-item-2 .card img {
                border-radius: 2px;
            }
            
            .carousel-indicators li {
            	text-indent: 0px;
            }
            .carousel-indicators {
            	bottom: -50;
            }
        </style>
    </head>
    <body>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	    <script>
	      $('#myModal').on('shown.bs.modal', function () {
	        $('#myInput').trigger('focus')
	      })
	    </script>
	    
        <div class="container my-4">
            <!--Carousel Wrapper-->
            <div id="multi-item-example" class="carousel slide carousel-multi-item" data-ride="carousel" data-interval="false">
                <button type="button" class="btn btn-success" onclick="location.href='${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem1';">Pridaj</button>

				<!-- Button trigger modal -->
				<button type="button" class="btn btn-secondary btn-info" data-toggle="modal" data-target="#exampleModal">
				  Filter
				</button>                 

                <c:set var="carouselItemActive" value="<div class='carousel-item active'>" />
                <c:set var="carouselItem" value="<div class='carousel-item'>" />
                <c:set var="coldMd4" value="<div class='col-md-4'>" />
                <c:set var="coldMd4Clearfix" value="<div class='col-md-4 clearfix d-none d-md-block'>" />
                <c:set var="count" value="6" />
                <c:set var="sz" value="${responseSize}" />
				<c:if test="${itemWithFileResponses.size() % count == 0}">
					<c:set var="iterationsCount" value="${itemWithFileResponses.size() / count}" />
				</c:if>				
				<c:if test="${itemWithFileResponses.size() % count != 0}">
					<c:set var="iterationsCount" value="${itemWithFileResponses.size() / count + 1}" />
				</c:if>

                <br>
                <br>
                <!--Slides-->
                <div class="carousel-inner" role="listbox">
					<c:forEach begin="1" end="${iterationsCount}" varStatus="loop">					
                        <c:if test="${loop.count == 1}">${carouselItemActive}</c:if>
                        <c:if test="${loop.count != 1}">${carouselItem}</c:if>
                            <div class="row">
                                <c:forEach items="${itemWithFileResponses}" var="itemWithFileResponse" varStatus="loopCounter">                                    
                                    <c:if test="${loopCounter.count >= loop.count * count - 5 && loopCounter.count < loop.count * count + 1}">                                    
                                        <%--<c:if test="${loopCounter.count % count == 0}">${coldMd4}</c:if>--%>
                                        <%--<c:if test="${loopCounter.count % count != 0}">${coldMd4Clearfix}</c:if>--%>
                                        <div class="col-md-4 mb-4">
                                            <div class="card mb-2">
                                                <img class="card-img-top" src="${itemWithFileResponse.getFileResponse().getImgPath()}" alt="Card image cap">
                                                <div class="card-body">
                                                    <h4 class="card-title">${itemWithFileResponse.getItemResponse().getBrand()} ${itemWithFileResponse.getItemResponse().getType()}</h4>
                                                    <p class="card-text">
                                                        <c:out value="${itemWithFileResponse.getItemResponse().getSpeedometerCondition()}"/> km, r.v.: <c:out value="${itemWithFileResponse.getItemResponse().getProductionYear()}"/>
                                                    </p>
                                                    <div class="d-flex bd-highlight mb-3">
                                                        <div class="mr-auto p-2 bd-highlight">
                                                            <div class="btn btn-primary" onclick="location.href='${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getItemWithFiles?guid=${itemWithFileResponse.getItemResponse().getGuid()}';">
                                                                Detail
                                                            </div>
                                                        </div>
                                                        <div class="my-auto p-2 bd-highlight text-danger">
                                                            <h5><strong>${itemWithFileResponse.getItemResponse().getPrice()} Eur</strong></h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
					</c:forEach>
                </div>
                <!--/.Slides-->
                
                <!--Multi itemy - posuvne gulicky pod galeriou -->
                <center>
	                <!--Indicators-->
	                <ol class="carousel-indicators">

	                    <c:if test="${page-1 >= 0}">
	                    	<c:if test="${search == true}">
	                    		<li><p onclick="location.href='${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/search100?page=${page-1}&brand=${searchRequest100.getBrand()}&type=${searchRequest.getType()}'"> << </p></li>
	                    	</c:if>
	                    	<c:if test="${search == false}">
	                    		<li><p onclick="location.href='${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getItemWithFileResponses?page=${page-1}'"> << </p></li>
	                    	</c:if>
	                    </c:if>
	                    
	                    <c:forEach begin="1" end="${iterationsCount}" varStatus="loop">
	                    	<c:if test="${loop.count == 1}">
	                    		<li data-target="#multi-item-example" data-slide-to="${loop.count-1}" class="active">${loop.count + page * 2}</li>
	                    	</c:if>
                        	<c:if test="${loop.count != 1}">
                        		<li data-target="#multi-item-example" data-slide-to="${loop.count-1}">${loop.count + page * 2}</li>
                        	</c:if>
	                    </c:forEach>
	                    
	                    <c:if test="${(page+1)*12 < sz}">
	                    	<c:if test="${search == true}">
	                    		<li><p onclick="location.href='${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/search100?page=${page+1}&brand=${searchRequest100.getBrand()}&type=${searchRequest100.getType()}'"> >> </p></li>
	                    	</c:if>
	                    	<c:if test="${search == false}">
	                    		<li><p onclick="location.href='${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getItemWithFileResponses?page=${page+1}'"> >> </p></li>
	                    	</c:if>
	                    </c:if>
             
	                </ol>
	                <!--/.Indicators-->
                </center>
                
            </div>
            <!--/.Carousel Wrapper-->
        </div>
        
        <form:form class="container register-form" method="post" modelAttribute="searchRequest" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/search">
	 		<!-- Modal -->
		    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		      <div class="modal-dialog" role="document">
		        <div class="modal-content">
		        
		          <div class="modal-header">
		            <h5 class="modal-title" id="exampleModalLabel">Filter</h5>
		            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">&times;</span>
		            </button>
		          </div>
		          <div class="modal-body">
		            <div class="form-group">
		              <label for="brand">Značka:</label>
		              <form:input type="text" id="brand" path="brand" class="form-control" value=""/>
		            </div>            
		          </div>
		          
		          <div class="modal-body">
		            <div class="form-group">
		              <label for="type">Model:</label>
		              <form:input type="text" id="type" path="type" class="form-control" value=""/>
		            </div>            
		          </div>
		          
		          <div class="modal-footer">
		            <button type="submit" class="btn btn-success">Vyfiltruj</button>
		          </div>
		        </div>
		      </div>
		    </div>
	 	</form:form>
        
    </body>
</html>