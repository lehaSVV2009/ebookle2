<%@ page import="com.ebookle.entity.Book" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper">

    <div class="well">
        Search result
        <table class="table table-bordered table-striped table1">
            <thead>
            <tr>
                <td>
                    Title
                </td>
                <td>
                    Description
                </td>
                <td>
                    Author
                </td>
                <td>
                    Category
                </td>
                <td>
                    Rating
                </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>
                        ${book.title}
                    </td>
                    <td>
                        ${book.description}
                    </td>

                    <td>
                        ${book.user.name}
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
    </div>
</div>