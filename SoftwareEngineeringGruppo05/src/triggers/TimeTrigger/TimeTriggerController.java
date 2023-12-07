package triggers.TimeTrigger;

import java.net.URL;
import java.time.LocalTime;
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
public class TimeTriggerController implements Initializable, ControllerTrigger {

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
       // createSpinners();
        BooleanProperty isTriggerTimeSelected = timeTriggerRB.selectedProperty();
        boxSpinnerTriggerTime.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean timeTriggerSelected = isTriggerTimeSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!timeTriggerSelected) {
                        vboxSpinner.getChildren().remove(boxSpinnerTriggerTime);
                        timeTriggerBox.setPrefHeight(75);
                    } else {
                        createSpinners();
                        vboxSpinner.getChildren().add(boxSpinnerTriggerTime);
                        timeTriggerBox.setPrefHeight(137);
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
        CheckValueClass check = new CheckValueClass();
        check.checkValue(timeSpinnerHours, 0, 23);
    }

    @FXML
    private void onChangedMinute(KeyEvent event) {
        CheckValueClass check = new CheckValueClass();
        check.checkValue(timeSpinnerMinutes, 0, 59);
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
}
