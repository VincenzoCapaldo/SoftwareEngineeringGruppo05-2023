package triggers.timeTrigger;

import triggers.TriggerCreator;
import triggers.timeTrigger.TimeTrigger;
import triggers.timeTrigger.TimeTriggerController;
import rule.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class TimeTriggerCreator extends TriggerCreator{
    
    TimeTrigger trigger;
    
    public TimeTriggerCreator() {
        super("/triggers/timeTrigger/TimeTrigger.fxml");
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