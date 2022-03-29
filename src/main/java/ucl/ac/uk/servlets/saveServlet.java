package ucl.ac.uk.servlets;

import ucl.ac.uk.Model.Model;
import ucl.ac.uk.Model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/save.html"})
public class saveServlet extends HttpServlet {
    public saveServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Use the model to do the search and put the results into the request object sent to the
        // Java Server Page used to display the results.
        Model model = ModelFactory.getModel();
        String content = (String) request.getParameter("mainContent");
        String curr = (String) request.getParameter("noteName");
        File f = new File("./data/"+curr+".txt");
        model.write(f, content);

        request.setAttribute("currentNote", curr);
        File f2 = new File("./data/"+curr+".txt");
        ArrayList<String> contents = model.readFile(f2);
        request.setAttribute("body", contents);

        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewNote.jsp");
        dispatch.forward(request, response);
    }
}
