<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper registration-div">

    <spring:message code="registration_label" var="registrationLabel"/>
    <spring:message code="login_label" var="loginLabel"/>
    <spring:message code="password_label" var="passwordLabel"/>
    <spring:message code="email_label" var="emailLabel"/>
    <spring:message code="name_label" var="nameLabel"/>
    <spring:message code="surname_label" var="surnameLabel"/>
    <spring:message code="register_text" var="registerText"/>

    <c:if test="${not empty badInput}">
        <div class="alert-error">
                ${badInput}
        </div>
        <br/>
    </c:if>

    <div class="well">
        <form action="/register" id="registrationForm" name="registrationForm" method="post" class="form-horizontal"
              role="form">
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="text" class="form-control" placeholder="${loginLabel}" name="login">
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="password" class="form-control" placeholder="${passwordLabel}" name="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="text" class="form-control" placeholder="${emailLabel}" name="email">
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="text" class="form-control" placeholder="${nameLabel}" name="name">
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="text" class="form-control" placeholder="${surnameLabel}" name="surname">
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button type="submit" class="btn btn-default">${registerText}</button>
                </div>
            </div>

        </form>

    </div>
</div>