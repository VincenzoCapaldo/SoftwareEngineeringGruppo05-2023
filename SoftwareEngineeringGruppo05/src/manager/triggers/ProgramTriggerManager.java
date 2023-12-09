package manager.triggers;

import controller.triggers.ProgramTriggerController;
import manager.TriggerManager;
import model.rules.Rule;
import model.triggers.CreateProgramTrigger;
import model.triggers.ProgramTrigger;
import model.triggers.Trigger;

/**
 *
 * @author maria
 */
public class ProgramTriggerManager extends TriggerManager{

    ProgramTrigger trigger;
    
    public ProgramTriggerManager() {
        super("/view/triggers/ProgramTrigger.fxml");
    }
    
    @Override
    public Trigger createTrigger() {
        ProgramTriggerController ptc = ((ProgramTriggerController)super.getController());
        CreateProgramTrigger cpt = new CreateProgramTrigger();
        trigger = cpt.createProgramTrigger(ptc.getFilePath(), ptc.getTextArea(), ptc.getExitStatus());
        return trigger;
    }

    @Override
    public void addObserver(Rule rule) {
        trigger.addObserver(rule);
    }
    
}
