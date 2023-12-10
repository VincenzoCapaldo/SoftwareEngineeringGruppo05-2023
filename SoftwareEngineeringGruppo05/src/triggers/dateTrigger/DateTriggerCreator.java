package triggers.dateTrigger;

import triggers.TriggerCreator;
import triggers.dateTrigger.DateTrigger;
import triggers.dateTrigger.DateTriggerController;
import rule.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DateTriggerCreator extends TriggerCreator{
    
    DateTrigger trigger;
    
    public DateTriggerCreator() {
        super("/triggers/dateTrigger/DateTrigger.fxml");
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