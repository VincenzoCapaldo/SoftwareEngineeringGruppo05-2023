package softwareengineeringgruppo05;

import actions.Action;
import java.util.Map;
import manager.ActionManager;
import manager.TriggerManager;
import rules.Rule;
import rules.RuleManager;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class RuleCreator {

    public static void createRule(String ruleName, Map<String,ActionManager> actionManager, String selectedAction, Map<String,TriggerManager> triggerManager, String selectedTrigger){
        Action action = actionManager.get(selectedAction).createAction(); 
        Trigger trigger = triggerManager.get(selectedTrigger).createTrigger();
        Rule rule = new Rule(ruleName,action,trigger);
        RuleManager ruleManager = RuleManager.getInstance();
        ruleManager.addRule(rule);
    }
    
}