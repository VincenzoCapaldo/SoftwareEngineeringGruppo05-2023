package actions.CopyFileAction;

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
import actions.ActionController;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class CopyFileActionController implements Initializable, ActionController {

    @FXML
    private HBox copyFileActionBox;
    @FXML
    private VBox vboxCopyFile;
    @FXML
    private RadioButton copyFileActionRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private Button fileButton;
    @FXML
    private Button directoryButton;
    
    private File selectedFile;
    private File selectedDirectory;
    
    private BooleanProperty flagCopyFile;
    @FXML
    private Label copyLabel;
    @FXML
    private Label toLabel;
    @FXML
    private HBox hboxCopyFileOptions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagCopyFile = new SimpleBooleanProperty(true);
        BooleanProperty isCopyFileActionSelected = copyFileActionRB.selectedProperty();
        
        hboxCopyFileOptions.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean CopyFileSelected = isCopyFileActionSelected.get();

                // Aggiungi o rimuovi elementi dal layout in base allo stato del RadioButton.
                Platform.runLater(() -> {
                        if (!CopyFileSelected) {
                            hboxCopyFileOptions.getChildren().removeAll(copyLabel, fileButton, toLabel, directoryButton);
                            hboxCopyFileOptions.setPrefHeight(70);
                            copyFileActionBox.setPrefHeight(80);
                            vboxCopyFile.setPrefHeight(70);
                            flagCopyFile.set(true);    
                        } else {
                            hboxCopyFileOptions.getChildren().addAll(copyLabel, fileButton, toLabel, directoryButton);
                            copyFileActionBox.setPrefHeight(118);
                            hboxCopyFileOptions.setPrefHeight(118);
                            vboxCopyFile.setPrefHeight(118);
                        }
                });
                return CopyFileSelected;
            },
            isCopyFileActionSelected
        ));
         
    }    

    @FXML
    private void fileButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file");
        
        Stage stage = (Stage) fileButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        Platform.runLater(() -> {
            if (!(selectedFile == null || selectedDirectory == null)) {
                flagCopyFile.set(false);
            } else {
                flagCopyFile.set(true);
            }
        });
    }

    @FXML
    private void directoryButtonAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");

        Stage stage = (Stage) directoryButton.getScene().getWindow();
        selectedDirectory = directoryChooser.showDialog(stage);
        
        Platform.runLater(() -> {
            if (!(selectedFile == null || selectedDirectory == null)) {
                flagCopyFile.set(false);
            } else {
                flagCopyFile.set(true);
            }
        });
    }
    
    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        copyFileActionRB.setToggleGroup(toggleGroup);
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

}
