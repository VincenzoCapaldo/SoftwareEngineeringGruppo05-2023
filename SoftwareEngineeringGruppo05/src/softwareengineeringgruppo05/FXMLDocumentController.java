package softwareengineeringgruppo05;

import actions.Action;
import actions.AudioAction.AudioAction;
import actions.CopyFileAction.CopyFileAction;
import actions.MessageAction.MessageAction;
import actions.MoveFileAction.MoveFileAction;
import actions.ProgramAction.ProgramAction;
import actions.WriterAction.WriterAction;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private WriterActionController writerActionController;
    private TimeTriggerController timeTriggerController;
    private CopyFileActionController copyFileActionController;
    private MoveFileActionController moveFileActionController;
    private ProgramActionController programActionController;
    private RuleManager ruleManager;
    private RepetitionController repetitionController;
    private HBox repetitionBox;
    private HBox soundActionBox;
    
    @FXML
    private CheckBox repetitionCheck;
    @FXML
    private VBox boxSleeping;
    @FXML
    private Button goBackButton;
    @FXML
    private HBox HSleeping;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        
        ruleManager = RuleManager.getInstance();
        ruleManager.loadRules();
        boxSleeping.getChildren().remove(HSleeping);
        
        loadAllRules();
    }    

    @FXML
    private void goToWindowThree(ActionEvent event) throws IOException {
        nameRuleTextField.clear();
        repetitionCheck.setSelected(false);
        boxSleeping.getChildren().remove(HSleeping);

        window3.visibleProperty().set(true);
        window1.visibleProperty().set(false);
        
        loadAllActionsCards();
        loadAllTriggersCards();
        
        BooleanBinding bb1 = nameRuleTextField.textProperty().isEmpty();
        BooleanBinding bb2 = actionToggleGroup.selectedToggleProperty().isNull().or(triggerToggleGroup.selectedToggleProperty().isNull());

        BooleanProperty isAudioActionNotCompleted = audioActionController.getFlagAudio();
        BooleanProperty isMessageActionNotCompleted = messageActionController.getFlagMessage();
        BooleanProperty isWriterActionNotCompleted = writerActionController.getFlagWriter();
        BooleanProperty isCopyFileActionNotCompleted = copyFileActionController.getFlagCopyFile();
        BooleanProperty isMoveFileActionNotCompleted = moveFileActionController.getFlagMoveFile();
        
        BooleanProperty isProgramActionNotCompleted = programActionController.getFlagProgram();
        
        BooleanBinding bb3 = isAudioActionNotCompleted.and(isMessageActionNotCompleted).and(isWriterActionNotCompleted)
                .and(isCopyFileActionNotCompleted).and(isMoveFileActionNotCompleted).and(isProgramActionNotCompleted);
        
        BooleanBinding bb = bb1.or(bb2).or(bb3);
        
        createRuleButton.disableProperty().bind(bb);
        
    }

    //back to homepage after create a rule
    @FXML
    private void goToWindowOne(ActionEvent event) throws IOException {
        
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
        }else if("Write".equals(selectedAction.getText())){
            action = new WriterAction(writerActionController.getFilePath(), writerActionController.getTextArea());
        }else if("CopyFile".equals(selectedAction.getText())){
            action = new CopyFileAction(copyFileActionController.getFilePath(), copyFileActionController.getDirectoryPath());
        }else if("MoveFile".equals(selectedAction.getText())){
            action = new MoveFileAction(moveFileActionController.getFilePath(), moveFileActionController.getDirectoryPath());
        }else if("Program".equals(selectedAction.getText())){
            action = new ProgramAction(programActionController.getFilePath(), programActionController.getTextArea());
        }
        
        if("Time".equals(selectedTrigger.getText())){           
            trigger = new TimeTrigger(timeTriggerController.getHours(), timeTriggerController.getMinutes());
        }
        
        Duration duration = null;
        
        boolean repetition = repetitionCheck.isSelected();
        
        if(repetition){
            duration = Duration.ofDays(repetitionController.getDaysSleeping())
            .plusHours(repetitionController.getHoursSleeping())
            .plusMinutes(repetitionController.getMinutesSleeping());
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
                ruleCardController.setRule(rule);
                
                rule.addObserver(ruleCardController);

                scrollRules.getChildren().add(ruleBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    //load all actions in window3, so the user can visualize all action cards
    private void loadAllActionsCards() throws IOException {
        scrollAllActions.getChildren().clear();
        
        //crea un togglegroup da passare alle card. In questo modo l'utente può selezionare un'unica azione
        actionToggleGroup = new ToggleGroup();

        //carica AudioAction card
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/actions/AudioAction/AudioAction.fxml"));
        soundActionBox = fxmlLoader.load();
        audioActionController = fxmlLoader.getController();
        audioActionController.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(soundActionBox);
        
        //carica MessageAction card
        FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("/actions/MessageAction/MessageAction.fxml"));
        HBox messageActionBox = fxmlLoader2.load();
        messageActionController = fxmlLoader2.getController();
        messageActionController.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(messageActionBox);
        
        //carica WriteAction card
        FXMLLoader fxmlLoader3 = new FXMLLoader();
        fxmlLoader3.setLocation(getClass().getResource("/actions/WriterAction/WriterAction.fxml"));
        HBox writerActionBox = fxmlLoader3.load();
        writerActionController = fxmlLoader3.getController();
        writerActionController.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(writerActionBox);
        
        //carica CopyFileAction card
        FXMLLoader fxmlLoader4 = new FXMLLoader();
        fxmlLoader4.setLocation(getClass().getResource("/actions/CopyFileAction/CopyFileAction.fxml"));
        HBox copyFileActionBox = fxmlLoader4.load();
        copyFileActionController = fxmlLoader4.getController();
        copyFileActionController.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(copyFileActionBox);    
        
        //carica MoveFileAction card
        FXMLLoader fxmlLoader5 = new FXMLLoader();
        fxmlLoader5.setLocation(getClass().getResource("/actions/MoveFileAction/MoveFileAction.fxml"));
        HBox moveFileActionBox = fxmlLoader5.load();
        moveFileActionController = fxmlLoader5.getController();
        moveFileActionController.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(moveFileActionBox); 
        
        
        //carica ProgramAction card
        FXMLLoader fxmlLoader7 = new FXMLLoader();
        fxmlLoader7.setLocation(getClass().getResource("/actions/ProgramAction/ProgramAction.fxml"));
        HBox programActionBox = fxmlLoader7.load();
        programActionController = fxmlLoader7.getController();
        programActionController.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(programActionBox); 
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
    private void repetitionIsChecked(ActionEvent event) throws IOException {
        boolean isChecked = repetitionCheck.isSelected();
        
        if (isChecked) {
            boxSleeping.getChildren().add(HSleeping);
            HSleeping.getChildren().clear(); // Rimuovi tutti i figli dal contenitore
            // Se la checkbox è selezionata, carica l'HBox e aggiungilo al contenitore
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Repetition.fxml"));
            repetitionBox = loader.load();
            repetitionController = loader.getController();
            HSleeping.getChildren().add(repetitionBox);
        }else{          
            boxSleeping.getChildren().remove(HSleeping);
        }
    }
    
    @FXML
    private void goToHome(ActionEvent event) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        loadAllRules();
    }
    
}