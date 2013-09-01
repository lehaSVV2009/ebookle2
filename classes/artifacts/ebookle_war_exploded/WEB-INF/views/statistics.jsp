<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper">

    <spring:message code="label.book_category_statistics" var="labelStatistics"/>

    <script src="/web-resources/raphael/raphael.js"></script>
    <script src="/web-resources/raphael/g.raphael.js"></script>
    <script src="/web-resources/raphael/g.bar.js"></script>

    <script type="text/javascript">

        function start() {
            var data = [];
            var labels = [];

            <c:if test="${not empty bookCategoryStatistics}">
                <c:forEach items="${bookCategoryStatistics}" var="entry">
                    labels.push("${entry.key.name}");
                    data.push(${entry.value});
                </c:forEach>
            </c:if>

            drawChart(labels, data);
        }

        function drawChart(labels, data) {

            var r = Raphael("holder", 600, 300);

            var chart = r.g.barchart(10, 10, 580, 280, [data], {stacked: true, type: "soft"});

            chart.hover(function () {
                var postfix = " books";
                if (this.bar.value == 1) {
                    postfix = " book";
                }
                this.flag = r.g.popup(this.bar.x, this.bar.y, (this.bar.value || "0") + postfix).insertBefore(this);
            }, function () {
                this.flag.animate({opacity: 0}, 300, function () {
                    this.remove();
                });
            });

            r.g.txtattr = {font: "20px Fontin-Sans, Arial, sans-serif", fill: "#0F0", "font-weight": "bold"};

            chart.label(labels);

            for (var i = 0; i < chart.bars[0].length; i++) {
                var bar = chart.bars[0][i];
                if (bar.value >= 10) {
                    bar.attr("fill", "#bf2f2f");
                    bar.attr("stroke", "#bf2f2f");
                }
            }
        }

        window.onload = function () {
            start();
        }
    </script>

    <h2>${labelStatistics}</h2>

    <div id="holder"></div>

</div>