package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;
import controller.DatabaseConnection;
import remote.RoleService;

/**
 * Triển khai RoleService sử dụng RMI
 */
public class RoleServiceImpl extends UnicastRemoteObject implements RoleService {

    public RoleServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean addRole(Role role) throws RemoteException {
        String sql = "INSERT INTO tbl_role (role_name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, role.getRoleName());
            return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thêm vai trò", e);
        }
    }

    @Override
    public List<Role> getAllRoles() throws RemoteException {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT * FROM tbl_role";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                roles.add(new Role(rs.getInt("id"), rs.getString("role_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy danh sách vai trò", e);
        }
        return roles;
    }

    @Override
    public boolean deleteRole(int id) throws RemoteException {
        String sql = "DELETE FROM tbl_role WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi xóa vai trò", e);
        }
    }

    @Override
    public boolean updateRole(Role role) throws RemoteException {
        String sql = "UPDATE tbl_role SET role_name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, role.getRoleName());
            stmt.setInt(2, role.getId());
            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi cập nhật vai trò", e);
        }
    }

    @Override
    public Role getRoleById(int id) throws RemoteException {
        String sql = "SELECT * FROM tbl_role WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Role(rs.getInt("id"), rs.getString("role_name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi lấy vai trò", e);
        }
        return null;
    }
}
