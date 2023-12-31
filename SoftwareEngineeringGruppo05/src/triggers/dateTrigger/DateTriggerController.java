package triggers.dateTrigger;

import triggers.TriggerController;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class DateTriggerController implements Initializable, TriggerController {

    @FXML
    private HBox dateTriggerBox;
    @FXML
    private VBox dateTriggerVbox;
    @FXML
    private RadioButton dateTriggerRB;
    @FXML
    private DatePicker datePicker;
    
    private BooleanProperty isTriggerDateSelected = new SimpleBooleanProperty(false);
    private BooleanProperty flagDateTrigger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagDateTrigger= new SimpleBooleanProperty(true);
        isTriggerDateSelected = dateTriggerRB.selectedProperty();
        flagDateTrigger.bind(Bindings.when(isTriggerDateSelected).then(false).otherwise(true));
        datePicker.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean timeTriggerSelected = isTriggerDateSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!timeTriggerSelected) {
                        dateTriggerVbox.getChildren().remove(datePicker);
                        dateTriggerBox.setPrefHeight(75);
                    } else {
                        configureDatePicker();
                        dateTriggerVbox.getChildren().add(datePicker);
                        dateTriggerBox.setPrefHeight(137);
                    }
                });
                return timeTriggerSelected;
            },
            isTriggerDateSelected
        ));
    }    

    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
         dateTriggerRB.setToggleGroup(toggleGroup);
    }
    
    public LocalDate getDate(){
        return datePicker.getValue();
    }
    
    private void configureDatePicker() {
        // Imposta il DayCellFactory per disabilitare le date passate
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });
        // Imposta il DatePicker sulla data corrente
        datePicker.setValue(LocalDate.now());
    }

    @Override
    public BooleanProperty getFlag() {
        return flagDateTrigger;
    }
    
}
