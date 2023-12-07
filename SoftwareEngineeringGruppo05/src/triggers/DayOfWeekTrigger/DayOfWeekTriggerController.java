/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package triggers.DayOfWeekTrigger;

import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import triggers.ControllerTrigger;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class DayOfWeekTriggerController implements Initializable, ControllerTrigger {

    @FXML
    private RadioButton dayOfWeekRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private ComboBox<DayOfWeek> dayOfWeekCombo;
    @FXML
    private HBox dayOfWeekBox;
    @FXML
    private VBox dayOfWeekVbox;
    @FXML
    private CheckBox repetitionCheckBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       createComboDay();
        BooleanProperty isTriggerDayOfWeekSelected = dayOfWeekRB.selectedProperty();
        dayOfWeekCombo.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean dayOfWeekTriggerSelected = isTriggerDayOfWeekSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!dayOfWeekTriggerSelected) {
                        dayOfWeekVbox.getChildren().removeAll(dayOfWeekCombo, repetitionCheckBox);
                        dayOfWeekBox.setPrefHeight(75);
                    } else {
                        repetitionCheckBox.setSelected(false);
                        // Aggiungi il boxSpinnerTriggerTime
                        dayOfWeekVbox.getChildren().addAll(dayOfWeekCombo, repetitionCheckBox);
                        dayOfWeekBox.setPrefHeight(180);
                        dayOfWeekCombo.setValue(DayOfWeek.MONDAY);
                    }
                });
                return dayOfWeekTriggerSelected;
            },
            isTriggerDayOfWeekSelected
        ));
    }
    
    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        dayOfWeekRB.setToggleGroup(toggleGroup);
    }

    private void createComboDay(){
       dayOfWeekCombo.getItems().addAll(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY);
       
       dayOfWeekCombo.setValue(DayOfWeek.MONDAY);
    }
    
    public DayOfWeek getDayOfWeek(){
        return dayOfWeekCombo.getValue();
    }

    public Boolean repetitionIsSelected(){
        return repetitionCheckBox.isSelected();
    }
}
