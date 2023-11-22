/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

import actions.MessageActionPackage.MessageAction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane window1;
    @FXML
    private Button rulesButton;
    @FXML
    private VBox scrollRules;
    @FXML
    private Button newRule;
    @FXML
    private AnchorPane window3;
    @FXML
    private VBox scrollAllActions;
    @FXML
    private VBox scrollAllTriggers;
    @FXML
    private TextField nameRuleTextField;
    @FXML
    private Button createRuleButton;
    @FXML 
    private ToggleGroup toggleGroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
    }    

    @FXML
    private void goToWindowThree(ActionEvent event) throws IOException {
        window3.visibleProperty().set(true);
        window1.visibleProperty().set(false);
        
        loadAllActionsCards();
        loadAllTriggersCards();
        
        
    }

    @FXML
    private void goToWindowOne(ActionEvent event) throws IOException {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
    }
    
    //load all actions in window3, so the user can visualize all action cards
    private void loadAllActionsCards() throws IOException {
        scrollAllActions.getChildren().clear();
        //create a togglegroup, so the user can select only one card.
        ToggleGroup toggleGroup = new ToggleGroup();

        //load SoundAction card
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/actions/SoundActionPackage/SoundAction.fxml"));
        HBox soundActionBox = fxmlLoader.load();
        SoundActionController soundActionController = fxmlLoader.getController();
        soundActionController.setToggleGroup(toggleGroup);
        scrollAllActions.getChildren().add(soundActionBox);
        
        //load MessageAction card
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("/actions/MessageActionPackage/MessageAction.fxml"));
        HBox messageActionBox = fxmlLoader2.load();
        MessageActionController messageActionController = fxmlLoader2.getController();
        messageActionController.setToggleGroup(toggleGroup);
        scrollAllActions.getChildren().add(messageActionBox);
    }
    
    private void loadAllTriggersCards(){
        
    }
    
    
}

    