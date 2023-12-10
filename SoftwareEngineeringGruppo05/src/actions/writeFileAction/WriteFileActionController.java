package actions.writeFileAction;

import actions.ActionController;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
 * @author enzo0
 */
public class WriteFileActionController implements Initializable, ActionController {

    @FXML
    private HBox writerActionBox;
    @FXML
    private Button browseButton;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private VBox vboxWriter;
    @FXML
    private HBox hboxWriter;
    @FXML
    private CheckBox writeFileCB;

    private File selectedFile;
    private BooleanProperty flagWriter;
    private SimpleBooleanProperty selectedFilePresent;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagWriter = new SimpleBooleanProperty(false);
        selectedFilePresent = new SimpleBooleanProperty(false);
        
        vboxWriter.getChildren().remove(messageTextArea);
        hboxWriter.getChildren().remove(browseButton);
        writerActionBox.setPrefHeight(70);
        messageTextArea.clear();
        
        // La flagWriter è vera solo quando non è stato selezionato un file
        flagWriter.bind(Bindings.createBooleanBinding(
            () -> writeFileCB.isSelected() && (selectedFile == null || messageTextArea.getText().isEmpty()),
            writeFileCB.selectedProperty(),
            selectedFilePresent,
            messageTextArea.textProperty()
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
           
        selectedFilePresent.set(selectedFile != null);
  
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
        }else{
            vboxWriter.getChildren().remove(messageTextArea);
            hboxWriter.getChildren().remove(browseButton);
            writerActionBox.setPrefHeight(70);
            messageTextArea.clear(); 
        }
    }

}
