/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package triggers.DayOfMonthTrigger;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import softwareengineeringgruppo05.CheckValueClass;
import triggers.ControllerTrigger;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class DayOfMonthTriggerController implements Initializable, ControllerTrigger {

    @FXML
    private HBox dayOfMonthBox;
    @FXML
    private VBox dayOfMonthVbox;
    @FXML
    private RadioButton dayOfMonthRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private Spinner<Integer> dayOfMonthSpinner;
    
    private LocalDate currentDate  = LocalDate.now();
        
    private int daysInMonth = YearMonth.from(currentDate).lengthOfMonth();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        BooleanProperty isTriggerDayOfMonthSelected = dayOfMonthRB.selectedProperty();
        dayOfMonthSpinner.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean dayOfMonthTriggerSelected = isTriggerDayOfMonthSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!dayOfMonthTriggerSelected) {
                        dayOfMonthVbox.getChildren().remove(dayOfMonthSpinner);
                        dayOfMonthBox.setPrefHeight(75);
                    } else {
                        createSpinner();
                        dayOfMonthVbox.getChildren().add(dayOfMonthSpinner);
                        dayOfMonthBox.setPrefHeight(137);
                        
                    }
                });
                return dayOfMonthTriggerSelected;
            },
            isTriggerDayOfMonthSelected
        ));
    }    

    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        dayOfMonthRB.setToggleGroup(toggleGroup);
    }
    
    private void createSpinner(){

        SpinnerValueFactory<Integer> valueFactoryMonth = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, daysInMonth, currentDate.getDayOfMonth());
        
        dayOfMonthSpinner.setValueFactory(valueFactoryMonth);
    }

    @FXML
    private void onChangedDayOfMonth(KeyEvent event) {
        CheckValueClass check = new CheckValueClass();
        check.checkValue(dayOfMonthSpinner, 1, daysInMonth);
    }
    
     public int getDayOfMonth(){
        return Integer.valueOf(dayOfMonthSpinner.getEditor().getText());
    }
    
}
