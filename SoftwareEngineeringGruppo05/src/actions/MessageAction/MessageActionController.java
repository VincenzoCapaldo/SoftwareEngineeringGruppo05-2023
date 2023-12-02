package actions.MessageAction;

import actions.ControllerAction;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class MessageActionController implements Initializable, ControllerAction {

    @FXML
    private HBox messageActionBox;
    @FXML
    private RadioButton messageActionRB;
    @FXML
    private ToggleGroup selectActionTG;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private VBox vboxMessage;
    
    private BooleanProperty flagMessage;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagMessage = new SimpleBooleanProperty(true);
        BooleanProperty isMessageActionSelected = messageActionRB.selectedProperty();
        
         flagMessage.bind(Bindings.createBooleanBinding(
                () -> messageTextArea.getText().isEmpty(),
                messageTextArea.textProperty()
        ));

        messageTextArea.visibleProperty().bind(Bindings.createBooleanBinding(
            () -> {
                boolean messageActionSelected = isMessageActionSelected.get();

                // Aggiungi o rimuovi il pulsante dal layout in base allo stato del RadioButton: azione eseguita dal thread principale
                Platform.runLater(() -> {
                    if (!messageActionSelected) {
                        vboxMessage.getChildren().remove(messageTextArea);
                        messageActionBox.setPrefHeight(70);
                        messageTextArea.clear();
                    } else{ 
                        vboxMessage.getChildren().add(messageTextArea);
                        messageActionBox.setPrefHeight(168);                      
                    }
                });

                return messageActionSelected;
            },
            isMessageActionSelected
        ));
    }    
    
    @Override
    public void setToggleGroup(ToggleGroup toggleGroup) {
        messageActionRB.setToggleGroup(toggleGroup);
    }
    
    public String getTextArea(){
        return messageTextArea.getText();
    }

    @Override
    public BooleanProperty getFlag() {
        return flagMessage;
    }

}