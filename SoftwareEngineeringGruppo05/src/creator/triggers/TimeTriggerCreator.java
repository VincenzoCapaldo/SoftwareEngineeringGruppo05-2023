package creator.triggers;

import model.triggers.TimeTrigger;
import controller.triggers.TimeTriggerController;
import model.rules.Rule;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class TimeTriggerCreator extends TriggerCreator{
    
    TimeTrigger trigger;
    
    public TimeTriggerCreator() {
        super("/view/triggers/TimeTrigger.fxml");
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