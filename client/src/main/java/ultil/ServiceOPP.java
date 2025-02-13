/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ultil;

import javax.swing.JComboBox;
import views.model.ComboItem;

/**
 *
 * @author vieta
 */
public class ServiceOPP {

    public static void selectComboBoxItemByID(JComboBox<ComboItem> comboBox, int id) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            ComboItem item = comboBox.getItemAt(i);
            if (item.getId() == id) {
                comboBox.setSelectedItem(item);
                break;
            }
        }
    }
    
        public static void selectComboBoxItemByNanme(JComboBox<ComboItem> comboBox, String name) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            ComboItem item = comboBox.getItemAt(i);
            if (item.getName() == name) {
                comboBox.setSelectedItem(item);
                break;
            }
        }
    }
}
