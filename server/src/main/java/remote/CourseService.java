package remote;


import model.Course;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CourseService extends Remote {
    void addCourse(Course course) throws RemoteException;
    List<Course> getAllCourses() throws RemoteException;
    void deleteCourse(int id) throws RemoteException;
    void updateCourse(Course course) throws RemoteException;
//    Course getCourseById(int id) throws RemoteException;
}

