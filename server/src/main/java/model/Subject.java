package model;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Serializable;

/**
 *
 * @author vieta
 */
public class Subject implements Serializable {

    private int id;
    private String subjectName;
    private String accredit;
    private double price;
    private String status;

    // Constructor, Getters and Setters
    public Subject() {
    }

    public Subject(int id, String subjectName, String accredit, double price, String status) {
        this.id = id;
        this.subjectName = subjectName;
        this.accredit = accredit;
        this.price = price;
        this.status = status;
    }
    // Getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getAccredit() {
        return accredit;
    }

    public void setAccredit(String accredit) {
        this.accredit = accredit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
