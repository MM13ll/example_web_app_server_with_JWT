<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" deferredSyntaxAllowedAsLiteral="true" buffer="64kb"
         pageEncoding="UTF-8" %>

<!-- Mirrored from agmstudio.io:443/themes/material-style/2.0.2/ by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 09 Dec 2017 07:38:58 GMT -->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="theme-color" content="#333">
    <title><spring:message code="brandName"/></title>
    <base href="/"/>
    <meta name="description" content="<spring:message code="brandName"/>">
    <link rel="shortcut icon" href="assets/img/favicon30f4.png?v=3">
    <%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
    <link rel="stylesheet" href="assets/css/preload.min.css">
    <link rel="stylesheet" href="assets/css/plugins.min.css">
    <link rel="stylesheet" href="assets/css/style.light-blue-500.min.css">
    <link rel="stylesheet" href="assets/css/width-boxed.min.css" id="ms-boxed" disabled="">
    <link rel="stylesheet" href="/assets/plugins/toastr/toastr.css"/>
    <link rel="stylesheet" href="/assets/css/course.css"/>
    <link rel="stylesheet" href="/assets/plugins/select2/css/select2.min.css"/>
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.min.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div id="ms-preload" class="ms-preload">
    <div id="status">
        <div class="spinner">
            <div class="dot1"></div>
            <div class="dot2"></div>
        </div>
    </div>
