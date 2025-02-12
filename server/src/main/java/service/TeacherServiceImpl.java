package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import model.Teacher;
import controller.DatabaseConnection;
import remote.TeacherService;

public class TeacherServiceImpl extends UnicastRemoteObject implements TeacherService {

    public TeacherServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void addTeacher(Teacher teacher) throws RemoteException {
        String sql = "INSERT INTO tbl_teacher (name, phone, address, dob, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getPhone());
            stmt.setString(3, teacher.getAddress());
            stmt.setDate(4, new java.sql.Date(teacher.getDob().getTime()));
            stmt.setString(5, teacher.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding teacher", e);
        }
    }

    @Override
    public List<Teacher> getAllTeachers() throws RemoteException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM tbl_teacher";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                teachers.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getDate("dob"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving teachers", e);
        }
        return teachers;
    }

    @Override
    public void deleteTeacher(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_teacher WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting teacher", e);
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws RemoteException {
        String sql = "UPDATE tbl_teacher SET name = ?, phone = ?, address = ?, dob = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getPhone());
            stmt.setString(3, teacher.getAddress());
            stmt.setDate(4, new java.sql.Date(teacher.getDob().getTime()));
            stmt.setString(5, teacher.getStatus());
            stmt.setInt(6, teacher.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating teacher", e);
        }
    }

    @Override
    public List<Teacher> getTeachersWithoutAccount() throws RemoteException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT id, name " +
                "FROM tbl_teacher " +
                "WHERE id NOT IN (SELECT teacher_id FROM tbl_account WHERE teacher_id IS NOT NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString(""),
                        rs.getString(""),
                        rs.getDate(""),
                        rs.getString("")
                );
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving teachers without accounts", e);
        }
        return teachers;
    }


}
