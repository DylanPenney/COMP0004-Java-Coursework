package ucl.ac.uk.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucl.ac.uk.Model.Model;
import ucl.ac.uk.Model.ModelFactory;

@WebServlet({"/notesList.html"})
public class viewNotesListServlet extends HttpServlet {
    public viewNotesListServlet() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Model model = ModelFactory.getModel();
        List<String> patientNames = model.getNoteNames();
        request.setAttribute("patientNames", patientNames);
        ServletContext context = this.getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/notesList.jsp");
        dispatch.forward(request, response);
    }
}