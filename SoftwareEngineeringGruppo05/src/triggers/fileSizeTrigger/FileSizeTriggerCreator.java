package triggers.fileSizeTrigger;

import triggers.TriggerCreator;
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
        trigger = new FileSizeTrigger(fstc.getFilePath(), fstc.getSize()); //crea il trigger
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}