package ucl.ac.uk.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/test.html"})
public class searchServlet extends HttpServlet {
    public searchServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            new Date();
            new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>COMP0004 Coursework</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h6>Page Content</h6>");
            out.println("</body>");
            out.println("</html");
        } catch (Throwable var7) {
            if (out != null) {
                try {
                    out.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (out != null) {
            out.close();
        }

    }
}
