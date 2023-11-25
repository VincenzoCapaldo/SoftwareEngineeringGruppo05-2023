package rules;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paolo
 */
public class RuleManager {
    private static RuleManager instance = null;
    private List<Rule> rules;

    private RuleManager() {
        rules = new ArrayList<>();
    }

    public static RuleManager getInstance() {
        // pattern Singleton
        if (instance == null) {
            instance = new RuleManager();
        }
        return instance;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void addRule(Rule newRule) {
        rules.add(newRule);
    }
    
    public void deleteRule(Rule oldRule) {
        rules.remove(oldRule);
    }
    
    public void deactivateRule(Rule rule) {
        rule.setState(false);
    }
    
    public void reactivateRule(Rule rule) {
        rule.setState(true);    
    }
    
}