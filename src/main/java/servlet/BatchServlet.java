package servlet;

import dao.batchDao;
import dao.batchDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Batch;

import java.io.IOException;
import java.util.List;

@WebServlet("/BatchServlet")
public class BatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private batchDao batchDAO = new batchDaoImpl();

    // Handle GET request to retrieve a list of batches or a single batch
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null) {
            // If an ID is specified, retrieve a single batch by ID
            int id = Integer.parseInt(idParam);
            Batch batch = batchDAO.getBatch(id);
            request.setAttribute("batch", batch);
            request.getRequestDispatcher("viewBatch.jsp").forward(request, response);
        } else {
            // If no ID is specified, retrieve all batches
            List<Batch> batches = batchDAO.getAllBatches();
            request.setAttribute("batches", batches);
            request.getRequestDispatcher("listBatches.jsp").forward(request, response);
        }
    }

    // Handle POST request to create a new batch
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String batchName = request.getParameter("batchName");
        String timing = request.getParameter("timing");
        
        Batch batch = new Batch();
        batch.setBatchName(batchName);
        batch.setTiming(timing);

        batchDAO.addBatch(batch);
       System.out.println("Check to redirect..");
       response.sendRedirect("batch");  // Redirect to the list of batches after adding
    }

    // Handle PUT request to update an existing batch
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String batchName = request.getParameter("batchName");
        String timing = request.getParameter("timing");

        Batch batch = new Batch();
        batch.setId(id);
        batch.setBatchName(batchName);
        batch.setTiming(timing);

        batchDAO.updateBatch(batch);
        response.getWriter().write("Batch updated successfully.");
    }

    // Handle DELETE request to delete a batch
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        batchDAO.deleteBatch(id);
        response.getWriter().write("Batch deleted successfully.");
    }
}
