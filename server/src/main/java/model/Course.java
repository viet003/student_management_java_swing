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
public class Course implements Serializable {

    private int id;
    private String courseName;
    private Date beginDate;
    private Date endDate;

    // Constructor, Getters and Setters
    public Course() {
    }

    public Course(int id, String courseName, Date beginDate, Date endDate) {
        this.id = id;
        this.courseName = courseName;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
    // Getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public java.sql.Date getBeginDate() {
        return (java.sql.Date) beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public java.sql.Date getEndDate() {
        return (java.sql.Date) endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
