package model;

import java.util.Date;
import java.io.Serializable;

/**
 * @author vieta
 */
public class Mark implements Serializable {

    private int student_id;
    private String studentName; // Thêm thuộc tính studentName
    private int subject_id;
    private double mark;
    private String status;
    private String note;
    private Date examDate;

    // Constructor mặc định
    public Mark() {
    }

    // Constructor với các tham số
    public Mark(int student_id, String studentName, int subject_id, double mark, String status, String note, Date examDate) {
        this.student_id = student_id;
        this.studentName = studentName; // Khởi tạo studentName
        this.subject_id = subject_id;
        this.mark = mark;
        this.status = status;
        this.note = note;
        this.examDate = examDate;
    }

    // Getters and setters...

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(int subject_id) {
        this.subject_id = subject_id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getExamDate() {
        return (Date) examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
}