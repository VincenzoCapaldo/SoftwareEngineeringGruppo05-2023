/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package actions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author maria
 */
public interface ControllerAction {
    
     public void setToggleGroup(ToggleGroup toggleGroup);
      public BooleanProperty getFlag();
 
}
