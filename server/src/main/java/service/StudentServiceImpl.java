package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Student;
import controller.DatabaseConnection;
import remote.StudentService;

public class StudentServiceImpl extends UnicastRemoteObject implements StudentService {

    public StudentServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean addStudent(Student student) throws RemoteException {
        String sql = "INSERT INTO tbl_student (msv, student_name, phone, email, address, dob, gender, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getMsv());
            stmt.setString(2, student.getStudentName());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getAddress());
            stmt.setDate(6, new java.sql.Date(student.getDob().getTime()));
            stmt.setString(7, student.getGender());
            stmt.setString(8, student.getStatus());
            return stmt.executeUpdate() > 0; // Trả về true nếu có ít nhất một bản ghi được thêm
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding student", e);
        }
    }

    @Override
    public List<Student> getAllStudents() throws RemoteException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM tbl_student";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getString("msv"),
                        rs.getString("student_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getDate("dob"),
                        rs.getString("gender"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving students", e);
        }
        return students;
    }

    @Override
    public boolean deleteStudent(String msv) throws RemoteException {
        String sql = "DELETE FROM tbl_student WHERE msv = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, msv);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting student", e);
        }
    }

    @Override
    public boolean updateStudent(Student student) throws RemoteException {
        String sql = "UPDATE tbl_student SET student_name = ?, phone = ?, email = ?, address = ?, dob = ?, gender = ?, status = ? WHERE msv = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getStudentName());
            stmt.setString(2, student.getPhone());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getAddress());
            stmt.setDate(5, new java.sql.Date(student.getDob().getTime()));
            stmt.setString(6, student.getGender());
            stmt.setString(7, student.getStatus());
            stmt.setString(8, student.getMsv());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating student", e);
        }
    }
}
