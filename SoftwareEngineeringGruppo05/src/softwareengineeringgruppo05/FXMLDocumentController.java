package softwareengineeringgruppo05;

import rules.RuleCardController;
import triggers.TimeTrigger.TimeTriggerController;
import actions.WriterAction.WriterActionController;
import actions.ProgramAction.ProgramActionController;
import actions.MoveFileAction.MoveFileActionController;
import actions.MessageAction.MessageActionController;
import actions.CopyFileAction.CopyFileActionController;
import actions.AudioAction.AudioActionController;
import actions.Action;
import actions.AudioAction.AudioAction;
import actions.ControllerAction;
import actions.CopyFileAction.CopyFileAction;
import actions.DeleteFileAction.DeleteFileAction;
import actions.DeleteFileAction.DeleteFileActionController;
import actions.MessageAction.MessageAction;
import actions.MoveFileAction.MoveFileAction;
import actions.ProgramAction.ProgramAction;
import actions.WriterAction.WriterAction;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import triggers.ControllerTrigger;
import triggers.DayOfWeekTrigger.DayOfWeekTriggerController;
import triggers.DayOfMonthTrigger.DayOfMonthTriggerController;
import triggers.TimeTrigger.TimeTrigger;
import triggers.Trigger;

/**
 * FXML Controller class
 *
 * @author enzo0
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane window1;//finestra riepilogo regole
    @FXML
    private Button rulesButton;
    @FXML
    private VBox scrollRules;
    @FXML
    private Button newRule;
    @FXML
    private AnchorPane window3;//finestra creazione regola
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
    private CopyFileActionController copyFileActionController;
    private MoveFileActionController moveFileActionController;
    private DeleteFileActionController deleteFileActionController;
    private ProgramActionController programActionController;
    
    private TimeTriggerController timeTriggerController;
    private DayOfWeekTriggerController dayOfWeekTriggerController;
    private DayOfMonthTriggerController dayOfMonthTriggerController;
    
    private RuleManager ruleManager;
    private RepetitionController repetitionController;
    private HBox repetitionBox;
    private HBox soundActionBox;
    private ControllerAction controller;

    
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
        
        ruleManager = RuleManager.getInstance();//istanziamento del RuleManager
        
        ruleManager.loadRules();//carica le regole dal file
    
        loadAllRules();//caricamento grafico delle regole
    }    

    //passaggio da window1 a window3 quando si seleziona createRuleButton 
    @FXML
    private void goToWindowThree(ActionEvent event) throws IOException {
        //pulizia campi 
        nameRuleTextField.clear();
        repetitionCheck.setSelected(false);
        boxSleeping.getChildren().remove(HSleeping);

        window3.visibleProperty().set(true);
        window1.visibleProperty().set(false);
        
        loadAllActionsCards();//caricamento grafico delle azioni
        loadAllTriggersCards();//caricamento grafico dei trigger
        
        //se il nome della regola non è inserito bb1=true
        BooleanBinding bb1 = nameRuleTextField.textProperty().isEmpty();
        
        //se non è selezionata almeno un'azione o almeno un trigger bb2=true
        BooleanBinding bb2 = actionToggleGroup.selectedToggleProperty().isNull().or(triggerToggleGroup.selectedToggleProperty().isNull());

        //se è selezionata xxxAction ma i campi previsti non sono compilati isxxxActionNotCompleted=true
        BooleanProperty isAudioActionNotCompleted = audioActionController.getFlag();
        BooleanProperty isMessageActionNotCompleted = messageActionController.getFlag();
        BooleanProperty isWriterActionNotCompleted = writerActionController.getFlag();
        BooleanProperty isCopyFileActionNotCompleted = copyFileActionController.getFlag();
        BooleanProperty isMoveFileActionNotCompleted = moveFileActionController.getFlag();
        BooleanProperty isDeleteFileActionNotCompleted = deleteFileActionController.getFlag();
        BooleanProperty isProgramActionNotCompleted = programActionController.getFlag();
        
        //se nessuna azione è completa bb3=true
        BooleanBinding bb3 = isAudioActionNotCompleted.and(isMessageActionNotCompleted).and(isWriterActionNotCompleted)
                .and(isCopyFileActionNotCompleted).and(isMoveFileActionNotCompleted).and(isDeleteFileActionNotCompleted)
                .and(isProgramActionNotCompleted);
        
        //se non è stato inserito il nome della regola O non è selezionata un'azione/ trigger O l'azione selezionata non è completa bb=true
        BooleanBinding bb = bb1.or(bb2).or(bb3);
        
        createRuleButton.disableProperty().bind(bb);  
    }

    //ritorna alla window1 dopo aver creato la regola
    @FXML
    private void goToWindowOne(ActionEvent event) throws IOException {
        
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        
        //restituisce l'azione selezionata dall'utente
        RadioButton selectedAction = (RadioButton) actionToggleGroup.getSelectedToggle();
        
        //restituisce il trigger selezionato dall'utente
        RadioButton selectedTrigger = (RadioButton) triggerToggleGroup.getSelectedToggle();
        
        Action action = null;
        Trigger trigger = null;
        
        if("Audio".equals(selectedAction.getText())){
            action = new AudioAction(audioActionController.getFilePath());
        }else if("Message".equals(selectedAction.getText())){         
            action = new MessageAction(messageActionController.getTextArea());
            ((MessageAction) action).addObserver(messageActionController);
        }else if("Write".equals(selectedAction.getText())){
            action = new WriterAction(writerActionController.getFilePath(), writerActionController.getTextArea());
        }else if("CopyFile".equals(selectedAction.getText())){
            action = new CopyFileAction(copyFileActionController.getFilePath(), copyFileActionController.getDirectoryPath());
        }else if("MoveFile".equals(selectedAction.getText())){
            action = new MoveFileAction(moveFileActionController.getFilePath(), moveFileActionController.getDirectoryPath());
        }else if("Program".equals(selectedAction.getText())){
            action = new ProgramAction(programActionController.getFilePath(), programActionController.getTextArea());
        }else if("DeleteFile".equals(selectedAction.getText())){
            String completePath = deleteFileActionController.getDirectoryPath() + "/" + deleteFileActionController.getFileName();
            action = new DeleteFileAction(completePath);
        }
        
        Duration duration = null;
        
        boolean repetition = repetitionCheck.isSelected();
        
        if(repetition){
            duration = Duration.ofDays(repetitionController.getDaysSleeping())
            .plusHours(repetitionController.getHoursSleeping())
            .plusMinutes(repetitionController.getMinutesSleeping());
        }
        
        if("Time".equals(selectedTrigger.getText())){           
            trigger = new TimeTrigger(timeTriggerController.getHours(), timeTriggerController.getMinutes(), repetition, duration);
        }
        
        Rule rule = new Rule(nameRuleTextField.getText(), action, trigger);
        ((TimeTrigger)trigger).addObserver(rule);
        
        ruleManager.addRule(rule);

        loadAllRules();
    }
    
    //caricamento delle ruleCards 
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
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    //caricamento delle azioni in window3, in modo tale che l'utente possa visualizzare le actionCards
    private void loadAllActionsCards() throws IOException {
        scrollAllActions.getChildren().clear();
        
        //crea un togglegroup da passare alle card. In questo modo l'utente può selezionare un'unica azione
        actionToggleGroup = new ToggleGroup();

        audioActionController = (AudioActionController) createCardAction("/actions/AudioAction/AudioAction.fxml", audioActionController);
        messageActionController = (MessageActionController) createCardAction("/actions/MessageAction/MessageAction.fxml", messageActionController);
        
        writerActionController = (WriterActionController) createCardAction("/actions/WriterAction/WriterAction.fxml", writerActionController);
        copyFileActionController = (CopyFileActionController) createCardAction("/actions/CopyFileAction/CopyFileAction.fxml", copyFileActionController);
        moveFileActionController = (MoveFileActionController) createCardAction("/actions/MoveFileAction/MoveFileAction.fxml", moveFileActionController);
        deleteFileActionController = (DeleteFileActionController) createCardAction("/actions/DeleteFileAction/DeleteFileAction.fxml", deleteFileActionController);
        programActionController = (ProgramActionController) createCardAction("/actions/ProgramAction/ProgramAction.fxml", programActionController);

    }
    
    private ControllerAction createCardAction(String pathFXML, ControllerAction controller) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(pathFXML));
        HBox hbox = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setToggleGroup(actionToggleGroup);
        scrollAllActions.getChildren().add(hbox);
        return controller;
    }
    
    //caricamento triggers in window3. L'utente può visualizzare tutti i triggerCards.
    private void loadAllTriggersCards() throws IOException{
        scrollAllTriggers.getChildren().clear();
        
        triggerToggleGroup = new ToggleGroup();
        
        timeTriggerController = (TimeTriggerController) createCardTrigger("/triggers/TimeTrigger/TimeTrigger.fxml", timeTriggerController);
        dayOfWeekTriggerController = (DayOfWeekTriggerController) createCardTrigger("/triggers/DayOfWeekTrigger/DayOfWeekTrigger.fxml", dayOfWeekTriggerController);
        dayOfMonthTriggerController = (DayOfMonthTriggerController) createCardTrigger("/triggers/DayOfMonthTrigger/DayOfMonthTrigger.fxml", dayOfMonthTriggerController);
    }
    
    private ControllerTrigger createCardTrigger(String pathFXML, ControllerTrigger controller) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(pathFXML));
        HBox hbox = fxmlLoader.load();
        controller = fxmlLoader.getController();
        controller.setToggleGroup(triggerToggleGroup);
        scrollAllTriggers.getChildren().add(hbox);
        return controller;
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
    
    //pulsante goBack permette di tornare alla window1
    @FXML
    private void goToHome(ActionEvent event) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        loadAllRules();
    }
    
}