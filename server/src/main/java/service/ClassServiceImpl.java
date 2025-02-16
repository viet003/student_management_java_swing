package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Class;
import controller.DatabaseConnection;
import remote.ClassService;

public class ClassServiceImpl extends UnicastRemoteObject implements ClassService {

    public ClassServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean addClass(Class classEntity) throws RemoteException {
        String sql = "INSERT INTO tbl_class (class_name, course_id, teacher_id, subject_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, classEntity.getClassName());
            stmt.setInt(2, classEntity.getCourseId());
            stmt.setInt(3, classEntity.getTeacherId());
            stmt.setInt(4, classEntity.getSubjectId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding class", e);
        }
    }

    @Override
    public List<Class> getAllClasses() throws RemoteException {
        List<Class> classes = new ArrayList<>();
        String sql = "SELECT c.id, c.class_name, c.course_id, c.teacher_id, c.subject_id, " +
                "co.course_name, t.name AS teacher_name, s.subject_name " +
                "FROM tbl_class c " +
                "JOIN tbl_course co ON c.course_id = co.id " +
                "JOIN tbl_teacher t ON c.teacher_id = t.id " +
                "JOIN tbl_subject s ON c.subject_id = s.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String className = rs.getString("class_name");
                int courseId = rs.getInt("course_id");
                int teacherId = rs.getInt("teacher_id");
                int subjectId = rs.getInt("subject_id");
                String courseName = rs.getString("course_name");
                String teacherName = rs.getString("teacher_name");
                String subjectName = rs.getString("subject_name");

                Class classEntity = new Class(id, className, courseId, teacherId, subjectId, courseName, teacherName, subjectName);
                classes.add(classEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving classes", e);
        }
        return classes;
    }

    @Override
    public boolean deleteClass(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_class WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting class", e);
        }
    }

    @Override
    public boolean updateClass(Class classEntity) throws RemoteException {
        String sql = "UPDATE tbl_class SET class_name = ?, course_id = ?, teacher_id = ?, subject_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, classEntity.getClassName());
            stmt.setInt(2, classEntity.getCourseId());
            stmt.setInt(3, classEntity.getTeacherId());
            stmt.setInt(4, classEntity.getSubjectId());
            stmt.setInt(5, classEntity.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị ảnh hưởng
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating class", e);
        }
    }

    @Override
    public int getClassCount() throws RemoteException {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM tbl_class";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy số lượng lớp học", e);
        }
        return count;
    }


}