<%@ page import="java.util.ArrayList" %>
<html>
    <head>
        <jsp:include page="/meta.jsp"/>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>
        <div class = "main">
            <%
                String noteName = (String) request.getAttribute("currentNote");
                if (noteName == null){
                    noteName = (String) request.getQueryString().substring(12);
                }
                ArrayList<String> contents = (ArrayList<String>) request.getAttribute("body");
            %>
            <h1><%=noteName%></h1>
            <div class="content">
                <%
                    if (contents != null) {
                %>
                    <form action="/save.html" method="POST">
                        <input type="hidden" name="noteName" value="<%=noteName%>"/>
                        <textarea name="mainContent"><%=String.join(" ", contents)%></textarea>
                        <input type ="submit" value="Save">
                    </form>
                <%
                    } else {
                %>
                    <form action="/save.html" method="POST">
                        <textarea name="mainContent" style="resize:none;"></textarea>
                        <input type = "submit" value="Save">
                    </form>
                <%
                   }
                %>
            </div>
        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>