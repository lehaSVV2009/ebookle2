<%@ page import="com.ebookle.util.Encoder" %>
<%@ page import="com.ebookle.entity.User" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.ebookle.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="wrapper">

<spring:message code="label.hello" var="labelHello"/>
<spring:message code="label.create_book" var="labelCreateBook"/>
<spring:message code="label.logout" var="labelLogout"/>
<spring:message code="label.admin" var="labelAdmin"/>
<spring:message code="label.login" var="labelLogin"/>
<spring:message code="label.registration" var="labelRegistration"/>
<spring:message code="label.most_popular" var="labelMostPopular"/>
<spring:message code="label.recent" var="labelRecent"/>
<spring:message code="label.statistics" var="labelStatistics"/>
<spring:message code="label.random" var="labelRandom"/>
<spring:message code="label.title" var="labelTitle"/>
<spring:message code="label.description" var="labelDescription"/>
<spring:message code="label.author" var="labelAuthor"/>
<spring:message code="label.category" var="labelCategory"/>
<spring:message code="label.rating" var="labelRating"/>


<c:if test="${not empty flashMessage}">
    <div class="alert-error">
            ${flashMessage}
    </div>
    <br/>
</c:if>

<c:if test="${not empty goodMessage}">
    <div class="alert-success">
            ${goodMessage}
    </div>
    <br/>
</c:if>

<c:url value='/j_spring_security_logout' var="securityLogout"/>

<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
    <div class="leftSidePanel">
        <div class="well">

                ${labelHello}, ${user.name}
            <br/>
            Your books
            <br/>

            <table class="table table-bordered leftSideTable">
                <%User user = (User) request.getAttribute("user");%>
                <%Iterator iterator = user.getBooks().iterator();%>
                <c:forEach items="${user.books}" var="userBook">
                    <%Book curUserBook = (Book) iterator.next();%>
                    <tr>
                        <td class="tableCol1">
                            <a href="/${user.login}/editBook/<%=Encoder.encode(curUserBook.getTitle())%>/1">
                                <c:choose>
                                    <c:when test="${fn:length(userBook.title) gt 20}">
                                        ${fn:substring(userBook.title, 0, 10)}..
                                    </c:when>
                                    <c:otherwise>
                                        ${userBook.title}
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </td>
                        <td>
                            <a href="/${user.login}/remove/${userBook.id}">
                                <img src="http://localhost:8080/web-resources/img/delete16.png"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <a href="/${user.login}/bookCreation">${labelCreateBook}</a>
            <br/>
            <a href="${securityLogout}">${labelLogout}</a>
            <c:if test="${person eq 'admin'}">
                <div style="float: right">
                    <br/>
                    <a href="/admin">${labelAdmin}</a>
                </div>
            </c:if>
        </div>
        <div class="well">
            <div id="yoblako">
                <c:if test="${not empty tags}">
                    <c:forEach items="${tags}" var="tag">
                        <a style="font-size:10pt;" href="/searcher/${tag.id}"
                           class="eTag eTagGr8">${tag.bookTag}</a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</sec:authorize>

<c:url value='j_spring_security_check' var="securityCheck"/>
<c:if test="${person eq 'guest'}">
    <div class="leftSidePanel">
        <div class="well">
            <form name='login_form' action="${securityCheck}"
                  method='POST'>
                <input type="text" class="form-control" id="inputEmail" placeholder="Email" name='j_username'
                       value=''/>
                <input type="password" class="form-control" id="inputPassword" placeholder="Password"
                       name='j_password'/>
                <button type="submit" class="btn btn-primary">${labelLogin}</button>
            </form>
            <a href="/registration">${labelRegistration}</a>

        </div>
        <div class="well">
            <div id="yoblako">
                <c:if test="${not empty tags}">
                    <c:forEach items="${tags}" var="tag">
                        <a style="font-size:10pt;" href="/searcher/${tag.id}"
                           class="eTag eTagGr8">${tag.bookTag}</a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</c:if>


<div class="well content">
    <form class="bs-example form-horizontal">
        <fieldset>
            <div class="btn-group btn-group-justified">
                <a href="/home/mostPopular" class="btn btn-default">${labelMostPopular}</a>
                <a href="/home/recent" class="btn btn-default">${labelRecent}</a>
                <a href="/home/random" class="btn btn-default">${labelRandom}</a>
            </div>

            <div class="btn-group btn-group-justified">
                <c:if test="${not empty categories}">
                    <c:forEach items="${categories}" var="category">
                        <a href="/home/${category.name}" class="btn btn-default">
                                ${category.name}
                        </a>
                    </c:forEach>
                </c:if>
            </div>

            <c:if test="${not empty books}">
                <table class="table table-bordered table1">
                    <thead>
                    <tr>
                        <td>
                                ${labelTitle}
                        </td>
                        <td>
                                ${labelDescription}
                        </td>
                        <td>
                                ${labelAuthor}
                        </td>
                        <td>
                                ${labelCategory}
                        </td>
                        <td>
                                ${labelRating}
                        </td>
                    </tr>
                    </thead>
                    <tbody>
                    <%Iterator bookIterator = ((List<Book>) request.getAttribute("books")).iterator();%>
                    <c:forEach items="${books}" var="book">
                        <%Book curBook = (Book) bookIterator.next();%>
                        <tr>
                            <td>
                                <a href="/${book.user.login}/editBook/<%=Encoder.encode(curBook.getTitle())%>/1/show">
                                    <c:choose>
                                        <c:when test="${fn:length(book.title) gt 20}">
                                            ${fn:substring(book.title, 0, 20)}...
                                        </c:when>
                                        <c:otherwise>
                                            ${book.title}
                                        </c:otherwise>
                                    </c:choose>
                                </a>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:length(book.description) gt 30}">
                                        ${fn:substring(book.description, 0, 30)}...
                                    </c:when>
                                    <c:otherwise>
                                        ${book.description}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                    ${book.user.name} ${book.user.surname}
                            </td>
                            <td>
                                    ${book.category.name}
                            </td>
                            <td>
                                    ${book.rating}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <script src="/web-resources/js/cloud.js"></script>

            <a href="/statistics/authorCounts">${labelStatistics}</a>

        </fieldset>
    </form>
</div>
</div>