<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h1>Search Result</h1>
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
        <li><a href="/note"><%=note%></a></li>
        <% }
        } else
        {%>
        <p>Nothing found</p>
        <%}%>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>