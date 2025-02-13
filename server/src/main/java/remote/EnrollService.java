package remote;

import model.Enroll;
import model.Student;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EnrollService extends Remote {
    List<Enroll> getAllEnrolls(int classId) throws RemoteException;
    boolean addEnroll(Enroll enroll) throws RemoteException;
    boolean deleteEnroll(String studentMsv, int classId) throws RemoteException;

    // Hàm mới để lấy danh sách sinh viên chưa đăng ký vào lớp có ID classId
    List<Student> getUnenrolledStudentsByClassId(int classId) throws RemoteException;
}
