package model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private String msv;  // Mã sinh viên là khóa chính
    private String studentName;
    private String phone;
    private String email;
    private String address;
    private Date dob;
    private String gender;
    private String status;

    // Constructor không tham số
    public Student() {
    }

    // Constructor đầy đủ tham số
    public Student(String msv, String studentName, String phone, String email, String address, Date dob, String gender, String status) {
        this.msv = msv;
        this.studentName = studentName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.status = status;
    }

    // Getters và Setters
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

    public Date getDob() {
        return dob;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
