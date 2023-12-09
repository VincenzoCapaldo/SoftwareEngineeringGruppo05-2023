package manager;

import triggers.TimeTrigger.TimeTrigger;
import controller.TimeTriggerController;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class TimeTriggerManager extends TriggerManager{
    
    public TimeTriggerManager() {
        super("/triggers/TimeTrigger/TimeTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        TimeTriggerController ttc = ((TimeTriggerController)super.getController());
        TimeTrigger tt = new TimeTrigger(ttc.getHours(), ttc.getMinutes(), ttc.repetitionIsSelected(), ttc.getSleeping());
        return tt;
    }
    
}