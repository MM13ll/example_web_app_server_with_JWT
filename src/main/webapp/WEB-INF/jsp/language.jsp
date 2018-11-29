<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.web.util.WebUtils" %>

<% Cookie lang = WebUtils.getCookie(request, "lang");
    String language = "en";%>
<% if (lang != null) {
    language = lang.getValue();
}%>

<div class="language-selection pull-left">
    <ul class="nav" class="waves-effect waves-dark">
        <li>
            <a class="myLanguage <%="uz".equals(language)?"selectedLang":""%>" id="uz" href="javascript:void(0)"><spring:message code="uz"/></a>
        </li>
        <li>
            <a class="myLanguage <%="ru".equals(language)?"selectedLang":""%>" id="ru" href="javascript:void(0)"><spring:message code="ru"/></a>
        </li>
        <li>
            <a class="myLanguage <%="en".equals(language)?"selectedLang":""%>" id="en" href="javascript:void(0)"><spring:message code="en"/></a>
        </li>
    </ul>
</div>