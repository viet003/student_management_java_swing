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
public class Class implements Serializable {

    private int id;
    private String className;
    private int course_id;
    private int teacher_id;
    private int subject_id;

    // Constructor, Getters and Setters
    public Class() {
    }

    public Class(int id, String className, int course_id, int teacher_id, int subject_id) {
        this.id = id;
        this.className = className;
        this.course_id = course_id;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
    }
    // Getters and setters...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCourseId() {
        return course_id;
    }

    public void setCourseId(int course_id) {
        this.course_id = course_id;
    }

    public int getTeacherId() {
        return teacher_id;
    }

    public void setTeacherId(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getSubjectId() {
        return subject_id;
    }

    public void setSubject(int subject_id) {
        this.subject_id = subject_id;
    }
    
}
