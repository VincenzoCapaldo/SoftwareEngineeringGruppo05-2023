/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import controller.Controller;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class MoveFileActionController implements Initializable, Controller {

    @FXML
    private HBox moveFileActionBox;
    @FXML
    private VBox vboxMoveFile;
    @FXML
    private RadioButton moveFileActionRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private HBox hboxMoveFileOptions;
    @FXML
    private Label moveLabel;
    @FXML
    private Button fileButton;
    @FXML
    private Label toLabel;
    @FXML
    private Button directoryButton;
    
    private File selectedFile;
    private File selectedDirectory;
    private BooleanProperty flagMoveFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagMoveFile = new SimpleBooleanProperty(true);
        BooleanProperty isMoveFileActionSelected = moveFileActionRB.selectedProperty();
        
        hboxMoveFileOptions.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean MoveFileSelected = isMoveFileActionSelected.get();

                // Aggiungi o rimuovi elementi dal layout in base allo stato del RadioButton.
                Platform.runLater(() -> {
                        if (!MoveFileSelected) {
                            hboxMoveFileOptions.getChildren().removeAll(moveLabel, fileButton, toLabel, directoryButton);
                            moveFileActionBox.setPrefHeight(70);
                            vboxMoveFile.setPrefHeight(70);
                            flagMoveFile.set(true);    
                        } else {
                            hboxMoveFileOptions.getChildren().addAll(moveLabel, fileButton, toLabel, directoryButton);
                            moveFileActionBox.setPrefHeight(118);
                            hboxMoveFileOptions.setPrefHeight(118);
                            vboxMoveFile.setPrefHeight(118);
                        }
                });
                return MoveFileSelected;
            },
            isMoveFileActionSelected
        ));
    }    

    @FXML
    private void fileButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        
        Stage stage = (Stage) fileButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        Platform.runLater(() -> {
            flagMoveFile.setValue(selectedFile == null || selectedDirectory == null);
        });
    }

    @FXML
    private void directoryButtonAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");

        Stage stage = (Stage) directoryButton.getScene().getWindow();
        selectedDirectory = directoryChooser.showDialog(stage);
        
        Platform.runLater(() -> {
           flagMoveFile.setValue(selectedFile == null || selectedDirectory == null);
        });
    }
    
    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        moveFileActionRB.setToggleGroup(toggleGroup);
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }
    
    public String getDirectoryPath(){
        return this.selectedDirectory.getAbsolutePath();
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagMoveFile;
    }
    
}
