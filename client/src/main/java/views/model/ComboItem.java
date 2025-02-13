/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.model;

/**
 *
 * @author vieta
 */
public class ComboItem {

    private int id;
    private String name;

    @Override
    public String toString() {
        return name; // Hiển thị tên trong ComboBox
    }

    public ComboItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
   
}
