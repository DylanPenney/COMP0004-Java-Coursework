<html>
<head>
    <jsp:include page="/meta.jsp"/>
</head>
<body>
    <jsp:include page="/header.jsp"/>
    <div class = "main">
        <h1></h1>
            <%
            List<String> notes = (List<String>) request.getAttribute("result");
            if (notes.size() !=0)
            {
            %>
            <ul>
                <%
                for (String note : notes)
                {
                %>
                <li><a href="./data/basic.txt"><%=note%></a></li>
                <% }
                } else
                {%>
                <p>Nothing found</p>
                <%}%>
            </ul>
    </div>