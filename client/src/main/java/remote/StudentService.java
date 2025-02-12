package remote;


import model.Student;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentService extends Remote {
    void addStudent(Student student) throws RemoteException;
    List<Student> getAllStudents() throws RemoteException;
    void deleteStudent(int id) throws RemoteException;
    void updateStudent(Student student) throws RemoteException;
//    Student getStudentById(int id) throws RemoteException;
}
