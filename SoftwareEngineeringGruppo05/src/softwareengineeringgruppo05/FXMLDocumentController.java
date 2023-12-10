package softwareengineeringgruppo05;

import rule.RuleCardController;
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
import rule.Rule;
import rule.RuleManager;
import actions.ActionCreator;
import triggers.TriggerCreator;
import java.util.LinkedHashMap;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author enzo0
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane window1;
    @FXML
    private VBox scrollRules;
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
    
    private Map<String,ActionCreator> actions = new LinkedHashMap<>();
    private Map<String,TriggerCreator> triggers = new LinkedHashMap<>();
        
    private RuleManager ruleManager;
    @FXML
    private Button rulesButton;
    @FXML
    private Button newRule;
    @FXML
    private Button goBackButton;
    private AnchorPane window2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        
        ruleManager = RuleManager.getInstance(); //preleva l'istanza del RuleManager
        ruleManager.loadRules(); //carica le regole da file
        loadRuleCards();//carica le regole graficamente
    
    }    

    //passaggio da window1 a window3 quando si seleziona createRuleButton 
    @FXML
    private void goToWindowThree(ActionEvent event) throws IOException {
        //pulizia campi 
        nameRuleTextField.clear();
        scrollAllActions.getChildren().clear();
        scrollAllTriggers.getChildren().clear();
        
        //actionToggleGroup = new ToggleGroup();
        triggerToggleGroup = new ToggleGroup();
        
        AvailableActions.createActionCreators(actions);
        AvailableTriggers.createTriggerCreators(triggers);
        
        window3.visibleProperty().set(true);
        window1.visibleProperty().set(false);
        
        for (ActionCreator am : actions.values()){
            //am.getController().setToggleGroup(actionToggleGroup);
            scrollAllActions.getChildren().add(am.getHbox());
        }
        
        for (TriggerCreator am : triggers.values()){
            am.getController().setToggleGroup(triggerToggleGroup);
            scrollAllTriggers.getChildren().add(am.getHbox());
        }
        
        
        
        //se il nome della regola non è inserito o è di solo spazi vuoti bb1=true
        BooleanBinding bbRuleName = Bindings.createBooleanBinding(
        () -> nameRuleTextField.getText().trim().isEmpty(),
        nameRuleTextField.textProperty()
        );
        
        //se non è selezionata almeno un'azione o almeno un trigger bb2=true
        BooleanBinding bbTriggerToggleGroup = triggerToggleGroup.selectedToggleProperty().isNull();
        
        ActionCreator[] actionManagers = actions.values().toArray(new ActionCreator[0]);
        BooleanBinding bbAction = null;
        BooleanBinding currentBindingAction = null;        
        for(int i=0; i<actionManagers.length-1; i=i+2){
            ActionCreator am = actionManagers[i];
            // Controlla se c'è un ActionManager successivo
            if (i + 1 < actionManagers.length) {
                ActionCreator nextAm = actionManagers[i + 1];
                // Inizializza la BooleanBinding con l'and tra lo stato "non completato" di am e nextAm
                currentBindingAction = Bindings.and(am.isNotCompleted(), nextAm.isNotCompleted());
                // Se bbAction è già inizializzata, effettua l'and con la nuova BooleanBinding
                if (bbAction != null) {
                    bbAction = bbAction.and(currentBindingAction);
                } else {
                    // Altrimenti, inizializza bbAction con la prima BooleanBinding
                    bbAction = currentBindingAction;
                }
            }
        }
        
        if (actionManagers.length % 2 != 0) {
            ActionCreator lastAm = actionManagers[actionManagers.length - 1];
            BooleanProperty currentBinding = lastAm.isNotCompleted();
            if (bbAction != null) {
                    bbAction = bbAction.and(currentBinding);
            } else {
                    // Altrimenti, inizializza bbAction con la prima BooleanBinding
                    bbAction = currentBinding.and(new SimpleBooleanProperty(true));
                }
        }
        
        
        TriggerCreator[] triggerManagers = triggers.values().toArray(new TriggerCreator[0]);
        BooleanBinding bbTrigger = null;
        BooleanBinding currentBindingTrigger = null;
        for(int i=0; i<triggerManagers.length; i=i+2){
            TriggerCreator tm = triggerManagers[i];
            // Controlla se c'è un TriggerManager successivo
            if (i + 1 < triggerManagers.length) {
                TriggerCreator nextTm = triggerManagers[i + 1];

                // Inizializza la BooleanBinding con l'and tra lo stato "non completato" di tm e nextTm
                currentBindingTrigger = Bindings.and(tm.isNotCompleted(), nextTm.isNotCompleted());

                // Se bbTrigger è già inizializzata, effettua l'and con la nuova BooleanBinding
                if (bbTrigger != null) {
                    bbTrigger = bbTrigger.and(currentBindingTrigger);
                } else {
                    // Altrimenti, inizializza bbAction con la prima BooleanBinding
                    bbTrigger = currentBindingTrigger;
                }
            }
        }
        
        if (triggerManagers.length % 2 != 0) {
            TriggerCreator lastTm = triggerManagers[triggerManagers.length - 1];
            BooleanProperty currentBinding = lastTm.isNotCompleted();
            if (bbTrigger != null) {
                    bbTrigger = bbTrigger.and(currentBinding);
            } else {
                    // Altrimenti, inizializza bbAction con la prima BooleanBinding
                    bbTrigger = currentBinding.and(new SimpleBooleanProperty(true));
                }
        }

        ActionCreator[] actionCreatorsbb4 = actions.values().toArray(new ActionCreator[0]);
        BooleanBinding bbAction4 = null;
        
        BooleanBinding currentBindingActionbb4 = null;        
        for(int i=0; i<actionCreatorsbb4.length-1; i=i+2){
            ActionCreator am = actionCreatorsbb4[i];
            // Controlla se c'è un ActionManager successivo
            if (i + 1 < actionCreatorsbb4.length) {
                ActionCreator nextAm = actionCreatorsbb4[i + 1];
                // Inizializza la BooleanBinding con l'and tra lo stato "non completato" di am e nextAm
                currentBindingActionbb4 = Bindings.or(am.getController().getCB().selectedProperty(), nextAm.getController().getCB().selectedProperty());
                // Se bbAction è già inizializzata, effettua l'and con la nuova BooleanBinding
                if (bbAction4 != null) {
                    bbAction4 = bbAction4.and(currentBindingActionbb4);
                } else {
                    // Altrimenti, inizializza bbAction con la prima BooleanBinding
                    bbAction4 = currentBindingActionbb4;
                }
            }
        }
        
        if (actionCreatorsbb4.length % 2 != 0) {
            ActionCreator lastAm = actionCreatorsbb4[actionCreatorsbb4.length - 1];
            BooleanProperty currentBinding = lastAm.isNotCompleted();
            if (bbAction4 != null) {
                    bbAction4 = bbAction4.and(currentBinding);
            } else {
                    // Altrimenti, inizializza bbAction con la prima BooleanBinding
                    bbAction4 = currentBinding.and(new SimpleBooleanProperty(true));
                }
        }
        
        
        //se non è stato inserito il nome della regola O non è selezionata un'azione/ trigger O l'azione selezionata non è completa bb=true
        BooleanBinding bbActionNot = Bindings.not(bbAction);
        BooleanBinding bbAction4Not = Bindings.not(bbAction4);
        BooleanBinding bb = bbRuleName.or(bbTriggerToggleGroup).or(bbActionNot).or(bbTrigger).or(bbAction4Not);
        
        //createRuleButton.disableProperty().bind(bb);  
        
    }

    //ritorna alla window1 dopo aver creato la regola
    @FXML
    private void goToWindowOne(ActionEvent event) throws IOException {
        
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        
        //restituisce l'azione selezionata dall'utente
        //RadioButton selectedAction = (RadioButton) actionToggleGroup.getSelectedToggle();
        
        //restituisce il trigger selezionato dall'utente
        RadioButton selectedTrigger = (RadioButton) triggerToggleGroup.getSelectedToggle();
        
        //crea la regola in accordo al nome, all'azione e al trigger scelti dall'utente
        RuleCreator.createRule(nameRuleTextField.getText().trim(), actions, triggers, selectedTrigger.getText()); 
        
        loadRuleCards();
    
    }
    
    //pulsante goBack permette di tornare alla window1
    @FXML
    private void goToHome(ActionEvent event) {
        window1.visibleProperty().set(true);
        window3.visibleProperty().set(false);
        loadRuleCards();
    }
    
    //caricamento delle ruleCards 
    private void loadRuleCards(){
        
        scrollRules.getChildren().clear(); //pulisce scrollRules
        
        ruleManager = RuleManager.getInstance(); //preleva l'istanza del RuleManager
        Set<Rule> rules = ruleManager.getRules(); //preleva le regole

        for (Rule rule : rules) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/rule/RuleCard.fxml")); 
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

}