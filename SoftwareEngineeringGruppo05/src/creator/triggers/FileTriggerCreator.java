package creator.triggers;

import model.triggers.FileTrigger;
import controller.triggers.FileTriggerController;
import model.rules.Rule;
import model.triggers.Trigger;

/**
 *
 * @author Paolo
 */
public class FileTriggerCreator extends TriggerCreator{
    
    FileTrigger trigger;
    
    public FileTriggerCreator() {
        super("/view/triggers/FileTrigger.fxml");
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