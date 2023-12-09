package manager.triggers;

import model.triggers.DayOfWeekTrigger;
import controller.triggers.DayOfWeekTriggerController;
import manager.TriggerManager;
import model.rules.Rule;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfWeekTriggerManager extends TriggerManager{
    
    DayOfWeekTrigger trigger;
    
    public DayOfWeekTriggerManager() {
        super("/view/triggers/DayOfWeekTrigger.fxml");
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