package manager;

import triggers.DateTrigger.DateTrigger;
import controller.DateTriggerController;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DateTriggerManager extends TriggerManager{
    
    public DateTriggerManager() {
        super("/triggers/DateTrigger/DateTrigger.fxml");
    }

    @Override
    public Trigger createTrigger() {
        DateTriggerController dtc = ((DateTriggerController)super.getController());
        DateTrigger dt = new DateTrigger(dtc.getDate());
        return dt;
    }
    
}