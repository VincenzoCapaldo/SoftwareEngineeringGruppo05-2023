package actions.MessageAction;

import actions.Action;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import softwareengineeringgruppo05.PopUpController;

/**
 *
 * @author maria
 */
public class MessageAction implements Action{
    private String message;

    public MessageAction(String message) {
        this.message = message;
    }
    
    @Override
    public void execute(){
        
            
        Platform.runLater(() -> {
            try{
                FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("PopUp.fxml"));
                Parent root1= (Parent) fxmlLoader.load();

                // Getting the controller after loading the FXML file
                PopUpController controller = fxmlLoader.getController();

                // Setting the new text on the Label using the method defined in the controller
                controller.setLabelText(message);

                // Creating the new stage
                Stage stage = new Stage();
                stage.setTitle("New message"); // Setting the title of the window
                stage.setScene(new Scene(root1));

                // Showing the new window
                stage.show();

            }catch(IOException e){
                System.out.println("Cant load new window");
            }            
        });
    }
             
    
    @Override
    public void add(Action a){
         throw new UnsupportedOperationException("Cannot add an action to MessageAction.");
    }
    
    @Override
    public void remove(Action a){
        throw new UnsupportedOperationException("Cannot remove an action from MessageAction.");
    }
    
}
