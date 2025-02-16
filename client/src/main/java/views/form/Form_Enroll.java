/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.form;

import java.awt.event.ItemEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Course;
import model.Enroll;
import model.Student;
import remote.AccountService;
import remote.ClassService;
import remote.EnrollService;
import remote.RoleService;
import remote.TeacherService;
import ultil.ServiceOPP;
import views.model.ComboItem;

/**
 *
 * @author vieta
 */
public class Form_Enroll extends javax.swing.JPanel {

    private Registry registry;
    private EnrollService enrollService;
    private TeacherService teacherService;
    private ClassService classService;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * Creates new form Form_Enroll
     */
    public Form_Enroll() {
        initComponents();
        init(); 
        loadEnrolls(0);
        loadClassToComboBox();    // Tải danh sách lớp học
        loadStudentToComboBox(0);   // Tải danh sách sinh viên
    }

    public void init() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 2025);

            enrollService = (EnrollService) registry.lookup("EnrollService");
            teacherService = (TeacherService) registry.lookup("TeacherService");
            classService = (ClassService) registry.lookup("ClassService");

            System.out.println("Kết nối RMI thành công!");
        } catch (RemoteException e) {
            System.err.println("Lỗi kết nối RMI: " + e.getMessage());
            e.printStackTrace();
        } catch (java.rmi.NotBoundException e) {
            System.err.println("Dịch vụ chưa được đăng ký trên Registry.");
        }
    }

    private void loadEnrolls(int id) {
        try {
            // Lấy danh sách Enroll từ server thông qua service
            List<Enroll> enrolls = enrollService.getAllEnrolls(id); // Giả sử enrollService là service để lấy dữ liệu Enroll
            DefaultTableModel model = (DefaultTableModel) table1.getModel(); // tableEnroll là JTable hiển thị dữ liệu
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi nạp mới

            // Duyệt qua danh sách Enroll và thêm vào bảng
            for (Enroll enroll : enrolls) {
                model.addRow(new Object[]{
                    enroll.getStudentMsv(), // Mã sinh viên
                    enroll.getClassId(), // ID lớp học
                    enroll.getStudentName(), // Tên sinh viên
                    enroll.getStudentEmail(), // Email sinh viên
                    enroll.getClassName() // Tên lớp học
                });
            }
        } catch (RemoteException e) {
            // Xử lý ngoại lệ nếu có lỗi khi kết nối hoặc lấy dữ liệu
            JOptionPane.showMessageDialog(this, "Lỗi tải danh sách đăng ký: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadClassToComboBox() {
        try {
            // Lấy danh sách lớp học từ server thông qua service
            List<model.Class> classes = classService.getAllClasses(); // Giả sử classService là service để lấy dữ liệu lớp học
            cb_class.removeAllItems(); // Xóa các mục cũ trước khi thêm mới

            // Duyệt qua danh sách lớp học và thêm vào ComboBox
            for (model.Class clazz : classes) {
                cb_class.addItem(new ComboItem(clazz.getId(), clazz.getClassName())); // Thêm ComboItem vào ComboBox
            }
        } catch (RemoteException e) {
            // Xử lý ngoại lệ nếu có lỗi khi kết nối hoặc lấy dữ liệu
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách lớp học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentToComboBox(int id) {
        try {
            // Lấy danh sách sinh viên từ server thông qua service
            List<Student> students = enrollService.getUnenrolledStudentsByClassId(id); // Giả sử studentService là service để lấy dữ liệu sinh viên
            cb_student.removeAllItems(); // Xóa các mục cũ trước khi thêm mới

            // Duyệt qua danh sách sinh viên và thêm vào ComboBox
            for (Student student : students) {
                cb_student.addItem(new ComboItem(Integer.parseInt(student.getMsv()), student.getStudentName())); // Thêm ComboItem vào ComboBox
            }
        } catch (RemoteException e) {
            // Xử lý ngoại lệ nếu có lỗi khi kết nối hoặc lấy dữ liệu
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách sinh viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTable(String keyword) {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table1.setRowSorter(sorter);

        if (keyword.isEmpty()) {
            sorter.setRowFilter(null); // Hiển thị toàn bộ dữ liệu nếu ô tìm kiếm rỗng
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword, 0)); // Lọc theo cột thứ 2 (cột tên giảng viên)
        }
    }

    private void reLoadText() {
        tx_studentName.setText("");
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
        bt_xoa = new javax.swing.JButton();
        bt_reload = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tx_studentName = new javax.swing.JTextField();
        cb_student = new javax.swing.JComboBox<>();
        cb_class = new javax.swing.JComboBox<>();

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
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
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

            },
            new String [] {
                "Mã sinh viên", "Mã lớp học", "Tên sinh viên", "Email", "Tên lớp học"
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

        jLabel1.setText("Tên lớp học");

        jLabel5.setText("Tên sinh viên");

        cb_student.setForeground(new java.awt.Color(102, 102, 102));

        cb_class.setForeground(new java.awt.Color(102, 102, 102));
        cb_class.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_classItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tx_studentName, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                            .addComponent(cb_student, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_class, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bt_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_class, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_student, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(bt_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1009, Short.MAX_VALUE)
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
                    .addGap(0, 2, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 3, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        String keyword = txt_search.getText().trim().toLowerCase(); // Lấy từ khóa tìm kiếm
        filterTable(keyword);
    }//GEN-LAST:event_txt_searchKeyReleased

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        // Lấy dòng được chọn
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            return; // Không chọn gì thì không làm gì cả
        }

        // Lấy dữ liệu từ bảng
        String studentId = table1.getValueAt(selectedRow, 0).toString(); // Cột 0: Mã sinh viên
        String studentName = table1.getValueAt(selectedRow, 1).toString(); // Cột 1: Tên sinh viên
        String studentEmail = table1.getValueAt(selectedRow, 2).toString(); // Cột 2: Email sinh viên
        String className = table1.getValueAt(selectedRow, 3).toString(); // Cột 3: Tên lớp học

        // Hiển thị dữ liệu lên các ô nhập
        tx_studentName.setText(studentName); // Hiển thị tên sinh viên lên JTextField
        tx_studentName.setText(studentEmail); // Hiển thị email lên JTextField (nếu có)

        // Chọn giá trị trong combobox (nếu có)
        ServiceOPP.selectComboBoxItemByNanme(cb_class, className);
        //        ServiceOPP.selectComboBoxItemByNanme(cb_teacher, teacherName);
    }//GEN-LAST:event_table1MouseClicked

    private void bt_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_themActionPerformed
        // TODO add your handling code here:
        try {
            // Kiểm tra nếu sinh viên chưa được chọn
            if (cb_student.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedStudent = (ComboItem) cb_student.getSelectedItem();
            String studentMsv = Integer.toString(selectedStudent.getId()); // Lấy mã sinh viên (student_msv)

            // Kiểm tra nếu lớp học chưa được chọn
            if (cb_class.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp học!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedClass = (ComboItem) cb_class.getSelectedItem();
            int classId = selectedClass.getId(); // Lấy ID lớp học (class_id)

            // Tạo đối tượng Enroll mới
            Enroll newEnroll = new Enroll(studentMsv, classId, "", "", "");

            // Gửi yêu cầu thêm dữ liệu vào bảng tbl_Enroll
            boolean success = enrollService.addEnroll(newEnroll);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu vào lớp học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                reLoadText(); // Xóa nội dung nhập (nếu có)
                loadEnrolls(classId); // Cập nhật danh sách Enroll (nếu cần)
                loadStudentToComboBox(classId); // Cập nhật danh sách sinh viên trong ComboBox
                loadClassToComboBox(); // Cập nhật danh sách lớp học trong ComboBox
            } else {
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu vào lớp học thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu vào lớp học(Enroll): " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_themActionPerformed

    private void bt_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoaActionPerformed
        // TODO add your handling code here:
        try {
            // Lấy dòng được chọn từ bảng
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bản ghi cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy dữ liệu từ bảng
            String studentMsv = table1.getValueAt(selectedRow, 0).toString(); // Cột 0: Mã sinh viên (student_msv)
            int classId = Integer.parseInt(table1.getValueAt(selectedRow, 1).toString()); // Cột 1: ID lớp học (class_id)

            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa bản ghi này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            // Nếu người dùng chọn "Yes"
            if (confirm == JOptionPane.YES_OPTION) {
                // Gửi yêu cầu xóa bản ghi
                boolean success = enrollService.deleteEnroll(studentMsv, classId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa bản ghi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    reLoadText(); // Xóa nội dung nhập (nếu có)
                    loadEnrolls(classId); // Cập nhật danh sách Enroll
                    loadStudentToComboBox(classId); // Cập nhật danh sách sinh viên trong ComboBox
                    loadClassToComboBox(); // Cập nhật danh sách lớp học trong ComboBox
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa bản ghi thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa bản ghi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_xoaActionPerformed

    private void bt_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_reloadActionPerformed
        // TODO add your handling code here:
        reLoadText();
        loadEnrolls(0); // Cập nhật danh sách Enroll
        loadClassToComboBox(); // Cập nhật danh sách lớp học trong ComboBox
        loadEnrolls(0); // Cập nhật danh sách Enroll
        loadStudentToComboBox(0); // Cập nhật danh sách sinh viên trong ComboBox
    }//GEN-LAST:event_bt_reloadActionPerformed

    private void cb_classItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_classItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            ComboItem selectedClass = (ComboItem) cb_class.getSelectedItem();
            if (selectedClass != null) {
                int classId = selectedClass.getId(); // Lấy ID của lớp học
                loadEnrolls(classId);
                loadStudentToComboBox(classId);            }
        }
    }//GEN-LAST:event_cb_classItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_reload;
    private javax.swing.JButton bt_them;
    private javax.swing.JButton bt_xoa;
    private javax.swing.JComboBox<ComboItem> cb_class;
    private javax.swing.JComboBox<ComboItem> cb_student;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private views.swing.Table table1;
    private javax.swing.JTextField tx_studentName;
    private views.swing.SearchText txt_search;
    // End of variables declaration//GEN-END:variables
}
