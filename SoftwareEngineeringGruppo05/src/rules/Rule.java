package rules;

import actions.Action;
import triggers.Trigger;

/**
 *
 * @author maria
 */
public class Rule {
    private String name;
    private Action action;
    private Trigger trigger;
    private boolean state;

    public Rule(String name, Action action, Trigger trigger, boolean state) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.state = state;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Action getAction(){
        return action;
    }
    
    public void setAction(Action action){
        this.action = action;
    }
    
    public Trigger getTrigger(){
        return trigger;
    }
    
    public void setTrigger(Trigger trigger){
        this.trigger = trigger;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

}