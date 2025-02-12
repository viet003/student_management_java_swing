package model;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Date;
import java.io.Serializable;

/**
 *
 * @author vieta
 */
public class Mark implements Serializable {

    private int student_id;
    private int subject_id;
    private double mark;
    private String status;
    private String note;
    private Date examDate;

    // Constructor, Getters and Setters
    public Mark() {
    }

    public Mark(int student_id, int subject_id, double mark, String status, String note, Date examDate) {
        this.student_id = student_id;
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

    public void setStudent(int student_id) {
        this.student_id = student_id;
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

    public java.sql.Date getExamDate() {
        return (java.sql.Date) examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
    
}
