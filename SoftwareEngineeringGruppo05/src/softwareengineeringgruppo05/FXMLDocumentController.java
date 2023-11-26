/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package softwareengineeringgruppo05;

import actions.Action;
import actions.AudioAction.AudioAction;
import actions.MessageAction.MessageAction;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    @FXML
    private CheckBox repetitionCheck;
    @FXML
    private TextField dayText;
    @FXML
    private TextField hourText;
    @FXML
    private TextField minuteText;
    @FXML
    private VBox boxSleeping;
    @FXML
    private HBox Hsleeping;
    @FXML
    private Button goBackButton;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        ruleManager = RuleManager.getInstance();
        boxSleeping.getChildren().remove(Hsleeping); 
        loadAllRules();
    }    

    @FXML
    private void goToWindowThree(ActionEvent event) throws IOException {
        nameRuleTextField.clear();
        repetitionCheck.setSelected(false);
        dayText.clear();
        hourText.clear();
        minuteText.clear();
        window3.visibleProperty().set(true);
        window1.visibleProperty().set(false);
        
        loadAllActionsCards();
        loadAllTriggersCards();
        
        BooleanBinding bb1 = nameRuleTextField.textProperty().isEmpty();
        BooleanBinding bb2 = actionToggleGroup.selectedToggleProperty().isNull().or(triggerToggleGroup.selectedToggleProperty().isNull());
        BooleanBinding bb3 = repetitionCheck.selectedProperty().and(
                                dayText.textProperty().isEmpty().or(
                                hourText.textProperty().isEmpty()).or(
                                minuteText.textProperty().isEmpty()));
        
        BooleanBinding bb= bb1.or(bb2).or(bb3);
        
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
        
        boolean repetition= repetitionCheck.isSelected();
        Duration duration= Duration.ofDays(0).plusHours(0).plusMinutes(0);
        if(repetition){
            duration = Duration.ofDays(getDaysSleeping()).plusHours(getHoursSleeping()).plusMinutes(getMinutesSleeping());
        }
        
        Rule rule = new Rule(nameRuleTextField.getText(), action, trigger, true, repetition, duration);
        ruleManager.addRule(rule);
                
        loadAllRules();
    }
    
    //load all rule cards 
    private void loadAllRules(){
        scrollRules.getChildren().clear();
        
        Set<Rule> rules = ruleManager.getRules();

        for (Rule rule : rules) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/rules/RuleCard.fxml")); 
                HBox ruleBox = fxmlLoader.load();

                RuleCardController ruleCardController = fxmlLoader.getController();
                ruleCardController.setRuleManager(ruleManager);
                ruleCardController.setRule(rule);
                ruleCardController.setData();

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

    @FXML
    private void repetitionIsChecked(ActionEvent event) {
        boolean isChecked = repetitionCheck.isSelected();
       if(isChecked){
           boxSleeping.getChildren().add(Hsleeping);
       }else
          boxSleeping.getChildren().remove(Hsleeping); 
    }

    @FXML
    private void onDayChanged(KeyEvent event) {
        if(!dayText.getText().matches("\\d+")){
            dayText.clear();
        }
    }

    @FXML
    private void onHourChanged(KeyEvent event) {
        int hours = 0;
        
        if(!hourText.getText().matches("\\d+")){
            hourText.clear();
        }else{
             hours = Integer.parseInt(hourText.getText());
             if (hours < 0 || hours > 23) {
                hourText.clear();
            }
        }
    }

    @FXML
    private void onMinuteChaged(KeyEvent event) {
        int minutes=0;
        
        if(!minuteText.getText().matches("\\d+")){
            minuteText.clear();
        }else{
             minutes = Integer.parseInt(minuteText.getText());
             if (minutes < 0 || minutes > 59) {
                minuteText.clear();
             }
        }
    }
    
    public int getDaysSleeping(){
        return Integer.parseInt(dayText.getText());
    }
        
    public int getHoursSleeping(){
        return Integer.parseInt(hourText.getText());
    }
 
    public int getMinutesSleeping(){
        return Integer.parseInt(minuteText.getText());
    }

    @FXML
    private void goToHome(ActionEvent event) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        loadAllRules();
    }
    
}