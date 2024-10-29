package servlet;
import dao.ParticipantDAO;
import dao.ParticipantDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Participant;


import java.io.IOException;
import java.util.List;

@WebServlet("/participants")
public class ParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ParticipantDAO participantDAO = new ParticipantDaoImpl();

    // Handle the GET request to retrieve a list of participants or a single participant
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            // If an ID is specified, retrieve a single participant by ID
            int id = Integer.parseInt(idParam);
            Participant participant = participantDAO.getParticipant(id);
            request.setAttribute("participant", participant);
            request.getRequestDispatcher("viewParticipant.jsp").forward(request, response);
        } else {
            // If no ID is specified, retrieve all participants
            List<Participant> participants = participantDAO.getAllParticipants();
            request.setAttribute("participants", participants);
            request.getRequestDispatcher("listParticipants.jsp").forward(request, response);
        }
    }

    // Handle the POST request to create a new participant
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        Participant participant = new Participant();
        participant.setName(name);
        participant.setAge(age);
        participant.setBatchId(batchId);

        participantDAO.addParticipant(participant);
        response.sendRedirect("participants");
    }

    // Handle the PUT request to update an existing participant
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int batchId = Integer.parseInt(request.getParameter("batchId"));

        Participant participant = new Participant();
        participant.setId(id);
        participant.setName(name);
        participant.setAge(age);
        participant.setBatchId(batchId);

        participantDAO.updateParticipant(participant);
        response.getWriter().write("Participant updated successfully.");
    }

    // Handle the DELETE request to delete a participant
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        participantDAO.deleteParticipant(id);
        response.getWriter().write("Participant deleted successfully.");
    }
}
