package rules;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Paolo
 */
public class RuleManager {
    private static RuleManager instance = null;
    private Map<Rule, Thread> mapRules;

    private RuleManager() {
        mapRules = new HashMap<>();
    }

    public static RuleManager getInstance() {
        // pattern Singleton
        if (instance == null) {
            instance = new RuleManager();
        }
        return instance;
    }

    public Set<Rule> getRules() {
        Set rules = mapRules.keySet();
        return rules;
    }

    public void addRule(Rule newRule) {
        Thread t = new Thread(new ThreadRule(newRule));
        mapRules.put(newRule, t);
        t.start();
    }
    
    public void deleteRule(Rule oldRule) {
        mapRules.get(oldRule).interrupt();
        mapRules.remove(oldRule);
    }
    
    public void deactivateRule(Rule rule) {
        rule.setState(false);
        mapRules.get(rule).interrupt();
    }
    
    public void reactivateRule(Rule rule) {
        rule.setState(true);  
        this.addRule(rule);
    }
    
}