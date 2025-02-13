package remote;


import model.Class;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClassService extends Remote {
    boolean addClass(Class studentClass) throws RemoteException;
    List<Class> getAllClasses() throws RemoteException;
    boolean deleteClass(int id) throws RemoteException;
    boolean updateClass(Class studentClass) throws RemoteException;
//    Class getClassById(int id) throws RemoteException;
}