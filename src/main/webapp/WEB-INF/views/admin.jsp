<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper">

    <spring:message code="label.administration" var="labelAdministration"/>
    <spring:message code="label.name_head" var="labelSurname"/>
    <spring:message code="label.surname_head" var="labelName"/>
    <spring:message code="label.login_head" var="labelLogin"/>
    <spring:message code="label.remove_head" var="labelRemove"/>

    <h2>${labelAdministration}</h2>

    <table class="table table-bordered table-striped table1">
        <thead>
        <tr>
            <td>
                ${labelSurname}
            </td>
            <td>
                ${labelName}
            </td>
            <td>
                ${labelLogin}
            </td>
            <td>
                ${labelRemove}
            </td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>
                    ${user.surname}
                </td>
                <td>
                    ${user.name}
                </td>
                <td>
                    ${user.login}
                </td>
                <td class="closeCol">
                    <a href="/removeUser/${user.id}">
                        <img src="http://localhost:8080/web-resources/img/delete16.png"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>