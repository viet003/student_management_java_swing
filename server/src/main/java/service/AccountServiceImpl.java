package service;

import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    // Phương thức mã hóa mật khẩu sử dụng SHA-256
    private String hashPassword(String password) {
        try {
            // Tạo đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Mã hóa mật khẩu thành mảng byte
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @Override
    public boolean addAccount(Account account) throws RemoteException {
        String sql = "INSERT INTO tbl_account (email, password, role_id, teacher_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Mã hóa mật khẩu trước khi lưu
            String hashedPassword = hashPassword(account.getPassword());

            stmt.setString(1, account.getEmail());
            stmt.setString(2, hashedPassword); // Lưu mật khẩu đã mã hóa
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
        String sql = "UPDATE tbl_account SET email = ?, password = ?, role_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Mã hóa mật khẩu trước khi cập nhật
            String hashedPassword = hashPassword(account.getPassword());

            stmt.setString(1, account.getEmail());
            stmt.setString(2, hashedPassword); // Cập nhật mật khẩu đã mã hóa
            stmt.setInt(3, account.getRoleId());
            stmt.setInt(4, account.getId());
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
    public boolean login(String email, String password) throws RemoteException {
        String sql = "SELECT password FROM tbl_account WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Lấy mật khẩu đã mã hóa từ cơ sở dữ liệu
                    String hashedPasswordFromDB = rs.getString("password");

                    // Mã hóa mật khẩu nhập vào
                    String hashedInputPassword = hashPassword(password);

                    // So sánh mật khẩu đã mã hóa
                    if (hashedInputPassword.equals(hashedPasswordFromDB)) {
                        return true; // Đăng nhập thành công
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error during login", e);
        }
        return false; // Đăng nhập thất bại
    }
}
