package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public boolean addTeacher(Teacher teacher) throws RemoteException {
        String sql = "INSERT INTO tbl_teacher (name, phone, address, dob, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getPhone());
            stmt.setString(3, teacher.getAddress());
            stmt.setDate(4, new java.sql.Date(teacher.getDob().getTime()));
            stmt.setString(5, teacher.getStatus());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Teacher> getAllTeachers() throws RemoteException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM tbl_teacher";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                java.sql.Date sqlDate = rs.getDate("dob");
                Date dob = (sqlDate != null) ? new Date(sqlDate.getTime()) : null;
                teachers.add(new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        dob,
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
    public boolean deleteTeacher(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_teacher WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateTeacher(Teacher teacher) throws RemoteException {
        String sql = "UPDATE tbl_teacher SET name = ?, phone = ?, address = ?, dob = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getPhone());
            stmt.setString(3, teacher.getAddress());
            stmt.setDate(4, new java.sql.Date(teacher.getDob().getTime()));
            stmt.setString(5, teacher.getStatus());
            stmt.setInt(6, teacher.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Teacher> getTeachersWithoutAccount() throws RemoteException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT id, name, phone, address, dob, status " +
                "FROM tbl_teacher " +
                "WHERE id NOT IN (SELECT teacher_id FROM tbl_account WHERE teacher_id IS NOT NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                String status = rs.getString("status");

                // Kiểm tra NULL tránh lỗi
                if (name == null) name = "";
                if (phone == null) phone = "";
                if (address == null) address = "";
                if (status == null) status = "Không xác định";

                Teacher teacher = new Teacher(id, name, phone, address, dob, status);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy danh sách giảng viên chưa có tài khoản", e);
        }
        return teachers;
    }


}
