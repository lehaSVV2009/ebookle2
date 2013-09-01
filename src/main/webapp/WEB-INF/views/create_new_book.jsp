<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper">

    <link rel="stylesheet" type="text/css"
          href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"/>

    <script type="text/javascript"
            src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
    <script type="text/javascript"
            src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>

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


    <form action="/${userLogin}/createNewBook" method="post" id="bookCreationForm" name="bookCreationForm"
          class="bs-example form-horizontal well">
        <fieldset>

            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">
                    <h2>${createBookLabel}</h2>
                </label>
            </div>
            <br/>
            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">${titleLabel}</label>

                <div class="col-lg-10">
                    <input type="text" name="title"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">${descriptionLabel}</label>

                <div class="col-lg-10">
                    <input type="text" name="description"/>
                </div>
            </div>
            <c:if test="${not empty categories}">
                <div class="form-group">
                    <label for="inputEmail" class="col-lg-2 control-label">${categoryLabel}</label>

                    <div class="col-lg-10">
                        <select name="category">
                            <c:forEach items="${categories}" var="categoryItem">
                                <option value="${categoryItem.id}">${categoryItem.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <label for="inputEmail" class="col-lg-2 control-label">${addTagLabel}</label>

                <div class="col-lg-10">
                    <input type="text" name="bookTag" id="bookTag"/>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-default btn-lg btn-block">${createBookButtonText}</button>
            </div>
        </fieldset>
    </form>

    <script type="text/javascript">
        $(document).ready(function() {

            $( "#bookTag" ).autocomplete({
                source: '${pageContext.request.contextPath}/autocomplete'
            });

        });
    </script>

</div>