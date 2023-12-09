package manager;

import triggers.FileTrigger.FileTrigger;
import controller.FileTriggerController;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileTriggerManager extends TriggerManager{
    
    public FileTriggerManager() {
        super("/triggers/FileTrigger/FileTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        FileTriggerController ftc = ((FileTriggerController)super.getController());
        FileTrigger ft = new FileTrigger(ftc.getDirectoryPath(), ftc.getFileName());
        return ft;
    }
    
}