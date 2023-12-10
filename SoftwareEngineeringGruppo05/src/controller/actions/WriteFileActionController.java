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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class WriteFileActionController implements Initializable, ActionController {

    @FXML
    private HBox writerActionBox;
    private RadioButton writeActionRB;
    @FXML
    private Button browseButton;
    @FXML
    private TextArea messageTextArea;
    private File selectedFile;
    private BooleanProperty flagWriter;
    @FXML
    private VBox vboxWriter;
    @FXML
    private HBox hboxWriter;
    @FXML
    private CheckBox writeFileCB;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagWriter = new SimpleBooleanProperty(true);
        vboxWriter.getChildren().remove(messageTextArea);
        hboxWriter.getChildren().remove(browseButton);
        writerActionBox.setPrefHeight(70);
        messageTextArea.clear();
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
             flagWriter.bind(Bindings.createBooleanBinding(
                () -> selectedFile != null && !(messageTextArea.getText().isEmpty()),
                messageTextArea.textProperty()
            ));
        });
    }
    
    public String getTextArea(){
        return messageTextArea.getText();
    }
    
    public String getFilePath(){
        return this.selectedFile.getAbsolutePath();
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagWriter;
    }

    @Override
    public CheckBox getCB() {
        return writeFileCB;
    }

    @FXML
    private void writeFileActionSelected(ActionEvent event) {
        if(writeFileCB.isSelected()){
            hboxWriter.getChildren().add(browseButton);
            vboxWriter.getChildren().add(messageTextArea);
            writerActionBox.setPrefHeight(181);
            flagWriter.set(false);
        }else{
            vboxWriter.getChildren().remove(messageTextArea);
            hboxWriter.getChildren().remove(browseButton);
            writerActionBox.setPrefHeight(70);
            messageTextArea.clear(); 
            flagWriter.set(true);
        }
    }

}
