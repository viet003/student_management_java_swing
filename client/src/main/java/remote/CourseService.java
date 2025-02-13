package remote;


import model.Course;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CourseService extends Remote {
    List<Course> getAllCourses() throws RemoteException;
    boolean addCourse(Course course) throws RemoteException;
    boolean deleteCourse(int id) throws RemoteException;
    boolean updateCourse(Course course) throws RemoteException;
//    Course getCourseById(int id) throws RemoteException;
}

