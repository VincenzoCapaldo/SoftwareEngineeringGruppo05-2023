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
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class WriterActionController implements Initializable {

    @FXML
    private HBox writerActionBox;
    @FXML
    private RadioButton writeActionRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private Button browseButton;
    @FXML
    private TextArea messageTextArea;
    private java.io.File selectedFile;
    private BooleanProperty flagWriter;
    @FXML
    private VBox vboxWriter;
    @FXML
    private HBox hboxWriter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagWriter = new SimpleBooleanProperty(true);
        BooleanProperty isWriterActionSelected = writeActionRB.selectedProperty();
        
       
        // Creazione di un binding personalizzato per la visibilità del pulsante
        flagWriter.bind(Bindings.createBooleanBinding(
                () -> messageTextArea.getText().isEmpty(),
                messageTextArea.textProperty()
        ));
        messageTextArea.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean writerActionSelected = isWriterActionSelected.get();

                // Aggiungi o rimuovi il pulsante e la textArea dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                        if (!writerActionSelected) {
                            vboxWriter.getChildren().remove(messageTextArea);
                            hboxWriter.getChildren().remove(browseButton);
                            writerActionBox.setPrefHeight(70);
                            messageTextArea.clear(); 
                        } else {
                            hboxWriter.getChildren().add(browseButton);
                            vboxWriter.getChildren().add(messageTextArea);
                            writerActionBox.setPrefHeight(181);
                        }
                });
                return writerActionSelected;
            },
            isWriterActionSelected
        ));
        
        
    }    

    @FXML
    private void browseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a text file");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text File", "*.txt"));
        
        Stage stage = (Stage) browseButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        // La flagWriter è vera solo quando non è stato selezionato un file
        Platform.runLater(() -> {
            if (selectedFile == null) {
                flagWriter.set(true);
            } else {
                flagWriter.set(false);
            }
        });
    }
    
    public void setToggleGroup(ToggleGroup toggleGroup) {
        writeActionRB.setToggleGroup(toggleGroup);
    }
    
    public String getTextArea(){
        return messageTextArea.getText();
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }
    
    public BooleanProperty getFlagWriter() {
        return flagWriter;
    }
}
