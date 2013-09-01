<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<footer>

    <spring:message code="footer_text" var="footerText"/>

    <div class="container">

        <span>
            <a href="?lang=ru"><img src="http://localhost:8080/web-resources/img/rus16.png"/>rus</a>
            <a href="?lang=en"><img src="http://localhost:8080/web-resources/img/eng16.png"/>eng</a>
        </span>

        ${footerText}

        <span style="float: right">
            <a href="?theme=dark">dark</a> |
            <a href="?theme=light">light</a>
        </span>

    </div>
</footer>
