package servlets;

import businesslogic.NoteService;
import dataaccess.NotesDBException;
import domainmodel.Note;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        displayNotesJSP(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String contents = request.getParameter("contents");
            NoteService noteService = new NoteService();
            switch (request.getParameter("action")) {
                case "delete":
                    noteService.delete(Integer.parseInt(request.getParameter("selectednoteid")));
                    break;
                case "update":
                    noteService.update(Integer.parseInt(request.getParameter("selectednoteid")),contents);
                    break;
                case "add":
                    noteService.insert(contents);
                    break;
                case "edit":
                    request.setAttribute("selectedNote", noteService.get(Integer.parseInt(request.getParameter("selectednoteid"))));
                    break;
            }
        } catch (NotesDBException | NumberFormatException ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
        displayNotesJSP(request, response);
    }
    
    private void displayNotesJSP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        NoteService noteService = new NoteService();     
        try {
            request.setAttribute("notes", (ArrayList<Note>) noteService.getAll());
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/notes.jsp").forward(request, response);
    }
    
}
