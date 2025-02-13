package model;

import java.io.Serializable;
import java.util.Objects;

public class Enroll implements Serializable {
    private String studentMsv; // Mã sinh viên (khóa chính)
    private int classId;       // ID lớp học (khóa chính)
    private String studentName; // Tên sinh viên (từ bảng tbl_student)
    private String studentEmail; // Email sinh viên (từ bảng tbl_student)
    private String className;   // Tên lớp học (từ bảng tbl_class)

    // Constructor mặc định
    public Enroll() {
    }

    // Constructor với các tham số
    public Enroll(String studentMsv, int classId, String studentName, String studentEmail, String className) {
        this.studentMsv = studentMsv;
        this.classId = classId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.className = className;
    }

    // Getters and setters
    public String getStudentMsv() {
        return studentMsv;
    }

    public void setStudentMsv(String studentMsv) {
        this.studentMsv = studentMsv;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
