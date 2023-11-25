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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class TimeTriggerController implements Initializable {

    @FXML
    private HBox timeTriggerBox;
    @FXML
    private RadioButton timeTriggerRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private TextField timeTextFieldHours;
    @FXML
    private TextField timeTextFieldMinutes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setToggleGroup(ToggleGroup toggleGroup) {
        timeTriggerRB.setToggleGroup(toggleGroup);
    }
    
    public int getHours(){
        return Integer.parseInt(timeTextFieldHours.getText());
    }
    
    public int getMinutes(){
        return Integer.parseInt(timeTextFieldMinutes.getText());
    }

    @FXML
    private void onChangedHour(KeyEvent event) {
        int hours = 0;
        
        if(!timeTextFieldHours.getText().matches("\\d+")){
            timeTextFieldHours.clear();
        }else{
             hours = Integer.parseInt(timeTextFieldHours.getText());
             if (hours < 0 || hours > 23) {
                timeTextFieldHours.clear();
             }
        }
    }

    @FXML
    private void onChangedMinute(KeyEvent event) {
        int minutes=0;
        
        if(!timeTextFieldMinutes.getText().matches("\\d+")){
            timeTextFieldMinutes.clear();
        }else{
             minutes = Integer.parseInt(timeTextFieldMinutes.getText());
             if (minutes < 0 || minutes > 59) {
                timeTextFieldMinutes.clear();
             }
        }
    }
    
}
