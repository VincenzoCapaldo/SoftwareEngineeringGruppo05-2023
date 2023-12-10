package actions.messageAction;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maria
 */
public class PopUpController implements Initializable {

    @FXML
    private Label messageString;
    @FXML
    private AnchorPane messageWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Sets the text of the messageString Label.
     *
     * @param newText The new text to be set in the Label.
     */
     public void setLabelText(String newText) {
        messageString.setText(newText);
    }

      /**
     * Handles the action when the close button is clicked. Closes the PopUp window.
     *
     * @param event The ActionEvent triggered by clicking the close button.
     */
    @FXML
    private void closePopUp(ActionEvent event) {
        // Get the current Stage and close the PopUp window
        Stage stage = (Stage) messageWindow.getScene().getWindow();
        stage.close();
    }
    
}
