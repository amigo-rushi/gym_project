package dao;

import model.Participant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDaoImpl implements ParticipantDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/funfit_gym";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    // Method to add a new Participant
    @Override
    public void addParticipant(Participant participant) {
        String sql = "INSERT INTO Participant (name, age, batch_id) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, participant.getName());
            stmt.setInt(2, participant.getAge());
            stmt.setInt(3, participant.getBatchId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a Participant by ID
    @Override
    public Participant getParticipant(int id) {
        String sql = "SELECT * FROM Participant WHERE participant_id = ?";
        Participant participant = null;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                participant = new Participant();
                participant.setId(rs.getInt("participant_id"));
                participant.setName(rs.getString("name"));
                participant.setAge(rs.getInt("age"));
                participant.setBatchId(rs.getInt("batch_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participant;
    }

    // Method to update a Participant
    @Override
    public void updateParticipant(Participant participant) {
        String sql = "UPDATE Participant SET name = ?, age = ?, batch_id = ? WHERE participant_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, participant.getName());
            stmt.setInt(2, participant.getAge());
            stmt.setInt(3, participant.getBatchId());
            stmt.setInt(4, participant.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a Participant
    @Override
    public void deleteParticipant(int id) {
        String sql = "DELETE FROM Participant WHERE participant_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all Participants
    @Override
    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        String sql = "SELECT * FROM Participant";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Participant participant = new Participant();
                participant.setId(rs.getInt("participant_id"));
                participant.setName(rs.getString("name"));
                participant.setAge(rs.getInt("age"));
                participant.setBatchId(rs.getInt("batch_id"));
                participants.add(participant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }
}