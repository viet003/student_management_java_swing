package remote;


import model.Mark;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MarkService extends Remote {
    void addMark(Mark mark) throws RemoteException;
    List<Mark> getAllMarks() throws RemoteException;
    void deleteMark(int studentId, int subjectId) throws RemoteException;
    void updateMark(Mark mark) throws RemoteException;
//    Mark getMarkById(int studentId, int subjectId) throws RemoteException;
}