</div>
<div class="ms-site-container">
    <header class="ms-header ms-header-white">
        <!--ms-header-primary-->
        <div class="container-fluid container-full">
            <div class="ms-title">
                <a href="/">
                    <img src="${headOffice.photo}?size=100_100" alt="">
                    <%--<span class="ms-logo animated zoomInDown animation-delay-5">T</span>--%>
                    <h1 class="animated fadeInRight animation-delay-6"><spring:message code="brandName"/>
                    </h1>
                </a>
            </div>
            <div class="header-right">
                <div class="share-menu">
                    <ul class="share-menu-list">
                        <li class="animated fadeInRight animation-delay-3">
                            <a href="//${headOffice.telegram}" class="btn-circle btn-twitter">
                                <i class="fa fa-telegram fa-lg"></i>
                            </a>
                        </li>
                        <li class="animated fadeInRight animation-delay-2">
                            <a href="//${headOffice.facebook}" class="btn-circle btn-facebook">
                                <i class="zmdi zmdi-facebook"></i>
                            </a>
                        </li>
                        <li class="animated fadeInRight animation-delay-1">
                            <a href="//${headOffice.twitter}" class="btn-circle btn-twitter">
                                <i class="zmdi zmdi-twitter"></i>
                            </a>
                        </li>
                    </ul>
                    <a href="javascript:void(0)"
                       class="btn-circle btn-circle-primary animated zoomInDown animation-delay-7">
                        <i class="zmdi zmdi-share"></i>
                    </a>
                </div>

                <a href="javascript:void(0)"
                   class="btn-ms-menu btn-circle btn-circle-primary ms-toggle-left animated zoomInDown animation-delay-10">
                    <i class="zmdi zmdi-menu"></i>
                </a>
            </div>
        </div>
    </header>
    <nav class="navbar navbar-expand-md  navbar-static ms-navbar ms-navbar-primary">
        <div class="container container-full">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">
                    <img src="${headOffice.photo}?size=100_100" alt="">
                    <%--<span class="ms-logo ms-logo-sm">T</span>--%>
                    <span class="ms-title"><spring:message code="brandName"/>
              </span>
                </a>
            </div>
            <div class="collapse navbar-collapse" id="ms-navbar">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle animated fadeIn animation-delay-7"
                           role="button"><spring:message code="home"/>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#features" class="nav-link dropdown-toggle animated fadeIn animation-delay-7"
                           role="button"><spring:message code="features"/>
                        </a>
                    </li>
                    <li class="nav-item dropdown dropdown-megamenu-container">
                        <a href="#subscriptions" class="nav-link dropdown-toggle animated fadeIn animation-delay-7"
                           role="button"><spring:message code="lessons"/>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#ourTeam" class="nav-link dropdown-toggle animated fadeIn animation-delay-7"
                           role="button"><spring:message code="ourTeam"/>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="/login" class="nav-link dropdown-toggle animated fadeIn animation-delay-7"
                           role="button"><spring:message code="login"/>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="/sign-up" class="nav-link dropdown-toggle animated fadeIn animation-delay-7"
                           role="button"><spring:message code="signUp"/>
                        </a>
                    </li>
                </ul>
            </div>
            <a href="javascript:void(0)" class="ms-toggle-left btn-navbar-menu">
                <i class="zmdi zmdi-menu"></i>
            </a>
        </div>
        <!-- container -->
    </nav>


    <div class="ms-hero-page-override ms-hero-img-city ms-hero-bg-dark-light">
        <div class="container">
            <div class="text-center">
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${'login'.equals(page)}">
            <jsp:include page="inner/login.jsp"/>
        </c:when>
        <c:when test="${'sign-up'.equals(page)}">
            <jsp:include page="inner/sign-up.jsp" flush="false"/>
        </c:when>
        <c:when test="${'change-password'.equals(page)}">
            <jsp:include page="inner/change-password.jsp"/>
        </c:when>
    </c:choose>

    <!--container -->
    <aside class="ms-footbar">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 ms-footer-col">
                    <div class="ms-footbar-block">
                        <h3 class="ms-footbar-title"><spring:message code="siteMap"/></h3>
                        <ul class="list-unstyled ms-icon-list three_cols row">
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-book"></i><spring:message code="lessons"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-users"></i><spring:message code="students"/></a>
                            </li>
                            <li>
                                <a href="javascript:;"><i
                                        class="fa fa-user-secret"></i><spring:message code="teachers"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-calendar"></i><spring:message code="calendar"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-home"></i><spring:message code="rooms"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-institution"></i><spring:message code="branchs"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-hourglass-start"></i><spring:message code="attendance"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-send-o"></i><spring:message code="sms"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-address-book-o"></i><spring:message code="contact"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-address-card-o"></i><spring:message code="personalCabinet"/></a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="zmdi zmdi-help"></i><spring:message code="aboutUs"/></a>
                            </li>
                        </ul>
                    </div>

                </div>
                <div class="col-lg-5 col-md-7 ms-footer-col ms-footer-alt-color">
                    <div class="ms-footbar-block">
                        <h3 class="ms-footbar-title text-center mb-2">Last Articles</h3>
                        <div class="ms-footer-media">
                            <div class="media">
                                <div class="media-left media-middle">
                                    <a href="javascript:void(0)">
                                        <img class="media-object media-object-circle" src="assets/img/demo/p75.jpg"
                                             alt="..."> </a>
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">
                                        <a href="javascript:void(0)">Lorem ipsum dolor sit expedita cumque amet
                                            consectetur adipisicing repellat</a>
                                    </h4>
                                    <div class="media-footer">
                        <span>
                          <i class="zmdi zmdi-time color-info-light"></i> August 18, 2016</span>
                                        <span>
                          <i class="zmdi zmdi-folder-outline color-warning-light"></i>
                          <a href="javascript:void(0)">Design</a>
                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="media">
                                <div class="media-left media-middle">
                                    <a href="javascript:void(0)">
                                        <img class="media-object media-object-circle" src="assets/img/demo/p75.jpg"
                                             alt="..."> </a>
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">
                                        <a href="javascript:void(0)">Labore ut esse Duis consectetur expedita cumque
                                            ullamco ad dolor veniam velit</a>
                                    </h4>
                                    <div class="media-footer">
                        <span>
                          <i class="zmdi zmdi-time color-info-light"></i> August 18, 2016</span>
                                        <span>
                          <i class="zmdi zmdi-folder-outline color-warning-light"></i>
                          <a href="javascript:void(0)">News</a>
                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="media">
                                <div class="media-left media-middle">
                                    <a href="javascript:void(0)">
                                        <img class="media-object media-object-circle" src="assets/img/demo/p75.jpg"
                                             alt="..."> </a>
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">
                                        <a href="javascript:void(0)">voluptates deserunt ducimus expedita cumque quaerat
                                            molestiae labore</a>
                                    </h4>
                                    <div class="media-footer">
                        <span>
                          <i class="zmdi zmdi-time color-info-light"></i> August 18, 2016</span>
                                        <span>
                          <i class="zmdi zmdi-folder-outline color-warning-light"></i>
                          <a href="javascript:void(0)">Productivity</a>
                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-5 ms-footer-col ms-footer-text-right">
                    <div class="ms-footbar-block">
                        <div class="ms-footbar-title">
                            <img src="${headOffice.photo}?size=100_100" alt="">
                            <%--<span class="ms-logo ms-logo-white ms-logo-sm mr-1">T</span>--%>
                            <h3 class="no-m ms-site-title"><spring:message code="brandName"/>
                            </h3>
                        </div>
                        <address class="no-mb">
                            <p>
                                <i class="color-danger-light zmdi zmdi-pin mr-1"></i> ${headOffice.region} ${headOffice.city} ${headOffice.addressLine}
                            </p>
                            <p>
                                <i class="color-info-light zmdi zmdi-email mr-1"></i>
                                <a href="mailto:joe@example.com">${headOffice.email}</a>
                            </p>
                            <p>
                                <i class="color-royal-light zmdi zmdi-phone mr-1"></i>${headOffice.phone1} </p>
                            <c:if test="${not empty headOffice.phone2}">
                                <p>
                                    <i class="color-royal-light zmdi zmdi-phone mr-1"></i>${headOffice.phone2}
                                </p></c:if>
                            <c:if test="${not empty headOffice.phone3}">
                                <p>
                                    <i class="color-royal-light zmdi zmdi-phone mr-1"></i>${headOffice.phone3}
                                </p></c:if>

                        </address>
                    </div>
                    <div class="ms-footbar-block">
                        <h3 class="ms-footbar-title"><spring:message code="socialInfo"/></h3>
                        <div class="ms-footbar-social">
                            <a href="//${headOffice.facebook}" class="btn-circle btn-facebook" target="_blank">
                                <i class="zmdi zmdi-facebook"></i>
                            </a>
                            <a href="//${headOffice.twitter}" class="btn-circle btn-twitter" target="_blank">
                                <i class="zmdi zmdi-twitter"></i>
                            </a>
                            <a href="//${headOffice.youtube}" class="btn-circle btn-youtube" target="_blank">
                                <i class="zmdi zmdi-youtube-play"></i>
                            </a>
                            <a href="//${headOffice.google}" class="btn-circle btn-google" target="_blank">
                                <i class="zmdi zmdi-google"></i>
                            </a>
                            <br/>
                            <a href="//${headOffice.instagramm}" class="btn-circle btn-instagram" target="_blank">
                                <i class="zmdi zmdi-instagram"></i>
                            </a>
                            <a href="//${headOffice.telegram}" class="btn-circle btn-twitter" target="_blank">
                                <i class="fa fa-telegram fa-lg"></i>
                            </a>
                            <a href="//${headOffice.odnoklassniki}" class="btn-circle btn-google" target="_blank">
                                <i class="fa fa-odnoklassniki fa-lg"></i>
                            </a>
                            <a href="//${headOffice.whatsapp}" class="btn-circle btn-twitter" target="_blank">
                                <i class="fa fa-whatsapp fa-lg"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </aside>
    <footer class="ms-footer">
        <div class="container">
            <p>Copyright &copy; Rahimjon 2018</p>
        </div>
    </footer>
    <div class="btn-back-top">
        <a href="#" data-scroll id="back-top" class="btn-circle btn-circle-primary btn-circle-sm btn-circle-raised ">
            <i class="zmdi zmdi-long-arrow-up"></i>
        </a>
    </div>
