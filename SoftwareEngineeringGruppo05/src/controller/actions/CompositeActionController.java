/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.actions;

import controller.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class CompositeActionController implements Initializable, Controller {

    @FXML
    private HBox compositeActionBox;
    @FXML
    private RadioButton compositeActionRB;
    @FXML
    private ToggleGroup selectActionTG;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        compositeActionRB.setToggleGroup(toggleGroup);
    }

    @Override
    public BooleanProperty getFlag() {
        return new SimpleBooleanProperty(false);
    }
    
}
