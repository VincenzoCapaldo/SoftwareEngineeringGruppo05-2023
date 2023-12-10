/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.triggers;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import softwareengineeringgruppo05.CheckValueClass;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class ProgramTriggerController implements Initializable, TriggerController {

    @FXML
    private HBox programactionBox;
    @FXML
    private VBox vBoxProgram;
    @FXML
    private HBox hBoxProgram;
    @FXML
    private RadioButton programTriggerRB;
    @FXML
    private ToggleGroup selectTriggerTG;
    @FXML
    private Button browseButton;
    @FXML
    private TextArea commandTextArea;
    @FXML
    private HBox hbox2;
    @FXML
    private Spinner<Integer> exitStatusSpinner;
    
    private File selectedFile;
    private BooleanProperty flagProgramTrigger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createSpinner();
        flagProgramTrigger = new SimpleBooleanProperty(true);
        BooleanProperty isProgramTriggerSelected = programTriggerRB.selectedProperty();
 
        commandTextArea.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean programTriggerSelected = isProgramTriggerSelected.get();

                // Aggiungi o rimuovi il pulsante e la textArea dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                        if (!programTriggerSelected) {
                            vBoxProgram.getChildren().removeAll(commandTextArea, hbox2);
                            hBoxProgram.getChildren().removeAll(browseButton);
                            programactionBox.setPrefHeight(70);
                            commandTextArea.clear();
                            flagProgramTrigger.setValue(true);
                        } else {
                            hBoxProgram.getChildren().addAll(browseButton);
                            vBoxProgram.getChildren().addAll(commandTextArea, hbox2);
                            programactionBox.setPrefHeight(250);
                        }
                });
                return programTriggerSelected;
            },
            isProgramTriggerSelected
        ));
    }    

    @FXML
    private void browseProgram(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a executable program");
        
        Stage stage = (Stage) browseButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        // La flagProgram è vera solo quando non è stato selezionato un file
        Platform.runLater(() -> {
            flagProgramTrigger.setValue(selectedFile == null);
        });
    }

    @FXML
    private void exitStatusChanged(KeyEvent event) {
        CheckValueClass check = new CheckValueClass();
        check.checkValue(exitStatusSpinner, 0, 1000000);
    }
    
    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        programTriggerRB.setToggleGroup(toggleGroup);
    }
    
    public String getTextArea(){
        return commandTextArea.getText();
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }
    
    public int getExitStatus(){
        return Integer.valueOf(exitStatusSpinner.getEditor().getText());
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagProgramTrigger;
    }
    
    private void createSpinner() {
       // Rimuovi gli Spinner esistenti, se presenti
        hbox2.getChildren().remove(exitStatusSpinner);


        // Creazione di nuove SpinnerValueFactory con valori massimo e minimo
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000, 0);
        

        // Impostazione della SpinnerValueFactory per gli Spinner
        exitStatusSpinner.setValueFactory(valueFactory);
        
        hbox2.getChildren().add(exitStatusSpinner);
        
    }
    
}
