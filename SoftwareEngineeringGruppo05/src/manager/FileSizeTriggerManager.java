package manager;

import triggers.FileSizeTrigger.FileSizeTrigger;
import controller.FileSizeTriggerController;
import rules.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileSizeTriggerManager extends TriggerManager{
    
    FileSizeTrigger trigger;
    
    public FileSizeTriggerManager() {
        super("/triggers/FileSizeTrigger/FileSizeTrigger.fxml");
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