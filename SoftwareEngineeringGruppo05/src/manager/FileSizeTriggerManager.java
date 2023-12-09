package manager;

import triggers.FileSizeTrigger.FileSizeTrigger;
import controller.FileSizeTriggerController;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileSizeTriggerManager extends TriggerManager{
    
    public FileSizeTriggerManager() {
        super("/triggers/FileSizeTrigger/FileSizeTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        FileSizeTriggerController fstc = ((FileSizeTriggerController)super.getController());
        FileSizeTrigger fst = new FileSizeTrigger(fstc.getFilePath(), fstc.getSize());
        return fst;
    }
    
}