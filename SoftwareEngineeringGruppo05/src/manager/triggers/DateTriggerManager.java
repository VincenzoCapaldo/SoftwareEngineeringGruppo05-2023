package manager.triggers;

import model.triggers.DateTrigger;
import controller.triggers.DateTriggerController;
import manager.TriggerManager;
import model.rules.Rule;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DateTriggerManager extends TriggerManager{
    
    DateTrigger trigger;
    
    public DateTriggerManager() {
        super("/view/triggers/DateTrigger.fxml");
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