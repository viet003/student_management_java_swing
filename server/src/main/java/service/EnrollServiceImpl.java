package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Enroll;
import controller.DatabaseConnection;
import model.Student;
import remote.EnrollService;

public class EnrollServiceImpl extends UnicastRemoteObject implements EnrollService {

    public EnrollServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<Enroll> getAllEnrolls(int classId) throws RemoteException {
        List<Enroll> enrolls = new ArrayList<>();
        String sql = "SELECT e.student_msv, e.class_id, s.student_name, s.email, c.class_name " +
                "FROM tbl_enroll e " +
                "JOIN tbl_student s ON e.student_msv = s.msv " +
                "JOIN tbl_class c ON e.class_id = c.id " +
                "WHERE e.class_id = ?"; // Lọc theo classId

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, classId); // Gán giá trị classId vào câu lệnh SQL
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    enrolls.add(new Enroll(
                            rs.getString("student_msv"),
                            rs.getInt("class_id"),
                            rs.getString("student_name"),
                            rs.getString("email"),
                            rs.getString("class_name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving enrolls for class ID: " + classId, e);
        }
        return enrolls;
    }


    @Override
    public boolean addEnroll(Enroll enroll) throws RemoteException {
        String sql = "INSERT INTO tbl_enroll (student_msv, class_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, enroll.getStudentMsv());
            stmt.setInt(2, enroll.getClassId());
            return stmt.executeUpdate() > 0; // Trả về true nếu có dòng được thêm
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding enroll", e);
        }
    }

    @Override
    public boolean deleteEnroll(String studentMsv, int classId) throws RemoteException {
        String sql = "DELETE FROM tbl_enroll WHERE student_msv = ? AND class_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentMsv);
            stmt.setInt(2, classId);
            return stmt.executeUpdate() > 0; // Trả về true nếu có dòng bị xóa
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting enroll", e);
        }
    }

    @Override
    public List<Student> getUnenrolledStudentsByClassId(int classId) throws RemoteException {
        String sql = "SELECT s.msv, s.student_name, s.phone, s.email, s.address, s.dob, s.gender, s.status " +
                "FROM tbl_student s " +
                "WHERE s.msv NOT IN ( " +
                "    SELECT e.student_msv " +
                "    FROM tbl_enroll e " +
                "    WHERE e.class_id = ? " +
                ")";
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, classId); // Thiết lập giá trị cho tham số classId
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(
                            rs.getString("msv"),
                            rs.getString("student_name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getDate("dob"),
                            rs.getString("gender"),
                            rs.getString("status")
                    );
                    students.add(student);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error fetching unenrolled students", e);
        }
        return students;
    }
}
