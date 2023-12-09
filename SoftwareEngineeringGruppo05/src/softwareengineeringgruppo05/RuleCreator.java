package softwareengineeringgruppo05;

import model.actions.Action;
import java.util.Map;
import manager.ActionManager;
import manager.TriggerManager;
import model.rules.Rule;
import model.rules.RuleManager;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class RuleCreator {

    public static void createRule(String ruleName, Map<String,ActionManager> actionManager, String selectedAction, Map<String,TriggerManager> triggerManager, String selectedTrigger){
        Action action = actionManager.get(selectedAction).createAction(); 
        Trigger trigger = triggerManager.get(selectedTrigger).createTrigger();
        Rule rule = new Rule(ruleName,action,trigger);
        triggerManager.get(selectedTrigger).addObserver(rule);
        RuleManager ruleManager = RuleManager.getInstance();
        ruleManager.addRule(rule);
    }
    
}