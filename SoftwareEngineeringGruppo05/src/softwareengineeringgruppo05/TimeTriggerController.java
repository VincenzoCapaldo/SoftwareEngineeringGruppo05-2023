/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

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
    private Spinner<Integer> timeSpinnerHours;
    @FXML
    private Spinner<Integer> timeSpinnerMinutes;
    @FXML
    private HBox boxSpinnerTriggerTime;
    @FXML
    private VBox vboxSpinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        BooleanProperty isTriggerTimeSelected = timeTriggerRB.selectedProperty();
        boxSpinnerTriggerTime.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean timeTriggerSelected = isTriggerTimeSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!timeTriggerSelected) {
                        timeTriggerBox.getChildren().remove(boxSpinnerTriggerTime);
                        timeTriggerBox.setPrefHeight(5);
                        vboxSpinner.setPrefHeight(5);
                        boxSpinnerTriggerTime.getChildren().clear();
                    } else {
                        // Aggiungi il boxSpinnerTriggerTime
                        timeTriggerBox.getChildren().add(boxSpinnerTriggerTime);
                        timeTriggerBox.setPrefHeight(137);
                        createSpinners();
                    }
                });
                return timeTriggerSelected;
            },
            isTriggerTimeSelected
        ));
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        timeTriggerRB.setToggleGroup(toggleGroup);
    }
    
    public int getHours(){
        return Integer.valueOf(timeSpinnerHours.getEditor().getText());
    }
    
    public int getMinutes(){
        return Integer.valueOf(timeSpinnerMinutes.getEditor().getText());
    }

    @FXML
    private void onChangedHour(KeyEvent event) {
        CheckTimeClass check = new CheckTimeClass();
        check.checkTime(timeSpinnerHours, 0, 23);
    }

    @FXML
    private void onChangedMinute(KeyEvent event) {
        CheckTimeClass check = new CheckTimeClass();
        check.checkTime(timeSpinnerMinutes, 0, 59);
    }
    
    private void createSpinners(){
        // Creazione di una SpinnerValueFactory con valori massimo e minimo
        SpinnerValueFactory<Integer> valueFactoryHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        SpinnerValueFactory<Integer> valueFactoryMinutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);

        // Impostazione della SpinnerValueFactory per lo Spinner
        timeSpinnerHours.setValueFactory(valueFactoryHours);
        timeSpinnerMinutes.setValueFactory(valueFactoryMinutes);
        
        // Aggiungi gli spinner al boxSpinnerTriggerTime
        boxSpinnerTriggerTime.getChildren().addAll(timeSpinnerHours, timeSpinnerMinutes);
        vboxSpinner.getChildren().add(boxSpinnerTriggerTime);

    }
}
