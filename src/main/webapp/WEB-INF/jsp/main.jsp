<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<html lang="en">
  <head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

     <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
    <script src="//cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>

    <style>
    body {
      background-color:#1d1d1d !important;
      font-family: "Asap", sans-serif;
      color:#989898;
      margin:10px;
      font-size:16px;
    }

    #demo {
      height:100%;
      position:relative;
      overflow:hidden;
    }


    .green{
      background-color:#6fb936;
    }
            .thumb{
                margin-bottom: 30px;
            }

            .page-top{
                margin-top:85px;
            }


    img.zoom {
        width: 100%;
        height: 200px;
        border-radius:5px;
        object-fit:cover;
        -webkit-transition: all .3s ease-in-out;
        -moz-transition: all .3s ease-in-out;
        -o-transition: all .3s ease-in-out;
        -ms-transition: all .3s ease-in-out;
    }


    .transition {
        -webkit-transform: scale(1.2);
        -moz-transform: scale(1.2);
        -o-transform: scale(1.2);
        transform: scale(1.2);
    }
        .modal-header {

         border-bottom: none;
    }
        .modal-title {
            color:#000;
        }
        .modal-footer{
          display:none;
        }

    </style>

    <title>Hello, world!</title>
  </head>
  <body>

  <script>
  $(document).ready(function(){
    $(".fancybox").fancybox({
          openEffect: "none",
          closeEffect: "none"
      });

      $(".zoom").hover(function(){

  		$(this).addClass('transition');
  	}, function(){

  		$(this).removeClass('transition');
  	});
  });
  </script>

      <center>
          <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/createItem1"> Create item </a>
      </center>

    <!-- Page Content -->
       
        
        <!-- Page Content -->
       <div class="container page-top">
            <div class="row">

            <c:forEach items="${itemsWithFiles}" var="itemWithFiles">
                <div class="col-lg-3 col-md-4 col-xs-6 thumb">
                    <a href="${itemWithFiles}" class="fancybox" rel="ligthbox"> 
                        <img src="${itemWithFiles}" class="zoom img-fluid "  alt=""/> 
                    </a>
                </div>
            </c:forEach>

           </div>
        </div>

    <center>
          <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAll_v2?page=0"> 0 </a>
          <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAll_v2?page=1"> 1 </a>
          <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAll_v2?page=2"> 2 </a>
          <a href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/getAll_v2?page=3"> 3 </a>
    </center>

  </body>
</html>