package remote;

import model.Account;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Interface cho AccountService Server
public interface AccountService extends Remote {
    // Thêm tài khoản mới
    boolean addAccount(Account account) throws RemoteException;

    // Lấy danh sách tất cả tài khoản
    List<Account> getAllAccounts() throws RemoteException;

    // Xóa tài khoản theo ID
    boolean deleteAccount(int id) throws RemoteException;

    // Cập nhật thông tin tài khoản
    boolean updateAccount(Account account) throws RemoteException;


    // Đăng nhập với email và mật khẩu
    boolean login(String email, String password) throws RemoteException;
}