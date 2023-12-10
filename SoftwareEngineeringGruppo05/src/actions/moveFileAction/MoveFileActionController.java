package actions.moveFileAction;

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
public class MoveFileActionController implements Initializable, ActionController {

    @FXML
    private HBox moveFileActionBox;
    @FXML
    private VBox vboxMoveFile;
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
    @FXML
    private CheckBox moveFileCB;
        
    private File selectedFile;
    private File selectedDirectory;
    private BooleanProperty flagMoveFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagMoveFile = new SimpleBooleanProperty(false);
        hboxMoveFileOptions.getChildren().removeAll(moveLabel, fileButton, toLabel, directoryButton);
        moveFileActionBox.setPrefHeight(70);
        vboxMoveFile.setPrefHeight(70);
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

    @Override
    public CheckBox getCB() {
        return moveFileCB;
    }

    @FXML
    private void moveFileActionSelected(ActionEvent event) {
        if(moveFileCB.isSelected()){
            hboxMoveFileOptions.getChildren().addAll(moveLabel, fileButton, toLabel, directoryButton);
            moveFileActionBox.setPrefHeight(118);
            flagMoveFile.set(true);
        }else{
            hboxMoveFileOptions.getChildren().removeAll(moveLabel, fileButton, toLabel, directoryButton);
            moveFileActionBox.setPrefHeight(70);
            flagMoveFile.set(false);
        }
    }
    
}
