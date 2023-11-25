/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

import rules.ThreadRule;
import actions.Action;
import actions.AudioAction.AudioAction;
import actions.MessageAction.MessageAction;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import rules.Rule;
import rules.RuleManager;
import triggers.TimeTrigger.TimeTrigger;
import triggers.Trigger;

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
    
    private ToggleGroup actionToggleGroup;
    private ToggleGroup triggerToggleGroup;
    private AudioActionController audioActionController;
    private MessageActionController messageActionController;
    private TimeTriggerController timeTriggerController;
    private RuleManager ruleManager;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        ruleManager = RuleManager.getInstance();
        
        loadAllRules();
    }    

    @FXML
    private void goToWindowThree(ActionEvent event) throws IOException {
        window3.visibleProperty().set(true);
        window1.visibleProperty().set(false);
        
        loadAllActionsCards();
        loadAllTriggersCards();
        
        BooleanBinding bb = nameRuleTextField.textProperty().isEmpty().or(actionToggleGroup.selectedToggleProperty().isNull()).or(triggerToggleGroup.selectedToggleProperty().isNull()); 
        createRuleButton.disableProperty().bind(bb);
        
    }

    //back to homepage after create a rule
    @FXML
    private void goToWindowOne(ActionEvent event) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        
        Action action = null; 
        Trigger trigger = null;
        
        RadioButton selectedAction = (RadioButton) actionToggleGroup.getSelectedToggle();
        RadioButton selectedTrigger = (RadioButton) triggerToggleGroup.getSelectedToggle();
             
        if("Audio".equals(selectedAction.getText())){
            action = new AudioAction(audioActionController.getFilePath());
        }else if("Message".equals(selectedAction.getText())){         
            action = new MessageAction(messageActionController.getTextArea());
        }
        
        if("Time".equals(selectedTrigger.getText())){           
            trigger = new TimeTrigger(timeTriggerController.getHours(), timeTriggerController.getMinutes());
        }
        
        Rule rule = new Rule(nameRuleTextField.getText(), action, trigger, true);
        ruleManager.addRule(rule);
        
        Thread t = new Thread(new ThreadRule(rule));
        t.start();
        
        loadAllRules();
    }
    
    //load all rule cards 
    private void loadAllRules(){
        scrollRules.getChildren().clear();
        
        List<Rule> rules = ruleManager.getRules();

        for (Rule rule : rules) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/rules/RuleCard.fxml")); 
                HBox ruleBox = fxmlLoader.load();

                RuleCardController ruleCardController = fxmlLoader.getController();
                ruleCardController.setData(rule.getName());

                scrollRules.getChildren().add(ruleBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    //load all actions in window3, so the user can visualize all action cards
    private void loadAllActionsCards() throws IOException {
        scrollAllActions.getChildren().clear();
        
//create a togglegroup, so the user can select only one card.
        actionToggleGroup = new ToggleGroup();

        //load AudioAction card
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/actions/AudioAction/AudioAction.fxml"));
        HBox soundActionBox = fxmlLoader.load();
        audioActionController = fxmlLoader.getController();
        audioActionController.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(soundActionBox);
        
        //load MessageAction card
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("/actions/MessageAction/MessageAction.fxml"));
        HBox messageActionBox = fxmlLoader2.load();
        messageActionController = fxmlLoader2.getController();
        messageActionController.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(messageActionBox);
    }
    
    //load all triggers in winwod3. The user can visualize all triggers.
    private void loadAllTriggersCards() throws IOException{
        scrollAllTriggers.getChildren().clear();
        
        triggerToggleGroup = new ToggleGroup();
        
        //load timeTrigger card
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/triggers/TimeTrigger/TimeTrigger.fxml"));
        HBox timeTriggerBox = fxmlLoader.load();
        timeTriggerController = fxmlLoader.getController();
        timeTriggerController.setToggleGroup(triggerToggleGroup);
        scrollAllTriggers.getChildren().add(timeTriggerBox);
    }
    
}