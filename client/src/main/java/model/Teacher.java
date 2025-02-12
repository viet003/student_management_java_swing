package model;

import java.io.Serializable;
import java.util.Date;

public class Teacher implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String address;
    private Date dob;
    private String status;

    // Constructor mặc định
    public Teacher() {
    }

    // Constructor có đầy đủ thông tin
    public Teacher(int id, String name, String phone, String address, Date dob, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.status = status;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
