package softwareengineeringgruppo05;

import creator.actions.ActionCreator;
import creator.actions.AudioActionCreator;
import creator.triggers.TriggerCreator;
import java.util.Map;
import model.actions.Action;
import model.actions.CompositeAction;
import model.rules.Rule;
import model.rules.RuleManager;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class RuleCreator {

    //crea la regola in accordo al nome, all'azione e al trigger scelti dall'utente
    public static void createRule(String ruleName, Map<String,ActionCreator> actions, Map<String,TriggerCreator> triggers, String selectedTrigger){
        
        Action action = new CompositeAction();
        for(ActionCreator ac : actions.values()){
            if(ac.createAction() != null)
                action.add(ac.createAction());
        }
        
        //Action action = actions.get(selectedAction).createAction(); //crea l'azione scelta
        Trigger trigger = triggers.get(selectedTrigger).createTrigger(); //crea il trigger scelto
        Rule rule = new Rule(ruleName,action,trigger); //crea la regola
        triggers.get(selectedTrigger).addObserver(rule); //la regola osserva il trigger 
        RuleManager ruleManager = RuleManager.getInstance(); //ottiene il RuleManager
        ruleManager.addRule(rule); //aggiunge al regola al RuleManager
    }
    
}
