package main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import model.Account;
import remote.AccountService;
import views.page.Login;
import views.page.Main;

public class MainClient {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
//        try {
//            // Kết nối đến RMI Registry
//            Registry registry = LocateRegistry.getRegistry("localhost", 2025);
//
//            // Lấy các dịch vụ từ RMI Registry
//            AccountService account = (AccountService) registry.lookup("AccountService");
//            List<Account> ac = account.getAllAccounts();
//            System.out.print(ac);
//
//            // Sử dụng dịch vụ quản lý sinh viên

    

////            Account  = new Account("SV001", "Nguyen Van A", 20, "CNTT");
////            account.addStudent(student);
//
//     
//
//            System.out.println("Dữ liệu đã được thêm thành công!");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
