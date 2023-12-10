package rule;

import actions.Action;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import triggers.Trigger;

/**
 *
 * @author maria
 */
public class Rule extends Observable implements Serializable, Observer{
    private String name;
    private Action action;
    private Trigger trigger;
    private boolean state;

    public Rule(String name, Action action, Trigger trigger) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.state = true; //quando l'utente crea una nuova regola è attiva di default
    }

    public String getName(){
        return name;
    }
    
    public Action getAction(){
        return action;
    }
    
    public Trigger getTrigger(){
        return trigger;
    }
    
    public boolean getState() {
        return state;
    }

    public void setState(boolean newState) {
        if(state != newState){
            state = newState;
            setChanged(); //indica che lo stato della regola è stato modificato
        }
        notifyObservers(); //avvisa gli oggetti Observers registrati (in caso di modifica) 
    }

    @Override
    public void update(Observable subject, Object arg) {
        Trigger trigger = (Trigger) subject; //il trigger è l'Observable
        if (trigger.isVerified()){ //se il trigger è scattato
            this.getAction().execute(); //esegue la regola
            if(!trigger.isRepeated()){ //se non c'è ripetizione
                RuleManager.getInstance().deactivateRule(this); //disattiva la regola (dopo averla eseguita)
            }
        }
    }
    
}