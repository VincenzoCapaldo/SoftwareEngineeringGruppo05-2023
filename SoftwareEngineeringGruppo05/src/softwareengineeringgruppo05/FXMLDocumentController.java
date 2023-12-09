package softwareengineeringgruppo05;

import rules.RuleCardController;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rules.Rule;
import rules.RuleManager;
import manager.AudioActionManager;
import manager.ActionManager;
import manager.CopyFileActionManager;
import manager.DeleteFileActionManager;
import manager.MessageActionManager;
import manager.MoveFileActionManager;
import manager.ProgramActionManager;
import manager.WriteFileActionManager;
import manager.DateTriggerManager;
import manager.DayOfMonthTriggerManager;
import manager.DayOfWeekTriggerManager;
import manager.FileSizeTriggerManager;
import manager.FileTriggerManager;
import manager.TimeTriggerManager;
import manager.TriggerManager;
import controller.Controller;
import java.util.LinkedHashMap;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;

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
    
    private RuleManager ruleManager;
    private RepetitionController repetitionController;
    private HBox repetitionBox;
    private HBox soundActionBox;
    private Controller controller;

    @FXML
    private VBox boxSleeping;
    @FXML
    private Button goBackButton;

    private Map<String,ActionManager> actionManager = new LinkedHashMap<>();
    private Map<String,TriggerManager> triggerManager = new LinkedHashMap<>();
        
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
        scrollAllActions.getChildren().clear();
        scrollAllTriggers.getChildren().clear();
        
        actionToggleGroup = new ToggleGroup();
        triggerToggleGroup = new ToggleGroup();
        
        actionManager.put("Audio", new AudioActionManager());
        actionManager.put("Message", new MessageActionManager());
        actionManager.put("WriteFile", new WriteFileActionManager());
        actionManager.put("CopyFile", new CopyFileActionManager());
        actionManager.put("MoveFile", new MoveFileActionManager());
        actionManager.put("DeleteFile", new DeleteFileActionManager());
        actionManager.put("Program", new ProgramActionManager());
        
        triggerManager.put("Time", new TimeTriggerManager());
        triggerManager.put("DayOfWeek", new DayOfWeekTriggerManager());
        triggerManager.put("DayOfMonth", new DayOfMonthTriggerManager());
        triggerManager.put("Date", new DateTriggerManager());
        triggerManager.put("File", new FileTriggerManager());
        triggerManager.put("FileSize", new FileSizeTriggerManager());
        //triggerManager.put("Program", new ProgramTriggerManager());
        
        window3.visibleProperty().set(true);
        window1.visibleProperty().set(false);

        for (ActionManager am : actionManager.values()){
            am.getController().setToggleGroup(actionToggleGroup);
            scrollAllActions.getChildren().add(am.getHbox());
        }
        
        for (TriggerManager am : triggerManager.values()){
            am.getController().setToggleGroup(triggerToggleGroup);
            scrollAllTriggers.getChildren().add(am.getHbox());
        }
        
        //se il nome della regola non è inserito bb1=true
        BooleanBinding bbRuleName = nameRuleTextField.textProperty().isEmpty();
        
        //se non è selezionata almeno un'azione o almeno un trigger bb2=true
        BooleanBinding bbToggleGroup = actionToggleGroup.selectedToggleProperty().isNull().or(triggerToggleGroup.selectedToggleProperty().isNull());

        ActionManager[] actionManagers = actionManager.values().toArray(new ActionManager[0]);
        BooleanBinding bbAction = null;
        for(int i=0; i<actionManagers.length; i=i+2){
            ActionManager am = actionManagers[i];
            // Controlla se c'è un ActionManager successivo
            if (i + 1 < actionManagers.length) {
                ActionManager nextAm = actionManagers[i + 1];

                // Inizializza la BooleanBinding con l'and tra lo stato "non completato" di am e nextAm
                BooleanBinding currentBinding = Bindings.and(am.isNotCompleted(), nextAm.isNotCompleted());

                // Se bbAction è già inizializzata, effettua l'and con la nuova BooleanBinding
                if (bbAction != null) {
                    bbAction = bbAction.and(currentBinding);
                } else {
                    // Altrimenti, inizializza bbAction con la prima BooleanBinding
                    bbAction = currentBinding;
                }
            }
        }
        
        TriggerManager[] triggerManagers = triggerManager.values().toArray(new TriggerManager[0]);
        BooleanBinding bbTrigger = null;
        for(int i=0; i<triggerManagers.length; i=i+2){
            TriggerManager tm = triggerManagers[i];
            // Controlla se c'è un TriggerManager successivo
            if (i + 1 < triggerManagers.length) {
                TriggerManager nextTm = triggerManagers[i + 1];

                // Inizializza la BooleanBinding con l'and tra lo stato "non completato" di tm e nextTm
                BooleanBinding currentBinding = Bindings.and(tm.isNotCompleted(), nextTm.isNotCompleted());

                // Se bbTrigger è già inizializzata, effettua l'and con la nuova BooleanBinding
                if (bbTrigger != null) {
                    bbTrigger = bbTrigger.and(currentBinding);
                } else {
                    // Altrimenti, inizializza bbAction con la prima BooleanBinding
                    bbTrigger = currentBinding;
                }
            }
        }

        
        //se non è stato inserito il nome della regola O non è selezionata un'azione/ trigger O l'azione selezionata non è completa bb=true
        BooleanBinding bb = bbRuleName.or(bbToggleGroup).or(bbAction).or(bbTrigger);
        
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
        
        RuleCreator.createRule(nameRuleTextField.getText(), actionManager, selectedAction.getText(), triggerManager, selectedTrigger.getText()); 
        
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
    
    //pulsante goBack permette di tornare alla window1
    @FXML
    private void goToHome(ActionEvent event) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        loadAllRules();
    }
    
}