package model;

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
    private String courseName; // Thêm thuộc tính course_name
    private String teacherName; // Thêm thuộc tính teacherName
    private String subjectName; // Thêm thuộc tính subjectName

    // Constructor mặc định
    public Class() {
    }

    // Constructor với tất cả các thuộc tính
    public Class(int id, String className, int course_id, int teacher_id, int subject_id, String courseName, String teacherName, String subjectName) {
        this.id = id;
        this.className = className;
        this.course_id = course_id;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.subjectName = subjectName;
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

    public void setSubjectId(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
