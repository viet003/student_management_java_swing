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
    public void addClass(Class classEntity) throws RemoteException {
        String sql = "INSERT INTO tbl_class (class_name, course_id, teacher_id, subject_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, classEntity.getClassName());
            stmt.setInt(2, classEntity.getCourseId());
            stmt.setInt(3, classEntity.getTeacherId());
            stmt.setInt(4, classEntity.getSubjectId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding class", e);
        }
    }

    @Override
    public List<Class> getAllClasses() throws RemoteException {
        List<Class> classes = new ArrayList<>();
        String sql = "SELECT * FROM tbl_class";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                classes.add(new Class(rs.getInt("id"), rs.getString("class_name"), rs.getInt("course_id"), rs.getInt("teacher_id"), rs.getInt("subject_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving classes", e);
        }
        return classes;
    }

    @Override
    public void deleteClass(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_class WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting class", e);
        }
    }

    @Override
    public void updateClass(Class classEntity) throws RemoteException {
        String sql = "UPDATE tbl_class SET class_name = ?, course_id = ?, teacher_id = ?, subject_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, classEntity.getClassName());
            stmt.setInt(2, classEntity.getCourseId());
            stmt.setInt(3, classEntity.getTeacherId());
            stmt.setInt(4, classEntity.getSubjectId());
            stmt.setInt(5, classEntity.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating class", e);
        }
    }
}

