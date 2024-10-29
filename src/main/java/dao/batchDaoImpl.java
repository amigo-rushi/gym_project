package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import model.Batch;

public class batchDaoImpl implements batchDao{
	
	private static final String URL = "jdbc:mysql://localhost:3306/funfit_gym";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    // Method to add a new Batch
    @Override
    public void addBatch(Batch batch) {
        String sql = "INSERT INTO Batch (batch_name, timing) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, batch.getBatchName());
            stmt.setString(2, batch.getTiming());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a Batch by ID
    @Override
    public Batch getBatch(int id) {
        String sql = "SELECT * FROM Batch WHERE batch_id = ?";
        Batch batch = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                batch = new Batch();
                batch.setId(rs.getInt("batch_id"));
                batch.setBatchName(rs.getString("batch_name"));
                batch.setTiming(rs.getString("timing"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batch;
    }

    // Method to update a Batch
    @Override
    public void updateBatch(Batch batch) {
        String sql = "UPDATE Batch SET batch_name = ?, timing = ? WHERE batch_id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	
            stmt.setString(1, batch.getBatchName());
            stmt.setString(2, batch.getTiming());
            stmt.setInt(3, batch.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a Batch
    @Override
    public void deleteBatch(int id) {
        String sql = "DELETE FROM Batch WHERE batch_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Batch> getAllBatches() {
        List<Batch> batches = new ArrayList<>();
        String sql = "SELECT * FROM Batch";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement<?, ?> stmt = null;
            ResultSet rs = null;
            try {
                stmt = (Statement<?, ?>) conn.createStatement();
                rs = ((java.sql.Statement) stmt).executeQuery(sql);
                while (rs.next()) {
                    Batch batch = new Batch();
                    batch.setId(rs.getInt("batch_id"));
                    batch.setBatchName(rs.getString("batch_name"));
                    batch.setTiming(rs.getString("timing"));
                    batches.add(batch);
                }
            } finally {
                // Close ResultSet and Statement explicitly to avoid resource leaks
                if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (stmt != null) try { ((Connection) stmt).close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batches;
    }

}
