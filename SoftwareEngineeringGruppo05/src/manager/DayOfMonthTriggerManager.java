package manager;

import triggers.DayOfMonthTrigger.DayOfMonthTrigger;
import controller.DayOfMonthTriggerController;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfMonthTriggerManager extends TriggerManager{
    
    public DayOfMonthTriggerManager() {
        super("/triggers/DayOfMonthTrigger/DayOfMonthTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        DayOfMonthTriggerController domtc = ((DayOfMonthTriggerController)super.getController());
        DayOfMonthTrigger domt = new DayOfMonthTrigger(domtc.getDayOfMonth(),domtc.repetitionIsSelected());
        return domt;
    }
}