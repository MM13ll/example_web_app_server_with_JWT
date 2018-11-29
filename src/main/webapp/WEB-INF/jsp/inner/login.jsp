<%@ page contentType="text/html; charset=UTF-8" deferredSyntaxAllowedAsLiteral="true" buffer="64kb"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="container">
    <div class="row justify-content-md-center">
        <div class="col-lg-6">
            <div class="card card-hero card-primary animated fadeInUp animation-delay-7">
                <div class="card-block">
                    <h1 class="color-primary text-center"><spring:message code="login"/></h1>
                    <form autocomplete="on" method="post" action="/login" class="form-horizontal">
                        <fieldset>
                            <div class="form-group row">
                                <label for="userName" class="col-md-3 control-label"><spring:message
                                        code="phone"/></label>
                                <div class="col-md-9">
                                    <input type="tel" class="form-control" id="userName" name="userName">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="password" class="col-md-3 control-label"><spring:message
                                        code="password"/></label>
                                <div class="col-md-9">
                                    <input type="password" class="form-control" id="password"
                                           name="password">
                                </div>
                            </div>
                        </fieldset>
                        <button class="btn btn-raised btn-primary btn-block">Login
                            <i class="zmdi zmdi-long-arrow-right no-mr ml-1"></i>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>