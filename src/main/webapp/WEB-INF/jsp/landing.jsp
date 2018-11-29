<%@ page import="org.springframework.web.util.WebUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" deferredSyntaxAllowedAsLiteral="true" buffer="64kb"
         pageEncoding="UTF-8" %>

<% Cookie lang = WebUtils.getCookie(request, "lang");
    String language = "en";%>
<% if (lang != null) {
    language = lang.getValue();
}%>


<!-- Mirrored from agmstudio.io:443/themes/material-style/2.0.2/ by HTTrack Website Copier/3.x [XR&CO'2014], Sat, 09 Dec 2017 07:38:58 GMT -->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="theme-color" content="#333">
    <title><spring:message code="brandName"/></title>
    <meta name="description" content="<spring:message code="brandName"/>">
    <link rel="shortcut icon" href="assets/img/favicon30f4.png?v=3">
    <%--<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">--%>
    <link rel="stylesheet" href="assets/css/preload.min.css">
    <link rel="stylesheet" href="assets/css/plugins.min.css">
    <link rel="stylesheet" href="assets/css/style.light-blue-500.min.css">
    <link rel="stylesheet" href="assets/css/width-boxed.min.css" id="ms-boxed" disabled="">
    <link rel="stylesheet" href="/assets/plugins/toastr/toastr.css"/>
    <link rel="stylesheet" href="/assets/css/course.css"/>
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
                    <%--<span class="ms-logo animated zoomInDown animation-delay-5">
                        T
                    </span>--%>
                    <h1 class="animated fadeInRight animation-delay-6"><spring:message code="brandName"/>
                    </h1>
                </a>
            </div>
            <div class="header-right">
                <jsp:include page="language.jsp"></jsp:include>
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
                <a id="profilePhoto"
                   class="btn-circle btn-circle-primary no-focus animated zoomInDown animation-delay-8">
                    <img src="" style="border-radius: 50%;" class="mini"/>
                </a>
                <a href="javascript:void(0)"
                   class="btn-ms-menu btn-circle btn-circle-primary ms-toggle-left animated zoomInDown animation-delay-10">
                    <i class="zmdi zmdi-menu"></i>
                </a>
            </div>
        </div>
    </header>
    <nav class="navbar navbar-expand-md  navbar-static ms-navbar ms-navbar-primary">
        <div class="container container-full">
            <div class="navbar-header language-selection">
                <a class="navbar-brand" href="/">
                    <img src="${headOffice.photo}?size=100_100" alt="" class="mini">
                    <%--<span class="ms-logo ms-logo-sm">T</span>--%>
                    <span class="ms-title"><spring:message code="brandName"/>
              </span>
                </a>

                <ul class="navbar navbar-nav">
                    <li class="nav-item <%="uz".equals(language)?"active":""%>">
                        <a class="myLanguage" id="uz" href="javascript:void(0)"><spring:message code="uz"/> </a>
                    </li>
                    <li class="nav-item <%="ru".equals(language)?"active":""%>">
                        <a class="myLanguage" id="ru" href="javascript:void(0)"><spring:message code="ru"/></a>
                    </li>
                    <li class="nav-item <%="en".equals(language)?"active":""%>">
                        <a class="myLanguage" id="en" href="javascript:void(0)"><spring:message code="en"/></a>
                    </li>
                </ul>
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
    <div class="ms-hero ms-hero-material">
        <span class="ms-hero-bg"></span>
        <div class="container">
            <div class="row">
                <div class="col-xl-6 col-lg-7">
                    <div id="carousel-hero" class="carousel slide carousel-fade" data-ride="carousel"
                         data-interval="8000">
                        <!-- Wrapper for slides -->
                        <div class="carousel-inner" role="listbox">
                            <div class="carousel-item active">
                                <div class="carousel-caption">
                                    <div class="ms-hero-material-text-container">
                                        <header class="ms-hero-material-title animated slideInLeft animation-delay-5">
                                            <h1 class="animated fadeInLeft animation-delay-15 font-smoothing">The
                                                <strong>power design</strong> amazing projects</h1>
                                            <h2 class="animated fadeInLeft animation-delay-18">The most customizable
                                                <span class="color-warning">material design</span> template.</h2>
                                        </header>
                                        <ul class="ms-hero-material-list">
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-18">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-warning shadow-3dp">
                                <i class="zmdi zmdi-airplane"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-19">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Illo, fugit sit
                                                    nesciunt cum rerum iusto.
                                                </div>
                                            </li>
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-20">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-success shadow-3dp">
                                <i class="zmdi zmdi-bike"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-21">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Nisi laborum
                                                    dignissimos fuga maxime facere.
                                                </div>
                                            </li>
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-22">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-danger shadow-3dp">
                                <i class="zmdi zmdi-album"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-23">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Reprehenderit
                                                    sequi est repudianda.
                                                </div>
                                            </li>
                                        </ul>
                                        <div class="ms-hero-material-buttons text-right">
                                            <a href="/login"
                                               class="btn btn-warning btn-raised animated fadeInLeft animation-delay-24 mr-2">
                                                <i class="zmdi zmdi-sign-in"></i> <spring:message code="login"/> </a>
                                            <a href="/sign-up"
                                               class="btn btn-success btn-raised animated fadeInRight animation-delay-24">
                                                <i class="zmdi zmdi-watch"></i> <spring:message code="signUp"/> </a>
                                        </div>
                                    </div>
                                    <!-- ms-hero-material-text-container -->
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="carousel-caption">
                                    <div class="ms-hero-material-text-container">
                                        <header class="ms-hero-material-title animated slideInLeft animation-delay-5">
                                            <h1 class="animated fadeInLeft animation-delay-15">A template with
                                                <strong>infinite</strong> possibilities</h1>
                                            <h2 class="animated fadeInLeft animation-delay-18">Unlimited combinations to
                                                create
                                                <span class="color-warning">unique designs</span> .</h2>
                                        </header>
                                        <ul class="ms-hero-material-list">
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-18">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-info shadow-3dp">
                                <i class="zmdi zmdi-city"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-19">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Illo, fugit sit
                                                    nesciunt cum rerum iusto.
                                                </div>
                                            </li>
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-20">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-royal shadow-3dp">
                                <i class="zmdi zmdi-cake"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-21">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Nisi laborum
                                                    dignissimos fuga maxime facere.
                                                </div>
                                            </li>
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-22">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-warning shadow-3dp">
                                <i class="zmdi zmdi-coffee"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-23">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Reprehenderit
                                                    sequi est repudianda.
                                                </div>
                                            </li>
                                        </ul>
                                        <div class="ms-hero-material-buttons text-right">
                                            <a href="/login"
                                               class="btn btn-warning btn-raised animated fadeInLeft animation-delay-24 mr-2">
                                                <i class="zmdi zmdi-sign-in"></i> <spring:message code="login"/> </a>
                                            <a href="/sign-up"
                                               class="btn btn-success btn-raised animated fadeInRight animation-delay-24">
                                                <i class="zmdi zmdi-watch"></i> <spring:message code="signUp"/> </a>
                                        </div>
                                    </div>
                                    <!-- ms-hero-material-text-container -->
                                </div>
                            </div>
                            <div class="carousel-item">
                                <div class="carousel-caption">
                                    <div class="ms-hero-material-text-container">
                                        <header class="ms-hero-material-title animated slideInLeft animation-delay-5">
                                            <h1 class="animated fadeInLeft animation-delay-15">Commitment of
                                                <strong>monthly updates</strong>.</h1>
                                            <h2 class="animated fadeInLeft animation-delay-18">There will soon be a
                                                version for
                                                <span class="color-warning">Bootstrap 4</span>.</h2>
                                        </header>
                                        <ul class="ms-hero-material-list">
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-18">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-danger shadow-3dp">
                                <i class="zmdi zmdi-cutlery"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-19">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Illo, fugit sit
                                                    nesciunt cum rerum iusto.
                                                </div>
                                            </li>
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-20">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-info shadow-3dp">
                                <i class="zmdi zmdi-dns"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-21">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Nisi laborum
                                                    dignissimos fuga maxime facere.
                                                </div>
                                            </li>
                                            <li class="">
                                                <div class="ms-list-icon animated zoomInUp animation-delay-22">
                              <span class="ms-icon ms-icon-circle ms-icon-xlg color-success shadow-3dp">
                                <i class="zmdi zmdi-compass"></i>
                              </span>
                                                </div>
                                                <div class="ms-list-text animated fadeInRight animation-delay-23">Lorem
                                                    ipsum dolor sit amet, consectetur adipisicing elit. Reprehenderit
                                                    sequi est repudianda.
                                                </div>
                                            </li>
                                        </ul>
                                        <div class="ms-hero-material-buttons text-right">
                                            <a href="/login"
                                               class="btn btn-warning btn-raised animated fadeInLeft animation-delay-24 mr-2">
                                                <i class="zmdi zmdi-sign-in"></i> <spring:message code="login"/> </a>
                                            <a href="/sign-up"
                                               class="btn btn-success btn-raised animated fadeInRight animation-delay-24">
                                                <i class="zmdi zmdi-watch"></i> <spring:message code="signUp"/> </a>
                                        </div>
                                    </div>
                                    <!-- ms-hero-material-text-container -->
                                </div>
                            </div>
                            <div class="carousel-controls">
                                <!-- Controls -->
                                <a class="left carousel-control animated zoomIn animation-delay-30"
                                   href="#carousel-hero" role="button" data-slide="prev">
                                    <i class="zmdi zmdi-chevron-left"></i>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="right carousel-control animated zoomIn animation-delay-30"
                                   href="#carousel-hero" role="button" data-slide="next">
                                    <i class="zmdi zmdi-chevron-right"></i>
                                    <span class="sr-only">Next</span>
                                </a>
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#carousel-hero" data-slide-to="0"
                                        class="animated fadeInUpBig animation-delay-27 active"></li>
                                    <li data-target="#carousel-hero" data-slide-to="1"
                                        class="animated fadeInUpBig animation-delay-28"></li>
                                    <li data-target="#carousel-hero" data-slide-to="2"
                                        class="animated fadeInUpBig animation-delay-29"></li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-5">
                    <div class="ms-hero-img animated zoomInUp animation-delay-30">
                        <img src="assets/img/demo/mock-imac-material2.png" alt="" class="img-fluid">
                        <div id="carousel-hero-img" class="carousel carousel-fade slide" data-ride="carousel"
                             data-interval="3000">
                            <!-- Indicators -->
                            <ol class="carousel-indicators carousel-indicators-hero-img">
                                <li data-target="#carousel-hero-img" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-hero-img" data-slide-to="1"></li>
                                <li data-target="#carousel-hero-img" data-slide-to="2"></li>
                            </ol>
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="ms-hero-img-slider carousel-item active">
                                    <img src="assets/img/demo/hero1.png" alt="" class="img-fluid"></div>
                                <div class="ms-hero-img-slider carousel-item">
                                    <img src="assets/img/demo/hero3.png" alt="" class="img-fluid"></div>
                                <div class="ms-hero-img-slider carousel-item">
                                    <img src="assets/img/demo/hero2.png" alt="" class="img-fluid"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- container -->
    </div>
    <!-- ms-hero ms-hero-black -->
    <div class="section container mt-4" id="features">
        <h2 class="text-center color-primary mb-2 wow fadeInDown animation-delay-4"><spring:message
                code="knowOurFeatures"/></h2>
        <p class="lead text-center aco wow fadeInDown animation-delay-5 mw-800 center-block mb-4">
            <spring:message code="ourFeatures"/></p>
        <div class="row">
            <div class="ms-feature col-xl-3 col-lg-6 col-md-6 card wow flipInX animation-delay-4">
                <div class="text-center card-block">
              <span class="ms-icon ms-icon-circle ms-icon-xxlg color-info">
                <i class="zmdi zmdi-cloud-outline"></i>
              </span>
                    <h4 class="color-info"><spring:message code="smartRoom"/></h4>
                    <p class="" style="height: 100px">
                        <spring:message code="smartRoomDescription"/>
                    </p>
                    <a href="javascript:void(0)" class="btn btn-info btn-raised"><spring:message code="begin"/></a>
                </div>
            </div>
            <div class="ms-feature col-xl-3 col-lg-6 col-md-6 card wow flipInX animation-delay-8">
                <div class="text-center card-block">
              <span class="ms-icon ms-icon-circle ms-icon-xxlg color-warning">
                <i class="zmdi zmdi-desktop-mac"></i>
              </span>
                    <h4 class="color-warning"><spring:message code="modernTechnologies"/></h4>
                    <p class="" style="height: 100px"><spring:message code="modernTechnologiesDescription"/></p>
                    <a href="javascript:void(0)" class="btn btn-warning btn-raised"><spring:message code="begin"/> </a>
                </div>
            </div>
            <div class="ms-feature col-xl-3 col-lg-6 col-md-6 card wow flipInX animation-delay-10">
                <div class="text-center card-block">
              <span class="ms-icon ms-icon-circle ms-icon-xxlg color-success">
                <i class="zmdi zmdi-download"></i>
              </span>
                    <h4 class="color-success"><spring:message code="goodTeachers"/></h4>
                    <p style="height: 100px" class=""><spring:message code="goodTeachersDescription"/></p>
                    <a href="javascript:void(0)" class="btn btn-success btn-raised"><spring:message code="begin"/> </a>
                </div>
            </div>
            <div class="ms-feature col-xl-3 col-lg-6 col-md-6 card wow flipInX animation-delay-6">
                <div class="text-center card-block">
              <span class="ms-icon ms-icon-circle ms-icon-xxlg  color-danger">
                <i class="zmdi zmdi-flower-alt"></i>
              </span>
                    <h4 class="color-danger"><spring:message code="goodAtmosphere"/></h4>
                    <p style="height: 100px" class=""><spring:message code="goodAtmosphereDescription"/></p>
                    <a href="javascript:void(0)" class="btn btn-danger btn-raised"><spring:message code="begin"/></a>
                </div>
            </div>
        </div>
    </div>
    <!-- container -->
    <div class="section container mt-6" id="subscriptions">
        <div class="text-center mb-4">
            <h2 class="uppercase color-primary"><spring:message code="subscriptionPlans"/></h2>
        </div>

        <div class="owl-dots"></div>
        <div class="owl-carousel owl-theme no-gutters">
            <c:forEach var="lesson" items="${lessons}" varStatus="n">
                <div class="card card-royal animation-delay-5">

                    <div class="price-table price-table-info ${n.index % 2 > 0? 'prominent':''} wow zoomInUp animation-delay-2">
                        <header class="price-table-header">
                                <%--<span class="price-table-category">${lesson.name}</span>--%>
                            <h3>
                                    ${lesson.toString()}
                                <sub>
                                        <%--<spring:message code="uzd"/> / <spring:message code="month"/>--%>
                                </sub>
                            </h3>
                        </header>
                        <div class="price-table-body">
                            <ul class="price-table-list" style="height: 200px;">
                                    ${lesson.description}
                            </ul>
                            <div class="text-center">
                                <a href="javascript:void(0)" class="btn btn-info btn-raised">
                                    <i class="zmdi zmdi-cloud-download"></i><spring:message code="begin"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </div>

    <div class="section ms-hero-page-override ms-hero-img-team ms-hero-bg-primary" id="ourTeam">
        <div class="container">
            <div class="text-center">
                <h1 class="no-m ms-site-title color-white center-block ms-site-title-lg mt-2 animated zoomInDown animation-delay-5">
                    <spring:message code="ourTeam"/></h1>
                <p class="lead lead-lg color-medium text-center center-block mt-2 mw-800 text-uppercase fw-300 animated fadeInUp animation-delay-7">
                    <spring:message code="ourTeamDescription"/>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row d-flex justify-content-center card-top">
            <%
                String[] classes = new String[]{"danger", "warning", "info", "success", "primary", "royal"};
                int i = 0;
            %>
            <c:forEach var="teacher" items="${teachers}">
                <div class="col-lg-4 col-md-6">
                    <div class="card card-<%=classes[i%6]%> wow zoomInUp mb-4 animation-delay-5">
                        <div class="withripple zoom-img">
                            <a href="javascript:;" class="">
                                <img src="${empty teacher.photo ? "assets/img/demo/p2.jpg": teacher.photo}?size=300_300"
                                     alt="..." class="img-fluid img_300_300">
                            </a>
                        </div>
                        <div class="card-block">
                            <span class="label label-<%=classes[i%6]%> pull-right">${teacher.lesson}</span>
                            <h3 class="color-<%=classes[i%6]%>">${teacher.shortName}</h3>
                            <p style="height: 100px">${teacher.note}</p>
                            <div class="row">
                                <div class="col">
                                    <a href="javascript:void(0)"
                                       class="btn-circle btn-circle-raised btn-circle-xs mt-1 mr-1 no-mr-md btn-facebook">
                                        <i class="zmdi zmdi-facebook"></i>
                                    </a>
                                    <a href="javascript:void(0)"
                                       class="btn-circle btn-circle-raised btn-circle-xs mt-1 mr-1 no-mr-md btn-twitter">
                                        <i class="zmdi zmdi-twitter"></i>
                                    </a>
                                    <a href="javascript:void(0)"
                                       class="btn-circle btn-circle-raised btn-circle-xs mt-1 mr-1 no-mr-md btn-instagram">
                                        <i class="zmdi zmdi-instagram"></i>
                                    </a>
                                </div>
                                <div class="col text-right">
                                    <a href="javascript:void(0)" class="btn btn-raised btn-sm btn-<%=classes[i%6]%>">
                                        <i class="zmdi zmdi-account"></i> Profile</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%i++;%>
            </c:forEach>

        </div>
    </div>

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

<script src="assets/js/index.js"></script>

<script type="text/javascript" src="/assets/plugins/toastr/toastr.min.js"></script>
<script src="assets/js/transfer.js"></script>

</body>

</html>