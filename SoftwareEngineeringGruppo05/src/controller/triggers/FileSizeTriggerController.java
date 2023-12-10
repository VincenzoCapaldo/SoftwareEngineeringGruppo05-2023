package controller.triggers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import softwareengineeringgruppo05.CheckValuesClass;
import model.triggers.FileSizeUnit;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class FileSizeTriggerController implements Initializable, TriggerController {

    @FXML
    private HBox fileSizeTriggerBox;
    private VBox vboxFileTrigger;
    private RadioButton fileTriggerRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private HBox hbox1;
    @FXML
    private Label fileLabel;
    @FXML
    private Button browseButton;
    @FXML
    private HBox hbox2;
    @FXML
    private Label sizeLabel;
    private TextField fileNameTextField;
    @FXML
    private ComboBox<FileSizeUnit> sizeCombo;
    @FXML
    private RadioButton fileSizeTriggerRB;
    @FXML
    private VBox vboxFileSizeTrigger;
    
    
    private BooleanProperty flagFileSizeTrigger; 
    private File selectedFile;
    @FXML
    private Spinner<Integer> fileSizeSpinner;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createCombo();
        flagFileSizeTrigger = new SimpleBooleanProperty(true);
        BooleanProperty isFileSizeTriggerSelected = fileSizeTriggerRB.selectedProperty();
        hbox1.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean fileSizeTriggerSelected = isFileSizeTriggerSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!fileSizeTriggerSelected) {
                        vboxFileSizeTrigger.getChildren().removeAll(hbox1, hbox2);
                        fileSizeTriggerBox.setPrefHeight(75);
                        flagFileSizeTrigger.setValue(true);
                    } else {
                        vboxFileSizeTrigger.getChildren().addAll(hbox1, hbox2);
                        SpinnerValueFactory<Integer> valueFactorySize = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000, 0);
                        fileSizeSpinner.setValueFactory(valueFactorySize);  
                        sizeCombo.setValue(FileSizeUnit.B);
                        fileSizeTriggerBox.setPrefHeight(163); 
                    }
                });
                return fileSizeTriggerSelected;
            },
            isFileSizeTriggerSelected
        ));
    }    

    @FXML
    private void browseButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        
        Stage stage = (Stage) browseButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        Platform.runLater(() -> {
             flagFileSizeTrigger.setValue(selectedFile == null);
         });
        
        
    }
    
    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        fileSizeTriggerRB.setToggleGroup(toggleGroup);
    }
    
    public long getSize(){
        int fileSize = fileSizeSpinner.getValue();
        FileSizeUnit selectedUnit = sizeCombo.getValue();
        
        // Converti la dimensione in byte utilizzando il moltiplicatore associato all'unità selezionata
        long sizeInBytes = fileSize * selectedUnit.getMultiplier();
        
        return sizeInBytes; 
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }

    public BooleanProperty getFlag() {
        return flagFileSizeTrigger;
    }
    
    private void createCombo(){
        sizeCombo.getItems().addAll(FileSizeUnit.values());
        sizeCombo.setValue(FileSizeUnit.B);
    }

    @FXML
    private void onSizeChanged(KeyEvent event) {
        CheckValuesClass.checkValues(fileSizeSpinner, 0, 100000);
    }

}