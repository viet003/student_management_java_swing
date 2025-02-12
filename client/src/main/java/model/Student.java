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
public class Student implements Serializable {

    private int id;
    private String msv;
    private String studentName;
    private String phone;
    private String email;
    private String address;
    private Date dob;
    private String gender;
    private String img;
    private String status;
    private int class_id;

    // Constructor, Getters and Setters
    public Student() {
    }

    public Student(int id, String msv, String studentName, String phone, String email, String address, Date dob, String gender, String img, String status, int class_id) {
        this.id = id;
        this.msv = msv;
        this.studentName = studentName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.img = img;
        this.status = status;
        this.class_id = class_id;
    }
    // Getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.sql.Date getDob() {
        return (java.sql.Date) dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClassId() {
        return class_id;
    }

    public void setClassId(int class_id) {
        this.class_id = class_id;
    }
    
}
