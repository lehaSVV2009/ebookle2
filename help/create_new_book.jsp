<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper create-form">

    <spring:message code="create_book_label" var="createBookLabel"/>
    <spring:message code="title_label" var="titleLabel"/>
    <spring:message code="description_label" var="descriptionLabel"/>
    <spring:message code="category_label" var="categoryLabel"/>
    <spring:message code="add_tag_label" var="addTagLabel"/>
    <spring:message code="create_book_button_text" var="createBookButtonText"/>
    <spring:message code="cancel" var="cancel"/>

    <c:if test="${not empty error}">
        <div class="error">
            ${error}
        </div>
        <br/>
    </c:if>
    <div class="well">
        <h2>${createBookLabel}</h2>
        <form action="/${userLogin}/createNewBook" method="post" id="bookCreationForm" name="bookCreationForm" class="form-horizontal" role="form">
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="inputTitle1" placeholder="${titleLabel}" name="title">
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="inputDescription" placeholder="${descriptionLabel}" name="description">
                </div>
            </div>
            <div class="form-group">
                <c:if test="${not empty categories}">
                    <div class="col-lg-10">
                        <select name="category" class="form-control">
                            <c:forEach items="${categories}" var="categoryItem">
                                <option value="${categoryItem.id}">${categoryItem.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
            </div>
            <div class="form-group">
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="inputTag" placeholder="${addTagLabel}" name="bookTag">
                </div>
            </div>
                <div class="form-group">
                    <div class="col-lg-10">
                        <button type="submit" class="btn btn-default">${createBookButtonText}</button>
                    </div>
                </div>
        </form>
    </div>
</div>






