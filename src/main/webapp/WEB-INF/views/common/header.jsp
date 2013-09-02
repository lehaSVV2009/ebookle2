<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<?xml version="1.0" encoding="UTF-8" standalone="no"?>

<div id="header">

    <spring:message code="search_by_tag" var="searchByTag"/>
    <spring:message code="search_by_captions" var="searchCaptions"/>
    <spring:message code="search_by_content" var="searchByContent"/>
    <spring:message code="label.search" var="Search"/>
    <spring:url value="/" var="homeUrl"/>

    <div id="Header" class="navbar nav-bar-default fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a href="${homeUrl}" title="Home">
                    <img id="headerImg" src="http://localhost:8080/web-resources/img/logo-kittens.png"/>
                </a>
            </div>
            <form class="navbar-form navbar-right" action="/search" method="post">
                <input id="searchString" class="form-control col-lg-8" placeholder="${Search}" name="searchString">
                <select class="form-control" id="options" name="options">
                    <option value="1">${searchByTag}</option>
                    <option value="2">${searchCaptions}</option>
                    <option value="3">${searchByContent}</option>
                </select>
            </form>
        </div>
    </div>

</div>