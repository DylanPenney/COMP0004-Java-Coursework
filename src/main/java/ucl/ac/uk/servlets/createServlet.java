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

@WebServlet({"/createnote.html"})
public class createServlet extends HttpServlet {
    public createServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Use the model to do the search and put the results into the request object sent to the
        // Java Server Page used to display the results.
        Model model = ModelFactory.getModel();
        String createResult = model.create(request.getParameter("noteName"));
        request.setAttribute("currentNote", createResult);
        File f = new File("./data/"+createResult+".txt");
        ArrayList<String> contents = model.readFile(f);
        request.setAttribute("body", contents);

        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewNote.jsp");
        dispatch.forward(request, response);
    }
}
