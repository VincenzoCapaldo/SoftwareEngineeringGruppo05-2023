package controller;

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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class FileTriggerController implements Initializable, Controller {

    @FXML
    private HBox fileTriggerBox;
    @FXML
    private VBox vboxFileTrigger;
    @FXML
    private RadioButton fileTriggerRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private HBox hbox1;
    @FXML
    private Label fromLabel;
    @FXML
    private Button directoryButton;
    @FXML
    private HBox hbox2;
    @FXML
    private Label checkLabel;
    @FXML
    private TextField fileNameTextField;
    
    private File selectedDirectory;
    private BooleanProperty flagFileTrigger; 
    private BooleanProperty isFileTriggerSelected;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagFileTrigger = new SimpleBooleanProperty(true);

        isFileTriggerSelected = fileTriggerRB.selectedProperty();
        hbox1.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean fileTriggerSelected = isFileTriggerSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!fileTriggerSelected) {
                        selectedDirectory=null;
                        fileNameTextField.clear();
                        vboxFileTrigger.getChildren().removeAll(hbox1, hbox2);
                        fileTriggerBox.setPrefHeight(75);
                    } else {
                        vboxFileTrigger.getChildren().addAll(hbox1, hbox2);
                        fileTriggerBox.setPrefHeight(163);
                    }
                });
                return fileTriggerSelected;
            },
            isFileTriggerSelected
        ));
    }    

    @FXML
    private void directoryButtonAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");

        Stage stage = (Stage) directoryButton.getScene().getWindow();
        selectedDirectory = directoryChooser.showDialog(stage);
        Platform.runLater(() -> {
             flagFileTrigger.bind(Bindings.createBooleanBinding(
                () -> selectedDirectory == null || fileNameTextField.getText().isEmpty(),
                fileNameTextField.textProperty()
            ));
        });
    }

    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        fileTriggerRB.setToggleGroup(toggleGroup);
    }
    
    public String getDirectoryPath(){
        return this.selectedDirectory.getAbsolutePath();
    }
    
    public String getFileName(){
        return this.fileNameTextField.getText();
    }
    
    public BooleanProperty getFlag() {
        return flagFileTrigger;
    }
}
