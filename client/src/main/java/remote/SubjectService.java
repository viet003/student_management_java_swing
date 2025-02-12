package remote;


import model.Subject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SubjectService extends Remote {
    void addSubject(Subject subject) throws RemoteException;
    List<Subject> getAllSubjects() throws RemoteException;
    void deleteSubject(int id) throws RemoteException;
    void updateSubject(Subject subject) throws RemoteException;
//    Subject getSubjectById(int id) throws RemoteException;
}
