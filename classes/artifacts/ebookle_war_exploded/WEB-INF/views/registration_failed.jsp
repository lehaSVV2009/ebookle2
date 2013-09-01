<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="wrapper">

    <spring:message code="label.registration_failed" var="labelFailed"/>

    <div class="error">
        ${labelFailed}
    </div>

</div>