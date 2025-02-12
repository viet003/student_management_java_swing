package model;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String email;
    private String password;
    private int roleId;
    private String roleName;
    private int teacherId; // Dùng teacher_id thay vì Teacher object
    private String teacherName;

    // Constructor mặc định
    public Account() {
    }

    // Constructor có đầy đủ thông tin
    public Account(int id, String email, String password, int roleId, int teacherId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.teacherId = teacherId;
    }

    // Constructor có đầy đủ thông tin
    public Account(int id, String email, String password, int roleId, String roleName, int teacherId, String teacherName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.roleName = roleName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
