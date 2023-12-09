package manager.triggers;

import model.triggers.FileSizeTrigger;
import controller.triggers.FileSizeTriggerController;
import manager.TriggerManager;
import model.rules.Rule;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileSizeTriggerManager extends TriggerManager{
    
    FileSizeTrigger trigger;
    
    public FileSizeTriggerManager() {
        super("/view/triggers/FileSizeTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        FileSizeTriggerController fstc = ((FileSizeTriggerController)super.getController());
        trigger = new FileSizeTrigger(fstc.getFilePath(), fstc.getSize());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}