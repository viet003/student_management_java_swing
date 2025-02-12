package remote;


import model.Teacher;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TeacherService extends Remote {
    List<Teacher> getAllTeachers() throws RemoteException;
    void addTeacher(Teacher teacher) throws RemoteException;
    void deleteTeacher(int id) throws RemoteException;
    void updateTeacher(Teacher teacher) throws RemoteException;
    List<Teacher> getTeachersWithoutAccount() throws RemoteException; // Lấy giảng viên chưa có tài khoản
}
