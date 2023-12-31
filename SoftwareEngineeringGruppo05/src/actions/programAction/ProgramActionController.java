package actions.programAction;

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
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author Luca
 */
public class ProgramActionController implements Initializable, ActionController {

    @FXML
    private VBox vBoxProgram;
    @FXML
    private TextArea commandTextArea;
    @FXML
    private Button browseButton;
    @FXML
    private HBox programactionBox;
    @FXML
    private HBox hBoxProgram;
    @FXML
    private CheckBox programCB;

    private File selectedFile;
    private BooleanProperty flagProgram;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagProgram = new SimpleBooleanProperty(false);
        vBoxProgram.getChildren().remove(commandTextArea);
        hBoxProgram.getChildren().remove(browseButton);
        programactionBox.setPrefHeight(70);
        commandTextArea.clear();
    }    

    @FXML
    private void browseProgram(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a executable program");
        
        Stage stage = (Stage) browseButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        // La flagProgram è vera solo quando è stato selezionato un file
        Platform.runLater(() -> {
            flagProgram.setValue(selectedFile == null);
        });
    }
    
    public String getTextArea(){
        return commandTextArea.getText();
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagProgram;
    }

    @Override
    public CheckBox getCB() {
        return programCB;
    }

    @FXML
    private void programActionSelected(ActionEvent event) {
        if(programCB.isSelected()){
            hBoxProgram.getChildren().add(browseButton);
            vBoxProgram.getChildren().add(commandTextArea);
            programactionBox.setPrefHeight(181);
            flagProgram.setValue(true);
        }else{
            vBoxProgram.getChildren().remove(commandTextArea);
            hBoxProgram.getChildren().remove(browseButton);
            programactionBox.setPrefHeight(70);
            commandTextArea.clear();
            flagProgram.setValue(false);
        }
        
    }
    
}
