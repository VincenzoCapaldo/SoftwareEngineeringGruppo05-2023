package triggers.dayOfMonthTrigger;

import triggers.TriggerCreator;
import rule.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfMonthTriggerCreator extends TriggerCreator{
    
    DayOfMonthTrigger trigger;
    
    public DayOfMonthTriggerCreator() {
        super("/triggers/dayOfMonthTrigger/DayOfMonthTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        DayOfMonthTriggerController domtc = ((DayOfMonthTriggerController)super.getController());
        trigger = new DayOfMonthTrigger(domtc.getDayOfMonth(),domtc.repetitionIsSelected());  //crea il trigger
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
}