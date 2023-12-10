package actions.copyFileAction;

import actions.ActionController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class CopyFileActionController implements Initializable, ActionController {

    @FXML
    private HBox copyFileActionBox;
    @FXML
    private Button fileButton;
    @FXML
    private Button directoryButton;
    @FXML
    private Label copyLabel;
    @FXML
    private Label toLabel;
    @FXML
    private HBox hboxCopyFileOptions;
    @FXML
    private CheckBox copyFileCB;

    private File selectedFile;
    private File selectedDirectory;
    
    private BooleanProperty flagCopyFile;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagCopyFile = new SimpleBooleanProperty(false);
        hboxCopyFileOptions.getChildren().removeAll(copyLabel, fileButton, toLabel, directoryButton);
        copyFileActionBox.setPrefHeight(80);
    }    

    @FXML
    private void fileButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        
        Stage stage = (Stage) fileButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        Platform.runLater(() -> {
             flagCopyFile.setValue(selectedFile == null || selectedDirectory == null);
        });
    }

    @FXML
    private void directoryButtonAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");

        Stage stage = (Stage) directoryButton.getScene().getWindow();
        selectedDirectory = directoryChooser.showDialog(stage);
        
        Platform.runLater(() -> {
            flagCopyFile.setValue(selectedFile == null || selectedDirectory == null);
        });
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }
    
    public String getDirectoryPath(){
        return this.selectedDirectory.getAbsolutePath();
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagCopyFile;
    }

    @Override
    public CheckBox getCB() {
        return copyFileCB;
    }

    @FXML
    private void copyFileActionSelected(ActionEvent event) {
        if(copyFileCB.isSelected()){
            hboxCopyFileOptions.getChildren().addAll(copyLabel, fileButton, toLabel, directoryButton);
            copyFileActionBox.setPrefHeight(120);
            flagCopyFile.set(true); 
        }else{
            hboxCopyFileOptions.getChildren().removeAll(copyLabel, fileButton, toLabel, directoryButton);
            copyFileActionBox.setPrefHeight(80);
            flagCopyFile.set(false); 
        }
    }

}
