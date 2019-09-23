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
        <!--
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
         -->
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
        </style>
        <title>Pridaj</title>
</head>
<body>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> -->

    <form:form class="container register-form" method="post" modelAttribute="item" action="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem2">
        <div class="form">
            <div class="note">
                <p>Pridaj</p>
            </div>

            <div class="form-content">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <form:input type="text" path="brand" class="form-control" placeholder="Značka *" value=""/>
                        </div>
                        <div class="form-group">
                            <form:input type="text" path="email" class="form-control" placeholder="Email *" value=""/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <form:input type="text" path="type" class="form-control" placeholder="Model *" value=""/>
                        </div>
                        <div class="form-group">
                            <form:input type="number" path="price" class="form-control" placeholder="Cena *" value=""/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group green-border-focus">
                            <label for="exampleFormControlTextarea5">Description Colorful border on :focus state</label>
                            <form:textarea path="description" class="form-control" id="exampleFormControlTextarea5" rows="3"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <form:input type="text" path="fuelType" class="form-control" placeholder="Palivo *" value=""/>
                        </div>
                        <div class="form-group">
                            <form:input type="number" path="speedometerCondition" class="form-control" placeholder="Stav tachometra *" value=""/>
                        </div>
                    </div>
                    <div class="col-md-6">

                        <div class="form-group">
                            <form:input type="number" path="productionYear" class="form-control" placeholder="Rok vyroby *" value=""/>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btnSubmit">Hotovo</button>
            </div>
        </div>
    </form:form>

</body>
</html>