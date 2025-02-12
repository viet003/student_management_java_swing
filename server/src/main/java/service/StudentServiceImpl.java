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
    public void addStudent(Student student) throws RemoteException {
        String sql = "INSERT INTO tbl_student (msv, student_name, phone, email, address, dob, gender, img, status, class_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getMsv());
            stmt.setString(2, student.getStudentName());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getAddress());
            stmt.setDate(6, student.getDob());
            stmt.setString(7, student.getGender());
            stmt.setString(8, student.getImg());
            stmt.setString(9, student.getStatus());
            stmt.setInt(10, student.getClassId());
            stmt.executeUpdate();
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
                students.add(new Student(rs.getInt("id"), rs.getString("msv"), rs.getString("student_name"), rs.getString("phone"), rs.getString("email"), rs.getString("address"), rs.getDate("dob"), rs.getString("gender"), rs.getString("img"), rs.getString("status"), rs.getInt("class_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving students", e);
        }
        return students;
    }

    @Override
    public void deleteStudent(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_student WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting student", e);
        }
    }

    @Override
    public void updateStudent(Student student) throws RemoteException {
        String sql = "UPDATE tbl_student SET msv = ?, student_name = ?, phone = ?, email = ?, address = ?, dob = ?, gender = ?, img = ?, status = ?, class_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getMsv());
            stmt.setString(2, student.getStudentName());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getAddress());
            stmt.setDate(6, student.getDob());
            stmt.setString(7, student.getGender());
            stmt.setString(8, student.getImg());
            stmt.setString(9, student.getStatus());
            stmt.setInt(10, student.getClassId());
            stmt.setInt(11, student.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating student", e);
        }
    }
}
