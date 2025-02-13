/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.form;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Subject;
import remote.SubjectService;
import remote.TeacherService;

/**
 *
 * @author vieta
 */
public class Form_Subject extends javax.swing.JPanel {

    private Registry registry;
    private SubjectService subjectService;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * Creates new form Form_Subject
     */
    public Form_Subject() {
        initComponents();
        init();
        loadSubjects();
    }

    public void init() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 2025);

            subjectService = (SubjectService) registry.lookup("SubjectService");
            System.out.println("Kết nối RMI thành công!");
        } catch (RemoteException e) {
            System.err.println("Lỗi kết nối RMI: " + e.getMessage());
            e.printStackTrace();
        } catch (java.rmi.NotBoundException e) {
            System.err.println("Dịch vụ chưa được đăng ký trên Registry.");
        }
    }

    private void loadSubjects() {
        try {
            List<Subject> subjects = subjectService.getAllSubjects(); // Lấy danh sách môn học từ server
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi nạp mới

            for (Subject subject : subjects) {
                model.addRow(new Object[]{
                    subject.getId(),
                    subject.getSubjectName(),
                    subject.getAccredit(),
                    subject.getPrice(),
                    subject.getStatus()
                });
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải danh sách môn học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTable(String keyword) {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table1.setRowSorter(sorter);

        if (keyword.isEmpty()) {
            sorter.setRowFilter(null); // Hiển thị toàn bộ dữ liệu nếu ô tìm kiếm rỗng
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 1)); // Lọc theo cột thứ 2 (cột tên giảng viên)
        }
    }

    private void reLoadText() {
        tx_subjectName.setText("");
        tx_price.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_search = new views.swing.SearchText();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new views.swing.Table();
        jPanel4 = new javax.swing.JPanel();
        bt_them = new javax.swing.JButton();
        bt_sua = new javax.swing.JButton();
        bt_xoa = new javax.swing.JButton();
        bt_reload = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tx_price = new javax.swing.JTextField();
        tx_subjectName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cb_accredit = new javax.swing.JComboBox<>();
        cb_status = new javax.swing.JComboBox<>();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icon/menu.png"))); // NOI18N

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icon/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txt_search, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên môn học", "Công nhận", "Học phí", "Trạng thái"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        bt_them.setBackground(new java.awt.Color(0, 102, 102));
        bt_them.setForeground(new java.awt.Color(255, 255, 255));
        bt_them.setText("Thêm");
        bt_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_themActionPerformed(evt);
            }
        });

        bt_sua.setBackground(new java.awt.Color(0, 102, 102));
        bt_sua.setForeground(new java.awt.Color(255, 255, 255));
        bt_sua.setText("Sửa");
        bt_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_suaActionPerformed(evt);
            }
        });

        bt_xoa.setBackground(new java.awt.Color(0, 102, 102));
        bt_xoa.setForeground(new java.awt.Color(255, 255, 255));
        bt_xoa.setText("Xóa");
        bt_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_xoaActionPerformed(evt);
            }
        });

        bt_reload.setBackground(new java.awt.Color(0, 102, 102));
        bt_reload.setForeground(new java.awt.Color(255, 255, 255));
        bt_reload.setText("Cập nhật");
        bt_reload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_reloadActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên môn học");

        jLabel4.setText("Học phí");

        jLabel5.setText("Trạng thái");

        jLabel7.setText("Công nhận");

        cb_accredit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã được công nhận", " " }));

        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn hiệu lực", "Hết hiệu lực", " ", " " }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tx_price)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_subjectName, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_accredit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tx_subjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(3, 3, 3)
                .addComponent(cb_accredit, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tx_price, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        String keyword = txt_search.getText().trim().toLowerCase(); // Lấy từ khóa tìm kiếm
        filterTable(keyword);
    }//GEN-LAST:event_txt_searchKeyReleased

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        int selectedRow = table1.getSelectedRow();
        if (selectedRow != -1) {
            Object idObj = table1.getValueAt(selectedRow, 0);
            Object nameObj = table1.getValueAt(selectedRow, 1);
            Object accreditObj = table1.getValueAt(selectedRow, 2);
            Object priceObj = table1.getValueAt(selectedRow, 3);
            Object statusObj = table1.getValueAt(selectedRow, 4);

            // Kiểm tra và ép kiểu an toàn
            int id = (idObj instanceof Integer) ? (int) idObj : 0;
            String name = (nameObj instanceof String) ? (String) nameObj : "";
            int accredit = (accreditObj instanceof Integer) ? (int) accreditObj : 0;
            double price = 0.0;
            if (priceObj instanceof Double) {
                price = (double) priceObj;
            } else if (priceObj instanceof String) {
                try {
                    price = Double.parseDouble((String) priceObj);
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // Xử lý lỗi nếu giá không đúng định dạng
                }
            }
            String status = (statusObj instanceof String) ? (String) statusObj : "";

            // Đưa dữ liệu lên giao diện
            tx_subjectName.setText(name);
            tx_price.setText(String.valueOf(price));
            cb_accredit.setSelectedItem(accredit); // Chọn accredit trong JComboBox (giả sử accredit là số nguyên)
            cb_status.setSelectedItem(status); // Chọn trạng thái trong JComboBox
        }
    }//GEN-LAST:event_table1MouseClicked

    private void bt_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_themActionPerformed
        // TODO add your handling code here:
        try {
            String name = tx_subjectName.getText();
            String priceStr = tx_price.getText();
            String accredit = cb_accredit.getSelectedItem().toString(); // Lấy accredit từ ComboBox
            String status = cb_status.getSelectedItem().toString(); // Lấy trạng thái từ ComboBox

            // Kiểm tra dữ liệu nhập vào
            if (name.isEmpty() || priceStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá phải là một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Subject newSubject = new Subject(0, name, accredit, price, status);
            boolean isAdded = subjectService.addSubject(newSubject); // Kiểm tra kết quả

            if (isAdded) {
                JOptionPane.showMessageDialog(this, "Thêm môn học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadSubjects(); // Cập nhật lại danh sách
                reLoadText();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm môn học thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm môn học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_themActionPerformed

    private void bt_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suaActionPerformed
        try {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn môn học cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = (int) table1.getValueAt(selectedRow, 0); // Lấy ID của môn học cần sửa
            String name = tx_subjectName.getText();
            String priceStr = tx_price.getText();
            String accredit = cb_accredit.getSelectedItem().toString();
            String status = cb_status.getSelectedItem().toString();

            if (name.isEmpty() || priceStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá phải là một số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Subject updatedSubject = new Subject(id, name, accredit, price, status);
            boolean isUpdated = subjectService.updateSubject(updatedSubject); // Kiểm tra kết quả

            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "Cập nhật môn học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadSubjects(); // Cập nhật lại danh sách
                reLoadText();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật môn học thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật môn học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_suaActionPerformed

    private void bt_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoaActionPerformed
        try {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn môn học cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa môn học này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean isDeleted = subjectService.deleteSubject(id);
                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, "Xóa môn học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadSubjects(); // Cập nhật lại danh sách
                    reLoadText();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa môn học thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa môn học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một môn học để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_bt_xoaActionPerformed

    private void bt_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_reloadActionPerformed
        //         TODO add your handling code here:
        loadSubjects(); // Load dữ liệu khi khởi động
        reLoadText();
    }//GEN-LAST:event_bt_reloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_reload;
    private javax.swing.JButton bt_sua;
    private javax.swing.JButton bt_them;
    private javax.swing.JButton bt_xoa;
    private javax.swing.JComboBox<String> cb_accredit;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private views.swing.Table table1;
    private javax.swing.JTextField tx_price;
    private javax.swing.JTextField tx_subjectName;
    private views.swing.SearchText txt_search;
    // End of variables declaration//GEN-END:variables
}
