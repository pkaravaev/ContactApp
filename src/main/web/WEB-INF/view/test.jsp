<%--
  Created by IntelliJ IDEA.
  User: karav
  Date: 13.05.2018
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html>
<head>
    <title>Ajax</title>
    <s:url value="/static/js/jquery-3.3.1.min.js" var="url_jqlib"/>
    <script src="${url_jqlib}"></script>

    <script>
        $(document).ready(function () {
           // alert('jQuery is ready and integrated');
            $("#id_get_time").click(function () {

                $.ajax({
                    url : '/test/get_time',
                    success : function (data) {

                        $("#id_time").html(data);
                    }

                });
            });
        })

    </script>
</head>
<body>
<h1>AJAX Test Page</h1>

<button id="id_get_time">Get Server Time</button> <br/>
<p id="id_time"></p>

</body>
</html>
