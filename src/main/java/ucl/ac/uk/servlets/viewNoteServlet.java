package ucl.ac.uk.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucl.ac.uk.Model.Model;
import ucl.ac.uk.Model.ModelFactory;

@WebServlet("/viewNote.html")
public class viewNoteServlet extends HttpServlet{
    public viewNoteServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        String note = request.getQueryString();
        note = note.substring(12);
        note = note.replaceAll("[^a-zA-Z0-9]","").replace(" ", "");
        File f = new File("./data/"+note+".txt");
        ArrayList<String> contents = model.readFile(f);
        request.setAttribute("body", contents);

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/viewNote.jsp");
        dispatch.forward(request, response);
    }

}
