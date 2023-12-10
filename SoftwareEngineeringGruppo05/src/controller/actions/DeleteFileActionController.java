package controller.actions;

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
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class DeleteFileActionController implements Initializable, ActionController {

    @FXML
    private HBox deleteFileActionBox;
    @FXML
    private VBox vboxDeleteFile;
    private RadioButton deleteFileActionRB;
    @FXML
    private HBox hbox1;
    @FXML
    private Label deleteLabel;
    @FXML
    private TextField fileNameTextField;
    @FXML
    private HBox hbox2;
    @FXML
    private Label fromLabel;
    @FXML
    private Button directoryButton;
    
    private File selectedDirectory;
    private BooleanProperty flagDeleteFile;
    @FXML
    private VBox vbox1;
    @FXML
    private CheckBox deleteFileCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*flagDeleteFile = new SimpleBooleanProperty(true);
        
        BooleanProperty isDeleteFileActionSelected = deleteFileActionRB.selectedProperty();
      
        vbox1.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean DeleteFileSelected = isDeleteFileActionSelected.get();

                // Aggiungi o rimuovi elementi dal layout in base allo stato del RadioButton.
                Platform.runLater(() -> {
                        if (!DeleteFileSelected) {
                            hbox1.getChildren().removeAll(deleteLabel, fileNameTextField);
                            hbox2.getChildren().removeAll(fromLabel, directoryButton);
                            vbox1.getChildren().removeAll(hbox1, hbox2);
                            vboxDeleteFile.getChildren().removeAll(vbox1);
                            deleteFileActionBox.setPrefHeight(70);
                            vboxDeleteFile.setPrefHeight(70);
                            fileNameTextField.clear();
                        } else {
                            vboxDeleteFile.getChildren().addAll(vbox1);
                            vbox1.getChildren().addAll(hbox1, hbox2);
                            hbox1.getChildren().addAll(deleteLabel, fileNameTextField);
                            
                            hbox2.getChildren().addAll(fromLabel, directoryButton);
                            deleteFileActionBox.setPrefHeight(163);
                            vboxDeleteFile.setPrefHeight(163);
                        }
                });
                return DeleteFileSelected;
            },
            isDeleteFileActionSelected
        ));*/
    }    

    @FXML
    private void directoryButtonAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");

        Stage stage = (Stage) directoryButton.getScene().getWindow();
        selectedDirectory = directoryChooser.showDialog(stage);
        
        Platform.runLater(() -> {
             flagDeleteFile.bind(Bindings.createBooleanBinding(
                () -> selectedDirectory == null || fileNameTextField.getText().isEmpty(),
                fileNameTextField.textProperty()
            ));
        });
    }
    
    public String getDirectoryPath(){
        return this.selectedDirectory.getAbsolutePath();
    }
    
    public String getFileName(){
        return this.fileNameTextField.getText();
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagDeleteFile;
    }

    @Override
    public CheckBox getCB() {
        return deleteFileCB;
    }

}
