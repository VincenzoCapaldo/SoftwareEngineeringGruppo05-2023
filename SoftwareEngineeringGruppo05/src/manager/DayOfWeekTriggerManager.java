package manager;

import triggers.DayOfWeekTrigger.DayOfWeekTrigger;
import controller.DayOfWeekTriggerController;
import rules.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfWeekTriggerManager extends TriggerManager{
    
    DayOfWeekTrigger trigger;
    
    public DayOfWeekTriggerManager() {
        super("/triggers/DayOfWeekTrigger/DayOfWeekTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        DayOfWeekTriggerController dowtc = ((DayOfWeekTriggerController)super.getController());
        trigger = new DayOfWeekTrigger(dowtc.getDayOfWeek(),dowtc.repetitionIsSelected());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
}