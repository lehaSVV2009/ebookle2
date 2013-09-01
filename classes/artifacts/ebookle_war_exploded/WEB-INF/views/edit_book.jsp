<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper"   >

    <spring:message code="label.tags" var="labelTags"/>
    <spring:message code="label.add_tag" var="labelAddTag"/>
    <spring:message code="label.add_like" var="labelLike"/>
    <spring:message code="label.create_new_chapter" var="labelCreateChapter"/>
    <spring:message code="label.edit_chapter" var="labelEdit"/>
    <spring:message code="label.remove_chapter" var="labelRemove"/>
    <spring:message code="label.show_chapter" var="labelShow"/>
    <spring:message code="label.save_chapter" var="labelSave"/>
    <spring:message code="label.add_dislike" var="labelDislike"/>
    <spring:message code="label.font_size_plus" var="labelFontSizePlus"/>
    <spring:message code="label.font_size_minus" var="labelFontSizeMinus"/>
    <spring:message code="label.width_plus" var="labelWidthPlus"/>
    <spring:message code="label.width_minus" var="labelWidthMinus"/>

    <table>
        <tr>
            <h2>${book.title} </h2>
        </tr>
        <tr>
            <h2>${currentChapter.title}</h2>
        </tr>
    </table>
    <c:choose>
        <c:when test="${person eq 'ownUser'}">
            <c:choose>
                <c:when test="${userAction eq 'edit'}">

                    <div class="well leftSidePanel">
                        <a href="/${userLogin}/editBook/${bookTitle}/createNewChapter">${labelCreateChapter}</a>
                        <table class="table table-bordered leftSideTable">
                            <c:forEach items="${book.chapters}" var="chapter">
                                <tr>
                                    <td>
                                        <form action="/${userLogin}/editBook/${bookTitle}/${chapter.chapterNumber}"
                                              method="get">
                                            <input type="submit" value="${chapter.title}"/>
                                        </form>
                                    </td>
                                    <td>
                                        <a href="/${userLogin}/editBook/${bookTitle}/${chapter.chapterNumber}/deleteChapter">
                                            <img src="http://localhost:8080/web-resources/img/delete16.png"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br/>

                        <form action="/${userLogin}/editBook/${bookTitle}/${currentChapter.chapterNumber}/addTag"
                              method="post">
                            <input type="text" name="bookTag" placeholder="${labelAddTag}"/>
                        </form>
                        <br/>
                        ${labelTags}:
                        <br/>
                        <c:if test="${not empty tags}">
                            <c:forEach items="${tags}" var="tag">
                                ${tag.bookTag}
                                <br/>
                            </c:forEach>
                        </c:if>
                    </div>

                    <div class="well content">
                        <div class="btn-group btn-group-justified">
                            <a href="/${userLogin}/editBook/${bookTitle}/${currentChapter.chapterNumber}" class="btn btn-default">
                                ${labelEdit}</a>
                            <a href="/${userLogin}/editBook/${bookTitle}/${currentChapter.chapterNumber}/show" class="btn btn-default">
                                ${labelShow}</a>
                        </div>
                        <form action="/${userLogin}/editBook/${bookTitle}/${currentChapter.chapterNumber}/save" method="post">
                            <textarea name="text" class="reader">${currentChapter.text}</textarea>
                            <br/>
                            <button type="submit" class="btn btn-default btn-block">${labelSave}</button>
                        </form>
                    </div>

                </c:when>

                <c:otherwise>
                    <div class="well leftSidePanel">
                        <table class="table table-bordered leftSideTable">
                            <c:forEach items="${book.chapters}" var="chapter">
                                <tr>
                                    <td>
                                        <form action="/${userLogin}/editBook/${bookTitle}/${chapter.chapterNumber}/show"
                                              method="get">
                                            <input type="submit" value="${chapter.title}"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <br/>
                        ${labelTags}:
                        <br/>
                        <c:if test="${not empty tags}">
                            <c:forEach items="${tags}" var="tag">
                                ${tag.bookTag}
                                <br/>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="well content">

                        <div class="btn-group btn-group-justified">
                            <a href="#" class="btn btn-default" onClick="changeSize(10)">${labelFontSizePlus}</a>
                            <a href="#" class="btn btn-default"  onClick="changeSize(-10)">${labelFontSizeMinus}</a>
                            <a href="#" class="btn btn-default"  onClick="changeWidth(25)">${labelWidthPlus}</a>
                            <a href="#" class="btn btn-default"  onClick="changeWidth(-25)">${labelWidthMinus}</a>
                            <a href="/${userLogin}/editBook/${bookTitle}/${currentChapter.chapterNumber}" class="btn btn-default">
                                ${labelEdit}</a>
                            <a href="/${userLogin}/editBook/${bookTitle}/${currentChapter.chapterNumber}/show" class="btn btn-default">
                                ${labelShow}</a>
                        </div>
                        <div class="reader">${htmlChapterText}</div>

                        <br/>
                        <img src="http://localhost:8080/web-resources/img/rating.png"/>
                        ${book.rating}
                    </div>
                </c:otherwise>
            </c:choose>

        </c:when>

        <c:otherwise>

            <div class="well leftSidePanel">
                <table class="table table-bordered leftSideTable">
                    <c:forEach items="${book.chapters}" var="chapter">
                        <tr>
                            <td>
                                <form action="/${userLogin}/editBook/${bookTitle}/${chapter.chapterNumber}/show"
                                      method="get">
                                    <input type="submit" value="${chapter.title}"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <br/>
                ${labelTags}:
                <br/>
                <c:if test="${not empty tags}">
                    <c:forEach items="${tags}" var="tag">
                        ${tag.bookTag}
                        <br/>
                    </c:forEach>
                </c:if>
            </div>
            <div class="well content">
                <div class="btn-group btn-group-justified">
                    <a href="#" class="btn btn-default" onClick="changeSize(10)">${labelFontSizePlus}</a>
                    <a href="#" class="btn btn-default"  onClick="changeSize(-10)">${labelFontSizeMinus}</a>
                    <a href="#" class="btn btn-default"  onClick="changeWidth(25)">${labelWidthPlus}</a>
                    <a href="#" class="btn btn-default"  onClick="changeWidth(-25)">${labelWidthMinus}</a>
                </div>

                <div class="reader">${htmlChapterText}</div>

                <br/>

                <img src="http://localhost:8080/web-resources/img/rating.png"/>
                ${book.rating}
                <c:if test="${person eq 'notOwnUser'}">
                    <spring:url value="/${userLogin}/editBook/${bookTitle}/${currentChapter.chapterNumber}/show/1"
                                var="likeUrl"/>
                    <spring:url value="/${userLogin}/editBook/${bookTitle}/${currentChapter.chapterNumber}/show/-1"
                                var="dislikeUrl"/>

                    <c:if test="${mark ne 'showJustDislike'}">
                        <a href="${likeUrl}">
                            <img src="http://localhost:8080/web-resources/img/like.png"/>
                        </a>
                    </c:if>
                    <c:if test="${mark ne 'showJustLike'}">
                        <a href="${dislikeUrl}">
                            <img src="http://localhost:8080/web-resources/img/dislike.png"/>
                        </a>
                    </c:if>

                    <div style="float : right">
                        <a href="/${userLogin}/editBook/${bookTitle}/${chapterNumber}/savePdf">
                            <img src="http://localhost:8080/web-resources/img/pdf.png"/>
                        </a>
                    </div>
                </c:if>

            </div>
        </c:otherwise>

    </c:choose>

</div>