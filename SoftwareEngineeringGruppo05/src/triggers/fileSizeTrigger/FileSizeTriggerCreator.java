package triggers.fileSizeTrigger;

import triggers.TriggerCreator;
import triggers.fileSizeTrigger.FileSizeTrigger;
import triggers.fileSizeTrigger.FileSizeTriggerController;
import rule.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileSizeTriggerCreator extends TriggerCreator{
    
    FileSizeTrigger trigger;
    
    public FileSizeTriggerCreator() {
        super("/triggers/fileSizeTrigger/FileSizeTrigger.fxml");
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