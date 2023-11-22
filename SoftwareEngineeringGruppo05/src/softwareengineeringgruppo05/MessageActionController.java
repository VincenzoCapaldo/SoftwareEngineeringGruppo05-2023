/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class MessageActionController implements Initializable {

    @FXML
    private HBox messageActionBox;
    @FXML
    private RadioButton messageActionRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private TextArea messageTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setToggleGroup(ToggleGroup toggleGroup) {
        messageActionRB.setToggleGroup(toggleGroup);
    }
}
