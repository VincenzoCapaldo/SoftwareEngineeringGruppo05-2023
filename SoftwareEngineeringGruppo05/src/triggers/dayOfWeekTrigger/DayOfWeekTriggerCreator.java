package triggers.dayOfWeekTrigger;

import triggers.TriggerCreator;
import rule.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfWeekTriggerCreator extends TriggerCreator{
    
    DayOfWeekTrigger trigger;
    
    public DayOfWeekTriggerCreator() {
        super("/triggers/dayOfWeekTrigger/DayOfWeekTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        DayOfWeekTriggerController dowtc = ((DayOfWeekTriggerController)super.getController());
        trigger = new DayOfWeekTrigger(dowtc.getDayOfWeek(),dowtc.repetitionIsSelected()); //crea il trigger
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
}