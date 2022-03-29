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
import java.util.List;

@WebServlet({"/delete.html"})
public class deleteServlet extends HttpServlet {
    public deleteServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Use the model to do the search and put the results into the request object sent to the
        // Java Server Page used to display the results.
        Model model = ModelFactory.getModel();
        String curr = (String) request.getParameter("noteName");
        model.delete(curr);

        request.setAttribute("currentNote", curr);
        File f = new File("./data/"+curr+".txt");
        ArrayList<String> contents = model.readFile(f);
        request.setAttribute("body", contents);

        // Invoke the JSP page.
        List<String> notes = model.getNoteNames();
        request.setAttribute("noteNames", notes);
        ServletContext context = this.getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/notesList.jsp");
        dispatch.forward(request, response);
    }
}
