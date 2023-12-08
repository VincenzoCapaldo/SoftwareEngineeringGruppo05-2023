package actions.ProgramAction;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import actions.ActionController;

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
    private RadioButton programActionRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private Button browseButton;
    private File selectedFile;
    private BooleanProperty flagProgram;
    @FXML
    private HBox programactionBox;
    @FXML
    private HBox hBoxProgram;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagProgram = new SimpleBooleanProperty(true);
        BooleanProperty isWriterActionSelected = programActionRB.selectedProperty();
 
        commandTextArea.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean writerActionSelected = isWriterActionSelected.get();

                // Aggiungi o rimuovi il pulsante e la textArea dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                        if (!writerActionSelected) {
                            vBoxProgram.getChildren().remove(commandTextArea);
                            hBoxProgram.getChildren().remove(browseButton);
                            programactionBox.setPrefHeight(70);
                            commandTextArea.clear(); 
                        } else {
                            hBoxProgram.getChildren().add(browseButton);
                            vBoxProgram.getChildren().add(commandTextArea);
                            programactionBox.setPrefHeight(181);
                        }
                });
                return writerActionSelected;
            },
            isWriterActionSelected
        ));
 
    }    

    @FXML
    private void browseProgram(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a executable program");

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Executable program", "*.bat"));
        
        Stage stage = (Stage) browseButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        // La flagWriter è vera solo quando non è stato selezionato un file
        Platform.runLater(() -> {
            if (selectedFile == null) {
                flagProgram.set(true);
            } else {
                flagProgram.set(false);
            }
        });
    }
    
    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        programActionRB.setToggleGroup(toggleGroup);
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

    
}
