package remote;

import model.Student;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface StudentService extends Remote {
    List<Student> getAllStudents() throws RemoteException;
    boolean addStudent(Student student) throws RemoteException;
    boolean deleteStudent(String msv) throws RemoteException; // Đổi id thành msv
    boolean updateStudent(Student student) throws RemoteException;
    // Lấy số lượng sinh viên
    int getStudentCount() throws RemoteException;
    // Tùy chọn: Nếu cần tìm sinh viên theo mã sinh viên
    //    Student getStudentByMsv(String msv) throws RemoteException;
}
