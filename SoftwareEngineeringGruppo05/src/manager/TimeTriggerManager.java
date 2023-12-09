package manager;

import triggers.TimeTrigger.TimeTrigger;
import controller.TimeTriggerController;
import rules.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class TimeTriggerManager extends TriggerManager{
    
    TimeTrigger trigger;
    
    public TimeTriggerManager() {
        super("/triggers/TimeTrigger/TimeTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        TimeTriggerController ttc = ((TimeTriggerController)super.getController());
        trigger = new TimeTrigger(ttc.getHours(), ttc.getMinutes(), ttc.repetitionIsSelected(), ttc.getSleeping());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
    
}