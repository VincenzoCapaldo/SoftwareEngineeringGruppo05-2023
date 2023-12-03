package rules;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paolo
 */
public class RuleManager implements Serializable{
    private static RuleManager instance = null;
    private Map<Rule, Thread> mapRules;
    private String filePath;
    
    private RuleManager() {
        this.mapRules = new HashMap<>();
        this.filePath = "src/rules/backup.bin";  
    }

    public static RuleManager getInstance() {
        // pattern Singleton
        if (instance == null) {
            instance = new RuleManager();
        }
        return instance;
    }

    public Set<Rule> getRules() {
        Set<Rule> rules = mapRules.keySet();
        return rules;
    }

    public void addRule(Rule newRule) {
        Thread t = new Thread(new ThreadRule(newRule));
        mapRules.put(newRule, t);
        if(newRule.getState())
            t.start();
        this.saveRules();
    }
    
    public void deleteRule(Rule oldRule) {
        mapRules.get(oldRule).interrupt();
        mapRules.remove(oldRule);
        this.saveRules();
    }
    
    public void deactivateRule(Rule rule) {
        rule.setState(false);
        mapRules.get(rule).interrupt();
        this.saveRules();
    }
    
    public void reactivateRule(Rule rule) {
        rule.setState(true);  
        this.addRule(rule);
    }

    public void saveRules(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.filePath)))) {
            Set<Rule> rulesToSave = new HashSet<>(this.mapRules.keySet());
            oos.writeObject(rulesToSave);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadRules() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.filePath)))) {
            Set<Rule> rules = (Set<Rule>) ois.readObject();
            for (Rule rule : rules) {
                this.addRule(rule);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
