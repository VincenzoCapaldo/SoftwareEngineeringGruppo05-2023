package actions.messageAction;

import actions.ActionController;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class MessageActionController implements Initializable, ActionController, Observer {

    @FXML
    private HBox messageActionBox;

    @FXML
    private TextArea messageTextArea;
    @FXML
    private VBox vboxMessage;
    
    private BooleanProperty flagMessage;
    @FXML
    private CheckBox messageCB;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flagMessage = new SimpleBooleanProperty(false);
        
        
        flagMessage.bind(Bindings.createBooleanBinding(
                () ->  messageCB.isSelected() && messageTextArea.getText().isEmpty(),
                messageCB.selectedProperty(),
                messageTextArea.textProperty()
        ));
        
        
        vboxMessage.getChildren().remove(messageTextArea);
        messageActionBox.setPrefHeight(70);
        messageTextArea.clear();

    }    
    
    public String getTextArea(){
        return messageTextArea.getText();
    }

    public CheckBox getCB(){
        return messageCB;
    }
    
    @Override
    public BooleanProperty getFlag() {
        return flagMessage;
    }


    @FXML
    private void messageActionSelected(ActionEvent event) {
        if(messageCB.isSelected()){
            vboxMessage.getChildren().add(messageTextArea);
            messageActionBox.setPrefHeight(168);
        }else{
            vboxMessage.getChildren().remove(messageTextArea);
            messageActionBox.setPrefHeight(70);
            messageTextArea.clear();
        }
        
    }
    
    @Override
    public void update(Observable subject, Object arg) {
        MessageAction messageSubject = (MessageAction) subject;

            Platform.runLater(() -> {
                try{
                    FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/actions/messageAction/PopUp.fxml"));
                    Parent root1= (Parent) fxmlLoader.load();

                    // prende il controller dopo aver caricato FXML file
                    PopUpController controller = fxmlLoader.getController();

                    // Modifica il testo della label
                    controller.setLabelText(messageSubject.getMessage());

                    // Crea la finestra
                    Stage stage = new Stage();
                    stage.setTitle("New message"); // impostazione del titolo e dell'icona della finestra
                    stage.getIcons().add(new Image("css/message.png"));
                    stage.setScene(new Scene(root1));

                    // mostra la finestra
                    stage.show();
                    
                }catch(IOException e){
                    System.out.println("Cant load new window");
                }            
            });
  
    }
}