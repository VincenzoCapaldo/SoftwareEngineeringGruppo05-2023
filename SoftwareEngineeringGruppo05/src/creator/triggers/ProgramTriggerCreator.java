package creator.triggers;

import controller.triggers.ProgramTriggerController;
import model.rules.Rule;
import model.triggers.CreateProgramTrigger;
import model.triggers.ProgramTrigger;
import model.triggers.Trigger;

/**
 *
 * @author maria
 */
public class ProgramTriggerCreator extends TriggerCreator{

    ProgramTrigger trigger;
    
    public ProgramTriggerCreator() {
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
