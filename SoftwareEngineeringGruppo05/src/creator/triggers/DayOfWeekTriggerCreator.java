package creator.triggers;

import model.triggers.DayOfWeekTrigger;
import controller.triggers.DayOfWeekTriggerController;
import model.rules.Rule;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class DayOfWeekTriggerCreator extends TriggerCreator{
    
    DayOfWeekTrigger trigger;
    
    public DayOfWeekTriggerCreator() {
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