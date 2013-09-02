<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<?xml version="1.0" encoding="UTF-8" standalone="no"?>

<div id="header">

    <spring:message code="search_by_content" var="serachByContent"/>
    <spring:message code="search_by_tag" var="searchByTag"/>
    <spring:message code="search_by_category" var="searchByCategory"/>
    <spring:url value="/" var="homeUrl"/>

    <div id="Header" class="navbar nav-bar-default fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="${homeUrl}" title="Home">

                    <img src="http://localhost:8080/web-resources/img/logo-small.png"/>
                </a>
            </div>
            <form class="navbar-form navbar-right" action="/searchAll" method="post">
                <input id="searchString" class="form-control col-lg-8" placeholder="Search" name="searchString"/>
            </form>
            <form class="navbar-form navbar-right">
                <select class="form-control" id="select">
                    <option value="1">${serachByContent}</option>
                    <option value="2">${searchByTag}</option>
                    <option value="3">${searchByCategory}</option>
                </select>
            </form>
        </div>
    </div>

</div>