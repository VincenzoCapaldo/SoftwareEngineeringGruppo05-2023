package manager;

import triggers.DayOfWeekTrigger.DayOfWeekTrigger;
import controller.DayOfWeekTriggerController;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfWeekTriggerManager extends TriggerManager{
    
    public DayOfWeekTriggerManager() {
        super("/triggers/DayOfWeekTrigger/DayOfWeekTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        DayOfWeekTriggerController dowtc = ((DayOfWeekTriggerController)super.getController());
        DayOfWeekTrigger dowt = new DayOfWeekTrigger(dowtc.getDayOfWeek(),dowtc.repetitionIsSelected());
        return dowt;
    }
}