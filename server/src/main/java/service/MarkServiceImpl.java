package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Mark;
import controller.DatabaseConnection;
import remote.MarkService;

public class MarkServiceImpl extends UnicastRemoteObject implements MarkService {

    public MarkServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean addMark(Mark mark) throws RemoteException {
        String checkSql = "SELECT COUNT(*) FROM tbl_enroll e " +
                "JOIN tbl_class c ON e.class_id = c.id " +
                "WHERE e.student_msv = ? AND c.subject_id = ?";

        String insertSql = "INSERT INTO tbl_mark (student_msv, sub_id, mark, status, note, ex_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            // Kiểm tra xem sinh viên đã đăng ký môn học chưa
            checkStmt.setInt(1, mark.getStudentId());
            checkStmt.setInt(2, mark.getSubjectId());

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                throw new RemoteException("Sinh viên chưa đăng ký môn học này, không thể thêm điểm.");
            }

            // Nếu sinh viên đã đăng ký môn học, thực hiện thêm điểm
            insertStmt.setInt(1, mark.getStudentId());
            insertStmt.setInt(2, mark.getSubjectId());
            insertStmt.setDouble(3, mark.getMark());
            insertStmt.setString(4, mark.getStatus());
            insertStmt.setString(5, mark.getNote());

            // Chuyển đổi java.util.Date sang java.sql.Date
            java.util.Date utilDate = mark.getExamDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            insertStmt.setDate(6, sqlDate);

            insertStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Lỗi khi thêm điểm", e);
        }
    }


    @Override
    public List<Mark> getAllMarks() throws RemoteException {
        List<Mark> marks = new ArrayList<>();
        String sql = "SELECT m.student_msv, s.student_name, m.sub_id, m.mark, m.status, m.note, m.ex_date " +
                "FROM tbl_mark m " +
                "JOIN tbl_student s ON m.student_msv = s.msv"; // Kết hợp với bảng tbl_student để lấy student_name
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                marks.add(new Mark(
                        rs.getInt("student_msv"),
                        rs.getString("student_name"), // Lấy student_name từ kết quả truy vấn
                        rs.getInt("sub_id"),
                        rs.getDouble("mark"),
                        rs.getString("status"),
                        rs.getString("note"),
                        rs.getDate("ex_date") // Lấy java.sql.Date từ kết quả truy vấn
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error retrieving marks", e);
        }
        return marks;
    }

    @Override
    public boolean deleteMark(int studentId, int subId) throws RemoteException {
        String sql = "DELETE FROM tbl_mark WHERE student_msv = ? AND sub_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, subId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error deleting mark", e);
        }
    }

    @Override
    public boolean updateMark(Mark mark) throws RemoteException {
        String sql = "UPDATE tbl_mark SET mark = ?, status = ?, note = ?, ex_date = ? WHERE student_msv = ? AND sub_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, mark.getMark());
            stmt.setString(2, mark.getStatus());
            stmt.setString(3, mark.getNote());

            // Chuyển đổi java.util.Date sang java.sql.Date
            java.util.Date utilDate = mark.getExamDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Chuyển đổi tại đây
            stmt.setDate(4, sqlDate);

            stmt.setInt(5, mark.getStudentId());
            stmt.setInt(6, mark.getSubjectId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Error updating mark", e);
        }
    }
}