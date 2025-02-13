package remote;


import model.Mark;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MarkService extends Remote {
    boolean addMark(Mark mark) throws RemoteException;
    List<Mark> getAllMarks() throws RemoteException;
    boolean deleteMark(int studentId, int subjectId) throws RemoteException;
    boolean updateMark(Mark mark) throws RemoteException;
//    Mark getMarkById(int studentId, int subjectId) throws RemoteException;
}