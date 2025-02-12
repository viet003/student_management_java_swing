package service;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Subject;
import controller.DatabaseConnection;
import remote.SubjectService;

public class SubjectServiceImpl extends UnicastRemoteObject implements SubjectService {

    public SubjectServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void addSubject(Subject subject) throws RemoteException {
        String sql = "INSERT INTO tbl_subject (subject_name, accredit, price, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subject.getSubjectName());
            stmt.setInt(2, subject.getAccredit());
            stmt.setDouble(3, subject.getPrice());
            stmt.setString(4, subject.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding subject", e);
        }
    }

    @Override
    public List<Subject> getAllSubjects() throws RemoteException {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM tbl_subject";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                subjects.add(new Subject(rs.getInt("id"), rs.getString("subject_name"), rs.getInt("accredit"), rs.getDouble("price"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving subjects", e);
        }
        return subjects;
    }

    @Override
    public void deleteSubject(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_subject WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting subject", e);
        }
    }

    @Override
    public void updateSubject(Subject subject) throws RemoteException {
        String sql = "UPDATE tbl_subject SET subject_name = ?, accredit = ?, price = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subject.getSubjectName());
            stmt.setInt(2, subject.getAccredit());
            stmt.setDouble(3, subject.getPrice());
            stmt.setString(4, subject.getStatus());
            stmt.setInt(5, subject.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating subject", e);
        }
    }
}