/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views.form;

import java.awt.event.ItemEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Mark;
import model.Student;
import model.Subject;
import remote.ClassService;
import remote.CourseService;
import remote.MarkService;
import remote.StudentService;
import remote.SubjectService;
import remote.TeacherService;
import ultil.ServiceOPP;
import views.model.ComboItem;

/**
 *
 * @author vieta
 */
public class Form_Mark extends javax.swing.JPanel {

    private Registry registry;
    private MarkService markService;
    private StudentService studentService;
    private SubjectService subjectService;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * Creates new form Form_Mark
     */
    public Form_Mark() {
        initComponents();
        init();
        loadMarks();
        loadStudentToComboBox();
        loadSubjectToComboBox();
    }

    public void init() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 2025);
            markService = (MarkService) registry.lookup("MarkService");
            studentService = (StudentService) registry.lookup("StudentService");
            subjectService = (SubjectService) registry.lookup("SubjectService");
            System.out.println("Kết nối RMI thành công!");
        } catch (RemoteException e) {
            System.err.println("Lỗi kết nối RMI: " + e.getMessage());
            e.printStackTrace();
        } catch (java.rmi.NotBoundException e) {
            System.err.println("Dịch vụ chưa được đăng ký trên Registry.");
        }
    }

    private void loadMarks() {
        try {
            // Giả sử markService là một dịch vụ để lấy danh sách điểm từ server
            List<Mark> marks = markService.getAllMarks(); // Lấy danh sách điểm từ server

            // Lấy model của bảng (JTable)
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

            // Duyệt qua danh sách điểm và thêm vào bảng
            for (Mark mark : marks) {
                // Tạo một hàng dữ liệu từ thông tin của Mark
                Object[] rowData = {
                    mark.getStudentId(), // Cột 0: Mã sinh viên
                    mark.getStudentName(), // Cột 1: Tên sinh viên
                    mark.getSubjectId(), // Cột 2: Mã môn học
                    mark.getMark(), // Cột 3: Điểm
                    mark.getStatus(), // Cột 4: Trạng thái
                    mark.getNote(), // Cột 5: Ghi chú
                    mark.getExamDate() // Cột 6: Ngày thi
                };

                // Thêm hàng dữ liệu vào bảng
                model.addRow(rowData);
            }

//            // Xóa dữ liệu cũ trong các trường nhập liệu (nếu cần)
//            tx_msv.setText("");
//            cb_student.setSelectedIndex(-1);
//            cb_subject.setSelectedIndex(-1);
//            tx_mark.setText("");
//            cb_status.setSelectedIndex(-1);
//            tx_note.setText("");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải danh sách điểm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reLoadText() {
        tx_msv.setText(""); // Xóa nội dung của trường mã sinh viên
        cb_student.setSelectedIndex(-1); // Đặt lại combobox sinh viên
        cb_subject.setSelectedIndex(-1); // Đặt lại combobox môn học
        tx_mark.setText(""); // Xóa nội dung của trường điểm
        cb_status.setSelectedIndex(-1); // Đặt lại combobox trạng thái
        tx_note.setText(""); // Xóa nội dung của trường ghi chú
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

    private void loadStudentToComboBox() {
        try {
            // Giả sử studentService là một dịch vụ để lấy danh sách sinh viên từ server
            List<Student> students = studentService.getAllStudents(); // Lấy danh sách sinh viên từ server
            cb_student.removeAllItems(); // Xóa các mục cũ trước khi thêm mới

            // Duyệt qua danh sách sinh viên và thêm vào combobox
            for (Student student : students) {
                cb_student.addItem(new ComboItem(Integer.parseInt(student.getMsv()), student.getStudentName()));
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách sinh viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSubjectToComboBox() {
        try {
            // Giả sử subjectService là một dịch vụ để lấy danh sách môn học từ server
            List<Subject> subjects = subjectService.getAllSubjects(); // Lấy danh sách môn học từ server
            cb_subject.removeAllItems(); // Xóa các mục cũ trước khi thêm mới

            // Duyệt qua danh sách môn học và thêm vào combobox
            for (Subject subject : subjects) {
                cb_subject.addItem(new ComboItem(subject.getId(), subject.getSubjectName()));
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách môn học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_search = new views.swing.SearchText();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new views.swing.Table();
        jPanel5 = new javax.swing.JPanel();
        bt_them = new javax.swing.JButton();
        bt_xoa = new javax.swing.JButton();
        bt_reload = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tx_mark = new javax.swing.JTextField();
        cb_student = new javax.swing.JComboBox<>();
        cb_subject = new javax.swing.JComboBox<>();
        bt_sua = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tx_msv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tx_note = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cb_status = new javax.swing.JComboBox<>();

        jFormattedTextField1.setText("jFormattedTextField1");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txt_search, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sinh viên", "Họ và tên", "Mã môn học", "Điểm", "Trạng thái", "Ghi chú"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel1.setText("Mã sinh viên");

        jLabel5.setText("Tên sinh viên");

        cb_student.setForeground(new java.awt.Color(102, 102, 102));
        cb_student.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_studentItemStateChanged(evt);
            }
        });

        cb_subject.setForeground(new java.awt.Color(102, 102, 102));

        bt_sua.setBackground(new java.awt.Color(0, 102, 102));
        bt_sua.setForeground(new java.awt.Color(255, 255, 255));
        bt_sua.setText("Sửa");
        bt_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_suaActionPerformed(evt);
            }
        });

        jLabel6.setText("Điểm");

        jLabel7.setText("Môn học");

        jLabel8.setText("Trạng thái");

        jLabel9.setText("Ghi chú");

        cb_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã được công nhận", " " }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cb_student, 0, 263, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cb_subject, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_msv, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_note, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tx_msv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_student, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tx_mark, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tx_note, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1021, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1021, Short.MAX_VALUE)
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
        // Lấy dòng được chọn từ bảng
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            return; // Nếu không có dòng nào được chọn, thoát khỏi hàm
        }

        // Lấy dữ liệu từ bảng dựa trên model Mark
        int studentId = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString()); // Mã sinh viên
        String studentName = table1.getValueAt(selectedRow, 1).toString(); // Tên sinh viên
        int subjectId = Integer.parseInt(table1.getValueAt(selectedRow, 2).toString()); // Mã môn học
        double mark = Double.parseDouble(table1.getValueAt(selectedRow, 3).toString()); // Điểm
        String status = table1.getValueAt(selectedRow, 4).toString(); // Trạng thái
        String note = table1.getValueAt(selectedRow, 5).toString(); // Ghi chú

        // Hiển thị dữ liệu lên các ô nhập
        tx_msv.setText(String.valueOf(studentId)); // Hiển thị mã sinh viên
        ServiceOPP.selectComboBoxItemByNanme(cb_student, studentName); // Chọn tên sinh viên trong combobox
        cb_subject.setSelectedItem(subjectId); // Chọn mã môn học trong combobox
        tx_mark.setText(String.valueOf(mark)); // Hiển thị điểm
        cb_status.setSelectedItem(status); // Chọn trạng thái trong combobox
        tx_note.setText(note); // Hiển thị ghi chú
    }//GEN-LAST:event_table1MouseClicked

    private void bt_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_themActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        try {
            // Kiểm tra nếu sinh viên chưa được chọn
            if (cb_student.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedStudent = (ComboItem) cb_student.getSelectedItem();
            int studentId = selectedStudent.getId(); // Lấy mã sinh viên (student_id)

            // Kiểm tra nếu môn học chưa được chọn
            if (cb_subject.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn môn học!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedSubject = (ComboItem) cb_subject.getSelectedItem();
            int subjectId = selectedSubject.getId(); // Lấy mã môn học (subject_id)

            // Lấy điểm từ trường nhập liệu
            double mark;
            try {
                mark = Double.parseDouble(tx_mark.getText().trim()); // Lấy điểm từ trường nhập liệu
                if (mark < 0 || mark > 10) {
                    JOptionPane.showMessageDialog(this, "Điểm phải nằm trong khoảng từ 0 đến 10!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Điểm phải là một số hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy trạng thái và ghi chú từ trường nhập liệu
            String status = cb_status.getSelectedItem().toString(); // Lấy trạng thái từ combobox
            String note = tx_note.getText(); // Lấy ghi chú từ trường nhập liệu

            // Tạo đối tượng Mark mới
            Mark newMark = new Mark(studentId, selectedStudent.getName(), subjectId, mark, status, note, new Date());

            // Gửi yêu cầu thêm dữ liệu vào bảng Mark
            boolean success = markService.addMark(newMark);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu điểm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                reLoadText(); // Xóa nội dung nhập (nếu có)
                loadMarks(); // Cập nhật danh sách điểm (nếu cần)
                loadStudentToComboBox(); // Cập nhật danh sách sinh viên trong ComboBox
                loadSubjectToComboBox(); // Cập nhật danh sách môn học trong ComboBox
            } else {
                JOptionPane.showMessageDialog(this, "Thêm dữ liệu điểm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu điểm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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

            // Lấy dữ liệu từ bảng dựa trên model Mark
            int studentId = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString()); // Cột 0: Mã sinh viên (student_id)
            int subjectId = Integer.parseInt(table1.getValueAt(selectedRow, 2).toString()); // Cột 2: Mã môn học (subject_id)

            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa bản ghi này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            // Nếu người dùng chọn "Yes"
            if (confirm == JOptionPane.YES_OPTION) {
                // Gửi yêu cầu xóa bản ghi điểm
                boolean success = markService.deleteMark(studentId, subjectId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa bản ghi điểm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    reLoadText(); // Xóa nội dung nhập (nếu có)
                    loadMarks(); // Cập nhật danh sách điểm
                    loadStudentToComboBox(); // Cập nhật danh sách sinh viên trong ComboBox
                    loadSubjectToComboBox(); // Cập nhật danh sách môn học trong ComboBox
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa bản ghi điểm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa bản ghi điểm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_xoaActionPerformed

    private void bt_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_reloadActionPerformed
        // TODO add your handling code here:
        reLoadText(); // Xóa nội dung nhập liệu
        loadMarks(); // Cập nhật danh sách điểm
        loadSubjectToComboBox(); // Cập nhật danh sách môn học trong ComboBox
        loadStudentToComboBox(); // Cập nhật danh sách sinh viên trong ComboBox
    }//GEN-LAST:event_bt_reloadActionPerformed

    private void bt_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suaActionPerformed
        try {
            // Lấy dòng được chọn từ bảng
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bản ghi cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lấy dữ liệu từ bảng dựa trên model Mark
            int studentId = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString()); // Cột 0: Mã sinh viên (student_id)
            int subjectId = Integer.parseInt(table1.getValueAt(selectedRow, 2).toString()); // Cột 2: Mã môn học (subject_id)

            // Lấy điểm và trạng thái từ các trường nhập liệu
            double newMark;
            try {
                newMark = Double.parseDouble(tx_mark.getText().trim()); // Lấy điểm từ trường nhập liệu
                if (newMark < 0 || newMark > 10) {
                    JOptionPane.showMessageDialog(this, "Điểm phải nằm trong khoảng từ 0 đến 10!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Điểm phải là một số hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String newStatus = cb_status.getSelectedItem().toString(); // Lấy trạng thái từ combobox

            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn sửa bản ghi này?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );

            // Nếu người dùng chọn "Yes"
            if (confirm == JOptionPane.YES_OPTION) {
                // Tạo đối tượng Mark mới với điểm và trạng thái đã cập nhật
                Mark updatedMark = new Mark(studentId, "", subjectId, newMark, newStatus, "", new Date());

                // Gửi yêu cầu cập nhật bản ghi điểm
                boolean success = markService.updateMark(updatedMark);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Sửa bản ghi điểm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    reLoadText(); // Xóa nội dung nhập (nếu có)
                    loadMarks(); // Cập nhật danh sách điểm
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa bản ghi điểm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi sửa bản ghi điểm: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_suaActionPerformed

    private void cb_studentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_studentItemStateChanged
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            ComboItem selectedClass = (ComboItem) cb_student.getSelectedItem();
            if (selectedClass != null) {
                int classId = selectedClass.getId(); // Lấy ID của lớp học
                tx_msv.setText(Integer.toString(classId));
            }
        }
    }//GEN-LAST:event_cb_studentItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_reload;
    private javax.swing.JButton bt_sua;
    private javax.swing.JButton bt_them;
    private javax.swing.JButton bt_xoa;
    private javax.swing.JComboBox<String> cb_status;
    private javax.swing.JComboBox<ComboItem> cb_student;
    private javax.swing.JComboBox<ComboItem> cb_subject;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private views.swing.Table table1;
    private javax.swing.JTextField tx_mark;
    private javax.swing.JTextField tx_msv;
    private javax.swing.JTextField tx_note;
    private views.swing.SearchText txt_search;
    // End of variables declaration//GEN-END:variables
}
