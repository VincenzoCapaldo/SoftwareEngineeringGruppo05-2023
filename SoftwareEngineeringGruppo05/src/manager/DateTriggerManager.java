package manager;

import triggers.DateTrigger.DateTrigger;
import controller.DateTriggerController;
import rules.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DateTriggerManager extends TriggerManager{
    
    DateTrigger trigger;
    
    public DateTriggerManager() {
        super("/triggers/DateTrigger/DateTrigger.fxml");
    }

    @Override
    public Trigger createTrigger() {
        DateTriggerController dtc = ((DateTriggerController)super.getController());
        trigger = new DateTrigger(dtc.getDate());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}