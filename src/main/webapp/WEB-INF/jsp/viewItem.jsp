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
          .note
          {
              text-align: center;
              height: 80px;
              background: -webkit-linear-gradient(left, #0072ff, #8811c5);
              color: #fff;
              font-weight: bold;
              line-height: 80px;
          }
          .form-content
          {
              padding: 5%;
              border: 1px solid #ced4da;
              margin-bottom: 2%;
          }
          .form-control{
              border-radius:1.5rem;
          }
          .btnSubmit
          {
              border:none;
              border-radius:1.5rem;
              padding: 1%;
              width: 20%;
              cursor: pointer;
              background: #0062cc;
              color: #fff;
          }

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

    <div class="container register-form">
        <div class="form">
            <div class="note">
                <p>Nahlad</p>
            </div>

            <div class="form-content">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <blockquote class="blockquote">
                              <p class="mb-0">Znacka</p>
                              <footer class="blockquote-footer"> <c:out value="${itemWithFiles.getItemResponse().getBrand()}"/></footer>
                            </blockquote>
                        </div>
                        <div class="form-group">
                            <blockquote class="blockquote">
                              <p class="mb-0">Email</p>
                              <footer class="blockquote-footer"> <c:out value="${itemWithFiles.getItemResponse().getEmail()}"/></footer>
                            </blockquote>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <blockquote class="blockquote">
                              <p class="mb-0">Model</p>
                              <footer class="blockquote-footer"> <c:out value="${itemWithFiles.getItemResponse().getType()}"/></footer>
                            </blockquote>
                        </div>
                        <div class="form-group">
                            <blockquote class="blockquote">
                              <p class="mb-0">Popis</p>
                              <footer class="blockquote-footer"> <c:out value="${itemWithFiles.getItemResponse().getDescription()}"/></footer>
                            </blockquote>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <blockquote class="blockquote">
                              <p class="mb-0">Palivo</p>
                              <footer class="blockquote-footer"> <c:out value="${itemWithFiles.getItemResponse().getFuelType()}"/></footer>
                            </blockquote>
                        </div>
                        <div class="form-group">
                            <blockquote class="blockquote">
                              <p class="mb-0">Stav tachometra</p>
                              <footer class="blockquote-footer"> <c:out value="${itemWithFiles.getItemResponse().getSpeedometerCondition()}"/></footer>
                            </blockquote>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <blockquote class="blockquote">
                              <p class="mb-0">Palivo</p>
                              <footer class="blockquote-footer"> <c:out value="${itemWithFiles.getItemResponse().getProductionYear()}"/></footer>
                            </blockquote>
                        </div>

                    </div>
                </div>

                <!-- GALLERY -->
                <!--Carousel Wrapper-->
                <div id="multi-item-example" class="carousel slide carousel-multi-item carousel-multi-item-2" data-ride="carousel">

                  <!--Controls-->
                  <div class="controls-top">
                    <a class="black-text" href="#multi-item-example" data-slide="prev"><i class="fas fa-angle-left fa-3x pr-3"></i></a>
                    <a class="black-text" href="#multi-item-example" data-slide="next"><i class="fas fa-angle-right fa-3x pl-3"></i></a>
                  </div>
                  <!--/.Controls-->

                  <!--Slides-->
                  <div class="carousel-inner" role="listbox">        
        			<!--First slide-->
                    <div class="carousel-item active">
						<c:forEach items="${itemWithFiles.getFileResponses()}" var="fileResponse">
						  <div class="col-md-3 mb-3">
	                        <div class="card">
	                          <img class="img-fluid" src="${fileResponse.getImgPath()}"
	                            alt="Card image cap">
	                        </div>
	                      </div>
			        	</c:forEach>
                    </div>
                    <!--/.First slide-->
                  </div>
                  <!--/.Slides-->

                </div>
                <!--/.Carousel Wrapper-->

                <!-- BUTTONS -->
                <center>
                    <button type="button" class="btnSubmit" data-toggle="button" aria-pressed="false" autocomplete="off">
                      <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/updateItem1?guid=${itemWithFiles.getItemResponse().getGuid()}">Upravit</a>
                    </button>
                    <button style="display: none;" type="button" class="btnSubmit" data-toggle="button" aria-pressed="false" autocomplete="off">
                      <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/removeItemWithFiles?guid=${itemWithFiles.getItemResponse().getGuid()}">Vymazat</a>
                    </button>
                </center>

            </div>
        </div>
    </div>

</body>
</html>