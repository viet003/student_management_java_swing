package main;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import remote.RoleService;
import remote.TeacherService;
import remote.ClassService;
import remote.SubjectService;
import remote.StudentService;
import remote.CourseService;
import remote.MarkService;
import remote.AccountService;
import service.MarkServiceImpl;
import service.CourseServiceImpl;
import service.AccountServiceImpl;
import service.ClassServiceImpl;
import service.StudentServiceImpl;
import service.SubjectServiceImpl;
import service.RoleServiceImpl;
import service.TeacherServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author vieta
 */
public class MainServer {

    public static void main(String[] args) {
        int port = 2025;
        try {
            // Tạo Registry
            Registry registry = LocateRegistry.createRegistry(port);

            // Khởi tạo các dịch vụ
            RoleService roleService = new RoleServiceImpl();
            TeacherService teacherService = new TeacherServiceImpl();
            CourseService courseService = new CourseServiceImpl();
            SubjectService subjectService = new SubjectServiceImpl();
            ClassService classService = new ClassServiceImpl();
            StudentService studentService = new StudentServiceImpl();
            MarkService markService = new MarkServiceImpl();
            AccountService accountService = new AccountServiceImpl();

            // Đăng ký các dịch vụ vào RMI Registry
            registry.rebind("AccountService", accountService);
            registry.rebind("RoleService", roleService);
            registry.rebind("TeacherService", teacherService);
            registry.rebind("CourseService", courseService);
            registry.rebind("SubjectService", subjectService);
            registry.rebind("ClassService", classService);
            registry.rebind("StudentService", studentService);
            registry.rebind("MarkService", markService);

            System.out.println("Server is running at port " + port + "...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
