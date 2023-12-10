package actions.audioAction;

import actions.ActionController;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class AudioActionController implements Initializable, ActionController {

    @FXML
    private HBox soundActionBox;
    private RadioButton soundActionRB;
    @FXML
    private Button browseButton;
    
    private File selectedFile;
    private BooleanProperty flagAudio;

    @FXML
    private CheckBox audioCB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagAudio = new SimpleBooleanProperty(false);
        soundActionBox.getChildren().remove(browseButton);
        soundActionBox.setPrefHeight(65);
    }   

    @FXML
    private void browseSoundFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an audio file");

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Audio File", "*.wav"));
        
        Stage stage = (Stage) browseButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        // La flagAudio è vera solo quando è stato selezionato un file
        Platform.runLater(() -> {
             flagAudio.setValue(selectedFile == null);
         });
         
    }
    
    @Override
    public CheckBox getCB(){
        return audioCB;
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagAudio;
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }

    @FXML
    private void audioActionSelected(ActionEvent event) {
        if(audioCB.isSelected()){
            soundActionBox.getChildren().add(browseButton);
            soundActionBox.setPrefHeight(80);
            flagAudio.set(true); 
        }else{
            soundActionBox.getChildren().remove(browseButton);
            soundActionBox.setPrefHeight(65);
            flagAudio.set(false);    
        }
    }

}