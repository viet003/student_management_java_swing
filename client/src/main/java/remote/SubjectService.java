package remote;

import model.Subject;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SubjectService extends Remote {
    List<Subject> getAllSubjects() throws RemoteException;
    boolean addSubject(Subject subject) throws RemoteException;
    boolean deleteSubject(int id) throws RemoteException;
    boolean updateSubject(Subject subject) throws RemoteException;
//    Subject getSubjectById(int id) throws RemoteException;
}
