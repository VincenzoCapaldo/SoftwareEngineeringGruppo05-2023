package manager.triggers;

import model.triggers.DayOfMonthTrigger;
import controller.triggers.DayOfMonthTriggerController;
import manager.TriggerManager;
import model.rules.Rule;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfMonthTriggerManager extends TriggerManager{
    
    DayOfMonthTrigger trigger;
    
    public DayOfMonthTriggerManager() {
        super("/view/triggers/DayOfMonthTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        DayOfMonthTriggerController domtc = ((DayOfMonthTriggerController)super.getController());
        trigger = new DayOfMonthTrigger(domtc.getDayOfMonth(),domtc.repetitionIsSelected());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
}