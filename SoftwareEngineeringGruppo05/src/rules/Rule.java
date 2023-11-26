package rules;

import actions.Action;
import java.time.Duration;
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
    private boolean repeate;
    private Duration sleeping;

    public Rule(String name, Action action, Trigger trigger, boolean state, boolean repeate, Duration sleeping) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.state = state;
        this.repeate = repeate;
        this.sleeping = sleeping;
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

    public boolean getRepeate() {
        return repeate;
    }

    public void setRepeate(boolean repeate) {
        this.repeate = repeate;
    }

    public Duration getSleeping() {
        return sleeping;
    }

    public void setSleeping(Duration sleeping) {
        this.sleeping = sleeping;
    }
}