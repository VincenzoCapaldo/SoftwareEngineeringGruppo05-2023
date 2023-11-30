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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    private String pathFile;
    
    private RuleManager() {
        this.mapRules = new HashMap<>();
        this.pathFile = "src/rules/backup.bin";  
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(this.pathFile)))) {
            List<Rule> serializableRules = new ArrayList<>(this.getRules());
            oos.writeObject(serializableRules);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadRules() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.pathFile)))) {
            List<Rule> serializableRules = (List<Rule>) ois.readObject();
            Set<Rule> rules = new HashSet<>(serializableRules);
            for (Rule rule : rules) {
                this.addRule(rule);
                if(!rule.getState())
                    this.deactivateRule(rule);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
