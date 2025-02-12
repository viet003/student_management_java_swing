package service;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import controller.DatabaseConnection;
import remote.CourseService;

public class CourseServiceImpl extends UnicastRemoteObject implements CourseService {

    public CourseServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public void addCourse(Course course) throws RemoteException {
        String sql = "INSERT INTO tbl_course (course_name, begin_date, end_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setDate(2, course.getBeginDate());
            stmt.setDate(3, course.getEndDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding course", e);
        }
    }

    @Override
    public List<Course> getAllCourses() throws RemoteException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM tbl_course";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("id"), rs.getString("course_name"), rs.getDate("begin_date"), rs.getDate("end_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving courses", e);
        }
        return courses;
    }

    @Override
    public void deleteCourse(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_course WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting course", e);
        }
    }

    @Override
    public void updateCourse(Course course) throws RemoteException {
        String sql = "UPDATE tbl_course SET course_name = ?, begin_date = ?, end_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseName());
            stmt.setDate(2, course.getBeginDate());
            stmt.setDate(3, course.getEndDate());
            stmt.setInt(4, course.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating course", e);
        }
    }
}

