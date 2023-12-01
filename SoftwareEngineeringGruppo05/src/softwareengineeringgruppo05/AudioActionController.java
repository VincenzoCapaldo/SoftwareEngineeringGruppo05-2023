/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

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
    private BooleanProperty flagAudio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagAudio = new SimpleBooleanProperty(true);
        BooleanProperty isSoundActionSelected = soundActionRB.selectedProperty();
        
       
        // Creazione di un binding personalizzato per la visibilità del pulsante
        browseButton.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean soundActionSelected = isSoundActionSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                        if (!soundActionSelected) {
                            soundActionBox.getChildren().remove(browseButton);
                            soundActionBox.setPrefHeight(65);
                            flagAudio.set(true);    
                        } else {
                            soundActionBox.getChildren().add(browseButton);
                            soundActionBox.setPrefHeight(80);
                        }
                });
                return soundActionSelected;
            },
            isSoundActionSelected
        ));
    }   

    @FXML
    private void browseSoundFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an audio file");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio File", "*.wav"));
        
        Stage stage = (Stage) browseButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        // La flagAudio è vera solo quando non è stato selezionato un file
        Platform.runLater(() -> {
            if (selectedFile == null) {
                flagAudio.set(true);
            } else {
                flagAudio.set(false);
            }
        });
         
    }
    
    public void setToggleGroup(ToggleGroup toggleGroup) {
        soundActionRB.setToggleGroup(toggleGroup);
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }

    public BooleanProperty getFlagAudio() {
        return flagAudio;
    }

}
