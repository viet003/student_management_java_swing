package remote;

import model.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interface cho AccountService Server
public interface AccountService extends Remote {
    boolean addAccount(Account account) throws RemoteException;
    List<Account> getAllAccounts() throws RemoteException;
    boolean deleteAccount(int id) throws RemoteException;
    boolean updateAccount(Account account) throws RemoteException;
    Account getAccountByEmail(String email) throws RemoteException;
}
