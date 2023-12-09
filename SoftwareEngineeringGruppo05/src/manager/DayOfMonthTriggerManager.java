package manager;

import triggers.DayOfMonthTrigger.DayOfMonthTrigger;
import controller.DayOfMonthTriggerController;
import rules.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfMonthTriggerManager extends TriggerManager{
    
    DayOfMonthTrigger trigger;
    
    public DayOfMonthTriggerManager() {
        super("/triggers/DayOfMonthTrigger/DayOfMonthTrigger.fxml");
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