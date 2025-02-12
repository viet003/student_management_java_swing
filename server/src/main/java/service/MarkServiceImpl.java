package service;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mark;
import controller.DatabaseConnection;
import remote.MarkService;

public class MarkServiceImpl extends UnicastRemoteObject implements MarkService {

    public MarkServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void addMark(Mark mark) throws RemoteException {
        String sql = "INSERT INTO tbl_mark (student_id, sub_id, mark, status, note, ex_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, mark.getStudentId());
            stmt.setInt(2, mark.getSubjectId());
            stmt.setDouble(3, mark.getMark());
            stmt.setString(4, mark.getStatus());
            stmt.setString(5, mark.getNote());
            stmt.setDate(6, mark.getExamDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding mark", e);
        }
    }

    @Override
    public List<Mark> getAllMarks() throws RemoteException {
        List<Mark> marks = new ArrayList<>();
        String sql = "SELECT * FROM tbl_mark";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                marks.add(new Mark(rs.getInt("student_id"), rs.getInt("sub_id"), rs.getDouble("mark"), rs.getString("status"), rs.getString("note"), rs.getDate("ex_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving marks", e);
        }
        return marks;
    }

    @Override
    public void deleteMark(int studentId, int subId) throws RemoteException {
        String sql = "DELETE FROM tbl_mark WHERE student_id = ? AND sub_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, subId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting mark", e);
        }
    }

    @Override
    public void updateMark(Mark mark) throws RemoteException {
        String sql = "UPDATE tbl_mark SET mark = ?, status = ?, note = ?, ex_date = ? WHERE student_id = ? AND sub_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, mark.getMark());
            stmt.setString(2, mark.getStatus());
            stmt.setString(3, mark.getNote());
            stmt.setDate(4, mark.getExamDate());
            stmt.setInt(5, mark.getStudentId());
            stmt.setInt(6, mark.getSubjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating mark", e);
        }
    }
}
