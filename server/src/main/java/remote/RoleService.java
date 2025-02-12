package remote;


import model.Role;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interface Remote cho Role Service
public interface RoleService extends Remote {
    List<Role> getAllRoles() throws RemoteException;
    boolean addRole(Role role) throws RemoteException;
    boolean deleteRole(int id) throws RemoteException;
    boolean updateRole(Role role) throws RemoteException;
    Role getRoleById(int id) throws RemoteException;
}
