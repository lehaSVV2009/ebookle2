<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper">

    <spring:message code="registration_label" var="registrationLabel"/>
    <spring:message code="login_label" var="loginLabel"/>
    <spring:message code="password_label" var="passwordLabel"/>
    <spring:message code="email_label" var="emailLabel"/>
    <spring:message code="name_label" var="nameLabel"/>
    <spring:message code="surname_label" var="surnameLabel"/>
    <spring:message code="register_text" var="registerText"/>

    <c:if test="${not empty badInput}">
        <div class="error">
            ${badInput}
        </div>
        <br/>
    </c:if>

    <form action="/register" id="registrationForm" name="registrationForm" method="post"
          class="bs-example form-horizontal well">
        <fieldset>
            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">${loginLabel}</label>

                <div class="col-lg-10">
                    <input type="text" name="login"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">${passwordLabel}</label>

                <div class="col-lg-10">
                    <input type="password" name="password"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">${emailLabel}</label>

                <div class="col-lg-10">
                    <input type="email" name="email"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">${nameLabel}</label>

                <div class="col-lg-10">
                    <input type="text" name="name"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">${surnameLabel}</label>

                <div class="col-lg-10">
                    <input type="text" name="surname"/>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-default btn-lg btn-block">${registerText}</button>
            </div>
        </fieldset>
    </form>
</div>