</div>
<!-- ms-site-container -->
<div class="ms-slidebar sb-slidebar sb-left sb-style-overlay" id="ms-slidebar">
    <div class="sb-slidebar-container">
        <header class="ms-slidebar-header">
            <div class="ms-slidebar-login">
                <a href="javascript:void(0)" class="withripple">
                    <i class="zmdi zmdi-account"></i> Login</a>
                <a href="javascript:void(0)" class="withripple">
                    <i class="zmdi zmdi-account-add"></i> Register</a>
            </div>
            <div class="ms-slidebar-title">
                <div class="ms-slidebar-t">
                    <img src="${headOffice.photo}?size=100_100" alt="">
                    <%--<span class="ms-logo ms-logo-sm">T</span>--%>
                    <h3><spring:message code="brandName"/>
                    </h3>
                </div>
            </div>
        </header>
        <ul class="ms-slidebar-menu" id="slidebar-menu" role="tablist" aria-multiselectable="true">
            <li>
                <a href="#">
                    <i class="zmdi zmdi-home"></i> <spring:message code="home"/> </a>
            </li>
            <li>
                <a href="#features">
                    <i class="zmdi zmdi-desktop-mac"></i> <spring:message code="features"/> </a>
            </li>
            <li>
                <a href="#subscriptions">
                    <i class="fa fa-book fa-lg"></i> <spring:message code="lessons"/> </a>
            </li>
            <li class="card">
                <a href="#ourTeam">
                    <i class="fa fa-user-secret fa-lg"></i> <spring:message code="ourTeam"/> </a>
            </li>
            <li class="card">
                <a href="/login">
                    <i class="fa fa-user-secret fa-lg"></i> <spring:message code="login"/> </a>
            </li>
            <li class="card">
                <a href="/sign-up">
                    <i class="fa fa-user-secret fa-lg"></i> <spring:message code="signUp"/> </a>
            </li>
        </ul>
        <div class="ms-slidebar-social ms-slidebar-block">
            <h4 class="ms-slidebar-block-title"><spring:message code="socialInfo"/></h4>
            <div class="ms-slidebar-social">
                <a href="//${headOffice.facebook}" class="btn-circle btn-circle-raised btn-facebook" target="_blank">
                    <i class="zmdi zmdi-facebook"></i>
                    <div class="ripple-container"></div>
                </a>
                <a href="//${headOffice.twitter}" class="btn-circle btn-circle-raised btn-twitter" target="_blank">
                    <i class="zmdi zmdi-twitter"></i>
                    <div class="ripple-container"></div>
                </a>
                <a href="//${headOffice.telegram}" class="btn-circle btn-circle-raised btn-twitter" target="_blank">
                    <i class="fa fa-telegram fa-lg"></i>
                    <div class="ripple-container"></div>
                </a>
                <a href="//${headOffice.instagramm}" class="btn-circle btn-circle-raised btn-instagram" target="_blank">
                    <i class="zmdi zmdi-instagram"></i>
                    <div class="ripple-container"></div>
                </a>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/plugins.min.js"></script>
<script src="assets/js/app.min.js"></script>

<script type="text/javascript" src="/assets/plugins/select2/js/select2.full.min.js"></script>
<script type="text/javascript" src="/assets/plugins/toastr/toastr.min.js"></script>
<script src="assets/js/transfer.js"></script>
<script src="assets/js/login.js"></script>

</body>

</html>