package creator.triggers;

import model.triggers.DateTrigger;
import controller.triggers.DateTriggerController;
import model.rules.Rule;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DateTriggerCreator extends TriggerCreator{
    
    DateTrigger trigger;
    
    public DateTriggerCreator() {
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