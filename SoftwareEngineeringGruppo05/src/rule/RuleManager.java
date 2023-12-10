package rule;

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
    private static RuleManager instance;
    private Map<Rule,Thread> mapRules; //mappa contenente le regole e i corrispettivi threads che le controllano
    private String filePath; //path del file di salvataggio
    
    private RuleManager() {
        this.mapRules = new HashMap<>();
        this.filePath = "src/rule/backup.bin";  
    }
    
    // pattern Singleton        
    public static RuleManager getInstance() {
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
        Thread t = new Thread(new ThreadRule(newRule)); //thread che controlla se scatta il trigger la regola
        mapRules.put(newRule, t);
        if(newRule.getState())
            t.start(); //fa partire il thread solo se la regola è attiva
        this.saveRules();
    }
    
    public void deleteRule(Rule oldRule) {
        mapRules.get(oldRule).interrupt(); //prima di eliminare una regola si interrompe il suo thread
        mapRules.remove(oldRule);
        this.saveRules();
    }
    
    public void deactivateRule(Rule rule) {
        rule.setState(false); //disattiva la regola
        mapRules.get(rule).interrupt(); //interrompe il thread
        this.saveRules();
    }
    
    public void reactivateRule(Rule rule) {
        rule.setState(true); //riattiva la regola
        Thread t = new Thread(new ThreadRule(rule)); //crea un nuovo thread
        mapRules.replace(rule, t); //inseriamo la regola col nuovo thread nella mappa
        t.start();
        this.saveRules();
    }

    //salva il set di regole sul file di salvataggio
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
    
    //legge il set di regole dal file di salvataggio
    public void loadRules() {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(this.filePath)))) {
            Set<Rule> rules = (Set<Rule>) ois.readObject();
            for (Rule rule : rules) {
                this.addRule(rule); //aggiungo la regola alla mappa
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RuleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //interrompe tutti i threads in esecuzione (viene chiamato quando termina il programma)
    public void interruptThread() {
        for(Rule rule : this.getRules()){
            mapRules.get(rule).interrupt();
        }
    }

}
