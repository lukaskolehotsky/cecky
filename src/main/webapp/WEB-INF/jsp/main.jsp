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
    <!--<meta charset="utf-8">-->
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Hello, world!</title>

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
        </style>
    </head>
    <body>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <div class="container my-4">
            <!--Carousel Wrapper-->
            <div id="multi-item-example" class="carousel slide carousel-multi-item" data-ride="carousel">
                <button type="button" class="btn btn-success" onclick="location.href='${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem1';">Pridaj</button>

                <!--Multi itemy - posuvne gulicky pod galeriou -->
                <!--Indicators-->
                <!--<ol class="carousel-indicators">
                    <li data-target="#multi-item-example" data-slide-to="0" class="active"></li>
                    <li data-target="#multi-item-example" data-slide-to="1"></li>
                    <li data-target="#multi-item-example" data-slide-to="2"></li>
                    <li data-target="#multi-item-example" data-slide-to="3"></li>
                    <li data-target="#multi-item-example" data-slide-to="4"></li>
                </ol>-->
                <!--/.Indicators-->

                <c:set var="carouselItemActive" value="<div class='carousel-item active'>" />
                <c:set var="carouselItem" value="<div class='carousel-item'>" />
                <c:set var="coldMd4" value="<div class='col-md-4'>" />
                <c:set var="coldMd4Clearfix" value="<div class='col-md-4 clearfix d-none d-md-block'>" />

                <br>
                <br>
                <!--Slides-->
                <div class="carousel-inner" role="listbox">
                    <c:forEach begin="1" end="${itemWithFileResponses.size() / 3 + 1}" varStatus="loop">
                        <c:if test="${loop.count == 1}">${carouselItemActive}</c:if>
                        <c:if test="${loop.count != 1}">${carouselItem}</c:if>
                            <div class="row">
                                <c:forEach items="${itemWithFileResponses}" var="itemWithFileResponse" varStatus="loopCounter">
                                    <c:if test="${loopCounter.count >= loop.count * 3 - 2 && loopCounter.count < loop.count * 3 + 1}">
                                        <%--<c:if test="${loopCounter.count %3 == 0}">${coldMd4}</c:if>
                                        <c:if test="${loopCounter.count %3 != 0}">${coldMd4Clearfix}</c:if>--%>
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
                <center>
                    <!--Controls-->
                    <div class="controls-top">
                        <a class="btn-floating" href="#multi-item-example" data-slide="prev"><i class="fa fa-chevron-left"></i><button type="button" class="btn btn-info">Spat</button></a>
                        <a class="btn-floating" href="#multi-item-example" data-slide="next"><i class="fa fa-chevron-right"></i><button type="button" class="btn btn-info">Dalsi</button></a>
                    </div>
                    <!--/.Controls-->
                </center>
            </div>
            <!--/.Carousel Wrapper-->
        </div>
    </body>
</html>