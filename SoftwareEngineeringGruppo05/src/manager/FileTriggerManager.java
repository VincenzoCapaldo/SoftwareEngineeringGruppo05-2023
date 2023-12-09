package manager;

import triggers.FileTrigger.FileTrigger;
import controller.FileTriggerController;
import rules.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileTriggerManager extends TriggerManager{
    
    FileTrigger trigger;
    
    public FileTriggerManager() {
        super("/triggers/FileTrigger/FileTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        FileTriggerController ftc = ((FileTriggerController)super.getController());
        trigger = new FileTrigger(ftc.getDirectoryPath(), ftc.getFileName());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}