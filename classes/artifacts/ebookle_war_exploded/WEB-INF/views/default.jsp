<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>

<head>
    <link rel="stylesheet" type="text/css" media="screen"
          href="http://localhost:8080/web-resources/bootstrap/css/bootstrap.css"/>
    <script src="/web-resources/js/jquery-2.0.3.js"></script>
    <script src="/web-resources/js/sript.js"></script>
    <script src="http://neotech.3dn.ru/Tegs/swfobject.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8080/web-resources/css/default.css"/>

    <title>
        Message
    </title>
</head>
<body>
<tiles:insertAttribute name="header" ignore="true"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer" ignore="true"/>
</body>
</html>