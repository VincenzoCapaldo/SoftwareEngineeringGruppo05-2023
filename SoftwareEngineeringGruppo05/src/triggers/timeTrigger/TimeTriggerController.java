package triggers.timeTrigger;

import triggers.TriggerController;
import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import softwareengineeringgruppo05.CheckValuesClass;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class TimeTriggerController implements Initializable, TriggerController {

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
    @FXML
    private CheckBox repetitionCheckBox;
    @FXML
    private HBox hboxRepetition;
    @FXML
    private Spinner<Integer> repetitionTimeSpinnerDays;
    @FXML
    private Spinner<Integer> repetitionTimeSpinnerHours;
    @FXML
    private Spinner<Integer> repetitionTimeSpinnerMinutes;
    @FXML
    private VBox vboxRepetition;
    
    private BooleanProperty flagTimeTrigger;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagTimeTrigger = new SimpleBooleanProperty(true);
        BooleanProperty isTriggerTimeSelected = timeTriggerRB.selectedProperty();
        flagTimeTrigger.bind(Bindings.when(isTriggerTimeSelected).then(false).otherwise(true));
        boxSpinnerTriggerTime.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean timeTriggerSelected = isTriggerTimeSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!timeTriggerSelected) {
                        vboxSpinner.getChildren().removeAll(boxSpinnerTriggerTime, vboxRepetition);
                        timeTriggerBox.setPrefHeight(75);
                    } else {
                        repetitionCheckBox.setSelected(false);
                        createSpinners();
                        vboxSpinner.getChildren().addAll(boxSpinnerTriggerTime, vboxRepetition);
                        vboxRepetition.getChildren().removeAll(hboxRepetition);
                        timeTriggerBox.setPrefHeight(180);
                    }
                });
                return timeTriggerSelected;
            },
            isTriggerTimeSelected
        ));
    }

    @Override
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
        CheckValuesClass.checkValues(timeSpinnerHours, 0, 23);
    }

    @FXML
    private void onChangedMinute(KeyEvent event) {
        CheckValuesClass.checkValues(timeSpinnerMinutes, 0, 59);
    }
    
    private void createSpinners() {
       // Rimuovi gli Spinner esistenti, se presenti
        boxSpinnerTriggerTime.getChildren().clear();

        // Imposta l'orario corrente
        LocalTime currentTime = LocalTime.now();

        // Aggiungi un minuto
        LocalTime defaultTime = currentTime.plusMinutes(1);

        // Creazione di nuove SpinnerValueFactory con valori massimo e minimo
        SpinnerValueFactory<Integer> valueFactoryHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, defaultTime.getHour());
        SpinnerValueFactory<Integer> valueFactoryMinutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, defaultTime.getMinute());

        // Impostazione della SpinnerValueFactory per gli Spinner
        timeSpinnerHours.setValueFactory(valueFactoryHours);
        timeSpinnerMinutes.setValueFactory(valueFactoryMinutes);

        boxSpinnerTriggerTime.getChildren().addAll(timeSpinnerHours, timeSpinnerMinutes);
        
    }

    @FXML
    private void repetitionCheckBoxOnAction(ActionEvent event) {
        if(repetitionCheckBox.isSelected()){
            hboxRepetition.getChildren().clear();
            SpinnerValueFactory<Integer> valueFactoryDays = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 366, 0);
            SpinnerValueFactory<Integer> valueFactoryHours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
            SpinnerValueFactory<Integer> valueFactoryMinutes = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);

            // Impostazione della SpinnerValueFactory per gli Spinner
            repetitionTimeSpinnerDays.setValueFactory(valueFactoryDays);
            repetitionTimeSpinnerHours.setValueFactory(valueFactoryHours);
            repetitionTimeSpinnerMinutes.setValueFactory(valueFactoryMinutes);
            vboxRepetition.getChildren().add(hboxRepetition);
            hboxRepetition.getChildren().addAll(repetitionTimeSpinnerDays, repetitionTimeSpinnerHours, repetitionTimeSpinnerMinutes);
            timeTriggerBox.setPrefHeight(230);
        }else{
            hboxRepetition.getChildren().clear();
            vboxRepetition.getChildren().remove(hboxRepetition);
            timeTriggerBox.setPrefHeight(180);
        }
    }
    
    public Boolean repetitionIsSelected(){
        return repetitionCheckBox.isSelected();
    }
    
    public Duration getSleeping(){
        Duration duration = null;
        if(repetitionCheckBox.isSelected()){
            duration = Duration.ofDays(Integer.valueOf(repetitionTimeSpinnerDays.getEditor().getText()))
            .plusHours(Integer.valueOf(repetitionTimeSpinnerHours.getEditor().getText()))
            .plusMinutes(Integer.valueOf(repetitionTimeSpinnerMinutes.getEditor().getText()));
        }
        return duration;
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagTimeTrigger;
    }

    @FXML
    private void onChangedDayRepetition(KeyEvent event) {
        CheckValuesClass.checkValues(repetitionTimeSpinnerDays, 0, 366);
    }

    @FXML
    private void onChangedHourRepetition(KeyEvent event) {
        CheckValuesClass.checkValues(repetitionTimeSpinnerHours, 0, 23);
    }

    @FXML
    private void onChangedMinuteRepetition(KeyEvent event) {
        CheckValuesClass.checkValues(repetitionTimeSpinnerMinutes, 0, 59);
    }
    
}
