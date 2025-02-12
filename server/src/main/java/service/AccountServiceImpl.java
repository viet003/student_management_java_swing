package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import controller.DatabaseConnection;
import remote.AccountService;

public class AccountServiceImpl extends UnicastRemoteObject implements AccountService {

    public AccountServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean addAccount(Account account) throws RemoteException {
        String sql = "INSERT INTO tbl_account (email, password, role_id, teacher_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getPassword());
            stmt.setInt(3, account.getRoleId());
            stmt.setInt(4, account.getTeacherId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error adding account", e);
        }
    }

    @Override
    public boolean deleteAccount(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_account WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting account", e);
        }
    }

    @Override
    public boolean updateAccount(Account account) throws RemoteException {
        String sql = "UPDATE tbl_account SET email = ?, password = ?, role_id = ?, teacher_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getPassword());
            stmt.setInt(3, account.getRoleId());
            stmt.setInt(4, account.getTeacherId());
            stmt.setInt(5, account.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating account", e);
        }
    }


    @Override
    public List<Account> getAllAccounts() throws RemoteException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.id, a.email, a.password, a.role_id, a.teacher_id, " +
                "t.name AS teacher_name, r.role_name " +
                "FROM tbl_account a " +
                "LEFT JOIN tbl_teacher t ON a.teacher_id = t.id " +
                "LEFT JOIN tbl_role r ON a.role_id = r.id"; // JOIN với tbl_role để lấy role_name

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("role_id"),
                        rs.getString("role_name"),// Lấy tên vai trò
                        rs.getInt("teacher_id"),
                        rs.getString("teacher_name") // Lấy tên giảng viên
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving accounts", e);
        }
        return accounts;
    }

    @Override
    public Account getAccountByEmail(String email) throws RemoteException {
        String sql = "SELECT * FROM tbl_account WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getInt("role_id"),
                            rs.getInt("teacher_id") // Lấy teacher_id từ DB
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving account by email", e);
        }
        return null;
    }
}
