/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class AudioActionController implements Initializable {

    @FXML
    private HBox soundActionBox;
    @FXML
    private RadioButton soundActionRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private Button browseButton;
    
    private java.io.File selectedFile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void browseSoundFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an audio file");

        // Configura il filtro del tipo di file se necessario
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio File", "*.mp3*", "*.wav"));
        
        Stage stage = (Stage) browseButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
    }
    
    public void setToggleGroup(ToggleGroup toggleGroup) {
        soundActionRB.setToggleGroup(toggleGroup);
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }
}
