package triggers.dayOfMonthTrigger;

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
public class DayOfMonthTriggerController implements Initializable, TriggerController {

    @FXML
    private HBox dayOfMonthBox;
    @FXML
    private VBox dayOfMonthVbox;
    @FXML
    private RadioButton dayOfMonthRB;
    @FXML
    private Spinner<Integer> dayOfMonthSpinner;
    @FXML
    private CheckBox repetitionCheckBox;
    
    private LocalDate currentDate  = LocalDate.now();
    private BooleanProperty flagDayOfMonthTrigger;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagDayOfMonthTrigger = new SimpleBooleanProperty(true);
        
        BooleanProperty isTriggerDayOfMonthSelected = dayOfMonthRB.selectedProperty();
        flagDayOfMonthTrigger.bind(Bindings.when(isTriggerDayOfMonthSelected).then(false).otherwise(true));
        dayOfMonthSpinner.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean dayOfMonthTriggerSelected = isTriggerDayOfMonthSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!dayOfMonthTriggerSelected) {
                        dayOfMonthVbox.getChildren().removeAll(dayOfMonthSpinner,  repetitionCheckBox);
                        dayOfMonthBox.setPrefHeight(75);
                    } else {
                        repetitionCheckBox.setSelected(false);
                        createSpinner();
                        dayOfMonthVbox.getChildren().addAll(dayOfMonthSpinner,  repetitionCheckBox);
                        dayOfMonthBox.setPrefHeight(180);  
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

        SpinnerValueFactory<Integer> valueFactoryMonth = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, currentDate.getDayOfMonth());
        
        dayOfMonthSpinner.setValueFactory(valueFactoryMonth);
    }

    @FXML
    private void onChangedDayOfMonth(KeyEvent event) {
        CheckValuesClass.checkValues(dayOfMonthSpinner, 1, 31);
    }
    
     public int getDayOfMonth(){
        return Integer.valueOf(dayOfMonthSpinner.getEditor().getText());
    }
     
     public Boolean repetitionIsSelected(){
        return repetitionCheckBox.isSelected();
    }
         
    @Override
    public BooleanProperty getFlag() {
        return flagDayOfMonthTrigger;
    }
    
}
