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
import model.Course;
import model.Role;
import model.Subject;
import model.Teacher;
import remote.AccountService;
import remote.ClassService;
import remote.CourseService;
import remote.RoleService;
import remote.SubjectService;
import remote.TeacherService;
import ultil.ServiceOPP;
import views.model.ComboItem;

/**
 *
 * @author vieta
 */
public class Form_Class extends javax.swing.JPanel {

    private Registry registry;
    private ClassService classService;
    private TeacherService teacherService;
    private CourseService courseService;
    private SubjectService subjectService;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * Creates new form Form_CLass
     */
    public Form_Class() {
        initComponents();
        init();
        loadClasses();
        loadCourseToComboBox();
        loadTeachersToComboBox();
        loadSubjectsToComboBox();
    }

    public void init() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 2025);
            classService = (ClassService) registry.lookup("ClassService");
            courseService = (CourseService) registry.lookup("CourseService");
            teacherService = (TeacherService) registry.lookup("TeacherService");
            subjectService = (SubjectService) registry.lookup("SubjectService");
            System.out.println("Kết nối RMI thành công!");
        } catch (RemoteException e) {
            System.err.println("Lỗi kết nối RMI: " + e.getMessage());
            e.printStackTrace();
        } catch (java.rmi.NotBoundException e) {
            System.err.println("Dịch vụ chưa được đăng ký trên Registry.");
        }
    }

    private void loadClasses() {
        try {
            List<model.Class> classes = classService.getAllClasses(); // Lấy danh sách lớp từ server
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trước khi nạp mới

            for (model.Class cls : classes) {
                model.addRow(new Object[]{
                    cls.getId(), // ID lớp
                    cls.getClassName(), // Tên lớp
                    cls.getCourseName(), // Tên khóa học
                    cls.getTeacherName(), // Tên giáo viên
                    cls.getSubjectName() // Tên môn học
                });
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải danh sách lớp học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        tx_className.setText("");
    }

    private void loadCourseToComboBox() {
        try {
            List<Course> courses = courseService.getAllCourses(); // Lấy danh sách roles từ server
            cb_course.removeAllItems(); // Xóa các mục cũ trước khi thêm mới

            for (Course course : courses) {
                cb_course.addItem(new ComboItem(course.getId(), course.getCourseName()));
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách vai trò: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadTeachersToComboBox() {
        try {
            List<Teacher> teachers = teacherService.getAllTeachers(); // Lấy danh sách roles từ server
            cb_teacher.removeAllItems(); // Xóa các mục cũ trước khi thêm mới

            for (Teacher teacher : teachers) {
                cb_teacher.addItem(new ComboItem(teacher.getId(), teacher.getName()));
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách vai trò: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSubjectsToComboBox() {
        try {
            List<Subject> subjects = subjectService.getAllSubjects(); // Lấy danh sách môn học từ server
            cb_subject.removeAllItems(); // Xóa các mục cũ trước khi thêm mới

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
        jLabel6 = new javax.swing.JLabel();
        tx_className = new javax.swing.JTextField();
        cb_subject = new javax.swing.JComboBox<>();
        cb_course = new javax.swing.JComboBox<>();
        cb_teacher = new javax.swing.JComboBox<>();

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
                "ID", "Tên lớp", "Khóa hoc", "Giảng viên", "Môn học"
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

        jLabel1.setText("Tên lớp học");

        jLabel4.setText("Khóa học");

        jLabel5.setText("Giảng viên");

        jLabel6.setText("Môn học");

        cb_subject.setForeground(new java.awt.Color(102, 102, 102));

        cb_course.setForeground(new java.awt.Color(102, 102, 102));

        cb_teacher.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cb_course, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_subject, javax.swing.GroupLayout.Alignment.LEADING, 0, 272, Short.MAX_VALUE)
                            .addComponent(cb_teacher, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(62, 62, 62)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bt_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bt_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(tx_className, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tx_className, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(cb_course, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_teacher, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addComponent(cb_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGap(0, 593, Short.MAX_VALUE)
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
        // TODO add your handling code here:
        int selectedRow = table1.getSelectedRow(); // Lấy dòng được chọn
        if (selectedRow == -1) {
            return; // Không chọn gì thì không làm gì cả
        }

        // Lấy dữ liệu từ bảng dựa trên model Class
        int id = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString());
        String className = table1.getValueAt(selectedRow, 1).toString();
        String courseName = table1.getValueAt(selectedRow, 2).toString();
        String teacherName = table1.getValueAt(selectedRow, 3).toString();
        String subjectName = table1.getValueAt(selectedRow, 4).toString();

        // Hiển thị dữ liệu lên các ô nhập
        tx_className.setText(className);

        // Chọn giá trị trong combobox
        ServiceOPP.selectComboBoxItemByNanme(cb_course, courseName);
        ServiceOPP.selectComboBoxItemByNanme(cb_teacher, teacherName);
        ServiceOPP.selectComboBoxItemByNanme(cb_subject, subjectName);
    }//GEN-LAST:event_table1MouseClicked

    private void bt_reloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_reloadActionPerformed
        // TODO add your handling code here:
        reLoadText();
        loadClasses();
        loadCourseToComboBox();
        loadTeachersToComboBox();
        loadSubjectsToComboBox();
    }//GEN-LAST:event_bt_reloadActionPerformed

    private void bt_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_xoaActionPerformed
        // TODO add your handling code here:
        try {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString());

            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tài khoản này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = classService.deleteClass(id);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    reLoadText();
                    loadClasses();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa tài khoản: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_xoaActionPerformed

    private void bt_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suaActionPerformed
        // TODO add your handling code here:
        try {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lớp học cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(table1.getValueAt(selectedRow, 0).toString());
            String className = tx_className.getText().trim();

            // Kiểm tra xem đã chọn khóa học hay chưa
            if (cb_course.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khóa học!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedCourse = (ComboItem) cb_course.getSelectedItem();
            int courseId = selectedCourse.getId();

            // Kiểm tra xem đã chọn giảng viên hay chưa
            if (cb_teacher.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn giảng viên!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedTeacher = (ComboItem) cb_teacher.getSelectedItem();
            int teacherId = selectedTeacher.getId();

            // Kiểm tra xem đã chọn môn học hay chưa
            if (cb_subject.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn môn học!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedSubject = (ComboItem) cb_subject.getSelectedItem();
            int subjectId = selectedSubject.getId();

            // Kiểm tra tên lớp không được rỗng
            if (className.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên lớp không được để trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Tạo đối tượng Class để cập nhật
            model.Class updatedClass = new model.Class(id, className, courseId, teacherId, subjectId, selectedCourse.getName(), selectedTeacher.getName(), selectedSubject.getName());

            // Gửi yêu cầu cập nhật
            boolean success = classService.updateClass(updatedClass);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật lớp học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                reLoadText();
                loadClasses();
                loadCourseToComboBox();
                loadTeachersToComboBox();
                loadSubjectsToComboBox();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật lớp học thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID lớp học phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật lớp học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_suaActionPerformed

    private void bt_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_themActionPerformed
        // TODO add your handling code here:
        try {
            String className = tx_className.getText().trim();

            // Kiểm tra nếu tên lớp bị trống
            if (className.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên lớp!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kiểm tra nếu khóa học chưa được chọn
            if (cb_course.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khóa học!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedCourse = (ComboItem) cb_course.getSelectedItem();
            int courseId = selectedCourse.getId();

            // Kiểm tra nếu giảng viên chưa được chọn
            if (cb_teacher.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn giảng viên!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedTeacher = (ComboItem) cb_teacher.getSelectedItem();
            int teacherId = selectedTeacher.getId();

            // Kiểm tra nếu môn học chưa được chọn
            if (cb_subject.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn môn học!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ComboItem selectedSubject = (ComboItem) cb_subject.getSelectedItem();
            int subjectId = selectedSubject.getId();

            // Tạo lớp học mới
            model.Class newClass = new model.Class(0, className, courseId, teacherId, subjectId, selectedCourse.getName(), selectedTeacher.getName(), selectedSubject.getName());

            // Gửi yêu cầu thêm lớp học
            boolean success = classService.addClass(newClass);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm lớp học thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                reLoadText(); // Xóa nội dung nhập
                loadClasses(); // Cập nhật danh sách lớp học
                loadCourseToComboBox();
                loadTeachersToComboBox();
                loadSubjectsToComboBox();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm lớp học thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm lớp học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_themActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_reload;
    private javax.swing.JButton bt_sua;
    private javax.swing.JButton bt_them;
    private javax.swing.JButton bt_xoa;
    private javax.swing.JComboBox<ComboItem> cb_course;
    private javax.swing.JComboBox<ComboItem> cb_subject;
    private javax.swing.JComboBox<ComboItem> cb_teacher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private views.swing.Table table1;
    private javax.swing.JTextField tx_className;
    private views.swing.SearchText txt_search;
    // End of variables declaration//GEN-END:variables
}
