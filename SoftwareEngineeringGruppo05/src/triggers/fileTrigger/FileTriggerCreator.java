package triggers.fileTrigger;

import triggers.TriggerCreator;
import rule.Rule;
import triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileTriggerCreator extends TriggerCreator{
    
    FileTrigger trigger;
    
    public FileTriggerCreator() {
        super("/triggers/fileTrigger/FileTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        FileTriggerController ftc = ((FileTriggerController)super.getController());
        trigger = new FileTrigger(ftc.getDirectoryPath(), ftc.getFileName()); //crea il trigger
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}